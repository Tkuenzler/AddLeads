package WorkFlow;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Client.Record;

@Path("AddLittleBrook")
public class LittleBrook {

	@POST
	@Path("AddLead")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddLaunchPotato(
			@FormParam("first_name") String first_name,
			@FormParam("last_name") String last_name,
			@FormParam("phone") String phone,
			@FormParam("dob") String dob,
			@FormParam("gender") String gender,
			@FormParam("address") String address,
			@FormParam("city") String city,
			@FormParam("state") String state,
			@FormParam("zip") String zip,
			@FormParam("ip") String ip,
			@FormParam("email") String email,
			@FormParam("insurance_type") String insurance_type,
			@FormParam("subid") String subId) {
		Record record = new Record();
		record.setFirstName(first_name);
		record.setLastName(last_name);
		record.setPhone(phone);
		record.setDob(dob);
		if(gender==null)
			record.setGender("");
		else
			record.setGender(gender);
		record.setAddress(address);
		record.setCity(city);
		record.setState(state);
		record.setZip(zip);
		record.setEmail(email);
		record.setIp(ip);
		record.setCarrier(insurance_type);
		record.setVendorId("LittleBrook");
			return Response.status(400).entity("DENIED").build();
		/*
		if(insurance_type==null)
			return Response.status(400).entity("No Insurance type parameter included").build();
		record.setSubId(insurance_type);
		switch(insurance_type.toUpperCase()) {
			case "PRIVATE INSURANCE":
			case "PROVIDED BY JOB":
			case "MARKETPLACE":
			case "MEDICARE":
				return Verify.VerifyLittleBrook(record,"LittleBrook");
			case "MEDICAID":
			case "TRICARE":
			case "NO INSURANCE":
				return Response.status(400).entity("Invalid Insurance Type "+insurance_type).build();
			default:
				return Response.status(400).entity("Unknown Inusrance type").build();
		}
		*/
	}
	
}
