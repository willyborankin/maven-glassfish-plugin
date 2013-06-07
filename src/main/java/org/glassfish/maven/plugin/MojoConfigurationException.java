package org.glassfish.maven.plugin;

import java.util.List;

import org.apache.maven.plugin.MojoFailureException;

public class MojoConfigurationException extends MojoFailureException {

	private static final long serialVersionUID = -316675226303599803L;

	public MojoConfigurationException(GlassfishMojo mojo, List<String> configErrors) {
        super(errorMessage(mojo, configErrors));
    }

    private static String errorMessage(GlassfishMojo mojo, List<String> configErrors) {
    	
        StringBuilder errorMessage = new StringBuilder("One or more required plugin parameters are invalid/missing for '")
                .append(mojo.getPrefix()).append(':').append(mojo.getGoal()).append("'\n\n");
        for (String error : configErrors) {
            errorMessage.append(error).append("\n");
        }
        return errorMessage.toString();
    }
}
