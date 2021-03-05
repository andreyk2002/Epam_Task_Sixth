package by.epam.sixth.task.strategy;

import by.epam.sixth.task.entities.Trader;

public class BuyTransactionStrategy implements TransactionStrategy {
    @Override
    public void makeTransaction(double trade_ratio, Trader trader) {
        double oldCash = trader.getCash();
        double newCash = oldCash * 2; // trade_ratio;
        trader.setCash(newCash);
    }
}
