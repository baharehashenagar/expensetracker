package ir.expensetracker.web;

import ir.expensetracker.api.BudgetGoalCreateParam;
import ir.expensetracker.api.BudgetGoalDeleteParam;
import ir.expensetracker.api.BudgetGoalOfUserParam;
import ir.expensetracker.api.ErrorResult;
import ir.expensetracker.exception.InvalidParameterException;
import ir.expensetracker.exception.RecordNotFoundException;
import ir.expensetracker.service.facade.IBudgetGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budgetgoal")
public class BudgetGoalController {

    @Autowired
    IBudgetGoalService budgetGoalService;

    @PostMapping
    public ResponseEntity<Object> createBudgetGoal(@RequestBody BudgetGoalCreateParam budgetGoal, @RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(budgetGoalService.createBudgetGoal(budgetGoal, jwt));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(e.getMessage()));
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResult(e.getMessage()));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteBudgetGoal(@PathVariable("id") Integer budgetGoalId, @RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(budgetGoalService.deleteBudgetGoal(new BudgetGoalDeleteParam(budgetGoalId), jwt));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(e.getMessage()));
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResult(e.getMessage()));
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findUserBudgetGoals(BudgetGoalOfUserParam budgetGoal, @RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(budgetGoalService.findUserBudgetGoals(budgetGoal, jwt));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(e.getMessage()));
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResult(e.getMessage()));
        }
    }

}
