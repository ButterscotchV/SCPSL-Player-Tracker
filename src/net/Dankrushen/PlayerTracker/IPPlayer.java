package net.Dankrushen.PlayerTracker;

public class IPPlayer extends Player {
	
	public IPPlayer(PlayerEntry entry) {
		this.title = entry.getIP();
		
		this.addEntry(entry);
	}
	
	public void addEntry(PlayerEntry entry) {
		if (!entry.getIP().equals(this.title)) {
			return;
		}
		
		if (!this.firstList.contains(entry.getNameEntry())) {
			this.firstList.add(entry.getNameEntry());
		}
		
		if (!this.secondList.contains(entry.getHWIDEntry())) {
			this.secondList.add(entry.getHWIDEntry());
		}
	}
}
