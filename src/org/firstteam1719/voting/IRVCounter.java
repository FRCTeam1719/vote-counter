package org.firstteam1719.voting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class IRVCounter {
	private static final int OUTLEN = 3;
	
	public static String[] rank(String[][] votes) {
		int[] voteid = new int[votes.length];
		String[] top = new String[OUTLEN];
		Map<String, ArrayList<Integer>> whovoted = new HashMap<>();
		
		for(int i = 0; i < votes.length; i++) {
			voteid[i] = 0;
			ArrayList<Integer> list = whovoted.get(votes[i][0]);
			if(list == null) {
				list = new ArrayList<>();
				whovoted.put(votes[i][0], list);
			}
			list.add(i);
		}
		
		for(int rankid = 0; rankid < OUTLEN; rankid++) {
			Map<String, ArrayList<Integer>> _whovoted = new HashMap<>(whovoted);
			while(_whovoted.size() > 1) {
				String loser = Collections.min(_whovoted.entrySet(), Comparator.comparingInt(e -> e.getValue().size())).getKey();
				for(int revote : _whovoted.get(loser)) {
					ArrayList<Integer> newvote;
					do {
						voteid[revote]++;
						newvote = _whovoted.get(votes[revote][voteid[revote]]);
					} while(newvote == null);
					newvote.add(revote);
				}
				_whovoted.remove(loser);
			}
			top[rankid] = _whovoted.keySet().toArray(new String[1])[0];
			for(int i = 0; i < voteid.length; i++) voteid[i] = 0;
			for(int revote : whovoted.get(top[rankid])) {
				voteid[revote] = 1;
				ArrayList<Integer> list = whovoted.get(votes[revote][1]);
				if(list == null) {
					list = new ArrayList<>();
					whovoted.put(votes[revote][1], list);
				}
				list.add(revote);
			}
			whovoted.remove(top[rankid]);
		}
		return top;
	}
}
