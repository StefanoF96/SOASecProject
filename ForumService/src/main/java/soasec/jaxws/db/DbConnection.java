package soasec.jaxws.db;
import java.sql.*;

public class DbConnection {
	
	private static final String DB_URL = "jdbc:mysql://localhost/soasec_db";
	private static final String USER = "soasecproject";
	private static final String PASS = "Pippo123?";
	private Connection conn;
   
   public DbConnection() {
	   super();
	   connect();	   
   }
   
   public boolean connect() {
	   try {
	       Class.forName("com.mysql.jdbc.Driver");
	       System.out.println("Driver loaded!");
	   } catch (ClassNotFoundException e) {
	       throw new IllegalStateException("Cannot find the driver in the classpath!", e);
	   }
	   try {
		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
	   } catch (SQLException e) {
		   System.err.println("Cannot connect the database!");
	       e.printStackTrace();
	       return false;
	      } 
	   return true;
   }
   
   public ResultSet executeQuery(String query) throws SQLException {
		   Statement stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery(query);
		   return rs;
   }
   
   
   public void executeUpdate(String query) throws SQLException {
		   Statement stmt = conn.createStatement();
		   stmt.executeUpdate(query);
   }

   public static void main(String[] args) throws SQLException {
	   //TESTING CONNECTIVITY
	  /*
	   System.out.println("Loading driver...");

	   try {
	       Class.forName("com.mysql.jdbc.Driver");
	       System.out.println("Driver loaded!");
	   } catch (ClassNotFoundException e) {
	       throw new IllegalStateException("Cannot find the driver in the classpath!", e);
	   }
	   
	   try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
		    System.out.println("Database connected!");
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	  */

	   //test db
	   String test_query = "SELECT id, first, last, age FROM TestEmployees";
	   // Open a connection
	   DbConnection conn = new DbConnection();
	   ResultSet result = conn.executeQuery(test_query);
	   try {
		if (!result.isBeforeFirst() ) {    
			    System.out.println("No data"); 
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	
	   try {
		   while (result.next()) {
			   // Retrieve by column name
			   System.out.print("ID: " + result.getInt("id"));
			   System.out.print(", Age: " + result.getInt("age"));
			   System.out.print(", First: " + result.getString("first"));
			   System.out.println(", Last: " + result.getString("last"));
		   }
	   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	   }

   }
   
}
