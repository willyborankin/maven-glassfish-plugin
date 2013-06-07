package org.glassfish.maven.plugin;

import java.util.List;


/**
 * Created by dwhitla at Apr 3, 2007 8:46:34 PM
 *
 * @author <a href="mailto:dave.whitla@ocean.net.au">Dave Whitla</a>
 * @version $Id: Auth.java 0 Apr 3, 2007 8:46:34 PM dwhitla $
 */
public class Auth {

    /**
     * @parameter
     * @required
     */
    private Realm realm;

    /**
     * @parameter
     */
    private MessageSecurityProvider messageSecurityProvider;
    
    /**
     * @parameter
     */
    private List<String> loginModuleParams;


    public Realm getRealm() {
        return realm;
    }

    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    public MessageSecurityProvider getMessageSecurityProvider() {
        return messageSecurityProvider;
    }

    public void setMessageSecurityProvider(MessageSecurityProvider messageSecurityProvider) {
        this.messageSecurityProvider = messageSecurityProvider;
    }

	public List<String> getLoginModuleParams() {
		return loginModuleParams;
	}

	public void setLoginModuleParams(List<String> loginModuleParams) {
		this.loginModuleParams = loginModuleParams;
	}

}
