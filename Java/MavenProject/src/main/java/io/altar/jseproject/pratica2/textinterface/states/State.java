package io.altar.jseproject.pratica2.textinterface.states;

import io.altar.jseproject.pratica1.utils.ScannerUtils;
import io.altar.jseproject.pratica2.repositories.ProductRepository;
import io.altar.jseproject.pratica2.repositories.ShelfRepository;

public abstract class State {
	protected final ScannerUtils SCANNER_UTILS = new ScannerUtils();
	protected final ProductRepository PRODUCT_DB = ProductRepository.getInstance();
	protected final ShelfRepository SHELF_DB = ShelfRepository.getInstance();

	public abstract int on();
}
