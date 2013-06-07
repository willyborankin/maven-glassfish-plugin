package org.glassfish.maven.plugin;

import java.util.Properties;

public class PropertiesJndiResource extends JndiAwareResource {

	@Override
	public String getType() {
		return Properties.class.getName();
	}

}
