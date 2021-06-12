import javax.swing.*;
import java.time.LocalDateTime;
import java.util.*;

public class Fitness {
    private static Fitness instance = new Fitness();
    private ArrayList<Persoana> persoane = new ArrayList<Persoana>();
    private int id = 0;
    private Abonat abonatCurent = null;
    private Antrenor antrenorCurent = null;
    private Newsletter fitnessNews = new Newsletter();
    private List<Persoana> persoaneInSala = new ArrayList<>();
    private Queue<Persoana> listaAsteptare = new PriorityQueue<>();

    private Fitness(){
    }

    public static Fitness getInstance(){
        if(instance == null){
            instance = new Fitness();
        }

        return instance;
    }

    public String iesiDinSala(String email){
        for(Persoana persoanaInSala : persoaneInSala){
            if(persoanaInSala.getMail().equals(email)) {
                persoaneInSala.remove(persoanaInSala);
                Persoana nextInLine = listaAsteptare.poll();
                persoaneInSala.add(nextInLine);
                return "";
            }
        }

        return "Persoana cu adresa de email " + email + " nu este in sala!";
    }

    public String intraInSala(String email){
        Persoana persoanaAux = null;
        for(Persoana persoana : persoane){ //identificam daca exista o persoana cu mailul corect inscrisa
            if(persoana.getMail().equals(email)){
                persoanaAux = persoana;
                break;
            }
        }

        if(persoanaAux == null)
            return "";

        for(Persoana persoanaInSala : persoaneInSala){
            if(persoanaInSala.getMail().equals(persoanaAux.getMail()))
                return "Persoana cu adresa de email " + email + " este deja in sala!";
        }

        if(persoaneInSala.size() > 9){
            listaAsteptare.add(persoanaAux);
            return "Sala este deja plina, ati fost pus intr-o coada de asteptare!";
        }

        persoanaAux.addDate(LocalDateTime.now());
        persoaneInSala.add(persoanaAux);
        return "";
    }

    public String subscribeAbonat(){
        if(abonatCurent == null)
            return "Nu exista niciun abonat logat!";

        abonatCurent.subscribe(fitnessNews);
        return "Abonatul cu adresa de email " + abonatCurent.getMail() + " a fost abonat la newsletter";
    }

    public String subscribeAntrenor(){
        if(antrenorCurent == null)
            return "Nu exista niciun antrenor logat!";

        antrenorCurent.subscribe(fitnessNews);
        return "Antrenorul cu adresa de email " + antrenorCurent.getMail() + " a fost abonat la newsletter";
    }

    public String vizualizareaAbonatiiMei(){
        if(antrenorCurent == null)
            return "Nu exista niciun antrenor logat!";

        return antrenorCurent.getAllAbonati();
    }


    public String adaugaAntrenorPentruAbonat(String email){
        if(abonatCurent == null)
            return "Nu exista niciun abonat logat!";

        if(getAntrenor(email) == null)
            return "Nu exista antrenorul cu emailul " + email;

        if(getAntrenor(email).getNrCursanti() >= 10)
            return "Antrenorul nu mai are locuri libere";


        Antrenor viitorAntrenor = getAntrenor(email);
        Antrenor fostAntrenor = abonatCurent.getAntrenorulMeu();
        abonatCurent.setAntrenorulMeu(viitorAntrenor);
        viitorAntrenor.adaugaAbonat(abonatCurent);

        if(fostAntrenor != null)
            fostAntrenor.eliminaAbonat(abonatCurent);
        return "";
    }

    public String incrementProgress(int value){
        if(abonatCurent == null)
            return "Nu exista niciun abonat logat!";

        if((value + abonatCurent.getProgress()) > 10){
            return "Nu se poate face incrementarea. Progresul total ar fi:" + (value + abonatCurent.getProgress());
        }

        int oldValue = abonatCurent.getProgress();
        abonatCurent.setProgress(value + oldValue);
        return "";
    }

    public String decrementProgress(int value){
        if(abonatCurent == null)
            return "Nu exista niciun abonat logat!";

        if((abonatCurent.getProgress() - value) < 0){
            return "Nu se poate face decrementarea. Progresul total ar fi:" + (abonatCurent.getProgress() - value);
        }

        int oldValue = abonatCurent.getProgress();
        abonatCurent.setProgress(oldValue - value);
        return "";
    }

