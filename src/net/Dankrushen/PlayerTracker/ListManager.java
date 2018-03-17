package net.Dankrushen.PlayerTracker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

class SortbyName implements Comparator<PlayerEntry>
{
	public int compare(PlayerEntry a, PlayerEntry b)
	{
		String obj1 = a.getName();
		String obj2 = b.getName();

		if (obj1 == null) {
			return -1;
		}
		if (obj2 == null) {
			return 1;
		}
		if (obj1.equals( obj2 )) {
			return 0;
		}

		return obj1.compareTo(obj2);
	}
}

public class ListManager {

	private JFrame frame;

	public List<PlayerEntry> players = new ArrayList<PlayerEntry>();
	public List<NamePlayer> namePlayers = new ArrayList<NamePlayer>();
	public List<IPPlayer> ipPlayers = new ArrayList<IPPlayer>();
	public List<HWIDPlayer> hwidPlayers = new ArrayList<HWIDPlayer>();

	String mainDir;

	public ListManager(JFrame frame, String mainDir) {
		this.frame = frame;
		this.mainDir = mainDir;

		this.reloadData();
	}

	public void changeMainDir(String mainDir) {
		this.mainDir = mainDir.trim();

		this.reloadData();
	}

	public void reloadData() {
		players.clear();

		if(this.mainDir.isEmpty() || this.mainDir.trim().isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Invalid ban directory: \"" + this.mainDir + "\"", "Error!", JOptionPane.ERROR_MESSAGE);
		} else {
			File playersDir = new File(this.mainDir);

			if(playersDir != null && playersDir.exists() && playersDir.isDirectory()) {
				addFile(playersDir);
			} else {
				JOptionPane.showMessageDialog(frame, "Invalid ban directory: \"" + this.mainDir + "\"", "Error!", JOptionPane.ERROR_MESSAGE);
			}
		}

		players.sort(new SortbyName());
		
		namePlayers.clear();
		ipPlayers.clear();
		hwidPlayers.clear();

		for (PlayerEntry entry : players) {
			addNamePlayer(entry);
			addIPPlayer(entry);
			addHWIDPlayer(entry);
		}

		players.clear();
	}

	public void addFile(File file) {
		if (file.isDirectory()) {
			for (File ban : file.listFiles()) {
				addFile(ban);
			}
		} else {
			try {
				players.add(new PlayerEntry(file));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(frame, "Invalid ban file: \"" + file.getName() + "\"", "Error!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public DefaultListModel<Player> makeListModel(List<Player> playerList) {
		DefaultListModel<Player> model = new DefaultListModel<Player>();

		for (Player player : playerList) {
			model.addElement(player);
		}

		return model;
	}
	
	public DefaultListModel<Player> makeFilteredListModel(List<Player> playerList, String filter) {
		DefaultListModel<Player> model = new DefaultListModel<Player>();

		for (Player player : playerList) {
			if(player.title.toLowerCase().contains(filter.toLowerCase()) || player.listsContain(filter)) model.addElement(player);
		}

		return model;
	}

	public List<Player> toListPlayer(List<? extends Player> namePlayers) {
		List<Player> listPlayer = new ArrayList<Player>();

		listPlayer.addAll(namePlayers);

		return listPlayer;
	}

	public boolean containsPlayer(List<Player> playerList, String title) {
		for (Player player : playerList) {
			if (player.title.equals(title)) {
				return true;
			}
		}

		return false;
	}

	public NamePlayer addNamePlayer(PlayerEntry entry) {
		for (NamePlayer player : namePlayers) {
			if (player.title.equals(entry.getName())) {
				player.addEntry(entry);
				return player;
			}
		}

		NamePlayer player = new NamePlayer(entry);
		namePlayers.add(player);

		return player;
	}

	public IPPlayer addIPPlayer(PlayerEntry entry) {
		for (IPPlayer player : ipPlayers) {
			if (player.title.equals(entry.getIP())) {
				player.addEntry(entry);
				return player;
			}
		}

		IPPlayer player = new IPPlayer(entry);
		ipPlayers.add(player);

		return player;
	}

	public HWIDPlayer addHWIDPlayer(PlayerEntry entry) {
		for (HWIDPlayer player : hwidPlayers) {
			if (player.title.equals(entry.getHWID())) {
				player.addEntry(entry);
				return player;
			}
		}

		HWIDPlayer player = new HWIDPlayer(entry);
		hwidPlayers.add(player);

		return player;
	}
}
