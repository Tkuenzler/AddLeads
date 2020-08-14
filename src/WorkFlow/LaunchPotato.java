package WorkFlow;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Client.Record;

@Path("AddLaunchPotato")
public class LaunchPotato {

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
		record.setGender(gender);
		record.setAddress(address);
		record.setCity(city);
		record.setState(state);
		record.setZip(zip);
		record.setEmail(email);
		record.setIp(ip);
		record.setCarrier(insurance_type);
		record.setVendorId("LaunchPotato");
		if(insurance_type==null)
			return Response.status(400).entity("No Insurance type parameter included").build();
		switch(insurance_type) {
			case "Private":
			case "Provided by Job":
			case "Marketplace":
			case "Medicare":
				return Verify.VerifyLaunchPotato(record,"Launch Potato");
			case "Medicaid":
			case "Tricare":
			case "No Insurance":
				return Response.status(400).entity("Invalid Insurance Type "+insurance_type).build();
			default:
				return Response.status(400).entity("Unknown Inusrance type").build();
		}
		
		
	}
	@POST
	@Path("AddLeadAftetHours")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddLaunchPotatoAfterHours(
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
			@FormParam("insurance_type") String insurance_type,
			@FormParam("email") String email) {
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
		record.setVendorId("LaunchPotato After Hours");
		switch(insurance_type) {
			case "Private":
			case "Provided by Job":
			case "Marketplace":
			case "Medicare":
				return Verify.VerifyLaunchPotato(record,"LaunchPotato After Hours");
			case "Medicaid":
			case "Tricare":
			case "No Inusrance":
				return Response.status(400).entity("Invalid Insurance Type "+insurance_type).build();
			default:
				return Response.status(400).entity("Unknown Inusrance type").build();
		}
	}

	@POST
	@Path("AddLead2")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddLaunchPotato2(
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
		record.setVendorId("LaunchPotato2");
		if(insurance_type==null)
			return Response.status(400).entity("No Insurance type parameter included").build();
		switch(insurance_type) {
			case "Private":
			case "Provided by Job":
			case "Marketplace":
			case "Medicare":
				return Verify.VerifyLaunchPotato2(record,"LaunchPotato2");
			case "Medicaid":
			case "Tricare":
			case "No Insurance":
				return Response.status(400).entity("Invalid Insurance Type "+insurance_type).build();
			default:
				return Response.status(400).entity("Unknown Inusrance type").build();
		}
		
		
	}
	@POST
	@Path("AddLeadAftetHours2")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddLaunchPotatoAfterHours2(
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
			@FormParam("insurance_type") String insurance_type,
			@FormParam("email") String email) {
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
		record.setVendorId("LaunchPotato2 After Hours");
		switch(insurance_type) {
			case "Private":
			case "Provided by Job":
			case "Marketplace":
			case "Medicare":
				return Verify.VerifyLaunchPotato2(record,"LaunchPotato2 After Hours");
			case "Medicaid":
			case "Tricare":
			case "No Inusrance":
				return Response.status(400).entity("Invalid Insurance Type "+insurance_type).build();
			default:
				return Response.status(400).entity("Unknown Inusrance type").build();
		}
	}
	
	@POST
	@Path("AddDME")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddLaunchPotatoDME(
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
			@FormParam("carrier") String carrier,
			@FormParam("insurance_type") String insurance_type,
			@FormParam("email") String email) {
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
		record.setCarrier(carrier);
		record.setVendorId("LaunchPotato DME");
		switch(carrier.toUpperCase()) {
			case "AARP":
			case "AETNA":
			case "UNITED HEALTHCARE":
			case "HUMANA":
				break;
			default:
				return Response.status(400).entity("CANT ACCEPT THIS INSURANCE "+carrier).build();
		}
		switch(insurance_type.toUpperCase()) {
			case "PPO":
				return Verify.VerifyDME(record,"LaunchPotato DME");
			case "HMO":
				return Response.status(400).entity("CANT ACCEPT HMO").build();
			default:
				return Response.status(400).entity("Unknown Inusrance type").build();
		}
	}
	
	@POST
	@Path("AddCommercial")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddLaunchPotatoCommercial(
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
			@FormParam("insurance_type") String insurance_type,
			@FormParam("email") String email) {
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
		record.setVendorId("LaunchPotato Private Insurance");
		switch(insurance_type.toUpperCase()) {
			case "PRIVATE":
			case "MARKETPLACE":
				return Verify.VerifyLaunchPotatoPrivateInsurance(record,"LaunchPotato Private Insurance");
			case "MEDICARE":
			case "MEDICAID":
			case "TRICARE":
			case "NO INSURANCE":
				return Response.status(400).entity("Invalid Insurance Type "+insurance_type).build();
			default:
				return Response.status(400).entity("Unknown Inusrance type").build();
		}
		
	}
	
}
