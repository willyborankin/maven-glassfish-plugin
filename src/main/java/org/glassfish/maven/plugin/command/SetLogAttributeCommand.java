package org.glassfish.maven.plugin.command;

import java.util.List;

import org.glassfish.maven.plugin.GlassfishMojo;
import org.glassfish.maven.plugin.Property;

public class SetLogAttributeCommand extends InteractiveAsadminCommand {


	private Property property;

	public SetLogAttributeCommand(GlassfishMojo sharedContext, Property property) {
		super(sharedContext);
		this.property = property;
	}
	

	@Override
	protected String getName() {
		return "set-log-attributes";
	}
	

	@Override
	protected List<String> getParameters() {
		List<String> parameters = super.getParameters();
		parameters.add(property.getName()+"="+property.getValue());
		return parameters;
	}


	@Override
	protected String getErrorMessage() {
		return "cannot set log attributes";
	}

}
