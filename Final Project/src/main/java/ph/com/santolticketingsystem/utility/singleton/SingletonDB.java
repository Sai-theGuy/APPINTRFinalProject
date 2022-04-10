package ph.com.santolticketingsystem.utility.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

import ph.com.santolticketingsystem.model.information.LoginCredential;
import ph.com.santolticketingsystem.model.information.LoginInformation;
import ph.com.santolticketingsystem.model.information.UserInformation;
import ph.com.santolticketingsystem.model.user.transaction.UserTransaction;
import ph.com.santolticketingsystem.model.user.transaction.UserTransactions;

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
		
		public static void insertUser(String driver, String url, String userName, String password, UserInformation ui) {
			String sql = "INSERT INTO users" 
					+ " (FirstName, LastName, PassengerType, UserName, PassWord) " 
					+ " VALUES(?, ?, ?, ?, ?);";
			
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
					
					prep.setString(1, ui.getFirstName());
					prep.setString(2, ui.getLastName());
					prep.setString(3, ui.getPassengerType());
					prep.setString(4, ui.getUsername());
					prep.setString(5, BCrypt.hashpw(ui.getPassword(), BCrypt.gensalt()));
		
					// now commit this to the database table
					prep.executeUpdate();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
			}
		}
		
		public static void insertLogin(String driver, String url, String userName, String password, LoginInformation li) {
			String sql = 
					"INSERT IGNORE INTO logins(UserName, PassWord, PassengerType) "
					+ "VALUES(?, ?, ?);";

			
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

					prep.setString(1, li.getUserName());
					prep.setString(2, BCrypt.hashpw(li.getPassword(), BCrypt.gensalt()));
					prep.setString(3, li.getPassengerType());
		
					// now commit this to the database table
					prep.executeUpdate();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
			}
		}
		
		public static void insertTransaction(String driver, String url, String userName, String password, UserTransaction ut) {
			String sql = 
					"INSERT INTO Transactions(UserName, DateTime, Start, Stop, Price) "
					+ "VALUES(?, now(), ?, ?, ?);";
			
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
					
					prep.setString(1, ut.getUsername());
					prep.setString(2, ut.getStart());
					prep.setString(3, ut.getDestination());
					prep.setDouble(4, ut.getPrice());
		
					// now commit this to the database table
					prep.executeUpdate();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
			}
		}
		
		public static LoginInformation selectLogin(String driver, String url, String userName, String password, LoginCredential inputCreds) {
			String sql = "SELECT * FROM logins WHERE UserName = ?";
			
			// initialize the jdbc configuration variables
			jdbcUrl = url;
			jdbcDriver = driver;
			dbUserName = userName;
			dbPassword = password;
			
			LoginInformation sheeesh = null;
		
			try {
				connection = getDBConnection();
				if (connection != null) {
					// create a PreparedStatement object
					PreparedStatement prep = connection.prepareStatement(sql);
					
					prep.setString(1, inputCreds.getUserName());
					
					// now commit this to the database table
					ResultSet rs = prep.executeQuery();
					// take result set
					while (rs.next()) {
						if(! BCrypt.checkpw(inputCreds.getPassword(), rs.getString("PassWord"))) {
							return null;
						}
						sheeesh = new LoginInformation();
						sheeesh.setUserName(rs.getString("UserName"));
						sheeesh.setPassengerType(rs.getString("PassengerType"));
					}
				}
				
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
				return null;
			}
			return sheeesh;
		}
		
		public static UserTransactions selectTransactions(String driver, String url, String userName, String password, String username) {
			
			String sql = "SELECT * FROM transactions WHERE UserName = ?;";
			
			// initialize the jdbc configuration variables
			jdbcUrl = url;
			jdbcDriver = driver;
			dbUserName = userName;
			dbPassword = password;
		
			try {
				connection = getDBConnection();
				UserTransactions transactions = new UserTransactions();
				
				if (connection != null) {
					// create a PreparedStatement object
					PreparedStatement prep = connection.prepareStatement(sql);
					
					prep.setString(1, username);
					// now commit this to the database table
					ResultSet rs = prep.executeQuery();
					// take result set
					while(rs.next())
					{
						UserTransaction transaction = new UserTransaction(
							rs.getInt("id")
							, rs.getString("UserName")
							, rs.getString("DateTime")
							, rs.getString("Start")
							, rs.getString("Stop")
							, rs.getDouble("Price")
						);
						
						transactions.addTransaction(transaction);
					}
				}
				return transactions;
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
