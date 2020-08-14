package Client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;

import com.gargoylesoftware.htmlunit.AjaxController;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

import PBM.Insurance;

public class EmdeonClient {
	WebClient webClient = new WebClient(BrowserVersion.CHROME);
	CookieManager cookieManager;
	HtmlPage cardFinderPage;
	String npi;
	public EmdeonClient() {
		setWebClientOptions();
	}
	private void setWebClientOptions() {
		cookieManager = webClient.getCookieManager();
		cookieManager.setCookiesEnabled(true);
		webClient.setCookieManager(cookieManager);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setUseInsecureSSL(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.setAjaxController(new AjaxController(){
			@Override
		    public boolean processSynchron(HtmlPage page, WebRequest request, boolean async) {
		        return true;
		    }
		});
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
	    java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF); 
	    java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
	}
	public boolean login(String username,String pass,String npi){
		this.npi = npi;
		HtmlPage page1;
		try {
			page1 = webClient.getPage(FieldNames.EMDEON_URL);
			HtmlForm form = (HtmlForm) page1.getByXPath("//form[@id='frmMaster']").get(0);
		
			HtmlTextInput user = form.getInputByName(ElementIds.USER_NAME);
			form.removeAttribute("onsubmit");
			HtmlPasswordInput password = form.getInputByName(ElementIds.PASSWORD);
			HtmlSubmitInput button = form.getInputByName(ElementIds.LOGIN);
			user.setValueAttribute(username);
			password.setValueAttribute(pass);
			
			cardFinderPage = button.click();	
			
			cardFinderPage = webClient.getPage(FieldNames.CARDFINDER_URL);
			HtmlForm form2 = cardFinderPage.getFormByName("aspnetForm");
			HtmlTextInput coverageType = form2.getInputByName("ctl00$cphMain$pnlCallBack$ASPxRoundPanel1$ddServiceType");
			coverageType.setValueAttribute("Commercial Only");
			coverageType.removeAttribute("readonly");
			HtmlButtonInput b = (HtmlButtonInput) form2.getByXPath("(//input[@type='button'])").get(1);
			cardFinderPage = b.click();
			System.out.println("Login Successful");
			return true;
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	public void logout() {
		System.out.println("Logging Out");
		HtmlAnchor anchor = cardFinderPage.getAnchorByText("Logout");
		try {
			cardFinderPage = anchor.click();
			webClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		webClient.waitForBackgroundJavaScript(2000);
		System.out.println(cardFinderPage.asText());
	}
	public void close() {
		webClient.close();
	}

	public void fillOutForm(Record record) {
		Insurance privateInsurance = null;
		Insurance medicareInsurance = null;
		if(!checkRecord(record)) 
			record.setStatus("Invalid Record");
		else if(record.getFirstName().length()>=15 || record.getLastName().length()>=15) {
			record.setStatus("Name too long");
		}
		WebRequest request = cardFinderPage.getWebResponse().getWebRequest();
		List<NameValuePair> oldParameters = request.getRequestParameters();
		List<NameValuePair> newParameters = new ArrayList<NameValuePair>() {
			@Override
			public boolean contains(Object o) {
				String name = (String) o;
				//System.out.println(name);
				for(NameValuePair nvp: this) {
					if(nvp.getName().equalsIgnoreCase(name))
						return true;
					else 
						continue;
				}
				return false;
			}
		};
		for(NameValuePair name: oldParameters) {
			switch(name.getName()) {
				case EmdeonParameters.SERVICE_TYPE:
					newParameters.add(new NameValuePair(EmdeonParameters.SERVICE_TYPE,EmdeonParameters.COMMERCIAL));
					break;
				case EmdeonParameters.SERVICE_TYPE_VI:
					newParameters.add(new NameValuePair(EmdeonParameters.SERVICE_TYPE_VI,EmdeonParameters.COMMERCIAL_VALUE));
					break;
				case EmdeonParameters.SERVICE_TYPE_DL:
					newParameters.add(new NameValuePair(EmdeonParameters.SERVICE_TYPE_DL,EmdeonParameters.COMMERCIAL_VALUE));
					break;
				case EmdeonParameters.NPI:
					newParameters.add(new NameValuePair(EmdeonParameters.NPI,npi));
					break;
				case EmdeonParameters.FIRST_NAME:
					newParameters.add(new NameValuePair(EmdeonParameters.FIRST_NAME,record.getFirstName().trim()));
					break;
				case EmdeonParameters.LAST_NAME:
					newParameters.add(new NameValuePair(EmdeonParameters.LAST_NAME,record.getLastName().trim()));
					break;
				case EmdeonParameters.DOB:
					newParameters.add(new NameValuePair(EmdeonParameters.DOB,record.getDob()));
					break;
				case EmdeonParameters.DOB_STATE:
					String dob_state = "{&quot;rawValue&quot;:&quot;"+record.getDob()+"&quot;,&quot;validationState&quot;:&quot;&quot;}";
					newParameters.add(new NameValuePair(EmdeonParameters.DOB_STATE,dob_state));
					break;
				case EmdeonParameters.ZIP:
					newParameters.add(new NameValuePair(EmdeonParameters.ZIP,record.getZip()));
					break;
				case EmdeonParameters.GENDER:
					newParameters.add(new NameValuePair(EmdeonParameters.GENDER,Integer.toString(EmdeonParameters.getGenderValue(record))));
					break;
				default:
					newParameters.add(name);
			}
		}
		try {
			request.setRequestParameters(newParameters);
			request.setUrl(new URL(FieldNames.CARDFINDER_URL));
			cardFinderPage = webClient.getPage(request);
			HtmlButtonInput button2 = (HtmlButtonInput) cardFinderPage.getElementByName(ElementIds.SUBMIT);
			synchronized(cardFinderPage = button2.click()) {
				wait(cardFinderPage,5000);
				HtmlTable table = (HtmlTable) cardFinderPage.getElementById(ElementIds.COMMERCIAL_TABLE);
				if(table==null) {
					record.setStatus("Error");
				}
				getPrivateCellData(table,record);
			}
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ElementNotFoundException e) {
			System.out.println(cardFinderPage.getWebResponse().getContentAsString());
			e.printStackTrace();
			record.setStatus("ERROR");
		}
	}
	private String getPBM(String payer,String bin) {
		switch(payer) {
			case "8005465677": return "Express Scripts";
			case "8009221557": return "Express Scripts";
			case "8002386279": return "Aetna";
			case "8007002541": return "Wellpoint";
			case "8004212342": return "Caremark"; 
			case "8555380453": return "Caremark";
			case "8555380454": return "Caremark";
			case "8666934620": return "Caremark";
			case "8008801188": return "Catamaran";
			case "8887060421": return "Catamaran";
			case "8005227487": return "Cigna";
			case "8007831307": return "Humana";
			case "8008654034": return "Humana";
			case "8888776420": return "Prime Therapeutics";
			case "8008214795": return "Prime Therapeutics";
			case "8003911926": return "Prime Therapeutics";
			case "8773919291": return "Medimpact";
			case "8007882949": return "Medimpact";
			case "8773911123": return "Medimpact";
			case "8776866875": return "Horizon BCBS of NJ";
			case "8003614542": return "Envision RX";
			case "8557915302": return "Envision RX";
			case "8009939898": return "Independent Health";
			case "8663332757": return "Navitus";
			case "8004608988": return "US Scripts";
			case "8888694600": return "Catalyst Rx";
			case "8669846462": return "Meridian";
			case "8887917255": return "OptumRx";
			case "8778896510": return "OptumRx";
			case "8778896481": return "OptumRx";
			case "8008658715": return "Humana";
			case "8886255686": return "Cigna";
			case "8002446224": return "Cigna";
			case "8008473859": return "WV Medicaid";
			case "8007979791": return "OptumRx";
			case "8558540270":
			default: return getPBMByBin(bin);
		}
	}
	private String getPBMByBin(String bin) {
		switch(bin) {
			case "610455": return "Prime Therapeutics";
			case "610127": return "Optum Rx";
			default: return bin;
		}
	}
	private boolean checkRecord(Record r) {
		if(r.getFirstName().length()<=1 || r.getLastName().length()<=1)
			return false;
		else if( r.getZip().length()<=3 || r.getZip().length()>=6 || r.getDob().equalsIgnoreCase(""))
			return false;
		else if (r.getZip().length()==4) 
			r.setZip("0"+r.getZip());
		return true;
		
	}
	private void wait(HtmlPage page,int length) {
		try {
			page.wait(length);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void getPrivateCellData(HtmlTable table,Record record) {
		for(HtmlTableRow row: table.getRows()) {
			System.out.println(row.asText());
			for(HtmlTableCell cell: row.getCells()) {
				String cellData = cell.asText();
				switch(cellData) {
				case FieldNames.COVERAGE_TYPE:
					record.setStatus("FOUND");
					break;
				case FieldNames.PAYER_HELP_DESK:
					if(cell.getIndex()==1)
						record.setCarrier(getPBM(cell.getNextElementSibling().asText(),record.getBin()));
					else 
						record.setCarrier(getPBM(cell.getPreviousElementSibling().asText(),record.getBin()));
					break;
				case FieldNames.POLICY_ID:
					if(cell.getIndex()==1)
						record.setPolicyId(cell.getNextElementSibling().asText());
					else 
						record.setPolicyId(cell.getPreviousElementSibling().asText());
					break;
				case FieldNames.BIN:
					if(cell.getIndex()==1)
						record.setBin(cell.getNextElementSibling().asText());
					else 
						record.setBin(cell.getPreviousElementSibling().asText());
					break;
				case FieldNames.PCN:
					if(cell.getIndex()==1)
						record.setPcn(cell.getNextElementSibling().asText());
					else 
						record.setPcn(cell.getPreviousElementSibling().asText());
					break;
				case FieldNames.GROUP:
					if(cell.getIndex()==1)
						record.setGrp(cell.getNextElementSibling().asText());
					else 
						record.setGrp(cell.getPreviousElementSibling().asText());
					break;
				case FieldNames.ADDITIONAL_COVERAGE:
					if(cell.getIndex()==1)
						record.setAdditionalInfo(cell.getNextElementSibling().asText());
					else 
						record.setAdditionalInfo(cell.getPreviousElementSibling().asText());
					return;
				case ErrorNames.NO_DATA:
					record.setStatus(ErrorNames.NO_DATA);
					break;
				case ErrorNames.PATIENT_NOT_COVERED: 
					record.setStatus("Patient Is Not Covered");
					break;
				case ErrorNames.WRONG_FIRST_NAME:
					record.setStatus("WRONG FIRST NAME");
					break;
				case ErrorNames.LAST_NAME_TOO_LONG: 
					record.setStatus("Last Name to long");
					break;
				case ErrorNames.FIRST_NAME_TOO_LONG:
					record.setStatus("First Name too long");
					break;
				case ErrorNames.INVALID_DOB: 
					record.setStatus("Invalid DOB");
					break;
				case ErrorNames.INVALID_DOB2:
					record.setStatus("INVALID DOB");
					break;
				case ErrorNames.PATIENT_PRIVATE_NOT_FOUND3:
				case ErrorNames.PATIENT_PRIVATE_NOT_FOUND2:
				case ErrorNames.PATIENT_PRIVATE_NOT_FOUND: 
					record.setStatus("Not Found");
					break;
				case ErrorNames.PBM_NOT_PARTICIPATE: 
					record.setStatus("PBM NOT PARTICIPATE");
					break;
				case ErrorNames.PBM_NOT_PARTICIPATE2:
					record.setStatus("PBM NOT PARTICIPATE");
					break;
				case ErrorNames.NOT_ACTIVE: 
					record.setStatus("Not active");
					break;
				case ErrorNames.TIMED_OUT:
					record.setStatus("TIMED OUT");
					break;
				case ErrorNames.CONNECTION_ISSUES:
					record.setStatus("Connecton issues");
					break;
				case ErrorNames.CONNECTION_ISSUES2:
					record.setStatus("Connection Issues");
					break;
				case ErrorNames.EMDEON_DOWN:
					record.setStatus("Emdeon Down");
					break;
				}
			}
		}
	}
	
	private static class EmdeonParameters {
		//
		public static final String SERVICE_TYPE = "ctl00$cphMain$pnlCallBack$ASPxRoundPanel1$ddServiceType";
		public static final String NPI = "ctl00$cphMain$pnlCallBack$ASPxRoundPanel1$txtProviderId";
		public static final String FIRST_NAME = "ctl00$cphMain$pnlCallBack$ASPxRoundPanel1$txtNameFirst";
		public static final String LAST_NAME = "ctl00$cphMain$pnlCallBack$ASPxRoundPanel1$txtNameLast";
		public static final String DOB = "ctl00$cphMain$pnlCallBack$ASPxRoundPanel1$txtBirthDate";
		public static final String DOB_STATE = "ctl00$cphMain$pnlCallBack$ASPxRoundPanel1$txtBirthDate$State";
		public static final String SSN = "ctl00$cphMain$pnlCallBack$ASPxRoundPanel1$txtPatientID";
		public static final String ZIP = "ctl00$cphMain$pnlCallBack$ASPxRoundPanel1$txtZipCode";
		public static final String GENDER = "ctl00$cphMain$pnlCallBack$ASPxRoundPanel1$rbGender";
		public static final String SERVICE_TYPE_VI = "ctl00_cphMain_pnlCallBack_ASPxRoundPanel1_ddServiceType_VI";
		public static final String SERVICE_TYPE_DL = "ctl00$cphMain$pnlCallBack$ASPxRoundPanel1$ddServiceType$DDD$L";
		//Gender Values
		public static final int GENDER_MALE = 0;
		public static final int GENDER_FEMALE =1;
		//Service Types
		public static final String COMMERCIAL = "Commercial Only";
		public static final String MEDICARE_PART_D = "Medicare Part D Only";
		public static final String COMMERCIAL_AND_MEDICARE = "Commercial and Medicare Part A/B/D";

		//Service Type Values
		public static final String COMMERCIAL_VALUE = "ELIG";
		public static final String COMMERCIAL_PART_D = "SuperE1";
		
		public static int getGenderValue(Record record) {
			if(record.getGender()==null)
				return GENDER_MALE;
			if(record.getGender().startsWith("M") || record.getGender().startsWith("m"))
				return GENDER_MALE;
			else if(record.getGender().startsWith("F") || record.getGender().startsWith("f"))
				return GENDER_FEMALE;
			else 
				return GENDER_MALE;
		}
	}
	private static class ElementIds {
		//TextFields
		public static final String USER_NAME = "ctl00$ctl00$cphMain$cphPublicArea$LoginView2$Login1$Login1$UserName";
		public static final String PASSWORD = "ctl00$ctl00$cphMain$cphPublicArea$LoginView2$Login1$Login1$Password";
		public static final String LOGIN = "ctl00$ctl00$cphMain$cphPublicArea$LoginView2$Login1$Login1$LoginButton";
		//Tables
		public static final String COMMERCIAL_TABLE = "ctl00_cphMain_pnlCallBack_ASPxRoundPanel1_pgControl_gvElig_DXMainTable";
		public static final String MEDICARE_TABLE = "ctl00_cphMain_pnlCallBack_ASPxRoundPanel1_pgControl_gvMedicare_DXMainTable";
		public static final String MEDICARE_PART_D_TABLE = "ctl00_cphMain_pnlCallBack_ASPxRoundPanel1_pgControl_gvTroop_DXMainTable";
		
		public static final String SUBMIT = "ctl00$cphMain$pnlCallBack$ASPxRoundPanel1$btnSubmit";
		
	}
	private static class FieldNames {
		//
		public static final String COVERAGE_TYPE = "Coverage Type";
		public static final String PAYER_HELP_DESK = "Payer Help Desk Number";
		public static final String POLICY_ID = "Cardholder ID";
		public static final String BIN = "BIN";
		public static final String GROUP = "Group";
		public static final String PCN = "PCN";
		public static final String ADDITIONAL_COVERAGE = "Additional Coverage Information";
		public static final String PLAN_TYPE = "Plan Type";
		public static final String MEDICARE_NUMBER = "Medicare Beneficiary ID";
		//URLS1
		public static final String EMDEON_URL = "https://secure.erxnetwork.com/logon.aspx";
		public static final String CARDFINDER_URL = "https://secure.erxnetwork.com/NS/Cardfinder/CardFinder.aspx";

	}
	public static class ErrorNames {
		public static final String NO_DATA = "No data to display";
		public static final String CONNECTION_ISSUES = "ERX104 Connectivity Issue, Please Resubmit. If persists, call Emdeon Customer Support at (866) 379-6389";
		public static final String CONNECTION_ISSUES2 = "ERX106Connectivity Issue, Please Resubmit. If persists, call Emdeon Customer Support at (866) 379-6389";
		public static final String TIMED_OUT = "ERX119:CardFinder PBM Timeout: Please Resubmit";
		public static final String NOT_ACTIVE = "ERX180Patient Found Coverage Not Active On Submitted Date of Service";
		public static final String PBM_NOT_PARTICIPATE = "ERX120 PATIENT FOUND - PBM NOT PARTICIPATING WITH CARDFINDER SERVICE";
		public static final String PBM_NOT_PARTICIPATE2 = "ERX151 PBM Does Not Participate with CardFinder";
		public static final String PATIENT_PRIVATE_NOT_FOUND = "ERX108Patient Not Found";
		public static final String PATIENT_PRIVATE_NOT_FOUND2 = "ERX108 Patient Not Found";
		public static final String PATIENT_PRIVATE_NOT_FOUND3 = "ERX108 Patient not found";
		public static final String PATIENT_MEDICARE_NOT_FOUND = "MCARE ELIG;NO PATIENT MATCH FOUND";
		public static final String INVALID_DOB = "ERX102 Missing/Invalid Patient Date of Birth";
		public static final String INVALID_DOB2 = "ERX102:M/I Patient Date of Birth";
		public static final String WRONG_FIRST_NAME = "ERX100 Missing/Invalid Patient First Name";
		public static final String FIRST_NAME_TOO_LONG = "ERX124:MAX FIELD LENGTH EXCEEDED - (CA) Patient First Name \"MAX\" 12";
		public static final String LAST_NAME_TOO_LONG = "ERX125:MAX FIELD LENGTH EXCEEDED - (CB) Patient Last Name \"MAX\" 15";
		public static final String PATIENT_NOT_COVERED = "ERX108 Patient Is Not Covered";
		public static final String EMDEON_DOWN = "ERX03 CONNECTION TO PAYOR IS DOWN (M3BC43200)";
		public static final String MED_D_NOT_VALID = "MCARE ELIG;PATIENT FOUND BUT PART D COVERAGE OUTSIDE SUBMITTED DATE OF SERVICE";
	}
}