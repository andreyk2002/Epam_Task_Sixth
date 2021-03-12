package by.epam.sixth.task.entities;

import by.epam.sixth.task.strategy.Transaction;
import by.epam.sixth.task.strategy.factory.TransactionFactory;

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
    private static final ReentrantLock LOCK = new ReentrantLock();

    private final AtomicReference<BigDecimal> tradeRatio = new AtomicReference<>();
    private final Random random = new Random();

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

    public void handleTransaction(boolean isBuying, Trader trader) {
        try {
            MARKET_START.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TransactionFactory factory = new TransactionFactory();
        Transaction transaction = factory.getTransaction(isBuying);
        transaction.handle(tradeRatio.get(), trader);
    }

    @Override
    public void run() {
        double randomNormal = Math.abs(random.nextGaussian() + 1);
        BigDecimal generatedResult = new BigDecimal(randomNormal);
        tradeRatio.set(generatedResult);
        MARKET_START.countDown();
        while (true) {
            changeTradeRatio();
        }
    }

    private void changeTradeRatio() {
        double randomRatio = Math.random() + 0.5;
        BigDecimal changeRatio = new BigDecimal(randomRatio);
        BigDecimal newRatio = changeRatio.multiply(tradeRatio.get());
        tradeRatio.getAndSet(newRatio);
    }

}
