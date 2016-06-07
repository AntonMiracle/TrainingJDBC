package mysql;

/**
 * SQLConnector Main.java
 * 
 */
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 31 ñ³÷. 2016 ð.
 * 
 * @author Bondarenko A.M.
 * @version 1.0
 */
public class Main {

	public static void main(String[] argv) {

//		Query query = new Query();
		String query = "";
//		QueryConnection.updateTableExecute(query);
		
		
		
		QueryConnection.getContact();
	}

}
