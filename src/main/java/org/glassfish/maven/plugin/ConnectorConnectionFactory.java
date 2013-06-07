package org.glassfish.maven.plugin;

public class ConnectorConnectionFactory extends JndiAwareResource {

    /**
     * @parameter
     * @required
     */
    private String type;

    /**
     * @parameter
     * @required
     */
    private String resourceAdapter;
    
	public ConnectorConnectionFactory() {
		super();
	}

	@Override
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResourceAdapter() {
		return resourceAdapter;
	}

	public void setResourceAdapter(String resourceAdapter) {
		this.resourceAdapter = resourceAdapter;
	}

}
