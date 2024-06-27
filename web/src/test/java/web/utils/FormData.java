package web.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class FormData {
	
	private static final String boundary = "MNEMONICMNEMONICMNEMONIC";
	public static final String contentType = "multipart/form-data; boundary=#BOUNDARY#".replace("#BOUNDARY#", boundary);
	private List<Map<String, String>> parts = new ArrayList<>();
	
	public final void add(String name, String data) {
		Map<String, String> part = new HashMap<>();
		part.put("name", name);
		part.put("data", data);
		parts.add(part);
	}

	public final String toBodyString() {
		String delimiter = String.format("--%s\n", boundary);
		String prefix = String.format("--%s\n", boundary);
		String suffix = String.format("--%s--", boundary);

		return parts.stream().map(part -> {
			return String.format("""
					Content-Disposition: form-data; name="%s"\n\n%s
					""", part.get("name"), part.get("data"));
		}).collect(Collectors.joining(delimiter, prefix, suffix));
	}
}