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

package org.glassfish.maven.plugin;

import java.util.Set;

/**
 * Created by dwhitla at Apr 10, 2007 1:23:47 PM
 *
 * @author <a href="mailto:dave.whitla@ocean.net.au">Dave Whitla</a>
 */
public class JdbcDataSource implements Resource {
    
    private static final int DEFAULT_MAX_POOL_SIZE = 50;
    private static final int DEFAULT_STEADY_POOL_SIZE = 5;
    private static final int DEFAULT_IDLE_TIMEOUT = 300;
    private static final int DEFAULT_MAX_WAIT = 60000;
    private static final int DEFAULT_POOL_RESIZE = 2;

    public enum ValidationMethod {
        autoCommit("auto-commit"),
        metaData("meta-data"),
        table("table");

        private String realName;

        ValidationMethod(String realName) {
            this.realName = realName;
        }

        public String toString() {
            return realName;
        }

        public static ValidationMethod forName(String realName) {
            for (ValidationMethod method : values()) {
                if (method.realName.equalsIgnoreCase(realName)) {
                    return method;
                }
            }
            throw new IllegalArgumentException("No ValidationMethod exists with name \"" + realName + "\"");
        }
    }

    /**
     * @parameter
     * @required
     */
    private String poolName;

    /**
     * @parameter
     * @required
     */
    private String className;

    /**
     * @parameter
     * @required
     */
    private String name;

    /**
     * @parameter
     * @required
     */
    private String description;

    /**
     * @parameter property = "type"
     * @required
     */
    private String _type;

    /**
     * @parameter default-value = "true"
     */
    private boolean allowNonComponentCallers = true;

    /**
     * @parameter default-value = "false"
     */
    private boolean failConnection;

    /**
     * @parameter default-value = "false"
     */
    private boolean validateConnections;

    /**
     * @parameter default-value = "false"
     */
    private boolean isolationGuaranteed;

    /**
     * @parameter default-value = "false"
     */
    private boolean nonTransactionalConnections;

    /**
     * @parameter property = "validationMethod" default-value = "auto-commit"
     */
    private ValidationMethod validationMethod = ValidationMethod.autoCommit;

    /**
     * @parameter default-value = "2"
     */
    private int poolResize = DEFAULT_POOL_RESIZE;

    /**
     * @parameter default-value = "50"
     */
    private int maxPoolSize = DEFAULT_MAX_POOL_SIZE;

    /**
     * @parameter default-value = "5"
     */
    private int steadyPoolSize = DEFAULT_STEADY_POOL_SIZE;

    /**
     * @parameter default-value = "300"
     */
    private int idleTimeout = DEFAULT_IDLE_TIMEOUT;

    /**
     * @parameter default-value = "60000"
     */
    private int maxWait = DEFAULT_MAX_WAIT;

    /**
     * @parameter
     */
    private Set<Property> properties;


    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAllowNonComponentCallers() {
        return allowNonComponentCallers;
    }

    public void setAllowNonComponentCallers(boolean allowNonComponentCallers) {
        this.allowNonComponentCallers = allowNonComponentCallers;
    }

    public boolean isFailConnection() {
        return failConnection;
    }

    public void setFailConnection(boolean failConnection) {
        this.failConnection = failConnection;
    }

    public boolean isValidateConnections() {
        return validateConnections;
    }

    public void setValidateConnections(boolean validateConnections) {
        this.validateConnections = validateConnections;
    }

    public boolean isIsolationGuaranteed() {
        return isolationGuaranteed;
    }

    public void setIsolationGuaranteed(boolean isolationGuaranteed) {
        this.isolationGuaranteed = isolationGuaranteed;
    }

    public boolean isNonTransactionalConnections() {
        return nonTransactionalConnections;
    }

    public void setNonTransactionalConnections(boolean nonTransactionalConnections) {
        this.nonTransactionalConnections = nonTransactionalConnections;
    }

    public String getValidationMethod() {
        return validationMethod.toString();
    }

    public void setValidationMethod(String validationMethod) {
        this.validationMethod = ValidationMethod.valueOf(validationMethod);
    }

    public int getPoolResize() {
        return poolResize;
    }

    public void setPoolResize(int poolResize) {
        this.poolResize = poolResize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getSteadyPoolSize() {
        return steadyPoolSize;
    }

    public void setSteadyPoolSize(int steadyPoolSize) {
        this.steadyPoolSize = steadyPoolSize;
    }

    public int getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(int idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public Set<Property> getProperties() {
        return properties;
    }

    public void setProperties(Set<Property> properties) {
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return _type;
    }

    public void setType(String type) {
        this._type = type;
    }
}
