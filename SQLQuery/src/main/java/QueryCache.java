import java.sql.ResultSet;
import cr.ac.ucr.ecci.ci1310.cache.CacheMemory;
import cr.ac.ucr.ecci.ci1310.cache.algorithm.LastInFirstOut;

public class QueryCache extends Query {
    private CacheMemory cacheID;
    private CacheMemory cacheText;
    private boolean needQuery;

    public QueryCache() {
        this.cacheID = new LastInFirstOut();
        this.cacheText = new LastInFirstOut();
        this.needQuery = false;
    }

    protected void run(String searchType, String key) {
        ResultSet result = null; //Lista de valores a devolver.
        String query = ""; //Query para solicitud de datos que no están en caché.
        boolean correct = true;

        if (searchType.compareTo("id") == 0) { //Busqueda por ID
            result = (ResultSet) cacheID.get(key); //Guarda las tuplas resultantes si se encuentra en en caché

            if (result == null) {//si no hay resultados construye el query.
                query = "Select * from TablaPrueba where Codigo = '" + key + "'";
                result = getTuples(query);

                if (result != null) {
                    cacheID.put(key, result);
                }
            }
        } else if (searchType.compareTo("txt") == 0) { //Busqueda por texto
            result = (ResultSet) cacheText.get(key);

            if (result == null) {
                query = "Select * from TablaPrueba where Descripcion like '%" + key + "%'";
                result = getTuples(query);

                if (result != null) {
                    cacheText.put(key, result);
                }
            }
        } else {
            System.out.println("Ingrese tipo de búsqueda: id o txt.");
            correct = false;
        }

        if(correct) {
            result = getTuples(query);

            if(!printResults(result)) {
                System.out.println("No hay resultados para " + key + ".");
            }
        }
    }
}
        /*LinkedList<Tuple> result = new LinkedList<Tuple>(); //Lista de valores a devolver.
        String query = ""; //Query para solicitud de datos que no están en caché.
        Boolean correct = true;

        if ((cacheType).compareTo("id") == 0) { //Busqueda por ID
            result = (LinkedList<Tuple>) cacheID.get(key); //Guarda las tuplas resultantes si se encuentra en en caché

            if (result == null) {//si no hay resultados construye el query.
                query = "Select * from TablaPrueba where Codigo = '" + key + "'";
                result = getTuples(query);

                if (result.size() > 0) {
                    cacheID.put(key, result);
                }
            }
        } else if ((cacheType).compareTo("txt") == 0) { //Busqueda por texto
            result = (LinkedList<Tuple>) cacheText.get(key);

            if (result == null) {
                query = "Select * from TablaPrueba where Descripcion like '%" + key + "%'";
                result = getTuples(query);

                if (result.size() > 0) {
                    cacheText.put(key, result);
                }
            }
        } else {
            System.out.println("Ingrese tipo de búsqueda: id o txt.");
            correct = false;
        }

        //Imprime resultados si los hay.
        if (correct) {
            printResults(result, key);
        }
    }*/


