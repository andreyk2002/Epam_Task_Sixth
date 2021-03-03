package by.epam.sixth.task.entities;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.List;

public class StockMarket implements TradersObserver{

    private static AtomicReference<StockMarket> instance;
    private static final ReentrantLock LOCK = new ReentrantLock();
    private AtomicReference<Double> tradeRatio;
    private List<Trader>traders = new ArrayList<>();

    public static StockMarket getInstance() {
        StockMarket localInstance = instance.get();
        if (localInstance == null) {
            LOCK.lock();
            try {
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

    }

    private StockMarket() {

    }

    @Override
    public void addObservable(Trader trader) {
        traders.add(trader);
    }


    @Override
    public void update(Trader trader) {

    }
}
