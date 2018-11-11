import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class jdbcconn {

	public static void main(String[] args) throws Exception {
		//XML parsed data will go here or in the postdata, will decide on it. 
		postData();
	}
	
	public static void postData() throws Exception{
		try {
			Connection con = getConnection();
			final String dvc_id = "Device ID 2";
			final String path_nm = "Device path is";
			final String meas_nm = "Measurement name is";
			final String meas_time = "2018-01-03";
			final String meas_desc = "Measurement Description is";
			final double meas_val = 97.75;
			final String meas_qlty = "Meas qlty is";
			
			String insertSQL = "INSERT INTO measurements (DEVICE_ID, PATH_NAME, MEASUREMENT_NAME, " +
								" MEASUREMENT_TIMESTAMP, MEASUREMENT_DESCRIPTION, MEASUREMENT_VALUE, " +
								" MEASUREMENT_QUALITY) VALUES('" + dvc_id + "','" + path_nm + "','" + meas_nm +
								"','" + meas_time + "','" + meas_desc + "'," + meas_val + ",'" + meas_qlty + "')";
			
			System.out.println(insertSQL);
			
			PreparedStatement insertedrecord = con.prepareStatement(insertSQL);
			insertedrecord.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
			System.out.println("Record Inserted");
		}
	}

	public static Connection getConnection() throws Exception{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		    String dbName = "gridiq";
		    String userName = "master";
		    String password = "master123";
		    String hostname = "gridiq.cfrpuucjtl63.us-west-2.rds.amazonaws.com";
		    String port = "3306";
		    String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
			
		    Connection con = DriverManager.getConnection(jdbcUrl);
			System.out.println("Connection established");
			return con;
		}
		catch (Exception e){
			System.out.println(e);
		}
		
		return null;
	}
}