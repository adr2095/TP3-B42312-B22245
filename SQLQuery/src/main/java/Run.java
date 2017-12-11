import java.util.InputMismatchException;
import java.util.Scanner;

public class Run {

    public Run(){
        this.options();
    }

    private void options() {
        System.out.println("¡Bienvenido!\n");
        Scanner sc = new Scanner(System.in);
        char keepGoing = 'y';
        int version;
        String type;
        String key;
        Query query;

        System.out.print("Ingrese versión a utilizar: \n" +
                "[1] Caché\n"+
                "[2] Sin caché\n" +
                "Cualquier otro número definirá Caché por dafault: ");
        while (!sc.hasNextInt()) {
            System.out.print("Valor ingresado inválido. Ingrese un número: ");
            sc.next(); // this is important!
        }
        version = sc.nextInt();
        System.out.println();

        while (keepGoing == 'y') {
            System.out.print("Ingrese el tipo de búsqueda (id o txt): ");
            type = sc.next();
            System.out.print("Ingrese el valor a buscar: ");
            key = sc.next();
            if(type.toLowerCase().compareTo("id") == 0 || type.toLowerCase().compareTo("txt") == 0) {
                if(version == 1) {
                    query = new QueryCache(type, 2,true);
                } else {
                    query = new QueryNoCache(true);
                }
                query.run(type, key);
            } else {
                System.out.println("Búsqueda inválida. Ingrese 'id' o 'txt'.");
            }

            System.out.print("¿Desea hacer otra busqueda? (Y/N): ");
            keepGoing = sc.next().toLowerCase().charAt(0);
            System.out.println();
        }
    }
}
