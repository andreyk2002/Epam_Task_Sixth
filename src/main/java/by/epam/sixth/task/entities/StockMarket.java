package by.epam.sixth.task.entities;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class StockMarket implements Runnable {

    public static final CountDownLatch MARKET_START = new CountDownLatch(1);
    private static final AtomicReference<StockMarket> instance = new AtomicReference<>();
    public static final ReentrantLock LOCK = new ReentrantLock();

    private final AtomicReference<Double> tradeRatio = new AtomicReference<>();
    private static final long TIMEOUT = 10;


    public static StockMarket getInstance() {
        StockMarket localInstance = instance.get();
        if (localInstance == null) {
            try {
                LOCK.lock();
                localInstance = instance.get();
                if (localInstance == null) {
                    instance.set(new StockMarket());
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance.get();
    }

    public void handleTransaction(Trader trader) {
        double startCash = trader.getCash();
        double newCash = startCash * tradeRatio.get();
        trader.setCash(newCash);
    }

    private StockMarket() {

    }

    public void handleSell(Trader trader) {
        double startCash = trader.getCash();
        double newCash = startCash / tradeRatio.get();
        trader.setCash(newCash);
    }

    @Override
    public void run() {
        changeTradeRatio();
        MARKET_START.countDown();
        while (true) {
           changeTradeRatio();
            try {
                TimeUnit.MILLISECONDS.sleep(TIMEOUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void changeTradeRatio(){
        Random random = new Random();
        double randomNormal = random.nextGaussian() + 1;
        double newRatio = Math.abs(randomNormal);
        tradeRatio.set(newRatio);
    }

}
