package Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InfoDatabase {
	private Connection connect = null;
	public InfoDatabase() {
		try {
			//This will load the MySQL driver, each DB has its own driver
			 Class.forName("com.mysql.jdbc.Driver"); 
			 //Connect to database
			connect = DriverManager
					      .getConnection("jdbc:mysql://ltf5469.tam.us.siteprotect.com:3306/Info_Table", "tkuenzler","Tommy6847");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: "+e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: "+e.getMessage());
			e.printStackTrace();
		} 
	}
	public boolean isClosed() {
		try {
			return connect.isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
	}
	public boolean CheckZipcode(String zip) {
		String sql = "SELECT * FROM `SAMPLES_ZIP_CODES` WHERE `ZIP_CODE` = '"+zip+"'";
		Statement stmt = null;
		ResultSet set = null;
		try {
			stmt = connect.createStatement();
			set = stmt.executeQuery(sql);
			if(set.next())
				return true;
			else
				return false;
		} catch(SQLException ex) {
				ex.printStackTrace();
				return false;
		} catch(NullPointerException ex) {
			ex.printStackTrace();
			return true;
		} finally {
			try {
				if(stmt!=null) stmt.close();
				if(set!=null) set.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public int addToDNF(String fax) {
		String sql = "INSERT INTO `DNF` (`DONT_FAX`) VALUES ('"+fax+"')";
		Statement stmt = null;
		try {
			stmt = connect.createStatement();
			return stmt.executeUpdate(sql);
		} catch(SQLException ex) {
				ex.printStackTrace();
				return -1;
		} finally {
			try {
				if(stmt!=null) stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public boolean IsInDNF(String fax) {
		String sql  = "SELECT * FROM `DNF` WHERE `DONT_FAX` = '"+fax+"'";
		Statement stmt = null;
		ResultSet set = null;
		try {
			stmt = connect.createStatement();
			set = stmt.executeQuery(sql);
			if(set.next())
				return true;
			else
				return false;
		} catch(SQLException ex) {
			ex.printStackTrace();
			return true;
		} finally {
			try {
				if(stmt!=null) stmt.close();
				if(set!=null) set.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public int GetIncome(String zip) {
		String sql = "SELECT `45_64_INCOME` FROM `ALL_ZIPCODE_INCOME` WHERE `ZIP_CODE` = '"+zip+"'";
		Statement stmt = null;
		ResultSet set = null;
		try {
			stmt = connect.createStatement();
			set = stmt.executeQuery(sql);
			if(set.next())
				return Integer.parseInt(set.getString("45_64_INCOME"));
			else
				return -2;
		} catch(SQLException ex) {
			ex.printStackTrace();
			return -2;
		} finally {
			try {
				if(stmt!=null) stmt.close();
				if(set!=null) set.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
