package net.Dankrushen.PlayerTracker;

public class NamePlayer extends Player {
	
	public NamePlayer(PlayerEntry entry) {
		this.title = entry.getName();
		
		this.addEntry(entry);
	}
	
	public void addEntry(PlayerEntry entry) {
		if (!entry.getName().equals(this.title)) {
			return;
		}
		
		if (!this.firstList.contains(entry.getIPEntry())) {
			this.firstList.add(entry.getIPEntry());
		}
		if (!this.secondList.contains(entry.getHWIDEntry())) {
			this.secondList.add(entry.getHWIDEntry());
		}
	}
}
