package traking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import traking.tools.Conexion;
import traking.model.TrackingModel;
import org.postgis.Point;
import org.postgis.PGgeometry;
import traking.model.Coordenadas;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TrackingDAO {
	
	public List<TrackingModel> read() {
		List<TrackingModel> listado = new ArrayList<TrackingModel>();

	    // Establecer la conexión con la base de datos
	    Conexion dataConexion = new Conexion();
	    Connection connection = dataConexion.getConnection();

	    if (connection != null) {
	        PreparedStatement statement = null;
	        ResultSet resultSet = null;

	        try {
	            // Crear la consulta SQL
	            String query = "SELECT * FROM traking";

	            // Preparar el statement
	            statement = connection.prepareStatement(query);

	            // Ejecutar la consulta
	            resultSet = statement.executeQuery();

	            // Recorrer los resultados y crear instancias de TrackingModel
	            while (resultSet.next()) {
	            	Long id = resultSet.getLong("id");
	            	String matricula = resultSet.getString("matricula");
	                String pais = resultSet.getString("pais");
	                
	                PGgeometry coordenada = (PGgeometry) resultSet.getObject("coordenadas");
	                Point punto = (Point) coordenada.getGeometry();
	                
	                Coordenadas coordenadas = new Coordenadas(punto.getY(), punto.getX());
	               
	                String timestamp = resultSet.getTimestamp("timestamp").toString();	                
	                SimpleDateFormat inputFormat  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                Date date = inputFormat.parse(timestamp);                

	                TrackingModel traking = new TrackingModel(id, matricula, pais, coordenadas, date);
	                listado.add(traking);
	            }
	        } catch (SQLException e) {
	            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
	        } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	            // Cerrar el resultSet, el statement y la conexión
	            try {
	                if (resultSet != null) {
	                    resultSet.close();
	                }
	                if (statement != null) {
	                    statement.close();
	                }
	                dataConexion.closeConnection();
	            } catch (SQLException e) {
	                System.err.println("Error al cerrar el resultSet, el statement o la conexión: " + e.getMessage());
	            }
	        }
	    }

	    return listado;
	}

}
