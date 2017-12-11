import java.util.ArrayList;

public class QueryNoCache extends Query {
    public QueryNoCache(boolean mode){
        this.regularMode = mode;
    }

    protected void run(String searchType, String key) {
        ArrayList<TablaPrueba> tablaPruebas = null; //Lista de valores a devolver.
        String query = ""; //Query para solicitud de datos que no están en caché.

        if (searchType.compareTo("id") == 0) {
            query = "Select * from TablaPrueba where Codigo = '" + key + "'";
        } else { //Busqueda por texto
            query = "Select * from TablaPrueba where Descripcion like '%" + key + "%'";
        }

        tablaPruebas = getTuples(query);
        printResults(tablaPruebas, key);

    }
}
