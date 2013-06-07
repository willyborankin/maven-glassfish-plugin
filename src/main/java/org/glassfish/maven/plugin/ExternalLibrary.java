package org.glassfish.maven.plugin;

public class ExternalLibrary {
	
	/**
	 * @parameter
	 * @required
	 */
	private String groupId;
	
	/**
	 * @parameter
	 * @required
	 */
	private String artifactId;

	/**
	 * @parameter
	 */
	private String path;

	public String getGroupId() {
		return groupId;
	}

	
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	
	public String getArtifactId() {
		return artifactId;
	}

	
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public String toString() {
		return "ExternalLibrary [groupId=" + groupId + ", artifactId="
				+ artifactId + ", path=" + path + "]";
	}
	
}
