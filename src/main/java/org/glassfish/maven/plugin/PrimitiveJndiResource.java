package org.glassfish.maven.plugin;

import java.util.Set;

public class PrimitiveJndiResource extends JndiAwareResource {
	
    /**
     * @parameter
     * @required
     */
	private String type;
	
    /**
     * @parameter
     * @required
     */
	private String value;

	@Override
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public Set<Property> getProperties() {
		Set<Property> properties = super.getProperties();
		properties.add(new Property("value", value));
		return properties;
	}
	
	
	
}
