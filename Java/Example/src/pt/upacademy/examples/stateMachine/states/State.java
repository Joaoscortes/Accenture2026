package pt.upacademy.examples.stateMachine.states;

import io.altar.jseproject.utils.scannerUtils.ScannerUtils;

public abstract class State {
	protected ScannerUtils sc = new ScannerUtils();

	public abstract int on();
}
