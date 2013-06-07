package org.glassfish.maven.plugin.command;

import java.util.Arrays;
import java.util.List;

import org.glassfish.maven.plugin.Domain;
import org.glassfish.maven.plugin.GlassfishMojo;
import org.glassfish.maven.plugin.JavaMailResource;

public class CreateJavaMailResourceCommand extends InteractiveAsadminCommand {

	private final Domain domain;
	private final JavaMailResource mailResource;

	public CreateJavaMailResourceCommand(GlassfishMojo sharedContext, Domain domain, JavaMailResource mailResource) {
		super(sharedContext);
		this.domain = domain;
		this.mailResource = mailResource;
	}

	@Override
	protected String getName() {
		return "create-javamail-resource";
	}

	@Override
	protected List<String> getParameters() {
		List<String> parameters = super.getParameters();
        parameters.addAll(Arrays.asList(
                "--mailhost", mailResource.getMailHost(),
                "--mailuser", mailResource.getMailUser(),
                "--fromaddress", mailResource.getFromAddress(),
                "--enabled", mailResource.getEnabled().toString(),
                "--storeprotocol", mailResource.getStoreProtocol(),
                "--storeprotocolclass", mailResource.getStoreProtocolClass(),
                "--transprotocol", mailResource.getTransportProtocol(),
                "--transprotocolclass", mailResource.getTransportProtocolClass()
        ));
        if (mailResource.getDescription() != null && !mailResource.getDescription().isEmpty()) {
        	parameters.addAll(Arrays.asList(
        		"--description", mailResource.getDescription()
        	));
        }
        addProperties(parameters, mailResource.getProperties());
        parameters.add(mailResource.getName());
        return parameters;
	}

	@Override
	protected String getErrorMessage() {
        return "Unable to create JavaMail resource " + mailResource.getName() + ".";
    }

}
