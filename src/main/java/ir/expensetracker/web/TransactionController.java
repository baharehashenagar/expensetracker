package ir.expensetracker.web;

import ir.expensetracker.api.TransactionCreateParam;
import ir.expensetracker.service.facade.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    ITransactionService transactionService;

    @PostMapping
    public ResponseEntity<Object> createTransaction(@RequestBody TransactionCreateParam transaction) {
        try {
            return ResponseEntity.ok(transactionService.createTransaction(transaction));
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
