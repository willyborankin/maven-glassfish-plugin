package org.glassfish.maven.plugin;

public class ConfigReplacement {
	
	/**
	 * @parameter
	 */
	private String pattern;
	
	/**
	 * @parameter
	 */
	private String replacement;

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getReplacement() {
		return replacement;
	}

	public void setReplacement(String replacement) {
		this.replacement = replacement;
	}

}
