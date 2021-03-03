package by.epam.sixth.task.observer;

import by.epam.sixth.task.entities.Trader;

public interface Observer {
    void addObservable(Trader trader);

    void removeObservable(Trader trader);

    void update(Trader trader);
}
