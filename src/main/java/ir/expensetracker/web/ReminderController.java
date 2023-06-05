package ir.expensetracker.web;


import ir.expensetracker.api.ErrorResult;
import ir.expensetracker.api.ReminderCreateParam;
import ir.expensetracker.api.ReminderDeleteParam;
import ir.expensetracker.exception.InvalidParameterException;
import ir.expensetracker.exception.RecordNotFoundException;
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
    public ResponseEntity<Object> createReminder(@RequestBody ReminderCreateParam user, @RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(reminderService.createReminder(user, jwt));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(e.getMessage()));
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResult(e.getMessage()));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteReminder(@PathVariable("id") Integer reminderId, @RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(reminderService.deleteReminder(new ReminderDeleteParam(reminderId), jwt));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(e.getMessage()));
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResult(e.getMessage()));
        }
    }

    @GetMapping(value = "/findUserRemindersForSpecificDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findUserRemindersForSpecificDate(@RequestParam String date, @RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(reminderService.findUserRemindersForSpecificDate(date, jwt));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(e.getMessage()));
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResult(e.getMessage()));
        }
    }

    @GetMapping(value = "/findUserReminders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findUserReminders(@RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(reminderService.findUserReminders(jwt));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(e.getMessage()));
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResult(e.getMessage()));
        }
    }

}
