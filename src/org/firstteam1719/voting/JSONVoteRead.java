package org.firstteam1719.voting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

public class JSONVoteRead {

	public static String[][] readStrings(File dir) throws IOException {
		return jsonsToStrArr(dirToJSONs(dir));
	}
	
	private static JSONObject[] dirToJSONs(File dir) throws IOException {
		if(!dir.exists() || !dir.isDirectory()) return null;
		String[] filenames = dir.list();
		FileInputStream file;
		JSONTokener tokener;
		JSONObject[] ret = new JSONObject[filenames.length];
		for(int i = 0; i < filenames.length; i++) {
			file = new FileInputStream(new File(dir, filenames[i]));
			byte[] bytes = new byte[file.available()];
			tokener = new JSONTokener(new String(bytes));
			ret[i] = (JSONObject) tokener.nextValue();
		}
		return ret;
	}
	
	private static String[][] jsonsToStrArr(JSONObject[] jsons) {
		String[][] ret = new String[jsons.length][];
		for(int i = 0; i < jsons.length; i++) {
			ret[i] = (String[]) jsons[i].getJSONArray("ranked").toArray(new String[0]);
		}
		return ret;
	}

}
