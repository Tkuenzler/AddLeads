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
import Client.EmdeonClient;
import Client.Five9Client;
import Client.RDSClient;
import Client.Record;
import Client.RoadMapClient;
import PBM.InsuranceFilter;
import PBM.InsuranceType;

@Path("AddEngageIQ")
public class EngageIQ {
	
	@POST
	@Path("ChronicPain")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddChronicPain(
			@FormParam("first_name") String first_name,
			@FormParam("last_name") String last_name,
			@FormParam("insurance_type") String insurance_type,
			@FormParam("phone") String phone,
			@FormParam("dob") String dob,
			@FormParam("Gender") String gender,
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
		record.setSubId(subId);
		record.setVendorId("EngageIQ "+insurance_type);
		record.setInsuranceType(insurance_type);
		record.calculateAge();
		switch(insurance_type) {
			case "Private":
				return Verify.VerifyEngageIQPrivateLead(record,"EngageIQ Private");
			case "Provided by Job":
				return Verify.VerifyEngageIQPrivateLead(record,"EngageIQ Provided by Job");
			case "Marketplace":
				return Verify.VerifyEngageIQMedicareLead(record,"EngageIQ Marketplace");
			case "Medicaid-Medicare":
				return Verify.VerifyEngageIQMedicareLead(record,"EngageIQ Medicaid-Medicare");
			case "No Inusrance":
				Five9Client.addToList(record, "EngageIQ No Insurance");
				return Response.status(400).entity("No Insurance").build();
			default:
				DatabaseClient client = new DatabaseClient("MASTER_DATA");
				record.setSubId("EngaeIQ Denied");
				client.addRecord(record);
				client.closeConnection();
				Five9Client.addToList(record, "EngaeIQ Denied");
				return Response.status(400).entity("Invalid Insurance Type").build();		
		}
	}
	
	@POST
	@Path("PainCampaign")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddLead(
			@FormParam("first_name") String first_name,
			@FormParam("last_name") String last_name,
			@FormParam("insurance_type") String insurance_type,
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
		record.setVendorId("EngageIQ "+insurance_type);
		record.setSubId(subId);
		record.setInsuranceType(insurance_type);
		switch(insurance_type) {
			case "Private":
				return Verify.VerifyEngageIQPrivateLead(record, "EngageIQ Private");
			case "Provided by Job":
				return Verify.VerifyEngageIQPrivateLead(record, "EngageIQ Provided by Job");
			case "Medicaid/Medicare":
				return Verify.VerifyEngageIQMedicareLead(record, "EngageIQ Medicaid-Medicare");
			case "Marketplace":
				return Verify.VerifyEngageIQMedicareLead(record, "EngageIQ Marketplace");
			case "No Insurance":
				return Response.status(400).entity("Invalid Insurance").build();
			default:
				return Response.status(400).entity("Invalid Insurance").build();
		}
	}
	
