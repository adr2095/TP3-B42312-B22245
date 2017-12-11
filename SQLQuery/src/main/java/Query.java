import java.sql.ResultSet;

public abstract class Query {
    protected Connection connection; //Conexión con BD

    public Query() {
        this.connection = new Connection();
    }

    /**
     * Corre la aplicación SQL.
     * @param searchType tipo de busqueda.
     * @param key valor a buscar
     */
    protected abstract void run(String searchType, String key);

    /**
     * Imprime resultado de las tuplas.
     * @param result Tuplas resultantes.
     * @return 1 si fue exitoso.
     */
    protected boolean printResults(ResultSet result) {
        boolean success = false;

        if(result != null) {
            try {
                while (result.next()) {
                    System.out.println(result.getString(1) + " " + result.getString(2));
                }
                result.beforeFirst();
                success = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }

        return success;
    }

    /*protected void printResults(LinkedList<Tuple> result, String key) {
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
    }*/

    /**
     * Devuelve tuplas según query por parámetro.
     * @param query Consulta sql.
     * @return tuplas resultantes.
     */
    protected ResultSet getTuples(String query) {
        ResultSet resultSet = connection.execQuery(query); //Ejecuta consulta

        return resultSet;
    }

    /*protected LinkedList<Tuple> getTuples(String query) {
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
    }*/
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