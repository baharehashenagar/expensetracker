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
        return null;
    }

    @Override
    public UserChangePasswordResult forgetPassword(UserForgetPasswordParam input) {
        return null;
    }

    @Override
    public void login() {

    }
}
