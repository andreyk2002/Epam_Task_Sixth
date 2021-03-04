package by.epam.sixth.task.strategy;

import by.epam.sixth.task.entities.Trader;

public interface TransactionStrategy {

    void makeTransaction(double trade_ratio, Trader trader);
}
