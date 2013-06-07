This is fork of maven-glassfish-plugin (http://maven-glassfish-plugin.java.net/) that was developed by SUN. 
It is designed to easily manage Glassfish 3.x server using maven, 
and extends existing functionality for generation of domain (e.g. copy database drivers, creation of the realm etc.)

Authors:
dwhitla
willyborankin

Full Example:

<project>
    <build>
        <plugins>
            <plugin>
                <groupId>org.glassfish.maven.plugin</groupId>
                <artifactId>maven-glassfish-plugin</artifactId>
                <version>2.1</version>
                <configuration>
                    <glassfishDirectory>${glassfish.home}</glassfishDirectory>
                    <user>${domain.username}</user>
                    <adminPassword>${domain.password}</adminPassword>
                    <!-- <passFile>path/to/asadmin/passfile</passFile> -->
                    <autoCreate>true</autoCreate>
                    <debug>true</debug>
                    <echo>false</echo>
                    <terse>true</terse>
                    <skip>${test.int.skip}</skip>
                    <domain>
                        <name>${project.artifactId}</name>
                        <adminPort>4848</adminPort>
                        <httpPort>8080</httpPort>
                        <httpsPort>8443</httpsPort>
                        <iiopPort>3700</iiopPort>
                        <jmsPort>7676</jmsPort>
                        <reuse>false</reuse>
                        <jvmOptions>
                            <option>-Djava.security.auth.login.config=${project.build.testOutputDirectory}/login.conf</option>
                        </jvmOptions>
                        <properties>
                            <property>
                                <name>server.log-service.file</name>
                                <value>${domain.log.dir}/server.log</value>
                            </property>
                        </properties>
                        <!-- New feature -->
						<externalLibraries>
							<externalLibrary>
								<groupId>net.sourceforge.jtds</groupId>
								<artifactId>jtds</artifactId>
								<path>ext</path>
							</externalLibrary>
							<externalLibrary>
								<groupId>com.oracle</groupId>
								<artifactId>ojdbc6</artifactId>
								<path>ext</path>
							</externalLibrary>
							<externalLibrary>
								<groupId>ru.openback.security</groupId>
								<artifactId>open-login-module</artifactId>
							</externalLibrary>
							<externalLibrary>
								<groupId>ru.openback.security</groupId>
								<artifactId>open-login-principal</artifactId>
							</externalLibrary>
							<externalLibrary>
								<groupId>javax.jcr</groupId>
								<artifactId>jcr</artifactId>
							</externalLibrary>
						</externalLibraries>
                        <auth>
                            <realm>
                                <name>testRealm</name>
                                <className>com.sun.enterprise.security.auth.realm.file.FileRealm</className>
                                <properties>
                                    <property>
                                        <name>jaas-context</name>
                                        <value>test</value>
                                    </property>
                                    <property>
                                        <name>file</name>
                                        <value>${project.build.outputDirectory}/keyfile</value>
                                    </property>
                                </properties>
                            </realm>
                            <!-- New feature -->
							<loginModuleParams>
								<loginModuleParam>ru.openback.authentification.OpenLoginModule required</loginModuleParam>

								<loginModuleParam>openLoginModule.users.crmUser.name=crmUser</loginModuleParam>
								<loginModuleParam>openLoginModule.users.crmUser.MD5password=</loginModuleParam>
								<loginModuleParam>openLoginModule.users.crmUser.groups=SomeGroup</loginModuleParam>

								<loginModuleParam>openLoginModule.users.jmsGuest.name=guest</loginModuleParam>
								<loginModuleParam>openLoginModule.users.jmsGuest.MD5password=</loginModuleParam>

								<loginModuleParam>openLoginModule.users.jmsAdmin.name=admin</loginModuleParam>
								<loginModuleParam>openLoginModule.users.jmsAdmin.MD5password=</loginModuleParam>
								<loginModuleParam>openLoginModule.users.jmsAdmin.groups=admin</loginModuleParam>

								<loginModuleParam>openLoginModule.users.OSGi.name=OSGi</loginModuleParam>
								<loginModuleParam>openLoginModule.users.OSGi.MD5password=</loginModuleParam>
								<loginModuleParam>openLoginModule.users.OSGi.groups=OSGi</loginModuleParam>

								<loginModuleParam>openLoginModule.users.developerTest.name=developer</loginModuleParam>
								<loginModuleParam>openLoginModule.users.developerTest.MD5password=</loginModuleParam>
								<loginModuleParam>openLoginModule.users.developerTest.groups=NewBackAllowedUsers</loginModuleParam>

								<loginModuleParam>openLoginModule.users.developerSuper.name=lenin</loginModuleParam>
								<loginModuleParam>openLoginModule.users.developerSuper.MD5password=</loginModuleParam>
								<loginModuleParam>openLoginModule.users.developerSuper.groups=NewBackAllowedUsers</loginModuleParam>

								<loginModuleParam>openLoginModule.users.dbIntegration.name=aliendb</loginModuleParam>
								<loginModuleParam>openLoginModule.users.dbIntegration.MD5password=</loginModuleParam>
								<loginModuleParam>openLoginModule.users.dbIntegration.groups=NewBackAllowedUsers</loginModuleParam>

								<loginModuleParam>openLoginModule.developmentMode=${open.login.developmentMode}</loginModuleParam>
								<loginModuleParam>openLoginModule.allowAll=${open.login.allowOtherGroups};</loginModuleParam>
							</loginModuleParams>
						</auth>
                        <!-- <resourceDescriptor>path/to/resources.xml</resourceDescriptor> -->
                        <resources>
                        	<!-- New feature -->
							<ConnectorResource>
								<jndiName>jcr/OpenBackRepository</jndiName>
								<connectorConnectionFactory>
									<jndiName>jcr/OpenBackRepositoryConnectionPool</jndiName>
									<resourceAdapter>jackrabbit-jca</resourceAdapter>
									<type>javax.jcr.Repository</type>
									<properties>
										<property>
											<name>HomeDir</name>
											<value>${user.home}/repository${jcr.repository.path.suffix}</value>
										</property>
										<property>
											<name>ConfigFile</name>
											<value>${user.home}/repository${jcr.repository.path.suffix}/repository.xml</value>
										</property>
									</properties>		
								</connectorConnectionFactory>
							</ConnectorResource>	
                        	<!-- New feature -->
							<javaMailResource>
								<name>mail/newhope</name>
								<mailHost>mail.open.ru</mailHost>
								<mailUser>newhope</mailUser>
								<fromAddress>open@open.ru</fromAddress>
								<enabled>true</enabled>
								<description>Internal OpenBroker JSC Mail Service</description>
								<storeProtocol>imap</storeProtocol>
								<storeProtocolClass>com.sun.mail.imap.IMAPStore</storeProtocolClass>
								<transportProtocol>smtp</transportProtocol>
								<transportProtocolClass>com.sun.mail.smtp.SMTPTransport</transportProtocolClass>
							</javaMailResource>
							<!-- New feature -->
							<propertiesJndiResource>
								<jndiName>openConfig/properties</jndiName>
								<properties>
									<property>
										<name>rabbitHost</name>
										<value>${rabbit.host}</value>
									</property>
									<property>
										<name>rabbitUser</name>
										<value>${rabbit.user}</value>
									</property>
									<property>
										<name>rabbitPassword</name>
										<value>${rabbit.password}</value>
									</property>
									<property>
										<name>rabbitNonTradeQueue</name>
										<value>${rabbit.nontrade.queue}</value>
									</property>
									<property>
										<name>rabbitOldBackRoutingKey</name>
										<value>${rabbit.oldback.routing.key}</value>
									</property>
									<property>
										<name>httpPort</name>
										<value>${glassfish.https.port}</value>
									</property>
									<property>
										<name>jmsPort</name>
										<value>${glassfish.jms.port}</value>
									</property>
									<property>
										<name>environment</name>
										<value>${environment}</value>
									</property>
								</properties>
						</propertiesJndiResource>
						<!-- New Feature -->
						<deployables>
							<deployable>
								<groupId>org.apache.jackrabbit</groupId>
								<artifactId>jackrabbit-jca</artifactId>
							</deployable>	
						</deployables>
						<connectionFactory>
                                <jndiName>jms/testQueueConnectionFactory</jndiName>
                                <type>queueConnectionFactory</type>
                                <properties>
                                    <property>
                                        <name>UserName</name>
                                        <value>guest</value>
                                    </property>
                                    <property>
                                        <name>Password</name>
                                        <value>guest</value>
                                    </property>
                                    <property>
                                        <name>AddressList</name>
                                        <value>localhost:7676</value>
                                    </property>
                                </properties>
                            </connectionFactory>
                            <jmsTopic>
                                <jndiName>jms/testTopic</jndiName>
                                <destinationName>TestTopic</destinationName>
                                <connectionFactory>
                                    <jndiName>jms/testTopicConnectionFactory</jndiName>
                                    <properties>
                                        <property>
                                            <name>UserName</name>
                                            <value>guest</value>
                                        </property>
                                        <property>
                                            <name>Password</name>
                                            <value>guest</value>
                                        </property>
                                        <property>
                                            <name>AddressList</name>
                                            <value>localhost:7676</value>
                                        </property>
                                    </properties>
                                </connectionFactory>
                            </jmsTopic>
                            <jdbcDataSource>
                                <name>SomeDS</name>
                                <type>connectionPoolDataSource</type>
                                <poolName>SomePool</poolName>
                                <className>com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource</className>
                                <description>Some JDBC Connection Pool</description>
                                <allowNonComponentCallers>false</allowNonComponentCallers>
                                <validateConnections>true</validateConnections>
                                <validationMethod>metaData</validationMethod>
                                <properties>
                                    <property>
                                        <name>portNumber</name>
                                        <value>3306</value>
                                    </property>
                                    <property>
                                        <name>password</name>
                                        <value>somePassword</value>
                                    </property>
                                    <property>
                                        <name>user</name>
                                        <value>someUser</value>
                                    </property>
                                    <property>
                                        <name>serverName</name>
                                        <value>some.host.somewhere</value>
                                    </property>
                                    <property>
                                        <name>databaseName</name>
                                        <value>SomeDB</value>
                                    </property>
                                </properties>
                            </jdbcDataSource>
                        </resources>
						<!-- New features -->
						<deleteJvmOptions>
							<option>-Xmx512m</option> 
							<option>-XX:MaxPermSize=192m</option>
							<option>-client</option>
						</deleteJvmOptions>		
						<jvmOptions>
							<option>-Xmx${glassfish.jvm.max.heap.size}</option> 
							<option>-XX:MaxPermSize=${glassfish.jvm.max.perm.size}</option> 
							<option>-Dlogback.configurationFile=${com.sun.aas.instanceRoot}/applications/open-services-ear/open-spi-services_war/WEB-INF/classes/logback.xml</option>
							<option>-server</option>
						</jvmOptions>
						<replacements>
							<replacement>
								<pattern>keystore.jks</pattern>
								<replacement>${glassfish.keyStorage.name}</replacement>
							</replacement>
							<replacement>
								<pattern>s1as</pattern>
								<replacement>${glassfish.certificate.name}</replacement>
							</replacement>							
						</replacements>
						<copyFiles>
							<copyFile>
								<srcFile>${basedir}/${glassfish.certificate.file}</srcFile>
								<destFile>${glassfish.domain.dir}/${glassfish.domain.name}/config/${glassfish.keyStorage.name}</destFile>
								<skip>${glassfish.skipCertificate}</skip>
							</copyFile>
						</copyFiles>
						<cronTasks>
							<cronTask>
								<commandName>rotate-log</commandName>
								<interval>0 0 * * *</interval>
							</cronTask>
						</cronTasks>
						<deleteJvmOptions>
							<option>-Xmx512m</option> 
							<option>-XX:MaxPermSize=192m</option>
							<option>-client</option>
						</deleteJvmOptions>		
						<jvmOptions>
							<option>-Xmx${glassfish.jvm.max.heap.size}</option> 
							<option>-XX:MaxPermSize=${glassfish.jvm.max.perm.size}</option> 
							<option>-Dlogback.configurationFile=${com.sun.aas.instanceRoot}/applications/open-services-ear/open-spi-services_war/WEB-INF/classes/logback.xml</option>
							<option>-server</option>
						</jvmOptions>
						<replacements>
							<replacement>
								<pattern>keystore.jks</pattern>
								<replacement>${glassfish.keyStorage.name}</replacement>
							</replacement>
							<replacement>
								<pattern>s1as</pattern>
								<replacement>${glassfish.certificate.name}</replacement>
							</replacement>							
						</replacements>
						<copyFiles>
							<copyFile>
								<srcFile>${basedir}/${glassfish.certificate.file}</srcFile>
								<destFile>${glassfish.domain.dir}/${glassfish.domain.name}/config/${glassfish.keyStorage.name}</destFile>
								<skip>${glassfish.skipCertificate}</skip>
							</copyFile>
						</copyFiles>
						<cronTasks>
							<cronTask>
								<commandName>rotate-log</commandName>
								<interval>0 0 * * *</interval>
							</cronTask>
						</cronTasks>
						<savePassword>${glassfish.store.password}</savePassword>
						<loginProperties>
							<property>
								<name>com.sun.enterprise.server.logging.GFFileHandler.maxHistoryFiles</name>
								<value>14</value>
							</property>
							<property>
								<name>com.sun.enterprise.server.logging.GFFileHandler.rotationLimitInBytes</name>
								<value>2147483648</value>
							</property>
						</loginProperties>
                    </domain>
                    <components>
                        <component>
                            <name>${project.artifactId}</name>
                            <artifact>${project.build.directory}/artifacts/${project.build.finalName}.war</artifact>
                        </component>
                    </components>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>