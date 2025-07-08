package org.example.Service;

import org.example.DTO.TransactionRequest;
import org.example.Model.Transaction;
import org.example.Producer.KafkaProducerService;
import org.example.Repository.TransactionRepository;
import org.example.Rules.RuleEvaluator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository repository;
    private final RuleEvaluator evaluator;
    private final KafkaProducerService kafkaProducer;

    public TransactionService(TransactionRepository repository,
                              RuleEvaluator evaluator,
                              KafkaProducerService kafkaProducer) {
        this.repository = repository;
        this.evaluator = evaluator;
        this.kafkaProducer = kafkaProducer;
    }

    public Transaction processTransaction(TransactionRequest req) {
        Transaction txn = new Transaction();
        txn.setCustomerId(req.customerId);
        txn.setAmount(req.amount);
        txn.setLocation(req.location);
        txn.setTimestamp(LocalDateTime.now());

        boolean isSuspicious = evaluator.evaluate(txn);
        txn.setSuspicious(isSuspicious);

        if (isSuspicious) {
            kafkaProducer.sendAlert("Suspicious transaction detected: " + req.customerId + " - Amount: " + req.amount);
        }

        return repository.save(txn);
    }
}
