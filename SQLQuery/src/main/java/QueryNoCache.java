import java.sql.ResultSet;

public class QueryNoCache extends Query {
    protected void run(String searchType, String key) {
        ResultSet result = null; //Lista de valores a devolver.
        String query = ""; //Query para solicitud de datos que no están en caché.
        boolean correct = true;

        if (searchType.compareTo("id") == 0) {
            query = "Select * from TablaPrueba where Codigo = '" + key + "'";
        } else if (searchType.compareTo("txt") == 0) { //Busqueda por texto
            query = "Select * from TablaPrueba where Descripcion like '%" + key + "%'";
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

        /*Tuple> result = new LinkedList<Tuple>(); //Lista de valores a devolver.
        String query = ""; //Query para solicitud de datos que no están en caché.
        Boolean correct = true;

        if ((cacheType).compareTo("id") == 0) { //Busqueda por ID
                query = "Select * from TablaPrueba where Codigo = '" + key + "'";

        } else if ((cacheType).compareTo("txt") == 0) {
            query = "Select * from TablaPrueba where Descripcion like '%" + key + "%'";
        } else {
            System.out.println("Ingrese tipo de búsqueda: id o txt.");
            correct = false;
        }

        result = getTuples(query);

        //Imprime resultados si los hay.
        if (correct) {
            printResults(result, key);
        }*/
    }
}
