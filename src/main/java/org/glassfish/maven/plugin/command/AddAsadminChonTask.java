package org.glassfish.maven.plugin.command;

import java.util.Arrays;
import java.util.List;

import org.glassfish.maven.plugin.AsadminChronTask;
import org.glassfish.maven.plugin.GlassfishMojo;

public class AddAsadminChonTask extends InteractiveAsadminCommand {

	private AsadminChronTask task;
	
	public AddAsadminChonTask(GlassfishMojo sharedContext, AsadminChronTask task) {
		super(sharedContext);
		this.task = task;
	}

	@Override
	protected String getName() {
		return task.getCommandName();
	}

	@Override
	protected String getErrorMessage() {
		return "cannot add chron task "+task.getCommandName();
	}

	@Override
	public List<String> prepareCommandLine() {
		List<String> commandLine = super.prepareCommandLine();
		commandLine.add(0, "(crontab -l | grep -v '"+task.getCommandName()+"' ; echo \'");
		commandLine.add(1, task.getInterval());
		commandLine.add(2, "sh ");
		commandLine.add("\') | crontab -");
		StringBuilder stringBuilder = new StringBuilder();
		for (String string : commandLine) {
			stringBuilder.append(string).append(" ");
		}
		return Arrays.asList("/bin/sh", "-c", stringBuilder.toString());
	}
	
	

}
