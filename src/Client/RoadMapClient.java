package Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class RoadMapClient {
	String table,database;
	public Connection connect = null;
	private final String HOST_NAME = "ltf5469.tam.us.siteprotect.com";
	public RoadMapClient() {
		try {
			//This will load the MySQL driver, each DB has its own driver
			 Class.forName("com.mysql.jdbc.Driver"); 
			 connect = DriverManager
				      .getConnection("jdbc:mysql://"+HOST_NAME+":3306/Road_Map", "tkuenzler","Tommy6847");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} 
		this.table = table;
		this.database = "Road_Map";
	}
	public void setTable(String pharmacy) {
		this.table = pharmacy;
	}
	public String[] getPharmacies() {
		String sql = "SELECT * FROM `PHARMCIES`";
		try {
			Statement stmt = connect.createStatement();
			ResultSet pharmacies = stmt.executeQuery(sql);
			List<String> list = new ArrayList<String>();
			while(pharmacies.next()) {
				list.add(pharmacies.getString("NAME"));
			}
			return list.toArray(new String[list.size()]);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public int isInRoadMap(String pharmacy,String pbm,String state) {
		String sql = "SELECT `"+pbm+"` FROM `"+pharmacy+"` WHERE `State` = '"+state+"'";
		int value;
		try {
			Statement stmt = connect.createStatement();
			ResultSet set = stmt.executeQuery(sql);
			if(set.next()) {
				value = set.getInt(pbm);
				set.close();
				stmt.close();
			}
			else  {
				set.close();
				stmt.close();
				return -2;
			}
			return value;
		} catch(SQLException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	public int isInRoadMap(Record record,String pharmacy) {
		String sql = "SELECT `"+record.getPBMFromBin(record.getBin())+"` FROM `"+pharmacy+"` WHERE `State` = '"+record.getState()+"'";
		int value;
		try {
			Statement stmt = connect.createStatement();
			ResultSet set = stmt.executeQuery(sql);
			if(set.next()) {
				value = set.getInt(record.getPBMFromBin(record.getBin()));
				set.close();
				stmt.close();
			}
			else  {
				set.close();
				stmt.close();
				return -2;
			}
			return value;
		} catch(SQLException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	public void close() {
		try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
