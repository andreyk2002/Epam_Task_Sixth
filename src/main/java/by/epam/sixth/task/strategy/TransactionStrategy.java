package by.epam.sixth.task.strategy;

import by.epam.sixth.task.entities.Trader;

import java.math.BigDecimal;

public interface TransactionStrategy {

    void makeTransaction(BigDecimal tradeRatio, Trader trader);
}
