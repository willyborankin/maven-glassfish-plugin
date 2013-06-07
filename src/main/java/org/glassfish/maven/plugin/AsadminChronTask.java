package org.glassfish.maven.plugin;

public class AsadminChronTask {
	/**
	 * @parameter
	 */
	private String commandName;
	
	/**
	 * @parameter
	 */
	private String interval;

	/**
	 * @parameter
	 */
	private boolean skip;
	
	public String getCommandName() {
		return commandName;
	}
	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	public boolean isSkip() {
		return skip;
	}
	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
	
}
