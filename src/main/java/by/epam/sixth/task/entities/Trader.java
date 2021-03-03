package by.epam.sixth.task.entities;

import by.epam.sixth.task.observer.Observer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Trader {
    private double cash;
    private final  List<Observer>observers = new ArrayList<>();


    @JsonCreator
    public Trader(@JsonProperty("cash") double cash) {
        this.cash = cash;
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers(){
        observers.forEach(observer -> observer.update(this));
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof Trader)){
            return false;
        }

        Trader trader = (Trader) o;

        if (Double.compare(trader.cash, cash) != 0) {
            return false;
        }
        return observers.equals(trader.observers);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(cash);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + observers.hashCode();
        return result;
    }
}
