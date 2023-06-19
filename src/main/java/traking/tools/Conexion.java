package traking.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
	//private static final String URL = "jdbc:postgresql://node3316-cargauy-tse.web.elasticloud.uy/Traking";
	private static final String URL = "jdbc:postgresql://10.1.4.37:5432/Traking";
    private static final String USERNAME = "webadmin";
    private static final String PASSWORD = "zYXcxpnQ3t";

    private Connection connection;

    public Conexion() {
        try {
        	Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada correctamente");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