	@POST
	@Path("Arthritis")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddArthritis(
			@FormParam("first_name") String first_name,
			@FormParam("last_name") String last_name,
			@FormParam("insurance_type") String insurance_type,
			@FormParam("phone") String phone,
			@FormParam("dob") String dob,
			@FormParam("Gender") String gender,
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
		record.setVendorId("EngageIQ Arthritis");
		record.setSubId(subId);
		record.setInsuranceType(insurance_type);
		switch(insurance_type) {
			case "Private Insurance":
			case "Provided By Your/Spouse Job":
				break;
			default:
				DatabaseClient client = new DatabaseClient("MASTER_DATA");
				record.setSubId("EngaeIQ Denied");
				client.addRecord(record);
				client.closeConnection();
				Five9Client.addToList(record, "EngaeIQ Denied");
				return Response.status(400).entity("Invalid Insurance Type").build();
		}
		return Verify.VerifyEngageIQPrivateLead(record,"EngageIQ Arthritis");
	}
	
	
	@POST
	@Path("Neuropathy")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddNeuropathy(
			@FormParam("first_name") String first_name,
			@FormParam("last_name") String last_name,
			@FormParam("insurance_type") String insurance_type,
			@FormParam("phone") String phone,
			@FormParam("dob") String dob,
			@FormParam("Gender") String gender,
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
		record.setVendorId("EngageIQ Neuropathy");
		record.setSubId(subId);
		record.setInsuranceType(insurance_type);
		switch(insurance_type) {
		case "Private Insurance":
		case "Provided By Your/Spouse Job":
			break;
		default:
			DatabaseClient client = new DatabaseClient("MASTER_DATA");
			record.setSubId("EngaeIQ Denied");
			client.addRecord(record);
			client.closeConnection();
			Five9Client.addToList(record, "EngaeIQ Denied");
			return Response.status(400).entity("Invalid Insurance Type").build();
	}
		return Verify.VerifyEngageIQPrivateLead(record,"EngageIQ Neuropathy");
	}
	
	
	@GET
	@Path("AddTelmed")
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddTelmed(@QueryParam("first_name") String first_name,
			@QueryParam("last_name") String last_name,
			@QueryParam("phone") String phone,
			@QueryParam("dob") String dob,
			@QueryParam("gender") String gender,
			@QueryParam("address") String address,
			@QueryParam("city") String city,
			@QueryParam("state") String state,
			@QueryParam("zip") String zip,
			@QueryParam("ip") String ip,
			@QueryParam("email") String email,
			@QueryParam("insurance_type") String insurance_type,
			@QueryParam("pain_location") String pain_location,
			@QueryParam("pain_type") String pain_type,
			@QueryParam("pain_level") String pain_level,
			@QueryParam("skin") String skin,
			@QueryParam("podiatry") String podiatry,
			@QueryParam("metabolic") String metabolic,
			@QueryParam("migraines") String migraines,
			@QueryParam("subid") String subId) {
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
		record.setSubId(subId);
		if(!ValidInsuranceType(insurance_type))
			return Verify.VerifyEngageIQMedicareLead(record, "EngageIQ Medicaid-Medicare");
		if(!PainLocation(pain_location)) 
			return Verify.VerifyEngageIQMedicareLead(record, "EngageIQ Medicaid-Medicare");
		if(!PainType(record,pain_type)) 
			return Verify.VerifyEngageIQMedicareLead(record, "EngageIQ Medicaid-Medicare");
		if(!PainLevel(pain_level))
			return Verify.VerifyEngageIQMedicareLead(record, "EngageIQ Medicaid-Medicare");
		if(!CheckState(state))
			return Verify.VerifyEngageIQMedicareLead(record, "EngageIQ Medicaid-Medicare");
		EmdeonClient client = new EmdeonClient();
		client.login("rxcg", "pharmacy123", "1619320132");
		client.fillOutForm(record);
		String type = InsuranceFilter.Filter(record);
		String product_suggestions = "General_Pain_Muscle_Relaxers_MTK";
		if(Boolean.parseBoolean(skin))
			product_suggestions += ",DrySkin_MTK";
		if(Boolean.parseBoolean(podiatry))
			product_suggestions += ",Podiatry_MTK";
		if(Boolean.parseBoolean(metabolic))
			product_suggestions += ",Metabolic_MTK";
		if(Boolean.parseBoolean(migraines))
			product_suggestions += "Migrances_MTK";
		RoadMapClient map = new RoadMapClient();
		switch(type) {
			case InsuranceType.PRIVATE_UNKNOWN:
				if(map.isInRoadMap(record, "Tristar")==1) {
					map.close();
					return RDSClient.AddLead(record, product_suggestions,pain_level, pain_location, pain_type);
				}
			case InsuranceType.PRIVATE_VERIFIED:
				if(map.isInRoadMap(record, "Tristar")==1) {
					map.close();
					return RDSClient.AddLead(record, product_suggestions,pain_level, pain_location, pain_type);
				}
				else if(map.isInRoadMap(record, "MD_Topical")==1) {
					map.close();
					return RDSClient.AddLead(record, product_suggestions,pain_level, pain_location, pain_type);
				}
				else if(map.isInRoadMap(record, "360")==1) {
					map.close();
					return RDSClient.AddLead(record, product_suggestions,pain_level, pain_location, pain_type);
				}
				else if(map.isInRoadMap(record, "Medcore")==1) {
					map.close();
					return RDSClient.AddLead(record, product_suggestions,pain_level, pain_location, pain_type);
				}
				else if(map.isInRoadMap(record, "MRX")==1) {
					map.close();
					return RDSClient.AddLead(record, product_suggestions,pain_level, pain_location, pain_type);
				}
			case InsuranceType.MEDICARE_TELMED:
				if(map.isInRoadMap(record, "MD_Topical")==1) {
					map.close();
					return RDSClient.AddLead(record, product_suggestions,pain_level, pain_location, pain_type);
				}
				else if(map.isInRoadMap(record, "360")==1) {
					map.close();
					return RDSClient.AddLead(record, product_suggestions,pain_level, pain_location, pain_type);
				}
				else if(map.isInRoadMap(record, "MRX")==1) {
					map.close();
					return RDSClient.AddLead(record, product_suggestions,pain_level, pain_location, pain_type);
				}
			default:
				return Verify.VerifyEngageIQMedicareLead(record, "EngageIQ Medicaid-Medicare");
		}
	}
	private boolean CheckState(String state) {
		switch(state) {
			case "AL":
			case "AZ":
			case "AR":
			case "CO":
			case "CT":
			case "DE":
			case "FL":
			case "GA":
			case "ID":
			case "IL":
			case "IN":
			case "IA":
			case "KS":
			case "KY":
			case "LA":
			case "ME":
			case "MD":
			case "MA":
			case "MI":
			case "MN":
			case "MS":
			case "MO":
			case "NE":
			case "NV":
			case "NJ":
			case "NM":
			case "NY":
			case "ND":
			case "OH":
			case "OK":
			case "PA":
			case "RI":
			case "SD":
			case "TN":
			case "TX":
			case "UT":
			case "VA":
			case "WA":
			case "WV":
			case "WI":
			case "WY":
			case "NC":
				return true;
			default:
				return false;
		}
	}
	private boolean PatientInterested(String interested) {
		if(interested.equalsIgnoreCase("YES"))
			return true;
		else 
			return false;
	}
	private boolean ValidInsuranceType(String insurance_type) {
		switch(insurance_type) {
			case "Private Insurance":
			case "Provided by job":
				return true;
		default:
			return false;
		}
	}
	private boolean PainLocation(String pain_location) {
		switch(pain_location) {
			case "No Pain":
				return false;
			case "Neck":
			case "Back":
			case "Lower Back":
			case "Arms":
			case "Legs":
			case "Feet":
				return true;
			default:
				return false;
		}
	}
	private boolean PainType(Record record,String pain_type) {
		switch(pain_type.toUpperCase()) {
			case "NO PAIN":
			case "MINOR PAIN":
				return false;
			case "CHRONIC PAIN":
			case "ACUTE PAIN":
			case "NERVE PAIN":
			case "BONE PAIN":
				return true;
			default:
				add(record);
				return false;
		}
	}
	private boolean PainLevel(String pain_level) {
		switch(pain_level) {
			case "0":
			case "1":
			case "2":
			case "3":
			case "4":
				return false;
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
			case "10":
				return true;
			default:
				return false;
		}
	}
	private void add(Record record) {
		DatabaseClient client = new DatabaseClient("BAD_TELMED");
		client.addRecord(record);
		client.closeConnection();
		Five9Client.addToList(record, "BAD_TELMED");		
	}
}
