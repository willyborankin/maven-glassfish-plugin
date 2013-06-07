package org.glassfish.maven.plugin.command;

import org.glassfish.maven.plugin.Domain;
import org.glassfish.maven.plugin.GlassfishMojo;

/**
 * The enable-secure-admin subcommand causes the DAS and the instances
 * in the domain to use SSL certificates for encrypting the messages they send
 * to each other.<br/>
 * This subcommand also allows the DAS to accept administration messages from
 * remote admin clients such as the asadmin utility and IDEs.
 */
public class EnableSecureAdminCommand extends InteractiveAsadminCommand {
	
	private final Domain domain;
	
	public EnableSecureAdminCommand(GlassfishMojo sharedContext, Domain domain) {
		super(sharedContext);
		this.domain = domain;
	}

	@Override
	protected String getName() {
		return "enable-secure-admin";
	}
	

	@Override
	protected String getErrorMessage() {
		return "Unable to enable secure domain administration for \"" + domain.getName() + "\".";
	}

}
