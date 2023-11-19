import java.io.File;
import java.util.Scanner;

public class Actividad5ToniRoig {
   public static void main(String[] args) throws Exception {

      Scanner scanner = new Scanner(System.in);
      File currentFile = null;
      File newDir = null;

      while (true) {
         System.out.println("\n== Menú de operaciones ==");
         System.out.println("1) Carregar el fitxer.");
         System.out.println("2) Comprovar el fitxer.");
         System.out.println("3) Canviar el nom del fitxer.");
         System.out.println("4) Esborrar el fitxer.");
         System.out.println("5) Imprimir el nom del fitxer.");
         System.out.println("6) Imprimir el camí relatiu del fitxer.");
         System.out.println("7) Imprimir la ruta absoluta del fitxer.");
         System.out.println("8) Crear un directori.");
         System.out.println("9) Canviar el nom del directori.");
         System.out.println("10) Esborrar el directori.");
         System.out.println("0) Sortir");

         System.out.print("\nSelecciona una opció: ");
         int option = scanner.nextInt();
         scanner.nextLine();
         System.out.println("");

         switch (option) {
            case 1:

               System.out.print("Introdueix el nom del fitxer: ");
               String fileName = scanner.nextLine();
               currentFile = new File(fileName);
               break;
            case 2:

               if (currentFile != null) {

                  System.out.println(
                        "El fitxer " + currentFile.getName() + " existeix: " + currentFile.exists());
               } else {

                  System.out.println("No s'ha carregat cap fitxer encara.");
               }
               break;
            case 3:

               if (currentFile != null && currentFile.exists()) {

                  System.out.print("Introdueix el nou nom del fitxer: ");
                  String newFileName = scanner.nextLine();
                  File newFile = new File(newFileName);

                  if (currentFile.renameTo(newFile)) {

                     currentFile = newFile;
                     System.out.println("Fitxer renombrat amb èxit.");
                  } else {

                     System.out.println("No s'ha pogut renombrar el fitxer.");
                  }
               } else {

                  System.out.println("No s'ha carregat cap fitxer o el fitxer no existeix.");
               }
               break;
            case 4:

               if (currentFile != null && currentFile.exists()) {

                  if (currentFile.delete()) {

                     System.out.println("Fitxer esborrat amb èxit.");
                     currentFile = null;
                  } else {

                     System.out.println("No s'ha pogut esborrar el fitxer.");
                  }
               } else {

                  System.out.println("No s'ha carregat cap fitxer o el fitxer no existeix.");
               }
               break;
            case 5:

               if (currentFile != null) {

                  System.out.println("Nom del fitxer: " + currentFile.getName());
               } else {

                  System.out.println("No s'ha carregat cap fitxer encara.");
               }
               break;
            case 6:

               if (currentFile != null) {

                  System.out.println("Camí relatiu del fitxer: " + currentFile.getPath());
               } else {

                  System.out.println("No s'ha carregat cap fitxer encara.");
               }
               break;
            case 7:

               if (currentFile != null) {

                  System.out.println("Ruta absoluta del fitxer: " + currentFile.getAbsolutePath());
               } else {

                  System.out.println("No s'ha carregat cap fitxer encara.");
               }
               break;
            case 8:

               System.out.print("Introdueix el nom del directori a crear: ");
               String dirName = scanner.nextLine();
               newDir = new File(dirName);

               if (newDir.mkdir()) {

                  System.out.println("Directori creat amb èxit.");
               } else {

                  System.out.println("No s'ha pogut crear el directori.");
               }
               break;
            case 9:

               if (currentFile != null && currentFile.isDirectory()) {

                  System.out.print("Introdueix el nou nom del directori: ");
                  String newDirName = scanner.nextLine();
                  newDir = new File(currentFile.getParent(), newDirName);

                  if (currentFile.renameTo(newDir)) {

                     currentFile = newDir;
                     System.out.println("Directori renombrat amb èxit.");
                  } else {

                     System.out.println("No s'ha pogut renombrar el directori.");
                  }
               } else {

                  System.out.println("No s'ha carregat cap directori o el directori no existeix.");
               }
               break;
            case 10:

               System.out.print("Introdueix el nom del fitxer: ");
               fileName = scanner.nextLine();
               currentFile = new File(fileName);

               if (currentFile != null && currentFile.isDirectory()) {

                  if (currentFile.delete()) {

                     System.out.println("Directori esborrat amb èxit.");
                     currentFile = null;
                  } else {

                     System.out.println("No s'ha pogut esborrar el directori.");
                  }
               } else {

                  System.out.println("No s'ha carregat cap directori o el directori no existeix.");
               }
               break;
            case 0:

               System.out.println("Adeu!");
               scanner.close();
               System.exit(0);
            default:

               System.out.println("Opció no vàlida. Torna a provar.");
               break;
         }

         Thread.sleep(1000);
      }
   }
}
