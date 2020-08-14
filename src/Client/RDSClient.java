package Client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.core.Response;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RDSClient {
	private class URLS {
		public static final String RDS_URL = "http://telemed.quivvytech.com/api/v4/api.php";
	}
	private class Parameters {
		public static final String FIRST_NAME = "first_name";
		public static final String LAST_NAME = "last_name";
		public static final String PHONE_NUMBER ="phone_number";
		public static final String DOB_MONTH = "dob_month";
		public static final String DOB_DAY = "dob_day";
		public static final String DOB_YEAR = "dob_year";
		public static final String GENDER = "gender";
		public static final String ADDRESS = "address1";
		public static final String CITY = "city";
		public static final String STATE = "state";
		public static final String POSTAL_CODE = "postal_code";
		public static final String INSURANCE_CARRIER = "insurance_carrier";
		public static final String PATIENT_ID = "patient_id";
		public static final String GROUP_ID = "group_id";
		public static final String BIN = "bin_number";
		public static final String PCN = "pcnNumber";
		public static final String SOURCE_ID = "SOURCE_ID";
		public static final String PRODUCT_SUGGESTIONS = "productSuggestions";
		public static final String INSERT_TIME = "InsertTime";
		public static final String SIGNATURE_VALIDATION = "SignatureValidation";
	}
	private static URL CreateURL(Record record,String product_suggestions) {
		URIBuilder b = null;
		URL url = null;
		String[] dob_data = record.getDob().split("/");
		String dob_month = dob_data[0];
		String dob_day = dob_data[1];
		String dob_year = dob_data[2];
		try {
			b = new URIBuilder(URLS.RDS_URL);
			b.addParameter(Parameters.FIRST_NAME, record.getFirstName())
			.addParameter(Parameters.LAST_NAME, record.getLastName())
			.addParameter(Parameters.GENDER, record.getGender())
			.addParameter(Parameters.PHONE_NUMBER, record.getPhone())
			.addParameter(Parameters.DOB_MONTH, dob_month)
			.addParameter(Parameters.DOB_DAY, dob_day)
			.addParameter(Parameters.DOB_YEAR, dob_year)
			.addParameter(Parameters.ADDRESS, record.getAddress())
			.addParameter(Parameters.CITY, record.getCity())
			.addParameter(Parameters.STATE, record.getState())
			.addParameter(Parameters.POSTAL_CODE, record.getZip())
			.addParameter(Parameters.INSURANCE_CARRIER,  record.getCarrier())
			.addParameter(Parameters.PATIENT_ID, record.getPolicyId())
			.addParameter(Parameters.BIN, record.getBin())
			.addParameter(Parameters.PCN, record.getPcn())
			.addParameter(Parameters.GROUP_ID, record.getGrp())
			.addParameter(Parameters.SOURCE_ID, record.getPhone())
			.addParameter(Parameters.PRODUCT_SUGGESTIONS, product_suggestions)
			.addParameter(Parameters.INSERT_TIME, GetInsertTime())
			.addParameter(Parameters.SIGNATURE_VALIDATION, "jDOBAf$3");
			url = b.build().toURL();
			return url;
		} catch (URISyntaxException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static Response AddLead(Record record,String product_suggestions,String pain_level,String pain_location,String pain_type) {
		DatabaseClient client = new DatabaseClient("EngageIQ_Telmed");
		String add = client.addRecordToTelmed(record, pain_level, pain_location, pain_type);
		client.closeConnection();
		switch(add) {
			case "Successful":
				return  AddLeadToRDS(CreateURL(record,product_suggestions));
			default:
				return Response.status(400).entity(add).build();
		}		
	}
	private static Response AddLeadToRDS(URL url) {
		try {
			HttpGet get = new HttpGet(url.toString());
			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(get);
			int status_code = response.getStatusLine().getStatusCode();
			if(status_code==200) {
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity, "UTF-8");
				response.close();
				client.close();
				return Response.status(200).entity("SUCCESFULLY ADDED").build();
			}
			else {
				response.close();
				client.close();
				return Response.status(status_code).entity("UNEXPECTED ERROR").build();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return Response.status(400).entity(e.getMessage()).build();
		}
	}
	private static String GetInsertTime()  {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
