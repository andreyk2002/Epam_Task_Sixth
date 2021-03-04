package by.epam.sixth.task.entities;

import java.util.List;

public class Traders {
    private List<Trader>traders;

    public Traders(){
    }

    public Traders(List<Trader> traders) {
        this.traders = traders;
    }

    public List<Trader> getTraders() {
        return traders;
    }
}