    public String logareAbonat(String email, String password){
        if(abonatCurent != null)
            return "Alt abonat este deja conectat";

        if(!emailExists(email))
            return "Abonatul nu exista!";

        if(!(password.hashCode()==getPasswordHashForEmail(email)))  //password hashes are not the same
            return "Parola incorecta!";


        abonatCurent = (Abonat)getPersoana(email, password);
        return "";
    }

    public String logareAntrenor(String email, String password){
        if(antrenorCurent != null)
            return "Alt antrenor este deja conectat";

        if(!emailExists(email))
            return "Antrenorul nu exista!";

        if(!(password.hashCode()==getPasswordHashForEmail(email)))  //password hashes are not the same
            return "Parola incorecta!";


        antrenorCurent = (Antrenor)getPersoana(email, password);
        return "";
    }


    public String logoutAbonat(String email){
        if(email.equals(abonatCurent.getMail())) {
            abonatCurent = null;
            return "Abonatul " + email + " a fost deconectat!";
        }
        else{
            return "Abonatul nu era conectat!";
        }
    }

    public String logoutAntrenor(String email){
        if(email.equals(antrenorCurent.getMail())) {
            antrenorCurent = null;
            return "Antrenorul " + email + " a fost deconectat!";
        }
        else{
            return "Antrenorul nu era conectat!";
        }
    }

    public String adaugaPersoana(String email, String name, int variable, String password, String confirmation_password){

        if(!passwordMatcher(password, confirmation_password))
            return "Parole diferite, nu se poate face adaugarea!";

        if(!passwordLengthCheck(password))
            return "Parola este prea scurta!";

        if(!mailIsOK(email))
            return "Adresa de mail invalida!";

        if(personExists(email, name, variable, password))
            return "Abonat deja existent!";

        if(emailExists(email))
            return "Adresa de mail deja utilizata!";


        return "Pass";

    }


    public String adaugaAbonat(String email, String name, int progress, String password, String confirmation_password){
        if(!adaugaPersoana(email, name, progress, password, confirmation_password).equals("Pass")){
            return adaugaPersoana(email, name, progress, password, confirmation_password);
        }
        else{
            persoane.add(new Abonat(name, email,password, id, new ArrayList<LocalDateTime>(), progress));
            id++;
            return "Abonatul " + name + " a fost adaugat!";
        }
    }

    public String adaugaAntrenor(String email, String name, int nrCursanti, String password, String confirmation_password){
        if(!adaugaPersoana(email, name, nrCursanti, password, confirmation_password).equals("Pass")){
            return adaugaPersoana(email, name, nrCursanti, password, confirmation_password);
        }
        else{
            persoane.add(new Antrenor(name, email,password, id, new ArrayList<LocalDateTime>(), nrCursanti));
            id++;
            return "Antrenorul " + name + " a fost adaugat!";
        }
    }



    private boolean passwordMatcher(String password, String confirmation_password){
        if(password.equals(confirmation_password))
            return true;
        else return false;

    }

    private boolean passwordLengthCheck(String password){
        if(password.length()>=8)
            return true;
        else return false;
    }

    private boolean mailIsOK(String email){
        String first_part = email.split("@")[0];
        String second_part = email.split("@")[1];
        if(email.split("@").length == 2 && first_part.split("\\.").length == 1 && second_part.split("\\.").length == 2)
            return true;
        else return false;
    }

    private boolean personExists(String email, String name, int progress, String password){
        for (Persoana persoana : persoane) {
            if (persoana.equals(new Abonat(name, email, password, 0, new ArrayList<LocalDateTime>(), progress))) {
                return true;
            }
        }
        return false;
    }

    private boolean emailExists(String email) {
        for (Persoana persoana : persoane) {
            if (persoana.getMail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private Antrenor getAntrenor(String email){
        for (Persoana persoana : persoane) {
            if (persoana.getMail().equals(email) && (persoana instanceof Antrenor)) {
                return (Antrenor) persoana;
            }
        }
        return null;
    }

    private int getPasswordHashForEmail(String email){
        for (Persoana persoana : persoane) {
            if (persoana.getMail().equals(email)) {
                return persoana.hashCodeOfPassword();
            }
        }
        return 0;
    }

    private Persoana getPersoana(String email, String password){
        for (Persoana persoana : persoane) {
            if (persoana.getMail().equals(email) && password.hashCode() == persoana.hashCodeOfPassword()) {
                return persoana;
            }
        }
        return null;
    }
}
