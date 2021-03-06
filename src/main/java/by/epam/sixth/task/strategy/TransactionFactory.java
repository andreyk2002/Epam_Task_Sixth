package by.epam.sixth.task.strategy;

public class TransactionFactory {

    public TransactionFactory() {
    }

    public TransactionStrategy getStrategy(boolean isBuying) {
        return isBuying ? new BuyTransactionStrategy() : new SellTransactionStrategy();
    }
}
