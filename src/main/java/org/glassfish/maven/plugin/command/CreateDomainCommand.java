/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2007-2008 maven-glassfish-plugin developers. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by the copyright holder in the GPL Version 2 section of the
 * License file that accompanied this code.  If applicable, add the following
 * below the License Header, with the fields enclosed by brackets [] replaced
 * by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 ******************************************************************************/

package org.glassfish.maven.plugin.command;

import java.util.List;

import org.glassfish.maven.plugin.Domain;
import org.glassfish.maven.plugin.GlassfishMojo;

/**
 * Created by dwhitla at Apr 9, 2007 4:09:05 PM
 *
 * @author <a href="mailto:dave.whitla@ocean.net.au">Dave Whitla</a>
 * @version $Id: CreateDomainCommand.java 0 Apr 9, 2007 4:09:05 PM dwhitla $
 */
public class CreateDomainCommand extends InteractiveAsadminCommand {

    private final Domain domain;

    public CreateDomainCommand(GlassfishMojo sharedContext, Domain domain) {
        super(sharedContext);
        this.domain = domain;
    }

    @Override
	protected String getName() {
        return "create-domain";
    }

    @Override
	protected List<String> getParameters() {
        StringBuilder domainProperties = new StringBuilder();
        if (domain.getJmxPort() > 0) {
        	appendDomainProperty(domainProperties, "domain.jmxPort", domain.getJmxPort());
        }
        if (domain.getHttpsPort() > 0) {
            appendDomainProperty(domainProperties, "http.ssl.port", domain.getHttpsPort());
        }
        if (domain.getJpdaPort() > 0) {
        	appendDomainProperty(domainProperties, "java.debugger.port", domain.getJpdaPort());
        }
        if (domain.getJmsPort() > 0) {
        	appendDomainProperty(domainProperties, "jms.port", domain.getJmsPort());
        }
        if (domain.getIiopPort() > 0) {
        	appendDomainProperty(domainProperties, "orb.listener.port", domain.getIiopPort());
        }
        if (domain.getIiopsmPort() > 0) {
        	appendDomainProperty(domainProperties, "orb.mutualauth.port", domain.getIiopsmPort());
        }
        if (domain.getIiopsPort() > 0) {
        	appendDomainProperty(domainProperties, "orb.ssl.port", domain.getIiopsPort());
        }
        if (domain.getOsgiPort() > 0) {
        	appendDomainProperty(domainProperties, "osgi.shell.telnet.port", domain.getOsgiPort());
        }

        List<String> parameters = super.getParameters();
        addOptionalParameter(parameters, "--domaindir", domain.getDirectory());
        addOptionalParameter(parameters, "--adminport", domain.getAdminPort());
        addOptionalParameter(parameters, "--instanceport", domain.getHttpPort());
        addOptionalParameter(parameters, "--domainproperties", domainProperties.toString());
        addOptionalParameter(parameters, "--savemasterpassword", "true");
        
        parameters.add(domain.getName());
        return parameters;
    }
    
    protected void appendDomainProperty(StringBuilder properties, String propertyName, int propertyValue) {
    	if (properties.length() > 0) {
    		properties.append(":");
    	}
    	properties.append(propertyName);
    	properties.append("=");
    	properties.append(propertyValue);
    }

    @Override
	protected String getErrorMessage() {
        return "Unable to create domain \"" + domain.getName() + "\".";
    }
}
