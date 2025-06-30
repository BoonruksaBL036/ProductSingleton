import th.ac.npru.se.bl.product.ProductSingleton;

public class TestDriver {
	
	public static void main(String[] args) {
		ProductSingleton p1 = ProductSingleton.getInstance();
//		p1.showProduct();
		p1.setProduct("P005","Switch2", 19800);
		p1.showProduct();
		p1.insertProduct();
		String p_name = p1.getProductName("P002");
		System.out.println("Product name :"+p_name);
	}
}
