package io.altar.jseproject.pratica2.textinterface.states;

import io.altar.jseproject.pratica1.utils.ScannerUtils;

public abstract class State {
	protected ScannerUtils sc = new ScannerUtils();

	public abstract int on();
}
