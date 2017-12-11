import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ArrayList;

public abstract class Query {
    protected Connection connection; //Conexión con BD
    protected boolean regularMode;

    public Query() {
        this.connection = new Connection();
        regularMode = true;
    }

    /**
     * Corre la aplicación SQL.
     * @param searchType tipo de busqueda.
     * @param key valor a buscar
     */
    protected abstract void run(String searchType, String key);

    /**
     * Llama a impirmir resultado de las tuplas si la lista no esta vacía.
     * @param tablaPrueba Tuplas resultantes.
     */
    protected void printResults(ArrayList<TablaPrueba> tablaPrueba, String key) {
        if(regularMode) {
            if (tablaPrueba.size() > 0) {
                System.out.println("\nResultados:\n");
                print(tablaPrueba);
                System.out.println();
            } else {
                System.out.println("No hay resultados para " + key + ".");
            }
        }
    }

    /**
     * Imprime resultado de las tuplas.
     * @param tablaPrueba Tuplas resultantes.
     * @return 1 si fue exitoso.
     */
    protected void print(ArrayList<TablaPrueba> tablaPrueba) {
        Iterator it = tablaPrueba.iterator();
        TablaPrueba temp;

        while (it.hasNext()) {
            temp = (TablaPrueba) it.next();
            System.out.println(temp.getCodigo() + " " + temp.getDescripcion());
        }
    }

    /**
     * Devuelve lista de tuplas según query por parámetro.
     * @param query Consulta sql.
     * @return lista de tuplas mapeadas objeto TablaPrueba.
     */
    protected ArrayList<TablaPrueba> getTuples(String query) {
        ResultSet resultSet = connection.execQuery(query); //Ejecuta consulta
        ArrayList tablaPrueba = new ArrayList();
        int codigo;
        String descripcion;

        try {
            while (resultSet.next()) {
                codigo = Integer.parseInt(resultSet.getString(1));
                descripcion = resultSet.getString(2);
                tablaPrueba.add(new TablaPrueba(codigo,descripcion));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tablaPrueba;
    }

    public void setMode(boolean mode){
        this.regularMode = mode;
    }
}