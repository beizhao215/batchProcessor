package com.team07;

import java.io.File;
import java.io.FileInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BatchParser {

	private String workingDir = null;                    // workingDir for Parser and Batch

	public Batch buildBatch(File file) {

		FileInputStream fis;

		Batch batch = new Batch(); // one Batch

		try {
			/*
			 * Parse XML
			 */
			fis = new FileInputStream(file);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(fis);
			

			Element pnode = doc.getDocumentElement();
			
			NodeList nodes = pnode.getChildNodes();

			for (int idx = 0; idx < nodes.getLength(); idx++) {

				Node node = nodes.item(idx);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element elem = (Element) node;
					
					Command command = builderCommand(elem);
				
					command.parse(elem);

					if (command instanceof WdCommand) {

						workingDir = "/" + command.getPath();     // get workingdir from wd

					}

					batch.addCommand(command);                    // add each command to Batch;

				}

			}

		} catch (Exception e) {
			
			e.printStackTrace();
		}

		  return batch;

	}

	public String getWorkingDir() {

		return workingDir;
	}

	private Command builderCommand(Element element) {

		Command command = null;

		WdCommand wdCommand = new WdCommand();

		FileCommand fileCommand = new FileCommand();

		CmdCommand cmdCommand = new CmdCommand();

		PipeCommand pipeCommand = new PipeCommand();

		String nodeName = element.getNodeName();

		if (nodeName == null) {

		throw new ProcessException("One element of the batch file is Null.");

		}

		else if (nodeName.equalsIgnoreCase("wd")) {

			command = wdCommand;
			System.out.println("building wdcommand");

		}

		else if (nodeName.equalsIgnoreCase("file")) {

			System.out.println("building filecommand");
			
			command = fileCommand;

		}

		else if (nodeName.equalsIgnoreCase("cmd")) {

			System.out.println("building cmdcommand");
			command = cmdCommand;

		}

		else if (nodeName.equalsIgnoreCase("pipe")) {

			System.out.println("parsing pipecommand");

			command = pipeCommand;
		}

		else {

			throw new ProcessException("There is an unkown element in the batch file.");

		}
		return command;

	}
}
