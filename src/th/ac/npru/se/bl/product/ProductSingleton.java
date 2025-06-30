package th.ac.npru.se.bl.product;

import java.sql.*;

/**
 * The {@code ProductSingleton} class implements a singleton pattern for managing product data.
 * It provides methods to set product details, show the product, insert product into database,
 * and fetch product name by ID from the database.
 * <p>
 * This class assumes a MySQL database with a table named {@code Product}
 * with fields matching {@code p_id}, {@code p_name}, and {@code p_price}.
 * </p>
 * 
 * @author 
 */
public class ProductSingleton implements Manageble {

    // Singleton instance
    private static ProductSingleton instance = null;
    
    // Product attributes
    private String product_id;
    private String product_name;
    private int product_price;

    /**
     * Private constructor to prevent external instantiation.
     */
    private ProductSingleton() {}

    /**
     * Gets the singleton instance of {@code ProductSingleton}.
     *
     * @return the singleton instance
     */
    public static ProductSingleton getInstance() {
        if (instance == null) {
            instance = new ProductSingleton();
        }
        return instance;
    }

    /**
     * Displays the product information to the console.
     */
    public void showProduct() {
        System.out.println("Product_id " + product_id + " product_name " + product_name + " product_price " + product_price);
    }

    /**
     * Sets the product details.
     *
     * @param p_id    the product ID
     * @param p_name  the product name
     * @param p_price the product price
     */
    public void setProduct(String p_id, String p_name, int p_price) {
        this.product_id = p_id;
        this.product_name = p_name;
        this.product_price = p_price;
    }

    /**
     * Gets the product ID.
     *
     * @return the product ID
     */
    public String getProductId() {
        return product_id;
    }

    /**
     * Gets the product name.
     *
     * @return the product name
     */
    public String getProductName() {
        return product_name;
    }

    /**
     * Gets the product price.
     *
     * @return the product price
     */
    public int getProductPrice() {
        return product_price;
    }

    /**
     * Inserts the current product into the database.
     * Connects to a MySQL database named {@code testing} using root credentials.
     * Assumes the {@code Product} table exists with appropriate columns.
     */
    public void insertProduct() {
        Connection myConn = null;
        Statement myStmt = null;

        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "");
            System.out.println("Database connection successful!\n");

            myStmt = myConn.createStatement();
            String sql = "INSERT INTO Product VALUES ('" + this.product_id + "','" + this.product_name + "'," + this.product_price + ")";
            myStmt.executeUpdate(sql);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    /**
     * Retrieves the product name for a given product ID from the database.
     *
     * @param pid the product ID
     * @return the product name associated with the given ID, or an empty string if not found
     */
    public String getProductName(String pid) {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        String productName = "";

        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "");
            System.out.println("Database connection successful!\n");

            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT * FROM product WHERE p_id='" + pid + "'");

            while (myRs.next()) {
                productName = myRs.getString("p_name");
            }
            System.out.println(productName);

        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return productName;
    }

    /**
     * Deletes a product by its ID.
     * This implementation currently always returns false and is not implemented.
     *
     * @param id the product ID
     * @return false (not implemented)
     */
    public boolean deleteById(int id) {
        return false;
    }
}
