package io.altar.auth_example.util;

import java.net.URI;
import java.net.URISyntaxException;

public final class PhotoUrlValidator {

	public static final int MAX_LENGTH = 2048;

	private PhotoUrlValidator() {}

	/** Accepts null/blank (clears the photo) or an absolute http(s) URL within the length limit. */
	public static boolean isValid(String url) {
		if (url == null || url.trim().isEmpty()) {
			return true;
		}
		if (url.length() > MAX_LENGTH) {
			return false;
		}
		try {
			URI uri = new URI(url);
			String scheme = uri.getScheme();
			return ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme))
					&& uri.getHost() != null;
		} catch (URISyntaxException e) {
			return false;
		}
	}
}
