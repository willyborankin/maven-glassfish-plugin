package org.glassfish.maven.plugin;

import java.util.Set;

public class JavaMailResource implements Resource {

	/**
     * @parameter
     * @required
     */
	private String name;

	/**
     * @parameter
     * @required
     */
	private String mailHost;

	/**
     * @parameter
     * @required
     */
	private String mailUser;

	/**
     * @parameter
     * @required
     */
	private String fromAddress;

	/**
     * @parameter default-value = "true"
     */
	private Boolean enabled;

	/**
     * @parameter default-value = ""
     */
	private String description;

	/**
     * @parameter default-value = "imap"
     */
	private String storeProtocol;

	/**
     * @parameter default-value = "com.sun.mail.imap.IMAPStore"
     */
	private String storeProtocolClass;

	/**
     * @parameter default-value = "smtp"
     */
	private String transportProtocol;

	/**
     * @parameter default-value = "com.sun.mail.smtp.SMTPTransport"
     */
	private String transportProtocolClass;

	/**
     * @parameter
     */
    private Set<Property> properties;

	@Override
	public String getType() {
		return Type.javaMail.toString();
	}

	@Override
	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}

	@Override
	public Set<Property> getProperties() {
		return properties;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailHost() {
		return mailHost;
	}

	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}

	public String getMailUser() {
		return mailUser;
	}

	public void setMailUser(String mailUser) {
		this.mailUser = mailUser;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStoreProtocol() {
		return storeProtocol;
	}

	public void setStoreProtocol(String storeProtocol) {
		this.storeProtocol = storeProtocol;
	}

	public String getStoreProtocolClass() {
		return storeProtocolClass;
	}

	public void setStoreProtocolClass(String storeProtocolClass) {
		this.storeProtocolClass = storeProtocolClass;
	}

	public String getTransportProtocol() {
		return transportProtocol;
	}

	public void setTransportProtocol(String transportProtocol) {
		this.transportProtocol = transportProtocol;
	}

	public String getTransportProtocolClass() {
		return transportProtocolClass;
	}

	public void setTransportProtocolClass(String transportProtocolClass) {
		this.transportProtocolClass = transportProtocolClass;
	}

}
