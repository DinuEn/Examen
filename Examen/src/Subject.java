public interface Subject {
    void addObserver(Observer observer);
    String notifyObservers();
}
