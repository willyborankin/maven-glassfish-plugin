package org.glassfish.maven.plugin.command;

import org.glassfish.maven.plugin.Domain;
import org.glassfish.maven.plugin.GlassfishMojo;

/**
 * The disable-secure-admin subcommand disables secure admin if it is already enabled.
 */
public class DisableSecureAdminCommand extends InteractiveAsadminCommand {
	
	private final Domain domain;
	
	public DisableSecureAdminCommand(GlassfishMojo sharedContext, Domain domain) {
		super(sharedContext);
		this.domain = domain;
	}

	@Override
	protected String getName() {
		return "disable-secure-admin";
	}

	@Override
	protected String getErrorMessage() {
		return "Unable to disable secure domain administration for \"" + domain.getName() + "\".";
	}

}
