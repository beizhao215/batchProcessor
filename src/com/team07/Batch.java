package com.team07;

import java.util.LinkedHashMap;
import java.util.Map;

public class Batch {

	private String workingDir;

	private LinkedHashMap<String, Command> commands = new LinkedHashMap<>();

	public void addCommand(Command command) {

		commands.put(command.getId(), command);

	}

	public String getWorkingDir() {

		return workingDir;

	}

	public Map<String, Command> getCommands() {

		return commands;

	}

	public void setWorkingDir(String str) {

		this.workingDir = str;
	}
	


}
