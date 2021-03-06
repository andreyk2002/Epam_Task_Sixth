package by.epam.sixth.task.entities;

import by.epam.sixth.task.strategy.TransactionFactory;
import by.epam.sixth.task.strategy.TransactionStrategy;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class StockMarket implements Runnable {

    private static final CountDownLatch MARKET_START = new CountDownLatch(1);
    private static final AtomicReference<StockMarket> instance = new AtomicReference<>();
    public static final ReentrantLock LOCK = new ReentrantLock();
    private final AtomicReference<BigDecimal> tradeRatio = new AtomicReference<>();

    public static StockMarket getInstance() {
        StockMarket localInstance = instance.get();
        if (localInstance == null) {
            try {
                LOCK.lock();
                localInstance = instance.get();
                if (localInstance == null) {
                    StockMarket market = new StockMarket();
                    market.init();
                    instance.set(market);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance.get();
    }

    private void init() {
        ExecutorService service = Executors.newSingleThreadExecutor(runnable -> {
            Thread thread = Executors.defaultThreadFactory().newThread(runnable);
            thread.setDaemon(true);
            return thread;
        });
        service.execute(this);
    }

    private StockMarket() {
    }

    public CountDownLatch getMarketStart() {
        return MARKET_START;
    }

    public void handleTransaction(boolean isBuying, Trader trader) {
        TransactionFactory factory = new TransactionFactory();
        TransactionStrategy strategy = factory.getStrategy(isBuying);
        strategy.makeTransaction(tradeRatio.get(), trader);
    }

    @Override
    public void run() {
        changeTradeRatio();
        MARKET_START.countDown();
        while (true) {
            changeTradeRatio();
        }
    }

    private void changeTradeRatio() {
        Random random = new Random();
        double randomNormal = random.nextGaussian() + 1;
        double newRatio = Math.abs(randomNormal);
        BigDecimal generatedResult = new BigDecimal(newRatio);
        tradeRatio.set(generatedResult);
    }

}
