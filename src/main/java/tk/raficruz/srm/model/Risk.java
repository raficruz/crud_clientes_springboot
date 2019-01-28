package tk.raficruz.srm.model;

import java.util.Arrays;

public enum Risk {
	A("Low"), B("Medium"), C("High");

	private String description;

	Risk(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static Risk get(String index) {
		return Arrays.stream(Risk.values())
	            .filter(r -> r.name().equals(index))
	            .findFirst()
	            .orElseThrow(() -> new IllegalStateException(String.format("Unsupported type %s.", index)));
	}
}
