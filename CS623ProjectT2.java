package cs623_project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CS623ProjectT2 {

	public static void main(String[] args)  throws SQLException, IOException{
		

		System.out.println("Group 1/7 – The product p1 is deleted from Product and Stock. ");
		
		//	Group 5/11 – We add a product (p100, cd, 5) in Product and (p100, d2, 50) in Stock.
		
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

		// For atomicity
		conn.setAutoCommit(false);

		// For isolation
		conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

		Statement stmt = null;
		try {
			// Create statement object
			stmt = conn.createStatement();
			
			
			// atomicity.
			stmt.executeUpdate("delete from stock  where prodid='P1'");
			stmt.executeUpdate("delete from product  where prodid='P1'");
			
		} catch (SQLException e) {
			System.out.println("An exception was thrown");
			e.printStackTrace();
			// For atomicity
			conn.rollback();
			stmt.close();
			conn.close();
			return;
		}
		System.out.println("Deleted The product p1 from Product and Stock Successfully.");

		conn.commit();
		stmt.close();
		conn.close();
	}

}
