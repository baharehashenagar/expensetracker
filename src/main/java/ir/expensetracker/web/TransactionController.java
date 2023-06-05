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
    public ResponseEntity<Object> createTransaction(@RequestBody TransactionCreateParam transaction) {
        try {
            return ResponseEntity.ok(transactionService.createTransaction(transaction));
        } catch (InvalidParameterException| RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable("id") Integer transactionId) {
        try {
            return ResponseEntity.ok(transactionService.deleteTransaction(new TransactionDeleteParam(transactionId)));
        } catch (InvalidParameterException| RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAllTransactionsOfUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAllTransactionsOfUser(@PathVariable("id") Integer transactionId) {
        try {
            return ResponseEntity.ok(transactionService.findAllTransactionsOfUser(new AllTransactionsParam(transactionId)));
        } catch (InvalidParameterException| RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value ="/findTransactionsOfUserInMonth" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findTransactionsOfUserInMonth(AllTransactionsInMonthParam transaction) {
        try {
            return ResponseEntity.ok(transactionService.findTransactionsOfUserInMonth(transaction));
        } catch (InvalidParameterException| RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value ="/findTransactionsOfUserInMonthDetails" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findTransactionsOfUserInMonthDetails(AllTransactionsInMonthParam transaction) {
        try {
            return ResponseEntity.ok(transactionService.findTransactionsOfUserInMonthDetails(transaction));
        } catch (InvalidParameterException| RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/findTransactionsOfUserInMonthByCategory",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findTransactionsOfUserInMonthByCategory(AllTransactionsInMonthByCategoryParam transaction) {
        try {
            return ResponseEntity.ok(transactionService.findTransactionsOfUserInMonthByCategory(transaction));
        } catch (InvalidParameterException| RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/findTransactionsOfUserInMonthGreaterThanSpecificAmount",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findTransactionsOfUserInMonthGreaterThanSpecificAmount(AllTransactionsInMonthGreaterThanSpecificAmountParam transaction) {
        try {
            return ResponseEntity.ok(transactionService.findTransactionsOfUserInMonthGreaterThanSpecificAmount(transaction));
        } catch (InvalidParameterException| RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping (value = "/saveTransactionsOfUserInMonthAtExcel")
    public ResponseEntity<Object> saveTransactionsOfUserInMonthAtExcel(@RequestBody AllTransactionsInMonthParam transaction) {
        try {
            return ResponseEntity.ok(transactionService.saveTransactionsOfUserInMonthAtExcel(transaction));
        } catch (InvalidParameterException| RecordNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
