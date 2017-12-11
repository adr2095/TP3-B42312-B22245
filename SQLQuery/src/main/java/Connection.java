import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Connection {
    private String connectionURL;

    public Connection() {
        this.loadClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        this.connectionURL = "jdbc:sqlserver://localhost;database=BD_Universidad;integratedSecurity=true";
    }

    /**
     * Carga el driver class.
     * @param driverClass nombre del class.
     */
    private void loadClass(String driverClass){
        try {
            Class.forName(driverClass);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Ejecuta consulta sql y decvuelve tuplas resultantes.
     * @param query consulta sql.
     * @return tuplas resultantes.
     */
    public ResultSet execQuery(String query)
    {
        ResultSet resultSet = null;
        try {
            java.sql.Connection con = DriverManager.getConnection(this.connectionURL);
            PreparedStatement pStmt = con.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            resultSet = pStmt.executeQuery();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        return resultSet;
    }
}
