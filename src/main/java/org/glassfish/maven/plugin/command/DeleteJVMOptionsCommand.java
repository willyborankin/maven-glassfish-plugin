package org.glassfish.maven.plugin.command;

import java.util.Set;

import org.glassfish.maven.plugin.Domain;
import org.glassfish.maven.plugin.GlassfishMojo;

public class DeleteJVMOptionsCommand extends CreateJVMOptionsCommand {

	public DeleteJVMOptionsCommand(GlassfishMojo sharedContext, Domain domain) {
		super(sharedContext, domain);
	}

	@Override
	protected String getName() {
		return "delete-jvm-options";
	}

    protected String getErrorMessage() {
        return "Unable to delete JVM options for domain \"" + domain.getName() + "\".";
    }

	@Override
	protected Set<String> getOptions() {
		return domain.getDeleteJvmOptions();
	}
    
    
}
