 package traking.tools;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.util.List;

import traking.controller.TrackingController;
import traking.model.TrackingModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


public class EnvioDatosJob implements Job {
	
	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();	
	
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
    	
    	List<TrackingModel> datos = null;
    	
        try {
            //Se obtienen datos del controller del servicio REST
            TrackingController trackingController = new TrackingController();
            try {
				datos = trackingController.getTrakings();
			} catch (ParseException e) {
				e.printStackTrace();
			}      
        	
            String json = JSON_MAPPER.writeValueAsString(datos);           

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/cargauy-web/TrackingServlet?action=/track"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode == 200) {
                System.out.println("Los datos se enviaron correctamente al proyecto carga.uy");
            } else {
                System.out.println("Hubo un error al enviar los datos al proyecto carga.uy. Código de respuesta: " + statusCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
