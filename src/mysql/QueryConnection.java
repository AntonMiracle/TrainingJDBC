package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Project: SQLConnector QueryConnection.java
 */

/**
 * @author Bondarenko Anton Michailovich
 * @version 1.0
 *
 */
public class QueryConnection {
	private static Connection	dbConnection	= null;
	private static Statement	statement		= null;
	private static ResultSet	resultSet		= null;

	/////////////////////////// Start set options of connection to MySQL
	private static void getDBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/phone_book", "root", "root");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	/////////////////////////// End set options of connection to MySQL
	///////////////////////////// Start set options of connection to MySQL
	// private static void getDBConnection() {
	// try {
	// Class.forName("com.mysql.jdbc.Driver");
	// } catch (ClassNotFoundException e) {
	// System.out.println(e.getMessage());
	// }
	// try {
	// dbConnection =
	// DriverManager.getConnection("jdbc:mysql://sql8.freemysqlhosting.net:3306/sql8120623","sql8120623",
	///////////////////////////// "8bVs7Csv7r");
	// } catch (SQLException e) {
	// System.out.println(e.getMessage());
	// }
	// }
	///////////////////////////// End set options of connection to MySQL

	public synchronized static boolean updateTableExecute(String query) {
		try {
			getDBConnection();
			statement = dbConnection.createStatement();
			// выполнить SQL запрос
			statement.executeUpdate(query);
			return true;
			// JOptionPane.showMessageDialog(null, "Query: " + query + " is
			// execute!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			closeDBConnection();
		}
	}

	public synchronized static boolean createTableExecute(String query) {
		try {
			getDBConnection();
			statement = dbConnection.createStatement();
			// выполнить SQL запрос
			statement.execute(query);
			return true;
			// JOptionPane.showMessageDialog(null, "Query: " + query + " is
			// execute!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			closeDBConnection();
		}
	}

	public synchronized static boolean getResult(String query) {
		try {
			getDBConnection();
			statement = dbConnection.createStatement();
			// выполнить SQL запрос
			resultSet = statement.executeQuery(query);
			// И если что то было получено то цикл while сработает
			//
			// while (rs.next()) {
			// String userid = rs.getString("USER_ID");
			// String username = rs.getString("USERNAME");
			// String crDate = rs.getString("DATE");
			//
			// System.out.println("userid : " + userid);
			// System.out.println("username : " + username);
			// System.out.println("date : " + crDate);
			// System.out.println("Information select!");
			//
			// System.out.println(rs.getString(1));
			//
			// }

			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			closeDBConnection();
		}
	}

	
	public synchronized static String getContact() {
		String result = "";
		String query = "SELECT * "
				+ "FROM contacts c JOIN phone_list p  ON c.phone_list_id = p.id"
				+ " JOIN internet_data i ON c.internet_data_id = i.id ";	
		
		List<String> row = new ArrayList<String>();
		try {
			getDBConnection();
			statement = dbConnection.createStatement();
			// выполнить SQL запрос
			resultSet = statement.executeQuery(query);
			// И если что то было получено то цикл while сработает

			while (resultSet.next()) {
				
//				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				String lastname = resultSet.getString("lastname");
				String createDate = resultSet.getString("create_data");
				String phoneHome = resultSet.getString("home");
				String phoneWork = resultSet.getString("work");
				String phoneMobile = resultSet.getString("mobile");
				String phoneOther = resultSet.getString("other");
				String eMail = resultSet.getString("e_mail");
				String socialNetwork = resultSet.getString("social_network");
				String personalSite = resultSet.getString("personal_site");
				
					
//				System.out.println(id);	
				System.out.println(name);	
				System.out.println(surname);	
				System.out.println(lastname);	
				System.out.println(createDate);	
				System.out.println(phoneHome);	
				System.out.println(phoneWork);	
				System.out.println(phoneMobile);	
				System.out.println(phoneOther);	
				System.out.println(eMail);	
				System.out.println(socialNetwork);	
				System.out.println(personalSite);			

			}
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return result = "Error";
		} finally {
			closeDBConnection();
		}
	}
	
//	private List addToList
	
	public static ArrayList<String> getTableColumnNames(String tableName) {
		ArrayList<String> names = new ArrayList<String>();
		String selectTableSQL = "DESCRIBE " + tableName + "";
		try {
			getDBConnection();
			statement = dbConnection.createStatement();
			ResultSet rs = statement.executeQuery(selectTableSQL);
			while (rs.next()) {
				String column = rs.getString(1);
				if (!(column.toLowerCase().trim().equals("id"))) {
					names.add(column);
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return names;
	}

	private static boolean closeDBConnection() {
		try {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException ex) {
			System.out.println("Error on close connection");
			return false;
		}
		return true;
	}

}
