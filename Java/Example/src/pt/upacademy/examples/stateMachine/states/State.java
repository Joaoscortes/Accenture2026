package pt.upacademy.examples.stateMachine.states;

public abstract class State {
	protected ScannerUtils sc = new ScannerUtils();

	public abstract int on();
}
