package org.glassfish.maven.plugin.command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.glassfish.maven.plugin.ConfigReplacement;
import org.glassfish.maven.plugin.Domain;

public class ReplaceInConfigFile {
	private Domain domain;
	
	public ReplaceInConfigFile(Domain domain){
		this.domain = domain;
	}
	
	public void execute() throws MojoExecutionException, MojoFailureException{
		try{
			execute_();
		}
		catch(Exception e){
			throw new MojoFailureException("", e);
		}
	}
	
	private void execute_() throws Exception{
		File oldConfig = new File(domain.getDirectory(), domain.getName()+"/config/domain.xml");
		File newConfig = new File(domain.getDirectory(), domain.getName()+"/config/domain.xml.new");
		FileReader reader = new FileReader(oldConfig);
		FileWriter fileWriter = new FileWriter(newConfig);
		String line = null;
		try(
				BufferedReader bufferedReader = new BufferedReader(reader);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			){
			while(true){
				line = bufferedReader.readLine();
				if(line==null){
					break;
				}
				for (ConfigReplacement replacement : domain.getReplacements()) {
					line = line.replaceAll(replacement.getPattern(), replacement.getReplacement());
				}
				bufferedWriter.write(line);
				bufferedWriter.write("\n");
			}
			bufferedWriter.flush();
			oldConfig.delete();
			newConfig.renameTo(new File(newConfig.getParentFile().getPath(), "domain.xml"));
		}
	}
}
