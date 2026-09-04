package io.altar.jseproject.pratica2.textinterface.states;

import io.altar.jseproject.pratica2.repositories.ProductRepository;

public class MenuProducts extends State {
	private ProductRepository DBP = ProductRepository.getInstance();

	public int on() {
		DBP.getAllIds().forEach(id -> System.out.println("ID do produto : " + id));
		int[] options = new int[] { 1, 5 };
		System.out.println("1)\t Criar Produtos");
		if (!DBP.isEmpty()) {
			options = new int[] { 1, 2, 3, 4, 5 };
			System.out.println("2)\t Editar Produto");
			System.out.println("3)\t Consultar Produto");
			System.out.println("4)\t Remover Produto");
		}
		System.out.println("5)\t Voltar");

		return sc.getValidInt("Selecionar opção : ", options);
	}
}
