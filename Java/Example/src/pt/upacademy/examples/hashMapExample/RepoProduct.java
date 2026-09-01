package pt.upacademy.examples.hashMapExample;

public class RepoProduct extends HashMapExample<Product> {

	private static final RepoProduct INSTANCE = new RepoProduct();

	public static RepoProduct getInstance() {
		return INSTANCE;
	}

	private RepoProduct() {}
}
