import java.util.ArrayList;
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
        ArrayList tablaPruebas = null; //Lista de valores a devolver.
        String query = ""; //Query para solicitud de datos que no están en caché.
        boolean correct = true;

        if (searchType.compareTo("id") == 0) { //Busqueda por ID
            tablaPruebas = (ArrayList<TablaPrueba>) cacheID.get(key); //Guarda las tuplas resultantes si se encuentra en en caché

            if (tablaPruebas == null) {//si no hay resultados construye el query.
                query = "Select * from TablaPrueba where Codigo = '" + key + "'";
                tablaPruebas = getTuples(query);

                if (tablaPruebas.size() > 0) {
                    cacheID.put(key, tablaPruebas);
                }
            }
        } else if (searchType.compareTo("txt") == 0) { //Busqueda por texto
            tablaPruebas = (ArrayList<TablaPrueba>) cacheText.get(key);

            if (tablaPruebas == null) {
                query = "Select * from TablaPrueba where Descripcion like '%" + key + "%'";
                tablaPruebas = getTuples(query);

                if (tablaPruebas.size() > 0) {
                    cacheText.put(key, tablaPruebas);
                }
            }
        } else {
            System.out.println("Ingrese tipo de búsqueda: id o txt.");
            correct = false;
        }

        if(correct) {
            tablaPruebas = getTuples(query);
            printResults(tablaPruebas, key);
        }
    }
}










