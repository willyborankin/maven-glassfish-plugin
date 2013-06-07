package org.glassfish.maven.plugin.command;

import java.util.Arrays;
import java.util.List;

import org.glassfish.maven.plugin.ConnectorResource;
import org.glassfish.maven.plugin.Domain;
import org.glassfish.maven.plugin.GlassfishMojo;

public class CreateConnectorResourceCommand extends InteractiveAsadminCommand {

	private final Domain domain;
	
	private final ConnectorResource connectorResource;
	
	public CreateConnectorResourceCommand(GlassfishMojo sharedContext, Domain domain, ConnectorResource connectorResource) {
		super(sharedContext);
		this.domain = domain;
		this.connectorResource = connectorResource;
	}

	@Override
	protected String getName() {
		return "create-connector-resource";
	}

	@Override
	protected String getErrorMessage() {
		return null;
	}

	@Override
	protected List<String> getParameters() {
        List<String> parameters = super.getParameters();
        parameters.addAll(Arrays.asList(
                "--host", domain.getHost(),
                "--port", String.valueOf(domain.getAdminPort()),
                "--poolname", connectorResource.getConnectorConnectionFactory().getJndiName(),
                connectorResource.getJndiName()
        ));
        return parameters;
	}

}
