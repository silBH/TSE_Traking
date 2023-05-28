package traking.controller;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import traking.dao.TrakingDAO;
import traking.model.TrakingModel;

@Path("/system")
public class TrakingController {
	@GET
	@Path("/trak")	
	@Produces(MediaType.APPLICATION_JSON)
	public List<TrakingModel> getTrakings(){
		List<TrakingModel> trak = (new TrakingDAO().read());
		return trak;
	}
	
	@GET
	@Path("/mensaje")
	@Produces(MediaType.TEXT_PLAIN)
	public String mensaje() {
        return "¡Este es un mensaje de prueba!";
    }
}
