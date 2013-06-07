package org.glassfish.maven.plugin.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.MojoFailureException;
import org.glassfish.maven.plugin.CopyFile;
import org.glassfish.maven.plugin.Domain;

public class CopyFiles {
	
	private Domain domain;
	
	public CopyFiles(Domain domain) {
		super();
		this.domain = domain;
	}

	public void execute() throws MojoFailureException{
		try {
			execute_();
		} catch (Exception e) {
			throw new MojoFailureException("", e);
		}
	}
	
	private void execute_() throws Exception{
		for (CopyFile copyFile : domain.getCopyFiles()) {
			copyFile(copyFile);
		}
	}

	public static void copyFile(CopyFile copyFile) throws IOException, FileNotFoundException {
		if(copyFile.isSkip()){
			return;
		}
		try(
				FileInputStream inputStream = new FileInputStream(new File(copyFile.getSrcFile()));
				FileOutputStream outputStream = new FileOutputStream(new File(copyFile.getDestFile()));
			){
			IOUtils.copy(inputStream , outputStream);
		}
	}
	
}
