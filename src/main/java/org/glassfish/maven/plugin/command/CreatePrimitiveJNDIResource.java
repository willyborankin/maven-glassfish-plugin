package org.glassfish.maven.plugin.command;

import java.util.Arrays;
import java.util.List;

import org.glassfish.maven.plugin.Domain;
import org.glassfish.maven.plugin.GlassfishMojo;
import org.glassfish.maven.plugin.PrimitiveJndiResource;

public class CreatePrimitiveJNDIResource extends CreateJNDIResource {

	public CreatePrimitiveJNDIResource(GlassfishMojo sharedContext, Domain domain, PrimitiveJndiResource resource) {
		super(sharedContext);
		this.domain= domain;
		this.resource = resource;
	}

	@Override
	protected String getName() {
		return "create-custom-resource";
	}
	
	@Override
	protected List<String> getJNDIResourceParams() {
		return Arrays.asList("--factoryclass", "org.glassfish.resources.custom.factory.PrimitivesAndStringFactory");
	}

	@Override
	protected String getErrorMessage() {
		return "cannot create custom resource";
	}

}
