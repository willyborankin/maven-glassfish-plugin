package org.glassfish.maven.plugin;

import java.io.File;

/**
 * Created by dwhitla at Apr 5, 2007 2:15:09 PM
 *
 * @author <a href="mailto:dave.whitla@ocean.net.au">Dave Whitla</a>
 * @version $Id: Component.java 0 Apr 5, 2007 2:15:09 PM dwhitla $
 */
public class Component {

    /**
     * @parameter expression = "${project.artifactId}"
     */
    private String name;

    /**
     * @parameter
     * @required
     */
    private File artifact;

    public Component() {
    }

    public Component(String name, File artifact) {
        this.name = name;
        this.artifact = artifact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getArtifact() {
        return artifact;
    }

    public void setArtifact(File artifact) {
        this.artifact = artifact;
    }
}
