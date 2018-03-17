package net.Dankrushen.PlayerTracker;

public class HWIDPlayer extends Player {
	
	public HWIDPlayer(PlayerEntry entry) {
		this.title = entry.getHWID();
		
		this.addEntry(entry);
	}
	
	public void addEntry(PlayerEntry entry) {
		if (!entry.getHWID().equals(this.title)) {
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
