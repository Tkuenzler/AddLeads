package WorkFlow;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Client.Record;

@Path("AddInternetThings")
public class InternetThings {
	
	@POST
	@Path("AddLead")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddInternetThings(
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
		record.setGender(gender);
		record.setAddress(address);
		record.setCity(city);
		record.setState(state);
		record.setZip(zip);
		record.setEmail(email);
		record.setIp(ip);
		record.setCarrier(insurance_type);
		record.setVendorId("Internet Things");
		if(insurance_type==null)
			return Response.status(400).entity("No Insurance type parameter included").build();
		if(insurance_type.equalsIgnoreCase("Private") ||
			insurance_type.equalsIgnoreCase("Provided by Job") ||
			insurance_type.equalsIgnoreCase("Marketplace") ||
			insurance_type.equalsIgnoreCase("Medicare"))
				return Verify.VerifyInternetThings(record,"Internet Things");
		else if(insurance_type.equalsIgnoreCase("Medicaid") ||
			insurance_type.equalsIgnoreCase("Tricare") ||
			insurance_type.equalsIgnoreCase("No Insurance"))
				return Response.status(400).entity("Invalid Insurance Type "+insurance_type).build();
		else
			return Response.status(400).entity("Unknown Inusrance type").build();
		}
}
