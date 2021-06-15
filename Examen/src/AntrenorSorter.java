import java.util.Comparator;

public class AntrenorSorter implements Comparator<Antrenor> {
    @Override
    public int compare(Antrenor a1, Antrenor a2){
        return (a2.getNrCursanti()-a1.getNrCursanti())*10000 +  a1.getName().compareTo(a2.getName());
    }

}
