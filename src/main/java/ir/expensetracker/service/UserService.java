package ir.expensetracker.service;

import ir.expensetracker.api.*;
import ir.expensetracker.authentication.JWTUtil;
import ir.expensetracker.connector.SMSCenter;
import ir.expensetracker.entity.UserEntity;
import ir.expensetracker.exception.InvalidParameterException;
import ir.expensetracker.repository.IUserRepository;
import ir.expensetracker.service.facade.IUserService;
import ir.expensetracker.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        UserEntity result=getUser(input.getUsername());
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
    public UserChangePasswordResult changePassword(UserChangePasswordParam input, String jwt) {
        Optional<UserEntity> result=userRepository.findById(JWTUtil.getUserIdFromToken(jwt));
        if(!result.isPresent()){
            throw new InvalidParameterException("Invalid User");
        }
        if(input.getNewPassword()==null || input.getNewPassword().equals("") || !input.getNewPassword().equals(input.getNewPasswordConfirm())){
            throw new InvalidParameterException("Passwords are empty or do not match");
        }
        result.get().setPassword(SecurityUtil.SHA256(input.getNewPassword()));
        userRepository.save(result.get());
        return new UserChangePasswordResult(true);
    }

    @Override
    public UserForgetPasswordResult forgetPassword(UserForgetPasswordParam input) {
        UserEntity user=getUser(input.getUsername());
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
        UserEntity user=getUser(input.getUsername());
        if(user==null){
            throw new InvalidParameterException("Invalid Username");
        }
        if(input.getPassword()==null || input.getPassword().equals("") || !user.getPassword().equals(SecurityUtil.SHA256(input.getPassword()))){
            throw new InvalidParameterException("Invalid Password");
        }
        String token= JWTUtil.generateToken(input.getUsername(),user.getPassword(),user.getId());
        return new UserLoginResult(token);
    }

    @Override
    public UserEntity getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserEntity> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }
}
