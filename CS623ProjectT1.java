package cs623_project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CS623ProjectT1 {

	public static void main(String[] args)  throws SQLException, IOException{
		

		System.out.println("Group 5/11 – We add a product (p100, cd, 5) in Product and (p100, d2, 50) in Stock.");
		
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
			
			
			// atomicity
			stmt.executeUpdate("insert into product(prodid,pname,price) values\r\n"
					+ "('p100','cd',5)");
			stmt.executeUpdate("insert into stock(prodid,depid,quantity) values\r\n"
					+ "('p100','D2',50)");
			
		} catch (SQLException e) {
			System.out.println("An exception was thrown");
			e.printStackTrace();
			// For atomicity
			conn.rollback();
			stmt.close();
			conn.close();
			return;
		}
		System.out.println("Added product (p100, cd, 5) in Product and (p100, d2, 50) in Stock Successfully.");

		conn.commit();
		stmt.close();
		conn.close();
	}

}
