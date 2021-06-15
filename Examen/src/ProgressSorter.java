import java.util.Comparator;

public class ProgressSorter implements Comparator<Abonat> {
    @Override
    public int compare(Abonat a1, Abonat a2){
        return (a2.getProgress() - a1.getProgress())*10000 + a1.getName().compareTo(a2.getName());
    }
}
