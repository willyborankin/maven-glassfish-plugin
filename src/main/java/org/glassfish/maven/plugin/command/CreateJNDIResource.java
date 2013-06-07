package org.glassfish.maven.plugin.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.glassfish.maven.plugin.Domain;
import org.glassfish.maven.plugin.GlassfishMojo;
import org.glassfish.maven.plugin.JndiAwareResource;

public abstract class CreateJNDIResource extends InteractiveAsadminCommand {

	protected Domain domain;
	protected JndiAwareResource resource;

	public CreateJNDIResource(GlassfishMojo sharedContext) {
		super(sharedContext);
	}

	protected List<String> getParameters() {
	    List<String> parameters = super.getParameters();
	    parameters.addAll(Arrays.asList(
	            "--restype", resource.getType().toString()
	    ));
	    parameters.addAll(getJNDIResourceParams());
	    addProperties(parameters, resource.getProperties());
	    parameters.add(resource.getJndiName());
	    return parameters;
	}
	
	protected List<String> getJNDIResourceParams(){
		return Collections.emptyList();
	}

}