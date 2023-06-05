package ir.expensetracker.web;

import ir.expensetracker.api.UserChangePasswordParam;
import ir.expensetracker.api.UserCreateParam;
import ir.expensetracker.api.UserForgetPasswordParam;
import ir.expensetracker.api.UserLoginParam;
import ir.expensetracker.exception.InvalidParameterException;
import ir.expensetracker.exception.RecordNotFoundException;
import ir.expensetracker.service.facade.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserCreateParam user) {
        try {
            return ResponseEntity.ok(userService.createUser(user));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping(value = "/changepassword")
    public ResponseEntity<Object> changePassword(@RequestBody UserChangePasswordParam user, @RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(userService.changePassword(user, jwt));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping(value = "/forgetpassword")
    public ResponseEntity<Object> forgetPassword(@RequestBody UserForgetPasswordParam user) {
        try {
            return ResponseEntity.ok(userService.forgetPassword(user));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginParam user) {
        try {
            return ResponseEntity.ok(userService.login(user));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
