package WorkFlow;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Client.DatabaseClient;

@Path("Duplicate")
public class Duplicate {
	
	@POST
	@Path("Check")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response CheckDuplicatePost(@FormParam("phone") String phone) {
		if(phone==null)
			return Response.status(400).entity("Phone number required").build();
		DatabaseClient client = new DatabaseClient("MASTER_DATA");
		if(client.IsInDatabase(phone)) {
			client.closeConnection();
			return Response.status(400).entity("Duplicate Record").build();
		}
		else {
			client.closeConnection();
			return Response.status(200).entity("Unique").build();
		}
	}
	
	@GET
	@Path("Check")
	@Produces(MediaType.TEXT_PLAIN)
	public Response CheckDuplicateGet(@QueryParam("phone") String phone) {
		if(phone==null)
			return Response.status(400).entity("Phone number required").build();
		DatabaseClient client = new DatabaseClient("MASTER_DATA");
		if(client.IsInDatabase(phone)) {
			client.closeConnection();
			return Response.status(400).entity("Duplicate Record").build();
		}
		else {
			client.closeConnection();
			return Response.status(200).entity("Unique").build();
		}
	}
	
	@POST
	@Path("CheckDME")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response CheckDMEDuplicatePost(@FormParam("phone") String phone) {
		if(phone==null)
			return Response.status(400).entity("Phone number required").build();
		DatabaseClient client = new DatabaseClient("MASTER_DME_DATA");
		if(client.IsInDatabase(phone)) {
			client.closeConnection();
			return Response.status(400).entity("Duplicate Record").build();
		}
		else {
			client.closeConnection();
			return Response.status(200).entity("Unique").build();
		}
	}
	
	
	@GET
	@Path("CheckDME")
	@Produces(MediaType.TEXT_PLAIN)
	public Response CheckDMEDuplicateGet(@QueryParam("phone") String phone) {
		if(phone==null)
			return Response.status(400).entity("Phone number required").build();
		DatabaseClient client = new DatabaseClient("MASTER_DME_DATA");
		if(client.IsInDatabase(phone)) {
			client.closeConnection();
			return Response.status(400).entity("Duplicate Record").build();
		}
		else {
			client.closeConnection();
			return Response.status(200).entity("Unique").build();
		}
	}
}
