import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Abonat extends Persoana{
    private int progress;
    private Antrenor antrenorulMeu = null;

    public Abonat(String name, String mail, String password, int id, ArrayList<LocalDateTime> entry_dates, int progress) {
        super(name, mail, password, id, entry_dates);
        this.progress = progress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Abonat abonat = (Abonat) o;
        return progress == abonat.progress;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), progress);
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public Antrenor getAntrenorulMeu() {
        return antrenorulMeu;
    }

    public void setAntrenorulMeu(Antrenor AntrenorulMeu) {
        this.antrenorulMeu = AntrenorulMeu;
    }
}
