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

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.glassfish.maven.plugin.Domain;
import org.glassfish.maven.plugin.GlassfishMojo;

/**
 * Created by dwhitla at Apr 9, 2007 4:22:38 PM
 *
 * @author <a href="mailto:dave.whitla@ocean.net.au">Dave Whitla</a>
 * @version $Id: CreateJVMOptionsCommand.java 0 Apr 9, 2007 4:22:38 PM dwhitla $
 */
public class CreateJVMOptionsCommand extends InteractiveAsadminCommand {

    protected Domain domain;

    public CreateJVMOptionsCommand(GlassfishMojo sharedContext, Domain domain) {
        super(sharedContext);
        this.domain = domain;
    }

    protected String getName() {
        return "create-jvm-options";
    }

    protected List<String> getParameters() {
        Set<String> jvmOptions = getOptions();
        if (jvmOptions == null || jvmOptions.isEmpty()) {
            return null;
        }
        StringBuilder options = new StringBuilder();
        for (String option : jvmOptions) {
            if (options.length() > 0) {
                options.append(':');
            }
            options.append(escape(option, ";:", "\\\\"));
        }
        List<String> parameters = super.getParameters();
        parameters.addAll(Arrays.asList(
                options.insert(0, "\\").toString()
        ));
        return parameters;
    }

	protected Set<String> getOptions() {
		Set<String> jvmOptions = domain.getJvmOptions();
		return jvmOptions;
	}

    protected String getErrorMessage() {
        return "Unable to set JVM options for domain \"" + domain.getName() + "\".";
    }
}
