package com.team07;

import java.util.LinkedHashMap;
import java.util.Set;

public class FileHistory {       // Store any filecommands' id and path in batch for each cmdcommand;

	static LinkedHashMap<String, String> map = new LinkedHashMap<>();

	public static void addFile(String id, String path) {

		map.put(id, path);
	}

	public static String getPath(String id) {

		String path = null;

		Set<String> idSet = map.keySet();

		System.out.println(idSet);

		if (idSet.contains(id)) {

			path = map.get(id);

			return path;

		}

		else {

			throw new ProcessException(
			
					"Unable to locate FileCommand with id:" + id);

		}

	}

}
