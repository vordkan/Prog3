import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> contoObservers = new ArrayList<>();

    public void attachContoObserver(Observer observer) {
        contoObservers.add(observer);
    }

    public void detachContoObserver(Observer observer) {
        contoObservers.remove(observer);
    }

    public void notifyContoObservers(double nuovoConto) {
        for (Observer observer : contoObservers) {
            observer.update(nuovoConto);
        }
    }
}
