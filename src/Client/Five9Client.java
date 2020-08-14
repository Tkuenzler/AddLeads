package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.utils.URIBuilder;

public class Five9Client {
	private static String URL = "https://api.five9.com/web2campaign/AddToList";
	private static String DOMAIN = "F9domain";
	private static String LIST = "F9list";
	private static String F9 = "F9key";

	public static String createURL(Record r,String list) {
		URIBuilder b = null;
		URL url = null;
		try {
			b = new URIBuilder(URL);
			b.addParameter(DOMAIN, "Prescription Experts");
			b.addParameter(LIST, list);
			b.addParameter(F9, "number1").addParameter("number1", r.getPhone());
			b.addParameter(F9, "first_name").addParameter("first_name", r.getFirstName());
			b.addParameter(F9, "last_name").addParameter("last_name", r.getLastName());
			b.addParameter(F9, "street").addParameter("street", r.getAddress());
			b.addParameter(F9, "city").addParameter("city", r.getCity());
			b.addParameter(F9, "state").addParameter("state", r.getState());
			b.addParameter(F9, "zip").addParameter("zip", r.getZip());
			b.addParameter(F9, "Gender").addParameter("Gender", r.getGender());
			b.addParameter(F9, "Date Of Birth").addParameter("Date Of Birth", r.getDob());
			//Insruance
			b.addParameter(F9, "Primary Insurance").addParameter("Primary Insurance", r.getCarrier());
			b.addParameter(F9, "Email").addParameter("Email", r.getEmail());
			b.addParameter(F9, "VendorID").addParameter("VendorID",r.getVendorId());
			b.addParameter(F9, "Sub Id").addParameter("Sub Id",r.getSubId());
			b.addParameter(F9, "ip_address").addParameter("ip_address",r.getIp());
			url = b.build().toURL();
		} catch (URISyntaxException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url.toString();
	}
	public static void addToList(Record r,String list) {
		String url = createURL(r,list);
		StringBuilder result = new StringBuilder();
		HttpURLConnection connection = null;
		BufferedReader rd = null;
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.connect();
			rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = null;
			while((line=rd.readLine())!=null)
				result.append(line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rd.close();
				connection.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public static String createURLForCareboom(Record r) {
		URIBuilder b = null;
		URL url = null;
		try {
			b = new URIBuilder(URL);
			b.addParameter(DOMAIN, "Prescription Experts");
			b.addParameter(LIST, "Careboom");
			b.addParameter(F9, "number1").addParameter("number1", r.getPhone());
			b.addParameter(F9, "first_name").addParameter("first_name", r.getFirstName());
			b.addParameter(F9, "last_name").addParameter("last_name", r.getLastName());
			b.addParameter(F9, "street").addParameter("street", r.getAddress());
			b.addParameter(F9, "city").addParameter("city", r.getCity());
			b.addParameter(F9, "state").addParameter("state", r.getState());
			b.addParameter(F9, "zip").addParameter("zip", r.getZip());
			b.addParameter(F9, "Gender").addParameter("Gender", r.getGender());
			b.addParameter(F9, "Date Of Birth").addParameter("Date Of Birth", r.getDob());
			//Insruance
			b.addParameter(F9, "Primary Insurance").addParameter("Primary Insurance", r.getCarrier());
			b.addParameter(F9, "Email").addParameter("Email", r.getEmail());
			b.addParameter(F9, "VendorID").addParameter("VendorID",r.getVendorId());
			b.addParameter(F9, "Sub Id").addParameter("Sub Id",r.getClickId());
			b.addParameter(F9, "ip_address").addParameter("ip_address",r.getIp());
			url = b.build().toURL();
		} catch (URISyntaxException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url.toString();
	}
}
