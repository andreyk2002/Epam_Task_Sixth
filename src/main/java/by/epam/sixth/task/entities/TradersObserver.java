package by.epam.sixth.task.entities;

public interface TradersObserver {

    void addObservable(Trader trader);

    void update(Trader trader);
}
