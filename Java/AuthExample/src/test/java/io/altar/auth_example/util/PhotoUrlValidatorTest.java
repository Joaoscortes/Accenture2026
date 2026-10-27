package io.altar.auth_example.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PhotoUrlValidatorTest {

	@Test
	public void acceptsHttpAndHttps() {
		assertTrue(PhotoUrlValidator.isValid("https://example.com/a.png"));
		assertTrue(PhotoUrlValidator.isValid("http://example.com/a.png"));
	}

	@Test
	public void acceptsNullAndBlank() {
		assertTrue(PhotoUrlValidator.isValid(null));
		assertTrue(PhotoUrlValidator.isValid("  "));
	}

	@Test
	public void rejectsUnsafeOrMalformed() {
		assertFalse(PhotoUrlValidator.isValid("javascript:alert(1)"));
		assertFalse(PhotoUrlValidator.isValid("data:text/html;base64,AAAA"));
		assertFalse(PhotoUrlValidator.isValid("/relative/a.png"));
		assertFalse(PhotoUrlValidator.isValid("https://exa mple.com"));
	}

	@Test
	public void rejectsTooLong() {
		StringBuilder sb = new StringBuilder("https://example.com/");
		while (sb.length() <= PhotoUrlValidator.MAX_LENGTH) {
			sb.append('a');
		}
		assertFalse(PhotoUrlValidator.isValid(sb.toString()));
	}
}
