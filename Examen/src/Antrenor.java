import java.time.LocalDateTime;
import java.util.ArrayList;


public class Antrenor extends Persoana{
    private int nrCursanti = 0;
    private ArrayList<Abonat> abonatiiMei = new ArrayList<Abonat>();

    public Antrenor(String name, String mail, String password, int id, ArrayList<LocalDateTime> entry_dates, int nrCursanti) {
        super(name, mail, password, id, entry_dates);
        this.nrCursanti = nrCursanti;
    }

    public int getNrCursanti() {
        return nrCursanti;
    }

    public void adaugaAbonat(Abonat abonatNou){
        abonatiiMei.add(abonatNou);
        nrCursanti++;
    }

    public void eliminaAbonat(Abonat abonatDeScos){

        for (Abonat abonat : abonatiiMei) {
            if (abonat.getMail().equals(abonatDeScos.getMail()))
                break;
        }
        abonatiiMei.remove(abonatDeScos);
        nrCursanti--;
    }

    public String getAllAbonati(){
        StringBuilder listaAbonati = new StringBuilder();


        listaAbonati.append("Antrenorul cu mailul " + super.getMail() + " are abonatii cu mailurile:" + '\n');
        if(abonatiiMei.size()>0) {
            for (Abonat abonat : abonatiiMei) {
                listaAbonati.append(abonat.getMail() + ' ');
            }
        }
        else listaAbonati.append("");

        return listaAbonati.toString();
    }

}
