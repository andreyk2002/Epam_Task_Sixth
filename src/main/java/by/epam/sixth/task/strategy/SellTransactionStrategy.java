package by.epam.sixth.task.strategy;

import by.epam.sixth.task.entities.Trader;

public class SellTransactionStrategy implements TransactionStrategy {
    @Override
    public void makeTransaction(double trade_ratio, Trader trader) {
        double oldCash = trader.getCash();
        double newCash = oldCash * trade_ratio;
        trader.setCash(newCash);
    }
}
