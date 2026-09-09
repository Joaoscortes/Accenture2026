package io.altar.jseproject.pratica2.textinterface.states;

import io.altar.jseproject.pratica1.utils.ScannerUtils;
import io.altar.jseproject.pratica2.services.ProductService;
import io.altar.jseproject.pratica2.services.ShelfService;

public abstract class State {
	protected final ScannerUtils SCANNER_UTILS = new ScannerUtils();
	protected ProductService ps = new ProductService();
	protected ShelfService ss = new ShelfService();

	public abstract int on();
}
