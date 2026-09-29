package io.altar.jeeexample.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StoreTest {

	@Test
	@DisplayName("Test constructor")
	public void testConstructor() {
		Store s = new Store();
		long id = s.getId();

		assertEquals(0, id);
	}

	@Test
	public void test1() {
		int num1 = 0;
		int num2 = num1;
		num1++;

		assertNotEquals(num1, num2);

		Store s = new Store();
		s.setName("Ze");
		Store s2 = SerializationUtils.clone(s);
		s.setId(1);

		assertNotEquals(s, s2);
	}
}
