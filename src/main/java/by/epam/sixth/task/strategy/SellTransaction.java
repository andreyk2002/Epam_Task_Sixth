package by.epam.sixth.task.strategy;

import by.epam.sixth.task.entities.Trader;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SellTransaction implements Transaction {

    private static final int SCALE = 2;

    @Override
    public void handle(BigDecimal tradeRatio, Trader trader) {
        BigDecimal oldCash = trader.getCash();
        BigDecimal newCash = oldCash.divide(tradeRatio, SCALE, RoundingMode.HALF_EVEN);
        trader.setCash(newCash);
    }
}
