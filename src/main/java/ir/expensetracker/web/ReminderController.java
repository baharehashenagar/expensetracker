package ir.expensetracker.web;


import ir.expensetracker.api.ReminderCreateParam;
import ir.expensetracker.api.ReminderDeleteParam;
import ir.expensetracker.api.UserCreateParam;
import ir.expensetracker.exception.InvalidParameterException;
import ir.expensetracker.service.facade.IReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reminder")
public class ReminderController {
    @Autowired
    IReminderService reminderService;

    @PostMapping
    public ResponseEntity<Object> createReminder(@RequestBody ReminderCreateParam user) {
        try {
            return ResponseEntity.ok(reminderService.createReminder(user));
        } catch (InvalidParameterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteReminder(@PathVariable("id") Integer reminderId) {
        try {
            return ResponseEntity.ok(reminderService.deleteReminder(new ReminderDeleteParam(reminderId)));
        } catch (InvalidParameterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findUserRemindersForSpecificDate(@RequestParam Integer userId,@RequestParam String date) {
        try {
            return ResponseEntity.ok(reminderService.findUserRemindersForSpecificDate(userId, date));
        } catch (InvalidParameterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findUserReminders(@PathVariable("id") Integer userId) {
        try {
            return ResponseEntity.ok(reminderService.findUserReminders(userId));
        } catch (InvalidParameterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
