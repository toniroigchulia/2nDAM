import java.io.*;
import java.util.*;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Client> clients = new ArrayList<>();
        String nomFitxer = null;
        boolean sortir = false;

        while (!sortir) {
            System.out.println("Menú:");
            System.out.println("1) Seleccioneu el fitxer.");
            System.out.println("2) Nombre de clients.");
            System.out.println("3) Mostra les dades del client.");
            System.out.println("4) Afegeix client.");
            System.out.println("5) Eliminar el client.");
            System.out.println("6) Modificar el client.");
            System.out.println("7) Sortida.");
            System.out.print("Seleccioneu una opció: ");

            int opcio = scanner.nextInt();
            scanner.nextLine();

            switch (opcio) {
                case 1:
                    System.out.print("Introduïu el nom del fitxer: ");
                    nomFitxer = scanner.nextLine();
                    break;
                case 2:
                    if (nomFitxer != null) {
                        clients = llegirClients(nomFitxer);
                        System.out.println("Nombre de clients: " + clients.size());
                    } else {
                        System.out.println("Selecciona primer el fitxer");
                    }
                    break;
                case 3:
                    if (nomFitxer != null) {
                        mostrarDadesClient(clients);
                    } else {
                        System.out.println("Selecciona primer el fitxer");
                    }
                    break;
                case 4:
                    if (nomFitxer != null) {
                        afegirClient(clients, nomFitxer);
                    } else {
                        System.out.println("Selecciona primer el fitxer");
                    }
                    break;
                case 5:
                    if (nomFitxer != null) {
                        eliminarClient(clients, nomFitxer);
                    } else {
                        System.out.println("Selecciona primer el fitxer");
                    }
                    break;
                case 6:
                    if (nomFitxer != null) {
                        modificarClient(clients, nomFitxer);
                    } else {
                        System.out.println("Selecciona primer el fitxer");
                    }
                    break;
                case 7:
                    sortir = true;
                    break;
                default:
                    System.out.println("Opció no vàlida.");
            }
        }

        scanner.close();
    }

    public static List<Client> llegirClients(String nomFitxer) {
        List<Client> clients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomFitxer))) {
            String linia;
            while ((linia = reader.readLine()) != null) {
                if (linia.equals("=== [CLIENT] ===")) {
                    Client client = Client.llegirClientDesdeFitxer(reader);
                    clients.add(client);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public static void mostrarDadesClient(List<Client> clients) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdueix l'identificador del client: ");
        int identificador = scanner.nextInt();

        boolean trobat = false;
        for (Client client : clients) {
            if (client.getIdentificador() == identificador) {
                client.printInfo();
                trobat = true;
                break;
            }
        }

        if (!trobat) {
            System.out.println("El client amb identificador " + identificador + " no existeix.");
        }

        scanner.close();
    }

    public static void afegirClient(List<Client> clients, String nomFitxer) {
        Client nouClient = new Client();

        clients.add(nouClient);
        escriureClients(nomFitxer, clients);
        System.out.println("Nou client afegit correctament.");
    }

    public static void eliminarClient(List<Client> clients, String nomFitxer) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdueix l'identificador del client a eliminar: ");
        int identificador = scanner.nextInt();
        scanner.close();
        
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getIdentificador() == identificador) {
                clients.remove(i);
                escriureClients(nomFitxer, clients);
                System.out.println("Client eliminat correctament.");
                return;
            }
        }

        System.out.println("El client amb identificador " + identificador + " no existeix.");
    }

    public static void modificarClient(List<Client> clients, String nomFitxer) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdueix l'identificador del client a modificar: ");
        int identificador = scanner.nextInt();
        scanner.close();
        
        for (Client client : clients) {
            if (client.getIdentificador() == identificador) {
                escriureClients(nomFitxer, clients);
                System.out.println("Dades del client actualitzades correctament.");
                return;
            }
        }

        System.out.println("El client amb identificador " + identificador + " no existeix.");
    }

    public static void escriureClients(String nomFitxer, List<Client> clients) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFitxer))) {
            for (Client client : clients) {
                writer.write("=== [CLIENT] ===\n");
                writer.write("[Identificador] - " + client.getIdentificador() + "\n");
                writer.write("[NOM] - " + client.getNom() + "\n");
                writer.write("[COGNOM] - " + client.getCognom() + "\n");
                writer.write("[EDAT] - " + client.getEdat() + "\n");
                writer.write("[TELÈFON] - " + client.getTelefon() + "\n");
                writer.write("[EMAIL] - " + client.getEmail() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}