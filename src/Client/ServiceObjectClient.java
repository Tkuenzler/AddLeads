package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServiceObjectClient {
	public static final String URL = "http://trial.serviceobjects.com/GPPL2/api.svc/PhoneInfo/";
	public static final String BACK_URL = "/full/WS77-RLK5-DFN1?format=json";
	public static final String LICENESE = "WS77-RLK5-DFN1";
	private class Keys {
		public static final String PHONE_INFO = "PhoneInfo";
		public static final String CONTACTS = "Contacts";
		private class ContactKeys {
			public static final String NAME = "Name";
			public static final String ADDRESS = "Address";
			public static final String CITY = "City";
			public static final String STATE = "State";
			public static final String POSTAL_CODE = "PostalCode";
			public static final String QUALITY_SCORE = "QualityScore";
		}
		public static final String KEY = "LicenseKey";
		public static final String TEST = "TestType";
	}
	public static String GetPhoneInfo(String phone) throws MalformedURLException, IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(URL+phone+BACK_URL).openConnection();
		connection.connect();
		BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line=rd.readLine())!=null)
			sb.append(line);
		return sb.toString();
	}
	public void RateLead(Record record,String json) throws JSONException {
		JSONObject obj = new JSONObject(json);
		JSONObject phoneInfo = obj.getJSONObject(Keys.PHONE_INFO);
		if(phoneInfo.has(Keys.CONTACTS)) {
			JSONArray contacts = phoneInfo.getJSONArray(Keys.CONTACTS);
			for(int i = 0;i<contacts.length();i++) {
				JSONObject contact = contacts.getJSONObject(i);
				String name = contact.getString(Keys.ContactKeys.NAME);
				String address = contact.getString(Keys.ContactKeys.ADDRESS);
				String city = contact.getString(Keys.ContactKeys.CITY);
				String postalCode = contact.getString(Keys.ContactKeys.POSTAL_CODE);
				String score = contact.getString(Keys.ContactKeys.QUALITY_SCORE);
			}
			
		}
	}
}
