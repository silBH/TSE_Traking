 package traking.tools;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import traking.controller.TrackingController;
import traking.model.TrackingModel;

public class EnvioDatosJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            //Se obtienen datos del controller del servicio REST
            TrackingController trackingController = new TrackingController();
            List<TrackingModel> datos = trackingController.getTrakings();

            // Crea una solicitud HTTP para enviar los datos al proyecto Jakarta EE
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/cargauy-web/TrackingServlet"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(""))
                    .build();

            // Envía la solicitud y obtinene respuesta
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Verifica el código de respuesta
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
