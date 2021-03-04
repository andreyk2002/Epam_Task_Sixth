package by.epam.sixth.task.printers;

import by.epam.sixth.task.entities.StockMarket;
import by.epam.sixth.task.entities.Trader;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TradersPrinterRunnable implements Runnable{

    private static final CountDownLatch STOCK_START = StockMarket.MARKET_START;
    private final List<Trader> traders;
    private static final Long TIMEOUT = 30L;

    public TradersPrinterRunnable(List<Trader> traders) {
        this.traders = traders;
    }


    @Override
    public void run() {
        while (true){
            try {
                STOCK_START.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Actual situation on market:");
            traders.forEach(thread -> System.out.println(thread));
            try {
                TimeUnit.MILLISECONDS.sleep(TIMEOUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\n\n");
        }
    }
}
