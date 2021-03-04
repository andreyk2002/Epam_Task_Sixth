package by.epam.sixth.task.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Trader implements Runnable {
    private static final StockMarket MARKET = StockMarket.getInstance();
    private double cash;
    private final int id;
    private final int transactionsCount;
    private static final long TIMEOUT = 50;


    @JsonCreator
    public Trader(@JsonProperty("cash") double cash, @JsonProperty("id") int id,
                  @JsonProperty("transactions") int transactionsCount) {
        this.cash = cash;
        this.id = id;
        this.transactionsCount = transactionsCount;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public int getId() {
        return id;
    }

    public int getTransactionsCount() {
        return transactionsCount;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "cash=" + cash +
                ", id=" + id +
                ", transactionsCount=" + transactionsCount +
                '}';
    }

    @Override
    public void run() {
        for (int i = 0; i < transactionsCount; i++) {
            boolean isBuying = decideRandom();
            if (isBuying) {
                MARKET.handleTransaction(this);
            } else {
                MARKET.handleSell(this);
            }

            try {
                TimeUnit.MILLISECONDS.sleep(TIMEOUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean decideRandom() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
