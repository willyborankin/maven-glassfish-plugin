package org.glassfish.maven.plugin;


/**
 * Created by dwhitla at Jun 21, 2007 11:24:44 PM
 *
 * @author <a href="mailto:dave.whitla@ocean.net.au">Dave Whitla</a>
 * @version $Id: JmsConnectionFactory.java 0 Jun 21, 2007 11:24:44 PM dwhitla $
 */
public class ConnectionFactory extends JndiAwareResource {

    /**
     * @parameter property = "factoryType", default-value = "connectionFactory"
     */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
