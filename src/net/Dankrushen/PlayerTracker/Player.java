package net.Dankrushen.PlayerTracker;

import java.util.ArrayList;
import java.util.List;

public class Player {
	public String title;
	public List<String> firstList = new ArrayList<String>();
	public List<String> secondList = new ArrayList<String>();
	
	public List<String> getFirstList() {
		return this.firstList;
	}
	
	public List<String> getSecondList() {
		return this.secondList;
	}
	
	public boolean listsContain(String filter) {
		for (String entry : this.getFirstList()) {
			if (entry.toLowerCase().contains(filter.toLowerCase())) {
				return true;
			}
		}
		
		for (String entry : this.getSecondList()) {
			if (entry.toLowerCase().contains(filter.toLowerCase())) {
				return true;
			}
		}
		
		return false;
	}
	
	public String toString() {
		return (this.title.isEmpty() ? "(Missing Entry)" : this.title);
	}
}
