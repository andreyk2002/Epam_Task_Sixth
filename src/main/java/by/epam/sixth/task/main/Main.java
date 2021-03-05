package by.epam.sixth.task.main;

import by.epam.sixth.task.entities.StockMarket;
import by.epam.sixth.task.entities.Traders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Main {


    private static final String INPUT_FILE = "src/main/resources/Traders.json";

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        Traders tradersWrapper = mapper.readValue(new File(INPUT_FILE), Traders.class);
        var tradersList = tradersWrapper.getTraders();

        Thread marketThread = new Thread(StockMarket.getInstance());
        marketThread.setDaemon(true);
        marketThread.start();

        ExecutorService service = Executors.newFixedThreadPool(tradersList.size());
        List<Future<?>> futures = tradersList.stream()
                .map(trader -> service.submit(trader))
                .collect(Collectors.toList());

        for (var future : futures) {
            future.get();
        }
        service.shutdown();
    }
}
