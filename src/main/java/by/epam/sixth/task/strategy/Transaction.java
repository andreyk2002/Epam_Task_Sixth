package by.epam.sixth.task.strategy;

import by.epam.sixth.task.entities.Trader;

import java.math.BigDecimal;

public interface Transaction {

    void handle(BigDecimal tradeRatio, Trader trader);
}
