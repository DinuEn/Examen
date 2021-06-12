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

    @Override
    public String notifyObservers(){
        StringBuilder auxStringBuilder = new StringBuilder();

        for(Observer observer : observers){
            auxStringBuilder.append(observer.update(news));
        }
        return auxStringBuilder.toString();
    }
}
