package traking.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import traking.model.TrackingModel;

import traking.model.Coordenadas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
public class TrackingDAO {

	private static long idGlobal = 1L;
	
	
	public List<TrackingModel> read() throws ParseException {
		List<TrackingModel> listado = new ArrayList<TrackingModel>();
		Random random = new Random();	
       
        Date fecha = new Date();
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String timestamp = outputFormat.format(fecha);
        Date date = outputFormat.parse(timestamp);
        
        for (int i = 0; i < 1; i++) {
		    Long id = generarIdUnico();
		    String matricula = generarMatriculaAleatoria();
		    String pais = "Uruguay";
		    Coordenadas coordenadas = generarCoordenadasAleatorias();
		    TrackingModel traking = new TrackingModel(id, matricula, pais, coordenadas, date);
		    listado.add(traking);
		}
		
	    return listado;
	}
	
	private synchronized Long generarIdUnico() {
        idGlobal++; // Incrementar el ID global
        return idGlobal;
    }

    private Coordenadas generarCoordenadasAleatorias() {
    	double latMin = -30.143745;
    	double latMax = -34.868247; 
    	double longMin = -53.829751;
    	double longMax = -57.686301;
    	
    	Random random = new Random();
        double latitud = latMin + (latMax - latMin) * random.nextDouble();
        double longitud = longMin + (longMax - longMin) * random.nextDouble();
        return new Coordenadas(latitud, longitud);
    }
    
    private String generarMatriculaAleatoria() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            char randomChar = (char) (random.nextInt(26) + 'A');
            sb.append(randomChar);
        }
        sb.append(random.nextInt(10));
        sb.append(random.nextInt(10));
        sb.append(random.nextInt(10));
        return sb.toString();
    }
    
}
/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import traking.tools.Conexion;
import org.postgis.Point;
import org.postgis.PGgeometry;
import java.text.ParseException;

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
 */
