package by.epam.sixth.task.strategy;

import by.epam.sixth.task.entities.Trader;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BuyTransaction implements Transaction {

    private final static int SCALE = 2;

    @Override
    public void handle(BigDecimal tradeRatio, Trader trader) {
        BigDecimal oldCash = trader.getCash();
        BigDecimal newCash = oldCash.divide(tradeRatio, SCALE, RoundingMode.HALF_EVEN);
        trader.setCash(newCash);
    }
}
