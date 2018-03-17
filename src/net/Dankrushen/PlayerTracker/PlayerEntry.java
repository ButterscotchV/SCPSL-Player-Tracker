package net.Dankrushen.PlayerTracker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerEntry {

	private String playerName;
	private String playerHWID;
	private String playerIP;
	private String endDateTime;
	private String dateTime;

	public PlayerEntry(String playerName, String playerHWID, String playerIP, String endDateTime, String dateTime) {
		this.playerName = playerName;
		this.playerHWID = playerHWID;
		this.playerIP = playerIP;
		this.endDateTime = endDateTime;
		this.dateTime = dateTime;
	}

	public PlayerEntry(File banFile) throws FileNotFoundException, IOException {
		try {
			BufferedReader br = new BufferedReader(new FileReader(banFile));

			List<String> banText = new ArrayList<String>();
			String line;

			while ((line = br.readLine()) != null && banText.size() < 5) {
				banText.add(line);
			}

			br.close();

			if (banText.size() >= 4) {
				this.playerName = banText.get(0);
				this.playerHWID = banText.get(1);
				this.playerIP = banText.get(2);
				this.endDateTime = banText.get(3);
				this.dateTime = (banText.size() >= 5 ? banText.get(4) : "Unknown Date");
			}
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Invalid Ban Directory");
		}

		if (!isValid()) {
			throw new IOException("Invalid Ban File");
		}
	}

	public boolean isValid() {
		return (this.playerName != null && this.playerHWID != null && this.playerIP != null && this.endDateTime != null
				&& this.dateTime != null);
	}

	public String getNameEntry() {
		return this.getName() + (this.getName().isEmpty() ? "" : " ") + "(" + this.getDateTime() + ")";
	}

	public String getHWIDEntry() {
		return this.getHWID() + (this.getHWID().isEmpty() ? "" : " ") + "(" + this.getDateTime() + ")";
	}

	public String getIPEntry() {
		return this.getIP() + (this.getIP().isEmpty() ? "" : " ") + "(" + this.getDateTime() + ")";
	}

	public String getName() {
		return this.playerName;
	}

	public String getHWID() {
		return this.playerHWID;
	}

	public String getIP() {
		return this.playerIP;
	}

	public String getEndDateTime() {
		return this.endDateTime;
	}

	public String getDateTime() {
		return this.dateTime;
	}
}
