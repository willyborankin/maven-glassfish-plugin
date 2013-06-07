package org.glassfish.maven.plugin.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.MavenArtifactRepository;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.IOUtil;
import org.glassfish.maven.plugin.Domain;
import org.glassfish.maven.plugin.ExternalLibrary;
import org.glassfish.maven.plugin.GlassfishMojo;

public class CopyExternalLibrariesCommand extends InteractiveAsadminCommand {

	public CopyExternalLibrariesCommand(GlassfishMojo sharedContext) {
		super(sharedContext);
	}

	@Override
	protected String getName() {
		return "copy ext libs to artifacts";
	}

	@Override
	protected String getErrorMessage() {
		return null;
	}

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		Domain domain = sharedContext.getDomain();
		boolean exists = (domain.getExternalLibraries() != null && !domain.getExternalLibraries().isEmpty());
		if (exists) {
			for (ExternalLibrary library : domain.getExternalLibraries()) {
				Artifact artifact = findArtifact(library.getGroupId(), library.getArtifactId());
				if (artifact == null) {
					throw new MojoExecutionException("Could not find Artifat fot library " + library.toString());
				}
				copy(artifact, library.getPath());
			}
		}
	}

	private String getFileName(Artifact artifact) {
		StringBuilder builder = new StringBuilder();
		builder.append(artifact.getArtifactId());
		builder.append("-").append(artifact.getVersion()).append(".jar");
		return builder.toString();
	}

	private Artifact findArtifact(String groupId, String artifactId) {
		Artifact foundArtifact = null;
		MavenProject mavenProject = sharedContext.getProject();
		for (Object anObject : mavenProject.getDependencyArtifacts()) {
			Artifact artifact = (Artifact) anObject;
			if (artifact.getGroupId().equals(groupId)
					&& artifact.getArtifactId().equals(artifactId)) {
				foundArtifact = artifact;
				break;
			}
		}
		return foundArtifact;
	}

	private void copy(Artifact artifact, String path) throws MojoExecutionException {
		Domain domain = sharedContext.getDomain();
		File dstDir = getDstDirectory(domain, path);
		InputStream artefcatFileIn = resolveFilePath(artifact);
		getLog().info("Coping artifact " + artifact + " to " + dstDir.toString());

		FileOutputStream out = getDstFile(dstDir, getFileName(artifact));

		copy(artefcatFileIn, out);
	}

	@SuppressWarnings("resource")
	private InputStream resolveFilePath(Artifact artifact)
			throws MojoExecutionException {
		File artifactFile = artifact.getFile();
		InputStream in = null;
		if (artifactFile != null) {
			try {
				in = new FileInputStream(artifactFile);
			} catch (FileNotFoundException e) {
				throw new MojoExecutionException("Oops", e);
			}
		} else {
			MavenProject project = sharedContext.getProject();
			int repoCounter = project.getRemoteArtifactRepositories().size();
			for (Object anArtifactRepoObject : project
					.getRemoteArtifactRepositories()) {
				MavenArtifactRepository artifactRepository = (MavenArtifactRepository) anArtifactRepoObject;
				String path = artifactRepository.getUrl() + "/"
						+ artifactRepository.pathOf(artifact);
				getLog().debug("Path to repome repo is " + path);
				try {
					URL url = new URL(path);
					URLConnection urlConnection = url.openConnection();
					try {
						in = urlConnection.getInputStream();
						break;
					} catch (IOException e) {
						getLog().info(
								"Could not get artifact " + artifact + " from "
										+ artifactRepository.getUrl());
					}
				} catch (MalformedURLException e) {
					throw new MojoExecutionException("Oops", e);
				} catch (IOException e) {
					getLog().info(
							"Could not connect to repo "
									+ artifactRepository.getUrl());
				}
				repoCounter--;
			}
			if (repoCounter == 0 && in == null) {
				throw new MojoExecutionException("Could not get artifact "
						+ artifact + " from any repo ");
			}
		}
		return in;
	}

	private void copy(InputStream in, OutputStream out)
			throws MojoExecutionException {
		try {
			IOUtil.copy(in, out);
		} catch (IOException e) {
			throw new MojoExecutionException("Oops", e);
		} finally {
			IOUtil.close(in);
			IOUtil.close(out);
		}
	}

	private File getDstDirectory(Domain domain, String path) {
		return new File(domain.getDirectory(), domain.getName() + File.separator + (path != null ? "lib/" + path : "lib/"));
	}

	private FileOutputStream getDstFile(File dstDir, String fileName)
			throws MojoExecutionException {
		try {
			return new FileOutputStream(new File(dstDir, fileName));
		} catch (IOException e) {
			throw new MojoExecutionException("Oops", e);
		}
	}

	private Log getLog() {
		return sharedContext.getLog();
	}

}
