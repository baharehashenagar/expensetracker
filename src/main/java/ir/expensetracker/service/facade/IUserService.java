package ir.expensetracker.service.facade;

import ir.expensetracker.api.*;

public interface IUserService {

   public UserCreateResult createUser(UserCreateParam input);
   public UserChangePasswordResult changePassword(UserChangePasswordParam input);
   public UserChangePasswordResult forgetPassword(UserForgetPasswordParam input);
}
