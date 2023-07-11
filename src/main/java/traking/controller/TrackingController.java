package traking.controller;

import java.text.ParseException;
import java.util.List;

import org.quartz.SchedulerException;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import traking.dao.TrackingDAO;
import traking.model.TrackingModel;
import traking.tools.EnvioPrograma;

@Path("/system")
public class TrackingController {
	@GET
	@Path("/track")	
	@Produces(MediaType.APPLICATION_JSON)
	public List<TrackingModel> getTrakings() throws ParseException{
		List<TrackingModel> trak = (new TrackingDAO().read());
		return trak;
	}
	
	@GET
    @Path("/start")
    public Response startSendingData() throws ParseException {
        EnvioPrograma envioPrograma = new EnvioPrograma();
        try {
            envioPrograma.startSendingData();
            return Response.ok("Envio de datos iniciado.").build();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
	
	@GET
    @Path("/stop")
    public Response stopSendingData() {
        EnvioPrograma envioPrograma = new EnvioPrograma();
        try {
            envioPrograma.stopSendingData();
            return Response.ok("Envio de datos detenido.").build();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
	
	@GET
	@Path("/mensaje")
	@Produces(MediaType.TEXT_PLAIN)
	public String mensaje() {
        return "¡Este es un mensaje de prueba!";
    }
	
	
}
