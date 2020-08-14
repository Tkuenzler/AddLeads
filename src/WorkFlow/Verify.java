package WorkFlow;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.ws.rs.core.Response;

import Client.DatabaseClient;
import Client.Five9Client;
import Client.InfoDatabase;
import Client.Record;
import Client.ViciClient;

public class Verify {
	public static Response VerifyDME(Record record, String list) {
		if(!OverAge(record.getDob(),65))
			return Response.status(400).entity("Age Failed").build();
		if(!VerifyDMEState(record.getState()))
			return Response.status(400).entity("Invalid State "+record.getState()).build();
		if(!GoodPhoneNumber(record.getPhone()))
			return Response.status(400).entity("Invalid Phone Number").build();
		if(!GoodAreaCode(record.getPhone()))
			return Response.status(400).entity("Invalid Area Code").build();
		if(!CheckFirstName(record.getFirstName()))
			return Response.status(400).entity("Invlaid First Name").build();
		if(!CheckLastName(record.getLastName()))
			return Response.status(400).entity("Invlaid Last Name").build();
		return IsInDatabaseFive9(record,list,"MASTER_DME_DATA");	
	}
	public static Response VerifyLittleBrook(Record record,String list) {
		if(!UnderAge(record.getDob(),75))
			return Response.status(400).entity("Age Failed").build();
		if(!OverAge(record.getDob(),30))
			return Response.status(400).entity("Age Failed").build();
		if(!VerifyState(record.getState()))
			return Response.status(400).entity("Invalid State "+record.getState()).build();
		if(!GoodPhoneNumber(record.getPhone()))
			return Response.status(400).entity("Invalid Phone Number").build();
		if(!GoodAreaCode(record.getPhone()))
			return Response.status(400).entity("Invalid Area Code").build();
		if(!CheckFirstName(record.getFirstName()))
			return Response.status(400).entity("Invlaid First Name").build();
		if(!CheckLastName(record.getLastName()))
			return Response.status(400).entity("Invlaid Last Name").build();
		return IsInDatabaseFive9(record,list,"MASTER_DATA");	
	}
	public static Response VerifyZeelLead(Record record,String list) {
		if(!UnderAge(record.getDob(),75))
			return Response.status(400).entity("Age Failed").build();
		if(!OverAge(record.getDob(),40))
			return Response.status(400).entity("Age Failed").build();
		if(!VerifyMedicareStates(record.getState()))
			return Response.status(400).entity("Invalid State "+record.getState()).build();
		if(!GoodPhoneNumber(record.getPhone()))
			return Response.status(400).entity("Invalid Phone Number").build();
		if(!GoodAreaCode(record.getPhone()))
			return Response.status(400).entity("Invalid Area Code").build();
		if(!ValidZipCode(record))
			return Response.status(400).entity("Invalid Zip Code").build();
		if(!CheckLastName(record.getLastName()))
			return Response.status(400).entity("Invlaid Last Name").build();
		return IsInDatabaseFive9(record,list,"MASTER_DATA");		
	}
	public static Response VerifySamplesLead(Record record,String list) {
		if(!UnderAge(record.getDob(),65))
			return Response.status(400).entity("Age Failed").build();
		if(!OverAge(record.getDob(),40))
			return Response.status(400).entity("Age Failed").build();
		if(!VerifyState(record.getState()))
			return Response.status(400).entity("Invalid State "+record.getState()).build();
		if(!GoodPhoneNumber(record.getPhone()))
			return Response.status(400).entity("Invalid Phone Number").build();
		if(!GoodAreaCode(record.getPhone()))
			return Response.status(400).entity("Invalid Area Code").build();
		if(!ValidZipCode(record))
			return Response.status(400).entity("Invalid Zip Code").build();
		if(!CheckLastName(record.getLastName()))
			return Response.status(400).entity("Invlaid Last Name").build();
		return IsInDatabaseFive9(record,list,"MASTER_DATA");		
	}
	public static Response VerifyInternetThings(Record record,String list) {
		if(!UnderAge(record.getDob(),65))
			return Response.status(400).entity("Age Failed").build();
		if(!OverAge(record.getDob(),40))
			return Response.status(400).entity("Age Failed").build();
		if(!VerifyState(record.getState()))
			return Response.status(400).entity("Invalid State "+record.getState()).build();
		if(!GoodPhoneNumber(record.getPhone()))
			return Response.status(400).entity("Invalid Phone Number").build();
		if(!GoodAreaCode(record.getPhone()))
			return Response.status(400).entity("Invalid Area Code").build();
		if(!CheckLastName(record.getLastName()))
			return Response.status(400).entity("Invlaid Last Name").build();
		return IsInDatabaseFive9(record,list,"MASTER_DATA");		
	}
	public static Response VerifyEngageIQPrivateLead(Record record,String list) {
		if(!UnderAge(record.getDob(),65))
			return Response.status(400).entity("Age Failed").build();
		if(!VerifyState(record.getState()))
			return Response.status(400).entity("Invalid State "+record.getState()).build();
		if(!GoodPhoneNumber(record.getPhone()))
			return Response.status(400).entity("Invalid Phone Number").build();
		if(!GoodAreaCode(record.getPhone()))
			return Response.status(400).entity("Invalid Area Code").build();
		if(!CheckLastName(record.getLastName()))
			return Response.status(400).entity("Invlaid Last Name").build();
		return IsInDatabaseFive9(record,list,"MASTER_DATA");		
	}
	public static Response VerifyLaunchPotato(Record record,String list) {
		if(!UnderAge(record.getDob(),75))
			return Response.status(400).entity("Age Failed").build();
		if(!OverAge(record.getDob(),30))
			return Response.status(400).entity("Age Failed").build();
		if(!VerifyState(record.getState()))
			return Response.status(400).entity("Invalid State "+record.getState()).build();
		if(!GoodPhoneNumber(record.getPhone()))
			return Response.status(400).entity("Invalid Phone Number").build();
		if(!GoodAreaCode(record.getPhone()))
			return Response.status(400).entity("Invalid Area Code").build();
		if(!CheckFirstName(record.getFirstName()))
			return Response.status(400).entity("Invlaid First Name").build();
		if(!CheckLastName(record.getLastName()))
			return Response.status(400).entity("Invlaid Last Name").build();
		return IsInDatabaseFive9(record,list,"MASTER_DATA");		
	}
	public static Response VerifyLaunchPotato2(Record record,String list) {
		if(!UnderAge(record.getDob(),75))
			return Response.status(400).entity("Age Failed").build();
		if(!OverAge(record.getDob(),30))
			return Response.status(400).entity("Age Failed").build();
		if(!VerifyState2(record.getState()))
			return Response.status(400).entity("Invalid State "+record.getState()).build();
		if(!GoodPhoneNumber(record.getPhone()))
			return Response.status(400).entity("Invalid Phone Number").build();
		if(!GoodAreaCode(record.getPhone()))
			return Response.status(400).entity("Invalid Area Code").build();
		if(!CheckFirstName(record.getFirstName()))
			return Response.status(400).entity("Invlaid First Name").build();
		if(!CheckLastName(record.getLastName()))
			return Response.status(400).entity("Invlaid Last Name").build();
		return IsInDatabaseFive9(record,list,"MASTER_DATA");		
	}
	public static Response VerifyLaunchPotatoPrivateInsurance(Record record,String list) {
		if(!UnderAge(record.getDob(),65))
			return Response.status(400).entity("Age Failed").build();
		if(!OverAge(record.getDob(),40))
			return Response.status(400).entity("Age Failed").build();
		if(!VerifyState(record.getState()))
			return Response.status(400).entity("Invalid State "+record.getState()).build();
		if(!GoodPhoneNumber(record.getPhone()))
			return Response.status(400).entity("Invalid Phone Number").build();
		if(!GoodAreaCode(record.getPhone()))
			return Response.status(400).entity("Invalid Area Code").build();
		if(!CheckFirstName(record.getFirstName()))
			return Response.status(400).entity("Invlaid First Name").build();
		if(!CheckLastName(record.getLastName()))
			return Response.status(400).entity("Invlaid Last Name").build();
		return IsInDatabaseFive9(record,list,"MASTER_DATA");		
	}
	public static Response VerifyEngageIQMedicareLead(Record record,String list) {
		if(!VerifyState(record.getState()))
			return Response.status(400).entity("Invalid State "+record.getState()).build();
		if(!GoodPhoneNumber(record.getPhone()))
			return Response.status(400).entity("Invalid Phone Number").build();
		if(!GoodAreaCode(record.getPhone()))
			return Response.status(400).entity("Invalid Area Code").build();
		if(!CheckLastName(record.getLastName()))
			return Response.status(400).entity("Invlaid Last Name").build();
		return IsInDatabaseFive9(record,list,"MASTER_DATA");		
	}
	private static Response IsInDatabaseFive9(Record record,String list,String database) {
		DatabaseClient client = new DatabaseClient(database);
		if(client.IsInDatabase(record)) {
			client.closeConnection();
			return Response.status(400).entity("Duplicate Record").build();
		}
		else {
			//Add to Databae
			String add = client.addRecord(record);
			client.closeConnection();
			switch(add) {
				case "Successful":
					Five9Client.addToList(record, list);
					return Response.status(200).entity("Successful").build();
				case "Duplicate Record":
					return Response.status(400).entity("Duplicate Record").build();
				default:
					return Response.status(400).entity(add).build();
			}	
		}
	}
	
