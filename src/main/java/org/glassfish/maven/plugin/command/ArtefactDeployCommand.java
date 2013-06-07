package org.glassfish.maven.plugin.command;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.maven.artifact.Artifact;
import org.glassfish.maven.plugin.Deployable;
import org.glassfish.maven.plugin.Domain;
import org.glassfish.maven.plugin.GlassfishMojo;

public class ArtefactDeployCommand extends InteractiveAsadminCommand {

	private final Deployable deployable;
	
	private final Domain domain;
	
	public ArtefactDeployCommand(GlassfishMojo sharedContext, Domain domain, Deployable deployable) {
		super(sharedContext);
		this.domain = domain;
		this.deployable = deployable;
	}

	@Override
	protected List<String> getParameters() {
        boolean upload = true;
        try {
            upload = !InetAddress.getByName(domain.getHost()).isLoopbackAddress();
        } catch (UnknownHostException e) {
            // ignore
        }
        List<String> parameters = super.getParameters();
        Artifact artifact = findArtifact(deployable);
        if (artifact != null) {
            File file = artifact.getFile();
	        parameters.addAll(Arrays.asList(
	                "--upload=" + upload,
	                "--name", deployable.getArtifactId(),
	                file.getAbsolutePath()
	        ));
        }
        return parameters;
	}

	@SuppressWarnings("unchecked")
	private Artifact findArtifact(Deployable deployable) {
		Artifact foundArtifact = null;
		for (Iterator<Artifact> artifactsIterator = sharedContext.getProject().getArtifacts().iterator(); artifactsIterator.hasNext();) {
			Artifact artifact = artifactsIterator.next();
			if (artifact.getGroupId().equals(deployable.getGroupId()) && 
				artifact.getArtifactId().equals(deployable.getArtifactId())) {
				foundArtifact = artifact;
				break;
			}
		}
		return foundArtifact;
	}
	
	@Override
	protected String getName() {
		return "deploy";
	}

	@Override
	protected String getErrorMessage() {
        return "Deployment of " + deployable.getArtifactId() + " failed.";
	}


}
