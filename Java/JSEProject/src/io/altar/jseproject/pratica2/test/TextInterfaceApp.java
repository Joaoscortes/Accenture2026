package io.altar.jseproject.pratica2.test;

import io.altar.jseproject.pratica2.textinterface.TextInterfaceStateMachine;

public class TextInterfaceApp {

	public static void main(String[] args) {
//		TextInterface ti = new TextInterface();
		TextInterfaceStateMachine ti = new TextInterfaceStateMachine();
		ti.start();
	}

}