	private static Response IsInDatabaseVici(Record record,String listId) {
		DatabaseClient client = new DatabaseClient("MASTER_DATA");
		if(client.IsInDatabase(record)) {
			client.closeConnection();
			return Response.status(400).entity("Duplicate Record").build();
		}
		else {
			//Add to Databae
			String add = client.addRecord(record);
			client.closeConnection();
			switch(add) {
				case "Successful":
					String response = ViciClient.AddLead(record, ViciClient.ListIds.ZINQ_MEDIA_LIVE);
					if(response.startsWith("SUCCESS"))
						return Response.status(200).entity("Successful").build();
					else	
						return Response.status(400).entity("ERROR").build();
				case "Duplicate Record":
					return Response.status(400).entity("Duplicate Record").build();
				default:
					return Response.status(400).entity(add).build();
			}	
		}
	}
	private static boolean GoodPhoneNumber(String phone) {
		String number = phone.replaceAll("[()\\s-]+.", "").trim();
		if(number.length()!=10)
			return false;
		else {
			if(number.endsWith("5551212"))
				return false;
			else
				return true;
		}
	}
	private static boolean GoodAreaCode(String phone) {
		String number = phone.replaceAll("[()\\s-]+.", "").trim();
		String areaCode = number.substring(0, 3);
		switch(areaCode) {
			case "242":
			case "264":
			case "265":
			case "340":
			case "416":
			case "437":
			case "441":
			case "473":
			case "647":
			case "671":
			case "709":
			case "782":
			case "787":
			case "800":
			case "808":
			case "809":
			case "829":
			case "849":
			case "867":
			case "868":
			case "869":
			case "876":
			case "888":
			case "902":
			case "907":
			case "939":
				return false;
			default:
				return true;
		}
	}
	private static boolean VerifyDMEState(String state) {
		switch(state.toUpperCase()) {
			case "AL":
			case "AR":
			case "CO":
			case "CT":
			case "LA":
			case "MS":
			case "NV":
			case "NJ":
			case "ND":
			case "OK":
			case "PA":
			case "TX":
				return false;
			default:
				return true;
		}
	}
	private static boolean VerifyState2(String state) {
		switch(state.toUpperCase()) {
			case "CA":
			case "FL":
				return true;
			default:
				return false;
		}
	}
	private static boolean VerifyState(String state) {
		switch(state.toUpperCase()) {
			case "AZ": //Jewel
			case "CA": //Rheem
			case "CO": //Jewel
			case "CT": //Jewel
			case "FL": //Jewel
			case "GA": //Jewel
			case "IL": //Jewel
			case "IN": //Jewel 
			case "MA": //Jewel
			case "MN": //Jewel
			case "NC": //Hershey
			case "NJ": //Jewel
			case "NV": //Jewel
			case "NY": //Jewel
			case "OH": //Jewel
			case "TX": //No Home
			case "VA": //Eagle
			case "WA": //Jewel
				return true;
			default:
				return false;
				
				
		}
	}
	public static boolean VerifyMedicareStates(String state) {
		switch(state.toUpperCase()) {
			case "CA": //Rheem
			case "FL": //Jewel
			case "GA": //Jewel
			case "IL": //Jewel 
			case "NC": //Hershey
			case "VA": //Eagle
			return true;
		default:
			return false;
	}
	}
	public static boolean Under65Over40(String dob) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate birthDate = LocalDate.parse(dob,formatter);
		LocalDate currentDate = LocalDate.now();
		int currentAge = Period.between(birthDate, currentDate).getYears();
		if(currentAge<65 && currentAge>=40)
			return true;
		else 
			return false;
	}
	public static boolean UnderAge(String dob,int age) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate birthDate = LocalDate.parse(dob,formatter);
		LocalDate currentDate = LocalDate.now();
		int currentAge = Period.between(birthDate, currentDate).getYears();
		if(currentAge<=age)
			return true;
		else 
			return false;
	}
	public static boolean OverAge(String dob,int age) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate birthDate = LocalDate.parse(dob,formatter);
		LocalDate currentDate = LocalDate.now();
		int currentAge = Period.between(birthDate, currentDate).getYears();
		if(currentAge>=age)
			return true;
		else 
			return false;
	}
	private static boolean ValidZipCode(Record record) {
		InfoDatabase client = new InfoDatabase();
		if(client.CheckZipcode(record.getZip())) {
			client.close();
			return true;
		}
		else {
			DatabaseClient db = new DatabaseClient("MASTER_DATA");
			db.addRecord(record);
			db.closeConnection();
			Five9Client.addToList(record, "Sampley");
			return false;
		}
	}
	private static boolean CheckFirstName(String firstName) {
		if(firstName==null)
			return false;
		else if(firstName.length()==0)
			return false;
		else 
			return true;
	}
	private static boolean CheckLastName(String lastName) {
		if(lastName==null)
			return false;
		else if(lastName.length()==0)
			return false;
		else 
			return true;
	}
}
