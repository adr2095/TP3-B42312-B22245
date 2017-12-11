import java.util.ArrayList;

public class QueryNoCache extends Query {
    protected void run(String searchType, String key) {
        ArrayList<TablaPrueba> tablaPruebas = null; //Lista de valores a devolver.
        String query = ""; //Query para solicitud de datos que no están en caché.
        boolean correct = true;

        if (searchType.compareTo("id") == 0) {
            query = "Select * from TablaPrueba where Codigo = '" + key + "'";
        } else if (searchType.compareTo("txt") == 0) { //Busqueda por texto
            query = "Select * from TablaPrueba where Descripcion like '%" + key + "%'";
        } else {
            System.out.println("Búsqueda inválida. Ingrese 'id' o 'txt'.");
            correct = false;
        }

        if(correct) {
            tablaPruebas = getTuples(query);
            printResults(tablaPruebas, key);
        }
    }
}
