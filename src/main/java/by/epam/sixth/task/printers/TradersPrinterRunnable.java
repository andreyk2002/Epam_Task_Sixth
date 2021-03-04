package by.epam.sixth.task.printers;

import by.epam.sixth.task.entities.Trader;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TradersPrinterRunnable implements Runnable{
    private List<Trader> traders;
    private static final Long timeoutMilliseconds = 30L;

    public TradersPrinterRunnable(List<Trader> traders) {
        this.traders = traders;
    }


    @Override
    public void run() {
        while (true){
            System.out.println("Actual situation on market:");
            traders.forEach(thread -> System.out.println(thread));
            try {
                TimeUnit.MILLISECONDS.sleep(timeoutMilliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\n\n");
        }
    }
}
