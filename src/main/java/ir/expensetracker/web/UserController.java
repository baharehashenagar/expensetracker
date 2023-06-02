package ir.expensetracker.web;

import ir.expensetracker.api.UserCreateParam;
import ir.expensetracker.exception.InvalidParameterException;
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
        } catch (InvalidParameterException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
