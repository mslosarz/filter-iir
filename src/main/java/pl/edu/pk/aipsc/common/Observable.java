package pl.edu.pk.aipsc.common;

public interface Observable {
    void addObserver(Observer observer);

    void updateObservers();
}
