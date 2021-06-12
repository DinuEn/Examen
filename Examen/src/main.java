import java.io.*;

public class main {
    Fitness gym = Fitness.getInstance();
    public static void main(String[] args) throws Exception {

        Fitness gym = Fitness.getInstance();
        BufferedWriter writer = new BufferedWriter(new FileWriter("Examen/src/out.txt"));
        BufferedReader reader = new BufferedReader(new FileReader("Examen/src/input.txt"));

        String s;
        while ((s = reader.readLine()) != null){
            String[] arrayOfStrings = s.split(" ");
            String instruction = arrayOfStrings[0];
            switch (instruction){
                //instructiuni de adaugare si care necesita autentificare

                case "SIGNUP_ABONAT":
                    String email = arrayOfStrings[1];
                    String name = arrayOfStrings[2];
                    int progress = Integer.parseInt(arrayOfStrings[3]);
                    String password = arrayOfStrings[4];
                    String confirmation_password = arrayOfStrings[5];

                    String message = gym.adaugaAbonat(email, name, progress, password, confirmation_password);
                    writer.write(message + '\n');
                    break;


                case "SIGNUP_ANTRENOR":
                    email = arrayOfStrings[1];
                    name = arrayOfStrings[2];
                    int max_abonati = Integer.parseInt(arrayOfStrings[3]);
                    password = arrayOfStrings[4];
                    confirmation_password = arrayOfStrings[5];

                    message = gym.adaugaAntrenor(email, name, max_abonati, password, confirmation_password);
                    writer.write(message+ '\n');
                    break;

                case "LOGIN_ABONAT":
                    email = arrayOfStrings[1];
                    password = arrayOfStrings[2];

                    message = gym.logareAbonat(email, password);
                    if(!message.equals(""))
                        writer.write(message+ '\n');
                    break;

                case "LOGIN_ANTRENOR":
                    email = arrayOfStrings[1];
                    password = arrayOfStrings[2];

                    message = gym.logareAntrenor(email, password);
                    if(!message.equals(""))
                        writer.write(message+ '\n');
                    break;

                case "LOGOUT_ABONAT":
                    email = arrayOfStrings[1];

                    message = gym.logoutAbonat(email);
                    writer.write(message+ '\n');
                    break;

                case "LOGOUT_ANTRENOR":
                    email = arrayOfStrings[1];

                    message = gym.logoutAntrenor(email);
                    writer.write(message+ '\n');
                    break;

                case "INCRECEMENT_PROGRES":
                    int value = Integer.parseInt(arrayOfStrings[1]);

                    message = gym.incrementProgress(value);

                    if(!message.equals(""))
                        writer.write(message+ '\n');
                    break;

                case "DECREMENT_PROGRES":
                    value = Integer.parseInt(arrayOfStrings[1]);

                    message = gym.decrementProgress(value);

                    if(!message.equals(""))
                        writer.write(message+ '\n');
                    break;

                case "ADAUGA_ANTRENOR":
                    email = arrayOfStrings[1];

                    message = gym.adaugaAntrenorPentruAbonat(email);

                    if(!message.equals(""))
                        writer.write(message+ '\n');
                    break;

                case "VIZUALIZARE_ABONATII_MEI":
                    message = gym.vizualizareaAbonatiiMei();

                    if(!message.equals(""))
                        writer.write(message+ '\n');
                    break;

                case "SUBSCRIBE_ABONAT":
                    message = gym.subscribeAbonat();

                    writer.write(message + '\n');
                    break;

                case "SUBSCRIBE_ANTRENOR":
                    message = gym.subscribeAntrenor();

                    writer.write(message + '\n');
                    break;


                //Instructiuni fara logare

                case "ADAUGA_NEWS":
                    break;

                case "INTRA_IN_SALA":
                    email = arrayOfStrings[1];

                    message = gym.intraInSala(email);
                    if(!message.equals(""))
                        writer.write(message);
                    break;

                case "IESI_DIN_SALA":
                    email = arrayOfStrings[1];

                    message = gym.iesiDinSala(email);
                    if(!message.equals(""))
                        writer.write(message);
                    break;

                case "VIZUALIZARE_PERSOANE_CU_ANTRENOR":
                    break;

                case "VIZUALIZARE_ABONATI":
                    break;

                case "VIZUALIZARE_ANTRENORI":
                    break;

                case "PERSISTA_ABONATI":
                    break;

                case "PERSISTA_ANTRENORI":
                    break;
            }

        }
        writer.close();
    }
}