/*import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import cr.ac.ucr.ecci.ci1310.cache.CacheMemory;
import cr.ac.ucr.ecci.ci1310.cache.algorithm.LastInFirstOut;

public class Query {
    Connection connection;
    CacheMemory cacheID;
    CacheMemory cacheText;
    boolean needQuery;

    public Query(String args[]) {
        this.connection = new Connection();
        this.cacheID = new LastInFirstOut();
        this.cacheText = new LastInFirstOut();
        this.needQuery = false;
        this.run(args);
    }


private void run(String args[]) {
    if(args.length == 2) { //Verifica que hayan 2 argumentos
        //String cacheType = args[0].toLowerCase(); //define tipo de cache según tipo de búsqueda enviada por parámetro
        //String key = args[1]; //guarda valor por el que se buscará
        //LinkedList<ResultSet> result = new LinkedList<ResultSet>(); //Lista de valores a devolver.
        //String query = ""; //Query para solicitud de datos que no están en caché.

        if((cacheType).compareTo("id") == 0) { //Busqueda por ID
            result = (LinkedList<ResultSet>)cacheID.get(key); //Guarda las tuplas resultantes si se encuentra en en caché

            if (result == null) {//si no hay resultados construye el query.
                query = "Select * from TablaPrueba where Codigo = '" + key + "'";
                result = getTuples(query);
                try {
                    result.element().beforeFirst();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(result != null) {
                    cacheID.put(key, result);
                }
            }
        }
        else if ((cacheType).compareTo("txt") == 0){ //Busqueda por texto
            result = (LinkedList<ResultSet>)cacheText.get(key);

            if (result == null) {
                query = "Select * from TablaPrueba where Descripcion like '%" + key + "%'";
                result = getTuples(query);

                if(result != null) {
                    cacheText.put(key, result);
                }
            }
        } else {
            System.out.println("Ingrese tipo de búsqueda: id o txt.");
            System.exit(1);
        }

        //Imprime resultados si los hay.
        if(result != null) {
            Iterator it = result.iterator();

            try {
                ResultSet set = ((ResultSet) it.next());
                set.beforeFirst();
                System.out.println(set.getString(1));
                while (it.hasNext()) {
                    System.out.println(set.getString(1));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        } else {
            System.out.println("No hay resultados para " + key + ".");
        }
    } else {
        System.out.println("Ingrese tipo de búsqueda (id o txt) y valor a buscar.");
    }
}


    private LinkedList<ResultSet> getTuples(String query) {
        LinkedList<ResultSet> tuples = new LinkedList<ResultSet>();
        ResultSet resultSet = connection.execQuery(query); //Ejecuta consulta


        if (resultSet != null) { //Si existen tuplas las guarda en lista y las devuelve.
            try {

                while (resultSet.next()) {
                    tuples.add(resultSet);
                }
                resultSet.beforeFirst();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }

        return tuples;
    }














    import java.sql.ResultSet;
import java.util.Scanner;
import java.util.Iterator;
import java.util.LinkedList;
import cr.ac.ucr.ecci.ci1310.cache.CacheMemory;
import cr.ac.ucr.ecci.ci1310.cache.algorithm.LastInFirstOut;

public class QueryCache extends Query{
    Connection connection;
    CacheMemory cacheID;
    CacheMemory cacheText;
    boolean needQuery;

    public QueryCache() {
        this.connection = new Connection();
        this.cacheID = new LastInFirstOut();
        this.cacheText = new LastInFirstOut();
        this.needQuery = false;
        this.options();
    }

    private void options() {
        System.out.println("¡Bienvenido!");
        Scanner sc = new Scanner(System.in);
        int keepGoing = 1;
        String type;
        String key;

        while (keepGoing == 1) {
            System.out.print("Ingrese el tipo de búsqueda (id o txt): ");
            type = sc.next();
            System.out.print("Ingrese el valor a buscar: ");
            key = sc.next();

            this.run(type, key);

            System.out.println("¿Desea hacer otra busqueda? 1 para continuar, otro número para salir.");
            keepGoing = sc.nextInt();
        }

    }
private void run(String cacheType, String key) {
    LinkedList<Tuple> result = new LinkedList<Tuple>(); //Lista de valores a devolver.
    String query = ""; //Query para solicitud de datos que no están en caché.
    Boolean correct = true;

    if ((cacheType).compareTo("id") == 0) { //Busqueda por ID
        result = (LinkedList<Tuple>) cacheID.get(key); //Guarda las tuplas resultantes si se encuentra en en caché

        if (result == null) {//si no hay resultados construye el query.
            query = "Select * from TablaPrueba where Codigo = '" + key + "'";
            result = getTuples(query);

            if (result.size() > 0) {
                cacheID.put(key, result);
            }
        }
    } else if ((cacheType).compareTo("txt") == 0) { //Busqueda por texto
        result = (LinkedList<Tuple>) cacheText.get(key);

        if (result == null) {
            query = "Select * from TablaPrueba where Descripcion like '%" + key + "%'";
            result = getTuples(query);

            if (result.size() > 0) {
                cacheText.put(key, result);
            }
        }
    } else {
        System.out.println("Ingrese tipo de búsqueda: id o txt.");
        correct = false;
    }

    //Imprime resultados si los hay.
    if(correct) {
        if (result.size() > 0) {
            System.out.println("Resultados:\n");
            Iterator it = result.iterator();
            Tuple tuple;
            while (it.hasNext()) {
                tuple = (Tuple) it.next();
                System.out.println(tuple.getCodigo() + " " + tuple.getDescripcion());
            }
        } else {
            System.out.println("No hay resultados para " + key + ".");
        }
    }
}

    private LinkedList<Tuple> getTuples(String query) {
        LinkedList<Tuple> tuples = new LinkedList<Tuple>();
        ResultSet resultSet = connection.execQuery(query); //Ejecuta consulta

        if (resultSet != null) { //Si existen tuplas las guarda en lista y las devuelve.
            try {
                Tuple tuple;

                while (resultSet.next()) {

                    tuples.add(new Tuple(resultSet.getInt("Codigo"), resultSet.getString("Descripcion")));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }

        return tuples;
    }
}

/*import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import cr.ac.ucr.ecci.ci1310.cache.CacheMemory;
import cr.ac.ucr.ecci.ci1310.cache.algorithm.LastInFirstOut;

public class Query {
    Connection connection;
    CacheMemory cacheID;
    CacheMemory cacheText;
    boolean needQuery;

    public Query(String args[]) {
        this.connection = new Connection();
        this.cacheID = new LastInFirstOut();
        this.cacheText = new LastInFirstOut();
        this.needQuery = false;
        this.run(args);
    }


private void run(String args[]) {
    if(args.length == 2) { //Verifica que hayan 2 argumentos
        //String cacheType = args[0].toLowerCase(); //define tipo de cache según tipo de búsqueda enviada por parámetro
        //String key = args[1]; //guarda valor por el que se buscará
        //LinkedList<ResultSet> result = new LinkedList<ResultSet>(); //Lista de valores a devolver.
        //String query = ""; //Query para solicitud de datos que no están en caché.

        if((cacheType).compareTo("id") == 0) { //Busqueda por ID
            result = (LinkedList<ResultSet>)cacheID.get(key); //Guarda las tuplas resultantes si se encuentra en en caché

            if (result == null) {//si no hay resultados construye el query.
                query = "Select * from TablaPrueba where Codigo = '" + key + "'";
                result = getTuples(query);
                try {
                    result.element().beforeFirst();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(result != null) {
                    cacheID.put(key, result);
                }
            }
        }
        else if ((cacheType).compareTo("txt") == 0){ //Busqueda por texto
            result = (LinkedList<ResultSet>)cacheText.get(key);

            if (result == null) {
                query = "Select * from TablaPrueba where Descripcion like '%" + key + "%'";
                result = getTuples(query);

                if(result != null) {
                    cacheText.put(key, result);
                }
            }
        } else {
            System.out.println("Ingrese tipo de búsqueda: id o txt.");
            System.exit(1);
        }

        //Imprime resultados si los hay.
        if(result != null) {
            Iterator it = result.iterator();

            try {
                ResultSet set = ((ResultSet) it.next());
                set.beforeFirst();
                System.out.println(set.getString(1));
                while (it.hasNext()) {
                    System.out.println(set.getString(1));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        } else {
            System.out.println("No hay resultados para " + key + ".");
        }
    } else {
        System.out.println("Ingrese tipo de búsqueda (id o txt) y valor a buscar.");
    }
}


    private LinkedList<ResultSet> getTuples(String query) {
        LinkedList<ResultSet> tuples = new LinkedList<ResultSet>();
        ResultSet resultSet = connection.execQuery(query); //Ejecuta consulta


        if (resultSet != null) { //Si existen tuplas las guarda en lista y las devuelve.
            try {

                while (resultSet.next()) {
                    tuples.add(resultSet);
                }
                resultSet.beforeFirst();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }

        return tuples;
    }
}*/

