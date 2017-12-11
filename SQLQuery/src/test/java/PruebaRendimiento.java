import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

public class PruebaRendimiento {
    private String idArray[];
    private String textArray[];
    private String words[];
    private Random random;
    private long clock;
    private Query query;
    private long differences[][];
    private int row;

    public PruebaRendimiento() {
        this.idArray = new String[1000];
        this.textArray = new String[1000];
        this.words = new String[]{"carro", "perro", "casa", "celular", "pasta", "estuche", "mueble", "ipod", "equipo", "botella", "plato", "cuaderno", "cable", "televisor", "circunstancia", "pizarra", "pared", "java", "objeto", "sistemas", "operativos", "investigación", "camisa", "pantalón", "juego", "café", "máquina", "piso", "fresco", "hoja"};
        this.random = new Random();
        this.differences = new long[4][2];
        this.row = 0;
        this.generateIDKey();
        this.generateTextKey();
        this.run();
    }

    private void generateIDKey(){
        for(int i = 0; i < idArray.length; ++i) {
            idArray[i] = Integer.toString(random.nextInt(9999));
        }
    }

    private void generateTextKey(){
        for(int i = 0; i < textArray.length; ++i) {
            textArray[i] = words[random.nextInt(30)];
        }
    }

    private void runQueryNoCacheTest() {
        query = new QueryNoCache(false);

        System.out.println("Consulta por ID");
        runIDQuery(query);

        System.out.println("Consulta por texto");
        runTextQuery(query);

        ++this.row;
    }

    private void runQueryCacheTest(){
        System.out.println("[LIFO]");
        System.out.println("Consulta por ID");
        query = new QueryCache("Cache por id", 1, false);
        runIDQuery(query);
        System.out.println("Consulta por Texto");
        query = new QueryCache("Cache por texto", 1, false);
        runTextQuery(query);
        ++this.row;

        System.out.println("[FIFO]");
        System.out.println("Consulta por ID");
        query = new QueryCache("Cache por id", 2, false);
        runIDQuery(query);
        System.out.println("Consulta por Texto");
        query = new QueryCache("Cache por texto", 2, false);
        runTextQuery(query);
        ++this.row;

        System.out.println("[LRU]");
        System.out.println("Consulta por ID");
        query = new QueryCache("Cache por id", 3, false);
        runIDQuery(query);
        System.out.println("Consulta por Texto");
        query = new QueryCache("Cache por texto", 3,false);
        runTextQuery(query);
        ++this.row;
    }

    private void runIDQuery(Query query) {
        clock  = System.nanoTime();
        for (int i = 0; i < idArray.length; ++i) {
            query.run("id", idArray[i]);
        }
        printResults(System.nanoTime() - clock);
        this.differences[row][0] = System.nanoTime() - clock;
    }

    private void runTextQuery(Query query) {
        clock  = System.nanoTime();
        for (int i = 0; i < textArray.length; ++i) {
            query.run("txt", textArray[i]);
        }
        printResults(System.nanoTime() - clock);
        this.differences[row][1] = System.nanoTime() - clock;
    }

    private void printResults(long clock){
        System.out.println("Tiempo total de ejecución: " + clock + "\n");
    }

    private void getFinalResults() {
        long idTime = differences[0][0];
        long txtTime = differences[0][1];

        System.out.println("Resultados finales: \n");
        System.out.println("Búsqueda de ID sin caché:");
        System.out.println("Contra LIFO: " + (idTime - differences[1][0]));
        System.out.println("Contra FIFO: " + (idTime - differences[2][0]));
        System.out.println("Contra LRU: " + (idTime - differences[3][0]));

        System.out.println("\nBúsqueda de texto sin caché:");
        System.out.println("Contra LIFO: " + (txtTime - differences[1][1]));
        System.out.println("Contra FIFO: " + (txtTime - differences[2][1]));
        System.out.println("Contra LRU: " + (txtTime - differences[3][1]));
    }

    private void run(){
        System.out.println("Prueba 1. Consultas sin caché.\n");
        this.runQueryNoCacheTest();

        System.out.println("Prueba 2. Consultas con caché.\n");
        this.runQueryCacheTest();

        this.getFinalResults();
    }
}
