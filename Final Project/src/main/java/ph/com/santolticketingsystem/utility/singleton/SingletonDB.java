package ph.com.santolticketingsystem.utility.singleton;

import java.sql.*;
import java.util.ArrayList;

import ph.com.santolticketingsystem.model.user.transaction.UserTransaction;

public class SingletonDB {
	//this is defaulted to null
		private static Connection connection; 
		
		//for database setting
		private static String jdbcUrl;
		private static String jdbcDriver;
		private static String dbUserName;
		private static String dbPassword;
		
		private static Connection getDBConnection() {
			Connection connection = null;
			try {
				Class.forName(jdbcDriver);
				connection = DriverManager.getConnection(jdbcUrl, dbUserName, dbPassword);
			} catch (ClassNotFoundException cfne) {
				System.err.println(cfne.getMessage());
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
			}
			return connection;
		}
		
		//now this is the global method that can be accessed statically by
		//other classes when creating a Connection object
		public static Connection getConnection() {
			return (( connection !=null )? connection : getDBConnection());		
		}
		
		public static void createUsersTable(String driver, String url, String userName, String password) {

			String sql = 
					"CREATE TABLE IF NOT EXISTS users"
					+ "(id int NOT NULL AUTO_INCREMENT"
					+ ", FirstName NVARCHAR(500) NOT NULL"
					+ ", LastName NVARCHAR(500) NOT NULL"
					+ ", PassengerType NVARCHAR(500) NOT NULL"
					+ ", UserName NVARCHAR(500) NOT NULL"
					+ ", PassWord NVARCHAR(500) NOT NULL"
					+ ", PRIMARY KEY (id));";

			// initialize the jdbc configuration variables
			jdbcUrl = url;
			jdbcDriver = driver;
			dbUserName = userName;
			dbPassword = password;
			
			try {
				connection = getDBConnection();

				if (connection != null) {
					// create a PreparedStatement object
					PreparedStatement prep = connection.prepareStatement(sql);

					// now commit this to the database table
					prep.executeUpdate();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
			}
		}
		
		public static void createLoginsTable(String driver, String url, String userName, String password) {

			String sql = 
					// mcproducts table
					"CREATE TABLE IF NOT EXISTS logins"
					+ "(id int NOT NULL AUTO_INCREMENT"
					+ ", UserName NVARCHAR(500) NOT NULL"
					+ ", PassWord NVARCHAR(500) NOT NULL"
					+ ", PassengerType NVARCHAR(500) NOT NULL"
					+ ", PRIMARY KEY (id));";
			
			// initialize the jdbc configuration variables
			jdbcUrl = url;
			jdbcDriver = driver;
			dbUserName = userName;
			dbPassword = password;
		
			try {
				connection = getDBConnection();
		
				if (connection != null) {
					// create a PreparedStatement object
					PreparedStatement prep = connection.prepareStatement(sql);
		
					// now commit this to the database table
					prep.executeUpdate();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
			}
		}
		
		public static void createTransactionsTable(String driver, String url, String userName, String password) {

			String sql = 
					// mcproducts table
					"CREATE TABLE IF NOT EXISTS transactions"
					+ "(id int NOT NULL AUTO_INCREMENT"
					+ ", UserName NVARCHAR(500) NOT NULL"
					+ ", DateTime DATETIME NOT NULL"
					+ ", Start NVARCHAR(500) NOT NULL"
					+ ", Stop NVARCHAR(500) NOT NULL"
					+ ", Price NVARCHAR(500) NOT NULL"
					+ ", PRIMARY KEY (id));";
			
			// initialize the jdbc configuration variables
			jdbcUrl = url;
			jdbcDriver = driver;
			dbUserName = userName;
			dbPassword = password;
		
			try {
				connection = getDBConnection();
		
				if (connection != null) {
					// create a PreparedStatement object
					PreparedStatement prep = connection.prepareStatement(sql);
		
					// now commit this to the database table
					prep.executeUpdate();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
			}
		}
		
		public static void insertUser(String driver, String url, String userName, String password, String FirstName, String LastName, String UserName, String PassWord, String PassengerType) {
			String add = "('"  + FirstName + "','"  + LastName + "','" + PassengerType + "','" + UserName + "','" + PassWord + "')";
			String sql = 
					"INSERT IGNORE INTO logins(FirstName, LastName, PassengerType, UserName, PassWord) "
					+ "VALUES"
					+ add;
			
			// initialize the jdbc configuration variables
			jdbcUrl = url;
			jdbcDriver = driver;
			dbUserName = userName;
			dbPassword = password;
		
			try {
				connection = getDBConnection();
		
				if (connection != null) {
					// create a PreparedStatement object
					PreparedStatement prep = connection.prepareStatement(sql);
		
					// now commit this to the database table
					prep.executeUpdate();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
			}
		}
		
		public static void insertLogin(String driver, String url, String userName, String password, String UserName, String PassWord, String PassengerType) {
			String add = "('" + UserName + "','" + PassWord + "','" + PassengerType + "')";
			String sql = 
					"INSERT IGNORE INTO logins(UserName, PassWord, PassengerType) "
					+ "VALUES"
					+ add;

			
			// initialize the jdbc configuration variables
			jdbcUrl = url;
			jdbcDriver = driver;
			dbUserName = userName;
			dbPassword = password;
		
			try {
				connection = getDBConnection();
		
				if (connection != null) {
					// create a PreparedStatement object
					PreparedStatement prep = connection.prepareStatement(sql);
		
					// now commit this to the database table
					prep.executeUpdate();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
			}
		}
		
		public static void insertTransaction(String driver, String url, String userName, String password, String UserName, String Start, String End, String Price) {
			String add = "('" + UserName + "','"  + Start + "', now(),'" + End + "','" + Price + "')";
			String sql = 
					"INSERT IGNORE INTO Transactions(UserName, DateTime, Start, End, Price) "
					+ "VALUES"
					+ add;
			
			// initialize the jdbc configuration variables
			jdbcUrl = url;
			jdbcDriver = driver;
			dbUserName = userName;
			dbPassword = password;
		
			try {
				connection = getDBConnection();
		
				if (connection != null) {
					// create a PreparedStatement object
					PreparedStatement prep = connection.prepareStatement(sql);
		
					// now commit this to the database table
					prep.executeUpdate();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
			}
		}
		
		public static ArrayList<String> selectLogin(String driver, String url, String userName, String password, String name, String UserName, String PassWord) {
			
			String sql = "SELECT * FROM logins WHERE UserName = " + UserName + " AND PassWord = " + PassWord + ";";
			
			// initialize the jdbc configuration variables
			jdbcUrl = url;
			jdbcDriver = driver;
			dbUserName = userName;
			dbPassword = password;
		
			try {
				connection = getDBConnection();
				ArrayList<String> sheeesh = new ArrayList<String>();
				if (connection != null) {
					// create a PreparedStatement object
					PreparedStatement prep = connection.prepareStatement(sql);
					
					prep.setString(1, name);
					// now commit this to the database table
					ResultSet rs = prep.executeQuery();
					// take result set
					sheeesh.add(rs.getString(1));
					sheeesh.add(rs.getString(2));
					sheeesh.add(rs.getString(3));
				}
				return sheeesh;
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
				return null;
			}
		}
		
		public static ArrayList<UserTransaction> selectTransactions(String driver, String url, String userName, String password, String name) {
			
			String sql = "SELECT * FROM userTransactions";
			
			// initialize the jdbc configuration variables
			jdbcUrl = url;
			jdbcDriver = driver;
			dbUserName = userName;
			dbPassword = password;
		
			try {
				connection = getDBConnection();
				ArrayList<UserTransaction> trans1 = new ArrayList<UserTransaction>();
				
				int stockLeft = 0;
				if (connection != null) {
					// create a PreparedStatement object
					PreparedStatement prep = connection.prepareStatement(sql);
					
					prep.setString(1, name);
					// now commit this to the database table
					ResultSet rs = prep.executeQuery();
					// take result set
					while(rs.next())
					{
						UserTransaction trans2 = new UserTransaction(rs.getInt(0), rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(1));
						trans1.add(trans2);
					}
				}
				return trans1;
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
				return null;
			}
		}
		
		public static void testObjectEqualsProperties() {
					
			//now create a connection object
			try {
				Connection x = DriverManager
					.getConnection(jdbcUrl, dbUserName, dbPassword);
				
				Connection y = DriverManager
					.getConnection(jdbcUrl, dbUserName, dbPassword);
				
				Connection z = DriverManager
					.getConnection(jdbcUrl, dbUserName, dbPassword);
				
				if (x.equals(x)) {
					System.out.println("passed reflexive property: x.equals(x)");
				} else {
					System.out.println("NOT passed reflexive property: x.equals(x)");
				}
				
				if (x.equals(y) && y.equals(x)) {
					System.out.println("passed symmetric property: x.equals(y) && y.equals(x)");
				} else {
					System.out.println("NOT passed symmetric property: x.equals(y) && y.equals(x)");
				}
				
				//perform transitive property
				if (x.equals(y) &&  y.equals(z) && x.equals(z)){
					System.out.println("passed transitive property: x.equals(y) && y.equals(z)"
						+ " && " + "x.equals(z)");
				}else {
					System.out.println("NOT passed transitive property: x.equals(y) && y.equals(z)"
						+ "&& x.equals(z)");			
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
