package org.glassfish.maven.plugin.command;

import java.util.Arrays;
import java.util.List;

import org.glassfish.maven.plugin.ConnectorConnectionFactory;
import org.glassfish.maven.plugin.Domain;
import org.glassfish.maven.plugin.GlassfishMojo;

public class CreateConnectorPoolFactoryCommand extends InteractiveAsadminCommand {

	private final Domain domain;
	
	private final ConnectorConnectionFactory connectorConnectionFactory;
	
	public CreateConnectorPoolFactoryCommand(GlassfishMojo sharedContext, Domain domain, ConnectorConnectionFactory connectorConnectionFactory) {
		super(sharedContext);
		this.domain = domain;
		this.connectorConnectionFactory = connectorConnectionFactory;
	}

	@Override
	protected String getName() {
		return "create-connector-connection-pool";
	}

	@Override
	protected String getErrorMessage() {
		return "Unable to create JMS destination " + connectorConnectionFactory.getJndiName() + ".";
	}

	@Override
	protected List<String> getParameters() {
        List<String> parameters = super.getParameters();
        parameters.addAll(Arrays.asList(
                "--port", String.valueOf(domain.getAdminPort()),
                "--raname", connectorConnectionFactory.getResourceAdapter(),
                "--connectiondefinition", connectorConnectionFactory.getType()
        ));
        addProperties(parameters, connectorConnectionFactory.getProperties());
        parameters.add(connectorConnectionFactory.getJndiName());
        return parameters;
	}

}
