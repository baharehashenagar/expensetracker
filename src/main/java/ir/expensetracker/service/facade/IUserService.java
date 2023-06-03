package ir.expensetracker.service.facade;

import ir.expensetracker.api.*;
import ir.expensetracker.entity.UserEntity;
import ir.expensetracker.exception.InvalidParameterException;

import java.util.Optional;

public interface IUserService {

   public UserCreateResult createUser(UserCreateParam input) throws InvalidParameterException;
   public UserChangePasswordResult changePassword(UserChangePasswordParam input);
   public UserForgetPasswordResult forgetPassword(UserForgetPasswordParam input);
   public UserLoginResult login(UserLoginParam input);
   public UserEntity getUser(String userName);
   public Optional<UserEntity> getUserById(Integer userId);
}
