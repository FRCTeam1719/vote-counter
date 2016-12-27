package org.firstteam1719.voting;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		File dir = new File(args[0]);
		String[] top = IRVCounter.rank(JSONVoteRead.readStrings(dir));
		System.out.println("Top " + top.length + " Priorities\n=================\n");
		for(int i = 0; i < top.length; i++) System.out.println("" + i + ": " + top[i]);
	}

}
