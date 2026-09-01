package pt.upacademy.examples.hashMapExample;

public class RepoUser extends HashMapExample<User> {

	private static final RepoUser INSTANCE = new RepoUser();

	public static RepoUser getInstance() {
		return INSTANCE;
	}

	private RepoUser() {}
}
