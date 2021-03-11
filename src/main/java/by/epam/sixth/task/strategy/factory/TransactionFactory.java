package by.epam.sixth.task.strategy.factory;

import by.epam.sixth.task.strategy.BuyTransaction;
import by.epam.sixth.task.strategy.SellTransaction;
import by.epam.sixth.task.strategy.Transaction;

public class TransactionFactory {

    public TransactionFactory() {
    }

    public Transaction getTransaction(boolean isBuying) {
        return isBuying ? new BuyTransaction() : new SellTransaction();
    }
}
