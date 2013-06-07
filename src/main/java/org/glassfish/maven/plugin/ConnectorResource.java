package org.glassfish.maven.plugin;

public class ConnectorResource extends JndiAwareResource {

	/**
     * @parameter
     * @required
	 */
	private String type;
	
    /**
     * @parameter property = "connectorConnectionFactory"
     * @required
     */
	private ConnectorConnectionFactory connectorConnectionFactory;
	
	public ConnectorResource() {
		super();
	}

	@Override
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ConnectorConnectionFactory getConnectorConnectionFactory() {
		return connectorConnectionFactory;
	}

	public void setConnectorConnectionFactory(
			ConnectorConnectionFactory connectorConnectionFactory) {
		this.connectorConnectionFactory = connectorConnectionFactory;
	}

}
