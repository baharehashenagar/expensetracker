package ir.expensetracker.service;

import ir.expensetracker.api.*;
import ir.expensetracker.connector.SMSCenter;
import ir.expensetracker.entity.UserEntity;
import ir.expensetracker.exception.InvalidParameterException;
import ir.expensetracker.repository.IUserRepository;
import ir.expensetracker.service.facade.IUserService;
import ir.expensetracker.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;
    private SMSCenter smsCenter;

    @Autowired
    public UserService(IUserRepository iUserRepository,
                       SMSCenter smsCenter){
        this.userRepository=iUserRepository;
        this.smsCenter=smsCenter;
    }

    @Override
    public UserCreateResult createUser(UserCreateParam input) throws InvalidParameterException {
        UserEntity result=userRepository.findByUsername(input.getUsername());
        if(result!=null){
            throw new InvalidParameterException("Duplicate Username");
        }
        if(input.getMobileNumber()==null || input.getMobileNumber().equals("") || input.getMobileNumber().length()!=11){
            throw new InvalidParameterException("Invalid MobileNumber");
        }

        String password= SecurityUtil.generateRandomPassword();
        UserEntity user=new UserEntity();
        user.setUsername(input.getUsername());
        user.setMobileNumber("+98"+input.getMobileNumber().substring(1));
        user.setFullname(input.getFullname());
        user.setPassword(SecurityUtil.SHA256(password));
        user=userRepository.save(user);
        smsCenter.sendRegistrationSMS(user.getMobileNumber(), user.getUsername(), password);
        return new UserCreateResult(user.getId());
    }

    @Override
    public UserChangePasswordResult changePassword(UserChangePasswordParam input) {
        UserEntity result=userRepository.findByUsername(input.getUsername());
        if(result==null){
            throw new InvalidParameterException("Invalid Username");
        }
        if(input.getNewPassword()==null || input.getNewPassword().equals("") || !input.getNewPassword().equals(input.getNewPasswordConfirm())){
            throw new InvalidParameterException("Passwords are empty or do not match");
        }
        result.setPassword(SecurityUtil.SHA256(input.getNewPassword()));
        return new UserChangePasswordResult(true);
    }

    @Override
    public UserForgetPasswordResult forgetPassword(UserForgetPasswordParam input) {
        UserEntity user=userRepository.findByUsername(input.getUsername());
        if(user==null){
            throw new InvalidParameterException("Invalid Username");
        }
        if(input.getMobileNumber()==null || input.getMobileNumber().equals("") || input.getMobileNumber().length()!=11
                || !user.getMobileNumber().substring(3).equals(input.getMobileNumber().substring(1))){
            throw new InvalidParameterException("Invalid MobileNumber");
        }
        String password= SecurityUtil.generateRandomPassword();
        user.setPassword(SecurityUtil.SHA256(password));
        userRepository.save(user);
        smsCenter.sendForgetPasswordSMS(input.getMobileNumber(),password);
        return new UserForgetPasswordResult(true);
    }

    @Override
    public UserLoginResult login(UserLoginParam input){
        UserEntity user=userRepository.findByUsername(input.getUsername());
        if(user==null){
            throw new InvalidParameterException("Invalid Username");
        }
        if(input.getPassword()==null || input.getPassword().equals("") || user.getPassword().equals(SecurityUtil.SHA256(input.getPassword()))){
            throw new InvalidParameterException("Invalid Password");
        }
        String token="";
        //TODO generate token
        return new UserLoginResult(token);
    }
}
