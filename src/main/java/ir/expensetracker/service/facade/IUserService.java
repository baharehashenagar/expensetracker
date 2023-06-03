package ir.expensetracker.service.facade;

import ir.expensetracker.api.*;
import ir.expensetracker.exception.InvalidParameterException;

public interface IUserService {

   public UserCreateResult createUser(UserCreateParam input) throws InvalidParameterException;
   public UserChangePasswordResult changePassword(UserChangePasswordParam input);
   public UserForgetPasswordResult forgetPassword(UserForgetPasswordParam input);
   public UserLoginResult login(UserLoginParam input);
}
