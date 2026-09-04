package io.altar.jseproject.pratica2.textinterface.states;

public class MenuInit extends State {
	public int on() {
		System.out.println("Por favor selecione uma das seguintes opções:");
		System.out.println("\t1) Listar produtos");
		System.out.println("\t2) Listar prateleiras");
		System.out.println("\t3) Sair");
		return sc.getValidInt("Selecionar opção : ", 1, 3);
	}
}
