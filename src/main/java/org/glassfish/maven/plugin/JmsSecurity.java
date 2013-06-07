package org.glassfish.maven.plugin;

public class JmsSecurity {
	/**
	 * @parameter 
	 */
	private String producerGroups;
	
	/**
	 * @parameter
	 */	
	private String consumerGroups;
	
	public String getProducerGroups() {
		return producerGroups;
	}
	public void setProducerGroups(String producerGroups) {
		this.producerGroups = producerGroups;
	}
	public String getConsumerGroups() {
		return consumerGroups;
	}
	public void setConsumerGroups(String consumerGroups) {
		this.consumerGroups = consumerGroups;
	}
	
	
}
