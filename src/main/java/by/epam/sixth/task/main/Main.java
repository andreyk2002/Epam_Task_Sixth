package by.epam.sixth.task.main;

import by.epam.sixth.task.entities.Traders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Main {

    private static final String INPUT_FILE = "src/main/resources/Traders.json";

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Traders traders = mapper.readValue(new File(INPUT_FILE), Traders.class);

        var tradersList = traders.getTraders();
        tradersList.forEach(trader -> System.out.println(trader.getCash()));

    }
}
