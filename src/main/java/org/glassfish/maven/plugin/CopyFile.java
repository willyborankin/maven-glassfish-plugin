package org.glassfish.maven.plugin;

public class CopyFile {
	/**
	 * @parameter
	 */
	private String srcFile;
	
	/**
	 * @parameter
	 */
	private String destFile;

	/**
	 * @parameter
	 */
	private boolean skip;
	
	public String getSrcFile() {
		return srcFile;
	}
	public void setSrcFile(String srcFile) {
		this.srcFile = srcFile;
	}
	public String getDestFile() {
		return destFile;
	}
	public void setDestFile(String destFile) {
		this.destFile = destFile;
	}
	public boolean isSkip() {
		return skip;
	}
	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
}
