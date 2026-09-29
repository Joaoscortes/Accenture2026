package io.altar.jeeexample.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.altar.jeeexample.persistence.StorePersistence;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class StoreTest {

	@Inject
	StorePersistence persistence;

	@Test
	@DisplayName("Test constructor")
	public void testConstructor() {
		Store s = new Store();
		long id = s.getId();

		assertEquals(0, id);
	}

	@Test
	@Transactional
	public void test1() {
		int num1 = 0;
		int num2 = num1;
		num1++;

		assertNotEquals(num1, num2);

		Store s = new Store();
		s.setName("Ze");
		Store persisted = persistence.create(s);

		assertNotEquals(0, persisted.getId());
		assertEquals("Ze", persistence.read(persisted.getId()).getName());

		Store s2 = SerializationUtils.clone(s);
		s.setId(1);

		assertNotEquals(s, s2);
	}
}
