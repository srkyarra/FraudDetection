package org.example.Service;


import org.example.DTO.TransactionRequest;
import org.example.Model.Transaction;
import org.example.Repository.TransactionRepository;
import org.example.Rules.RuleEvaluator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {
    private final TransactionRepository repository;
    private final RuleEvaluator evaluator;

    public TransactionService(TransactionRepository repository, RuleEvaluator evaluator) {
        this.repository = repository;
        this.evaluator = evaluator;
    }

    public Transaction processTransaction(TransactionRequest req) {
        Transaction txn = new Transaction();
        txn.setCustomerId(req.customerId);
        txn.setAmount(req.amount);
        txn.setLocation(req.location);
        txn.setTimestamp(LocalDateTime.now());
        txn.setSuspicious(evaluator.evaluate(txn));
        return repository.save(txn);
    }
}