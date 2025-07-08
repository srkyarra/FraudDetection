package org.example.Controller;
import org.example.DTO.TransactionRequest;
import org.example.Model.Transaction;
import org.example.Service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Transaction> submit(@RequestBody TransactionRequest request) {
        Transaction txn = service.processTransaction(request);
        return ResponseEntity.ok(txn);
    }
}