import java.util.ArrayList;
import java.util.List;

public class Newsletter implements Subject{
    private String news;
    private List<Observer> observers= new ArrayList<>();

    public void setNews(String news){
        this.news = news;
    }

    @Override
    public void addObserver(Observer observer){
        observers.add(observer);
    }

//    @Override
//    public void notifyObservers(){
//        observers.forEach(observer -> observer.update(news));
//    }
}
