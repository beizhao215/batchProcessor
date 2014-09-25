package com.team07;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public abstract class Command {

	private String nodeName; // define a nodeName for each command;

	private String id; // define a command id;

	public String getId() {
		return id;
	}

	public String getPath() {
		return path;
	}

	public String getIn() {
		return in;
	}

	public String getOut() {
		return out;
	}

	public String getArgs() {
		return args;
	}

	public String getCmd1() {
		return cmd1;
	}

	public String getCmd2() {
		return cmd2;
	}

	private String path = "";

	private String in = "";

	private String out = "";

	private String args = "";

	private String cmd1 = "";

	private String cmd2 = "";

	public abstract String describe();

	public void execute(String workingdir) throws IOException {

		ProcessBuilder builder = new ProcessBuilder();

		builder.directory(new File(workingdir));

		List<String> list = new ArrayList<String>();

		System.out.println("processing node: " + nodeName);

		if (nodeName.equalsIgnoreCase("file")) {

			FileHistory.addFile(id, path);

		}

		if (nodeName.equalsIgnoreCase("cmd")) {

			list.add(path);

			/* args process */

			String[] strArray = args.split("\\s+"); // split by" ";

			for (int i = 0; i < strArray.length; i++) {

				if (!strArray[i].equals(""))

					list.add(strArray[i]); // split into two strings to pass into ProcessBuilder;
			}

			if (!in.equals("")) {

				String strIn = FileHistory.getPath(in); // file's total path from in;
														
				File fileIn = new File(workingdir + "/" + strIn);

				System.out.println(fileIn.getPath());

				builder.redirectInput(fileIn);      //Pass the value of in to ProcessBuilder;

			}

			builder.command(list);

			System.out.println(builder.command());

			File fileOut = new File(workingdir + "/" + FileHistory.getPath(out));
			System.out.println(fileOut.getAbsolutePath());

			builder.redirectOutput(fileOut);        //Pass the value of out to ProcessBuilder;

			Process p = builder.start();

			/*
			 * 
			 * InputStream is = p.getInputStream();
			 * 
			 * InputStreamReader isr = new InputStreamReader(is);
			 * 
			 * BufferedReader br = new BufferedReader(isr);
			 * 
			 * String line;
			 * 
			 * while ((line = br.readLine()) != null) {
			 * 
			 * System.out.println(line); }
			 */
		}

		if (nodeName.equalsIgnoreCase("pipe")) {
			
	///
			
			
			
			
			
			
			
			
			
			
			
			
		}

	}

	public void parse(org.w3c.dom.Element element) {

		nodeName = element.getNodeName();

		if (nodeName == null) {

			// throws exception

		}

		else if (nodeName.equalsIgnoreCase("wd")) {

			System.out.println("parsing wdcommand");

			id = element.getAttribute("id");

			path = element.getAttribute("path");

		}

		else if (nodeName.equalsIgnoreCase("file")) {

			System.out.println("parsing filecommand");

			id = element.getAttribute("id");

			path = element.getAttribute("path");

		}

		else if (nodeName.equalsIgnoreCase("cmd")) {

			System.out.println("parsing cmdcommand");

			id = element.getAttribute("id");

			path = element.getAttribute("path");

			in = element.getAttribute("in");

			out = element.getAttribute("out");

			args = element.getAttribute("args");

		}

		else if (nodeName.equalsIgnoreCase("pipe")) {

			System.out.println("parsing pipecommand");

			id = element.getAttribute("id");

			cmd1 = element.getAttribute("cmd1");

			cmd2 = element.getAttribute("cmd2");

		}

		else {

			throw new ProcessException("There is an unkown element in the batch file.");

		}

	}

}
