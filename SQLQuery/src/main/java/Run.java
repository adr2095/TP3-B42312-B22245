import java.util.Scanner;

public class Run {

    public Run(){
        this.options();
    }

    private void options() {
        System.out.println("¡Bienvenido!");
        Scanner sc = new Scanner(System.in);
        int keepGoing = 1;
        int version;
        String type;
        String key;
        Query query = new QueryCache();

        System.out.print("Ingrese versión a utilizar: \n" +
                "[1] Caché\n"+
                "[2] Sin caché\n" +
                "Cualquier otro valor definira Caché por dafault: ");
        version = sc.nextInt();

        while (keepGoing == 1) {
            System.out.print("Ingrese el tipo de búsqueda (id o txt): ");
            type = sc.next();
            System.out.print("Ingrese el valor a buscar: ");
            key = sc.next();


            if(version == 2) {
                query = new QueryNoCache();
            }
            query.run(type, key);

            System.out.println("¿Desea hacer otra busqueda? 1 para continuar, otro número para salir.");
            keepGoing = sc.nextInt();
        }
    }
}
