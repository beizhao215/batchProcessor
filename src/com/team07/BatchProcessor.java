package com.team07;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class BatchProcessor {

	public static void main(String[] args) {

		BatchParser batchParser = new BatchParser();

		args[0] = args[0].replace("\\", "/");     // If args[0] contains "\", then changes to "/" ;
		
		File fi = new File(args[0]);
		
		Batch batch = batchParser.buildBatch(fi);
		
		batch.setWorkingDir(batchParser.getWorkingDir());
       
		BatchProcessor batchProcessor = new BatchProcessor();

		batchProcessor.execute(batch);
		

	}

	public void execute(Batch batch) {

		String workingdir = batch.getWorkingDir();

		Map<String, Command> map = batch.getCommands();

		for (String str : map.keySet()) {           

			Command command = map.get(str);

			try {
				command.execute(workingdir);
			
			} catch (IOException e) {
				
				e.printStackTrace();
			}    

		}

	}

}
