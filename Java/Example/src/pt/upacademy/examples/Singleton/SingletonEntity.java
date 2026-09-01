package pt.upacademy.examples.Singleton;

public class SingletonEntity {
	/*
 Criar um atributo (chamado INSTANCE) private static final do tipo da própria classe, ao
qual é atribuída na sua inicialização, uma instância da própria classe

b. Criar um método public static chamado getInstance que não recebe parâmetros e que
devolve um ProductRepository. Este método devolve o conteúdo da variável INSTANCE

c. Criar um construtor (o único) que não recebe parâmetros e que é private
	 */
	
	private static final SingletonEntity INSTANCE = new SingletonEntity();
	
	public static SingletonEntity getInstance() {
		return INSTANCE;
	}
	
	private SingletonEntity() {}
	
}
