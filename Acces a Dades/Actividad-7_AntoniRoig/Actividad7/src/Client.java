import java.io.BufferedReader;
import java.io.IOException;

public class Client {
    private int identificador;
    private String nom;
    private String cognom;
    private int edat;
    private String telefon;
    private String email;

    // Constructors, getters, setters
    // També implementa el mètode printInfo

    public void printInfo() {
        System.out.println("Identificador: " + identificador);
        System.out.println("Nom: " + nom);
        System.out.println("Cognom: " + cognom);
        System.out.println("Edat: " + edat);
        System.out.println("Telèfon: " + telefon);
        System.out.println("Email: " + email);
    }

    public static Client llegirClientDesdeFitxer(BufferedReader reader) throws IOException {
        Client client = new Client();
        String linia;

        while ((linia = reader.readLine()) != null) {
            if (linia.equals("[INICI_CLIENT]")) {
                // Llegir dades del client
                while (!(linia = reader.readLine()).equals("[FI_CLIENT]")) {
                    String[] parts = linia.split(" - ");
                    String atribut = parts[0].trim();
                    String valor = parts[1].trim();

                    switch (atribut) {
                        case "NOM":
                            client.setNom(valor);
                            break;
                        case "COGNOM":
                            client.setCognom(valor);
                            break;
                        case "EDAT":
                            client.setEdat(Integer.parseInt(valor));
                            break;
                        case "TELÈFON":
                            client.setTelefon(valor);
                            break;
                        case "EMAIL":
                            client.setEmail(valor);
                            break;
                    }
                }
            } else if (linia.equals("[FI_FITXER]")) {
                break;
            }
        }

        return client;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
