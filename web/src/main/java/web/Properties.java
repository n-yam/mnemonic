package web;

import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public final class Properties {

	private final ResourceBundle rb;

	public Properties(String baseName) {
		rb = ResourceBundle.getBundle(baseName);
	}

	public final String getString(String key) {
		return rb.getString(key);
	}

	public final int getInt(String key) {
		String value = rb.getString(key);
		return Integer.valueOf(value);
	}
	
	public final Set<String> getSetOfStrings(String key) {
		String[] values = rb.getString(key).split(",");
		return new HashSet<String>(Arrays.asList(values));
	}
}
