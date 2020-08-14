package Client;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;
import org.json.JSONObject;

public class Record implements Cloneable{
	
	String firstName,lastName,dob,ssn,gender;
	String address,city,state,zip,phone,mobile,carrier,id;
	String agent,vendorId,subId,ip,email;
	String policyId,bin,grp,pcn,additionalInfo;
	String insuranceType,status,type;
	String npi,drFirst,drLast,drAddress,drCity,drState,drZip,drPhone,drFax;
	String faxDisposition,telmedDisposition;
	String clickId;
	int age;
	boolean confirmDoctor;
	private class InsuranceKeys {
		public final static String STATUS = "STATUS";
		public final static String TYPE = "TYPE";
		public final static String CARRIER = "CARRIER";
		public final static String POLICY_ID = "POLICY_ID";
		public final static String BIN = "BIN";
		public final static String GROUP = "GROUP";
		public final static String PCN = "PCN";
		public final static String CHECK = "CHECK";
	}
	public class PatientKeys {
		public final static String SUCCESS = "success";
		public final static String UPDATED = "updated";
		public final static String FIRST_NAME = "firstName";
		public final static String LAST_NAME = "lastName";
		public final static String GENDER = "gender";
		public final static String SSN = "ssn";
		public final static String ADDRESS = "address";
		public final static String CITY = "city";
		public final static String STATE = "state";
		public final static String ZIP  = "zip";
		public final static String ID = "id";
		public final static String NAME = "name";
		public final static String DOB = "dob";
		public final static String PHONE = "phone";
		public final static String NPI = "npi";
		public final static String DR_FIRST = "drFirst";
		public final static String DR_LAST = "drLast";
		public final static String DR_ADDRESS = "drAddress";
		public final static String DR_CITY = "drCity";
		public final static String DR_STATE = "drState";
		public final static String DR_ZIP = "drZip";
		public final static String DR_PHONE = "drPhone";
		public final static String DR_FAX = "drFax";
		public final static String FAX_DIPSOSITION = "faxDisposition";
	}
	public Record() {
	
	}
	public void calculateAge() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate birthDate = LocalDate.parse(this.dob,formatter);
		LocalDate currentDate = LocalDate.now();
		int currentAge = Period.between(birthDate, currentDate).getYears();
		this.age = currentAge;
	}
	public int getAge() {
		return age;
	}
	public String getClickId() {
		return clickId;
	}

	public void setClickId(String clickId) {
		this.clickId = clickId;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getSubId() {
		if(this.subId == null)
			return "";
		else 
			return subId;
	}

	public void setSubId(String subId) {
		if(subId==null)
			this.subId = "";
		else
			this.subId = subId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public String setType(String type) {
		this.type = type;
		return this.type;
	}
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public String getBin() {
		return bin;
	}
	public void setBin(String bin) {
		this.bin = bin;
	}
	public String getGrp() {
		return grp;
	}
	public void setGrp(String grp) {
		this.grp = grp;
	}
	public String getPcn() {
		return pcn;
	}
	public void setPcn(String pcn) {
		this.pcn = pcn;
	}
	public void setInsuranceInfo(String json) throws JSONException {
		JSONObject obj = new JSONObject(json);
		if(!obj.getBoolean("IsSucess")) {
			setStatus("Error");
			return;
		}
		JSONObject data = obj.getJSONObject("Data");
		switch(data.getString("Status")) {
			case "Failed Age Verification":
				setStatus("Over 65");
				return;
			case "No data available":
				setStatus("Not Found");
				return;
		}
		setStatus("FOUND");
		setCarrier(getPBM(data.getString("PayerHelpDeskNo")));
		setPolicyId(data.getString("CardHolderId"));
		setBin(data.getString("BIN"));
		setGrp(data.getString("Group"));
		setPcn(data.getString("PCN"));
		setAdditionalInfo(data.getString("AdditionalCoverageInfo"));
	}
	private String getPBM(String payer) {
		switch(payer) {
			case "8009221557": return "Express Scripts";
			case "8002386279": return "Aetna";
			case "8004212342": return "Caremark"; 
			case "8008801188": return "Catamaran";
			case "8887060421": return "Catamaran";
			case "8005227487": return "Cigna";
			case "8007831307": return "Humana";
			case "8008214795": return "Prime Therapeutics";
			case "8007882949": return "Medimpact";
			case "8776866875": return "Horizon BCBS of NJ";
			case "8003614542": return "Envision RX";
			case "8557915302": return "Envision RX";
			case "8009939898": return "Independent Health";
			case "8663332757": return "Navitus";
			case "8004608988": return "US Scripts";
			case "8888694600": return "Catalyst Rx";
			case "8669846462": return "Meridian";
			case "8778896510": return "OptumRx";
			case "8778896481": return "OptumRx";
			case "8008658715": return "Humana";
			case "8886255686": return "Cigna";
			case "8002446224": return "Cigna";
			case "8008473859": return "WV Medicaid";
			case "8007979791": return "OptumRx";
			case "8558540270":
		default: return payer;
		}
	}
	
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getNpi() {
		return npi;
	}
	public void setNpi(String npi) {
		this.npi = npi;
	}
	public String getDrFirst() {
		return drFirst;
	}
	public void setDrFirst(String drFirst) {
		this.drFirst = drFirst;
	}
	public String getDrLast() {
		return drLast;
	}
	public void setDrLast(String drLast) {
		this.drLast = drLast;
	}
	
	public String getDrAddress() {
		return drAddress;
	}
	public void setDrAddress(String drAddress) {
		this.drAddress = drAddress;
	}
	public String getDrCity() {
		return drCity;
	}
	public void setDrCity(String drCity) {
		this.drCity = drCity;
	}
	public String getDrState() {
		return drState;
	}
	public void setDrState(String drState) {
		this.drState = drState;
	}
	public String getDrZip() {
		return drZip;
	}
	public void setDrZip(String drZip) {
		this.drZip = drZip;
	}
	public String getDrPhone() {
		return drPhone;
	}
	public void setDrPhone(String drPhone) {
		this.drPhone = drPhone;
	}
	public String getDrFax() {
		return drFax;
	}
	public void setDrFax(String drFax) {
		this.drFax = drFax;
	}
	public String getFaxDisposition() {
		return faxDisposition;
	}
	public void setFaxDisposition(String faxDisposition) {
		this.faxDisposition = faxDisposition;
	}
	public String getTelmedDisposition() {
		return telmedDisposition;
	}
	public void setTelmedDisposition(String telmedDisposition) {
		this.telmedDisposition = telmedDisposition;
	}
	public void setDoctorConfirmed(boolean confirmDoctor) {
		this.confirmDoctor = confirmDoctor;
	}
	public boolean isDoctorConfirmed() {
		return confirmDoctor;
	}
	public String returnPatientJSON(int i) {
		JSONObject obj = new JSONObject();
		try {
			obj.put(PatientKeys.SUCCESS, true);
			obj.put(PatientKeys.NAME, getFirstName()+" "+getLastName());
			obj.put(PatientKeys.FIRST_NAME, getFirstName());
			obj.put(PatientKeys.LAST_NAME, getLastName());
			obj.put(PatientKeys.GENDER, getGender());
			obj.put(PatientKeys.SSN, getSsn());
			obj.put(PatientKeys.ADDRESS, getAddress());
			obj.put(PatientKeys.CITY, getCity());
			obj.put(PatientKeys.STATE, getState());
			obj.put(PatientKeys.ZIP, getZip());
			obj.put(PatientKeys.DOB, getDob());
			obj.put(PatientKeys.PHONE, getPhone());
			obj.put(PatientKeys.ID, getId());
			obj.put(PatientKeys.NPI, getNpi());
			obj.put(PatientKeys.DR_FIRST, getDrFirst());
			obj.put(PatientKeys.DR_LAST, getDrLast());
			obj.put(PatientKeys.DR_ADDRESS, getDrAddress());
			obj.put(PatientKeys.DR_CITY, getDrCity());
			obj.put(PatientKeys.DR_STATE, getDrState());
			obj.put(PatientKeys.DR_ZIP, getDrZip());
			obj.put(PatientKeys.DR_PHONE, getDrPhone());
			obj.put(PatientKeys.DR_FAX, getDrFax());
			obj.put(PatientKeys.UPDATED, getUpdated(i));
			obj.put(PatientKeys.FAX_DIPSOSITION, getFaxDisposition());
		} catch(JSONException ex) {
			ex.printStackTrace();
			return ex.getMessage();
		}
		return obj.toString();
	}
	private String getUpdated(int update) {
		switch(update) {
		case 1:
			return "Succuesfully Updated";
		case 0:
			return "Record Not Found";
		case -1:
			return "There was an Error";
		case -2: 
			return "";
		default:
			return "Unknown Response";
	}

	}
	public static String toProperCase(String text) {
	    if (text == null || text.isEmpty()) {
	        return text;
	    }
	    StringBuilder converted = new StringBuilder();
	    boolean convertNext = true;
	    for (char ch : text.toCharArray()) {
	        if (Character.isSpaceChar(ch)) {
	            convertNext = true;
	        } else if (convertNext) {
	            ch = Character.toTitleCase(ch);
	            convertNext = false;
	        } else {
	            ch = Character.toLowerCase(ch);
	        }
	        converted.append(ch);
	    }
	    return converted.toString();
	}
	public String getPBMFromBin(String bin) {
		if(bin==null)
			return this.carrier;
		switch(bin) {
			case "004336": 
			case "610239":
			case "610591":
				return "Caremark";
			case "610502":
				return "Aetna";
			case "017010":
				return "Cigna";
			case "610014":
			case "400023":
			case "003858":
			case "011800":
				return "Express Scripts";
			case "012833":
			case "011552":
			case "016499":
			case "016895":
			case "014897":
			case "800001":
			case "004915":
			case "015905":
			case "610455":
				return "Prime Therapeutics";
			case "610011":
			case "015814":
			case "001553":
			case "610593":
			case "011214":
				return "Catamaran";
			case "015574":
			case "015921":
			case "003585":
				return "Medimpact";
			case "011842":
			case "610279":
			case "610097":
			case "610494":
			case "610127": 
				return "OptumRx";
			case "600428":
				return "Argus";
			case "005947":
			case "603286":
				return "Catalyst Rx";
			case "015599":
			case "015581":
				return "Humana";
			case "018117":
				return "Magellan Rx ";
			default:
				return this.carrier;
		}
	}
}