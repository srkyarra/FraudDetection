package org.example.Rules;

import org.example.Model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class RuleEvaluator {
    private static final double HIGH_RISK_THRESHOLD = 10000.0;

    public boolean evaluate(Transaction txn) {
        return txn.getAmount() > HIGH_RISK_THRESHOLD || !"USA".equalsIgnoreCase(txn.getLocation());
    }
}