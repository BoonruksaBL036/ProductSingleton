package th.ac.npru.se.bl.product;

import java.sql.*;

public class ProductSingleton {
	private static ProductSingleton instance = null;
	private String product_id;
	private String product_name;
	private int product_price;
	private ProductSingleton() {
		
	}
	public static ProductSingleton getInstance(){
		if(instance == null) {
			instance = new ProductSingleton();
		}
		return instance;
	}
	
	public void showProduct() {
		System.out.println("Product_id " + product_id + "product_name " + product_name + "product_price " + product_price);
	}
	
	public void setProduct(String p_id, String p_name, int p_price) {
		this.product_id = p_id;
		this.product_name = p_name;
		this.product_price = p_price;
	}
	public String getProductId() {
		return product_id;
	}

	public String getProductName() {
		return product_name;
	}

	public int getProductPrice() {
		return product_price;
	}
	
	public void insertProduct() {
		Connection myConn = null;
		Statement myStmt = null;

		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testling", "root" , "");
			
			System.out.println("Database connection successful!\n");
			
			// 2. Create a statement
			myStmt = myConn.createStatement();
			
			// 3. Execute SQL query
			String sql = "INSERT INTO Product " +
	                   "VALUES ('"+this.product_id+"','"+this.product_name+"',"+this.product_price+")";
			myStmt.executeUpdate(sql);

		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public String getProductName(String pid) {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		String productName="";
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testling", "root" , "");
			
			System.out.println("Database connection successful!\n");
			
			// 2. Create a statement
			myStmt = myConn.createStatement();
			
			// 3. Execute SQL query
			myRs = myStmt.executeQuery("select * from product where p_id='"+pid+"'");
			
			// 4. Process the result set
			while (myRs.next()) {
				productName = myRs.getString("p_name");
			}
			System.out.println(productName);
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		return productName;
	}

}
