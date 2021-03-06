package by.epam.sixth.task.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Trader implements Runnable {

    private final int id;
    private final int transactionsCount;
    private BigDecimal cash;
    private final Random random = new Random();

    @JsonCreator
    public Trader(@JsonProperty("cash") BigDecimal cash, @JsonProperty("id") int id,
                  @JsonProperty("transactions") int transactionsCount) {
        this.cash = cash;
        this.id = id;
        this.transactionsCount = transactionsCount;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
       this.cash = cash;
    }

    public int getId() {
        return id;
    }

    public int getTransactionsCount() {
        return transactionsCount;
    }

    @Override
    public void run() {
        for (int i = 0; i < transactionsCount; i++) {
            boolean isBuying = random.nextBoolean();
            StockMarket market = StockMarket.getInstance();
            CountDownLatch marketStart = market.getMarketStart();
            try {
                marketStart.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            market.handleTransaction(isBuying, this);
        }
    }
}
