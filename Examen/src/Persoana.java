import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Persoana implements Observer{
    private String name;
    private String mail;
    private String password;
    private int id;
    private ArrayList<LocalDateTime> entry_dates = new ArrayList<>();

    public Persoana(String name, String mail, String password, int id, ArrayList<LocalDateTime> entry_dates) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.id = id;
        this.entry_dates = entry_dates;
    }

    public String getMail() {
        return mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persoana persoana = (Persoana) o;
        return Objects.equals(name, persoana.name) && Objects.equals(mail, persoana.mail) && Objects.equals(password, persoana.password);// && Objects.equals(entry_dates, persoana.entry_dates);
    }

    public void addDate(LocalDateTime date){
        entry_dates.add(date);
    }

    public int hashCodeOfPassword() {
        return password.hashCode();
    }

    @Override
    public void subscribe(Subject subject){
        subject.addObserver(this);
    }

    @Override
    public String update(String news){
        return "A fost notificata persoana cu adresa de email" + mail + "de stirea cu mesajul " + news + '\n';
    }


}
