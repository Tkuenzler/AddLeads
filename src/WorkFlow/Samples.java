package WorkFlow;

import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Client.DatabaseClient;
import Client.Five9Client;
import Client.Record;

@Path("AddSamples")
public class Samples {

	@POST
	@Path("ChronicPain")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddSamplesPain(
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
		record.setVendorId("Samples.com Pain");
		record.setSubId(subId);
		record.setId(first_name+last_name+phone);
		if(cstdf(record))
			return Response.status(400).entity("Bad Phonenumber").build();
		else
			return Verify.VerifySamplesLead(record,"Samples.com");
	}
	@POST
	@Path("Neuropathy")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddNeuropathy(
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
		record.setVendorId("Samples Diabeitc Neuropathy");
		record.setSubId(subId);
		record.setId(first_name+last_name+phone);
		if(cstdf(record))
			return Response.status(400).entity("Bad Phonenumber").build();
		else
			return Verify.VerifySamplesLead(record,"Samples Diabeitc Neuropathy");
	}
	@POST
	@Path("ChronicPainAfterHours")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddChronicPainAfterHours(
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
		record.setVendorId("Samples.com After Hours");
		record.setSubId(subId);
		record.setId(first_name+last_name+phone);
		if(cstdf(record))
			return Response.status(400).entity("Bad Phonenumber").build();
		else
			return Verify.VerifySamplesLead(record,"Samples.com After Hours");
	}
	@POST
	@Path("NeuropathyAfterHours")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddNeuropathyAfterHours(
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
		record.setVendorId("Samples Diabeitc Neuropathy After Hours");
		record.setSubId(subId);
		record.setId(first_name+last_name+phone);
		if(cstdf(record))
			return Response.status(400).entity("Bad Phonenumber").build();
		else
			return Verify.VerifySamplesLead(record,"Samples Diabeitc Neuropathy After Hours");
	}
	private boolean cstdf(Record record) {
		Random rand = new Random();
		int random = rand.nextInt(99)+1;
		if(random>=75) {
			add(record);
			return true;
		}
		else
			return false;
	}
	private void add(Record record) {
		DatabaseClient client = new DatabaseClient("MASTER_DATA");
		client.addRecord(record);
		client.closeConnection();
		Five9Client.addToList(record, "Vista");
			
	}
}
