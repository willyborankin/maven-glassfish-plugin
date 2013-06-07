package org.glassfish.maven.plugin.command;

import java.util.Arrays;
import java.util.List;

import org.glassfish.maven.plugin.Domain;
import org.glassfish.maven.plugin.GlassfishMojo;
import org.glassfish.maven.plugin.PropertiesJndiResource;

public class CreatePropertiesJNDIResource extends CreateJNDIResource {

	public CreatePropertiesJNDIResource(GlassfishMojo sharedContext, Domain domain, PropertiesJndiResource resource) {
		super(sharedContext);
		this.domain = domain;
		this.resource = resource;
	}

	@Override
	protected String getName() {
		return "create-custom-resource";
	}

	@Override
	protected String getErrorMessage() {
		return "cannot create properties resource";
	}
	
	@Override
	protected List<String> getJNDIResourceParams() {
		return Arrays.asList("--factoryclass", "org.glassfish.resources.custom.factory.PropertiesFactory");
	}

}
