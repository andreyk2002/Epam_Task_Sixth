package by.epam.sixth.task.strategy;

import by.epam.sixth.task.entities.Trader;

public class Transaction {

    private final TransactionStrategy strategy;

    public Transaction(TransactionStrategy strategy) {
        this.strategy = strategy;
    }

    public void handleTransaction(double trade_ratio, Trader trader) {
        strategy.makeTransaction(trade_ratio, trader);
    }
}
