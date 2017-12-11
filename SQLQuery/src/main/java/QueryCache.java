import java.util.ArrayList;
import cr.ac.ucr.ecci.ci1310.cache.CacheMemory;
import cr.ac.ucr.ecci.ci1310.cache.algorithm.FirstInFirstOut;
import cr.ac.ucr.ecci.ci1310.cache.algorithm.LastInFirstOut;
import cr.ac.ucr.ecci.ci1310.cache.algorithm.LeastRecentlyUsed;

public class QueryCache extends Query {
    private CacheMemory cacheID;
    private CacheMemory cacheText;

    public QueryCache(String name, int type, boolean mode) {
        this.regularMode = mode;
        if(type == 1) {
            this.cacheID = new LastInFirstOut(10, name);
            this.cacheText = new LastInFirstOut(10, name);
        } else if (type == 2){
            this.cacheID = new FirstInFirstOut(10, name);
            this.cacheText = new FirstInFirstOut(10, name);
        } else {
            this.cacheID = new LeastRecentlyUsed(10, name);
            this.cacheText = new LeastRecentlyUsed(10, name);
        }
    }

    protected void run(String searchType, String key) {
        ArrayList tablaPruebas = null; //Lista de valores a devolver.
        String query = ""; //Query para solicitud de datos que no están en caché.

        if (searchType.compareTo("id") == 0) { //Busqueda por ID
            tablaPruebas = (ArrayList<TablaPrueba>) cacheID.get(key); //Guarda las tuplas resultantes si se encuentra en en caché

            if (tablaPruebas == null) {//si no hay resultados construye el query.
                query = "Select * from TablaPrueba where Codigo = '" + key + "'";
                tablaPruebas = getTuples(query);

                if (tablaPruebas.size() > 0) {
                    printResults(tablaPruebas, key);
                    cacheID.put(key, tablaPruebas);
                }
            } else {
                printResults(tablaPruebas, key);
            }
        } else { //Busqueda por texto
            tablaPruebas = (ArrayList<TablaPrueba>) cacheText.get(key);

            if (tablaPruebas == null) {
                query = "Select * from TablaPrueba where Descripcion like '%" + key + "%'";
                tablaPruebas = getTuples(query);

                if (tablaPruebas.size() > 0) {
                    printResults(tablaPruebas, key);
                    cacheText.put(key, tablaPruebas);
                }
            } else {
                printResults(tablaPruebas, key);
            }
        }
    }
}










