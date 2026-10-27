package pt.upacademy.examples.hashMapExample;

public class HashMapExampleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		HashMapExample<User> users = new HashMapExample<User>();
		RepoUser users = RepoUser.getInstance();
		System.out.println(users);
		
//		HashMapExample<Product> products = new HashMapExample<Product>();
		RepoProduct products = RepoProduct.getInstance();
		System.out.println(products);


		// HashMapExample<Atum> atuns = new HashMapExample<Atum>();

	}

}
