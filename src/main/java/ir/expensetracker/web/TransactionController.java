package ir.expensetracker.web;

import ir.expensetracker.api.*;
import ir.expensetracker.exception.InvalidParameterException;
import ir.expensetracker.exception.RecordNotFoundException;
import ir.expensetracker.service.facade.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    ITransactionService transactionService;

    @PostMapping
    public ResponseEntity<Object> createTransaction(@RequestBody TransactionCreateParam transaction, @RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(transactionService.createTransaction(transaction, jwt));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(e.getMessage()));
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResult(e.getMessage()));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable("id") Integer transactionId, @RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(transactionService.deleteTransaction(new TransactionDeleteParam(transactionId)));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(e.getMessage()));
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResult(e.getMessage()));
        }
    }

    @GetMapping(value = "/findAllTransactionsOfUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAllTransactionsOfUser(@RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(transactionService.findAllTransactionsOfUser(jwt));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(e.getMessage()));
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResult(e.getMessage()));
        }
    }

    @GetMapping(value = "/findTransactionsOfUserInMonth", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findTransactionsOfUserInMonth(AllTransactionsInMonthParam transaction, @RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(transactionService.findTransactionsOfUserInMonth(transaction, jwt));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(e.getMessage()));
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResult(e.getMessage()));
        }
    }

    @GetMapping(value = "/findTransactionsOfUserInMonthDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findTransactionsOfUserInMonthDetails(AllTransactionsInMonthParam transaction, @RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(transactionService.findTransactionsOfUserInMonthDetails(transaction, jwt));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(e.getMessage()));
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResult(e.getMessage()));
        }
    }

    @GetMapping(value = "/findTransactionsOfUserInMonthByCategory", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findTransactionsOfUserInMonthByCategory(AllTransactionsInMonthByCategoryParam transaction, @RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(transactionService.findTransactionsOfUserInMonthByCategory(transaction, jwt));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(e.getMessage()));
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResult(e.getMessage()));
        }
    }

    @GetMapping(value = "/findTransactionsOfUserInMonthGreaterThanSpecificAmount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findTransactionsOfUserInMonthGreaterThanSpecificAmount(AllTransactionsInMonthGreaterThanSpecificAmountParam transaction, @RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(transactionService.findTransactionsOfUserInMonthGreaterThanSpecificAmount(transaction, jwt));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(e.getMessage()));
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResult(e.getMessage()));
        }
    }

    @PostMapping(value = "/saveTransactionsOfUserInMonthAtExcel")
    public ResponseEntity<Object> saveTransactionsOfUserInMonthAtExcel(@RequestBody AllTransactionsInMonthParam transaction, @RequestHeader("Authorization") String jwt) {
        try {
            return ResponseEntity.ok(transactionService.saveTransactionsOfUserInMonthAtExcel(transaction, jwt));
        } catch (InvalidParameterException | RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(e.getMessage()));
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResult(e.getMessage()));
        }
    }
}
