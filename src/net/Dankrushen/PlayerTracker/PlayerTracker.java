package net.Dankrushen.PlayerTracker;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class PopUpMenu extends JPopupMenu {
	private static final long serialVersionUID = 1L;
	
	JMenuItem anItem;
    public PopUpMenu(){
        anItem = new JMenuItem("Ban");
        add(anItem);
    }
}

public class PlayerTracker {

	private JFrame frame = new JFrame();;

	// Top bar
	private JPanel topPanel;
	private JLabel lblListInformationBy;
	private JComboBox<String> comboBox;
	
	public String defaultBanFolder = "Bans";
	private JLabel lblPlayerBanFolder;
	private JTextField txtBanFolder;
	
	private JButton btnReloadInfo;

	// Main display panel
	private JPanel panel;

	// Information display, shows details about selected item on the list
	private JScrollPane scrollPane;
	private JPanel panel_1;

	private JLabel dataTitle;

	private JLabel firstLabel;
	private JScrollPane firstScrollPane;
	private JList<String> firstList;

	private JLabel secondLabel;
	private JScrollPane secondScrollPane;
	private JList<String> secondList;

	// Player list
	private JScrollPane scrollPane_1;
	private JList<Player> playerList;

	// Display choices
	private static final String NAME = "Username";
	private static final String IP = "IP Address";
	private static final String HWID = "Hardware ID";

	// List management
	public ListManager listManager;
	private JPanel searchPanel;
	private JLabel lblSearch;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new PlayerTracker();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PlayerTracker() {
		listManager = new ListManager(frame, defaultBanFolder);
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setTitle("SCP: SL Player Tracker");
		frame.setBounds(100, 100, 850, 450);
		frame.setMinimumSize(frame.getSize()); // Set minimum size to the default size
		frame.setLocationRelativeTo(null); // Center window on screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		topPanel = new JPanel();
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,}));

		lblListInformationBy = new JLabel("List information by:");
		lblListInformationBy.setHorizontalAlignment(SwingConstants.TRAILING);
		topPanel.add(lblListInformationBy, "2, 2, right, default");

		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("175dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("fill:default:grow"),}));

		scrollPane = new JScrollPane();
		panel.add(scrollPane, "1, 1, fill, fill");

		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), FormSpecs.RELATED_GAP_ROWSPEC, }));

		dataTitle = new JLabel("Username: None");
		dataTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(dataTitle, "2, 2");

		firstLabel = new JLabel("Known IPs");
		firstLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(firstLabel, "2, 4");

		firstScrollPane = new JScrollPane();
		panel_1.add(firstScrollPane, "2, 6, fill, fill");

		firstList = new JList<String>();
		firstScrollPane.setViewportView(firstList);

		secondLabel = new JLabel("Known HWIDs");
		secondLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(secondLabel, "2, 8");

		secondScrollPane = new JScrollPane();
		panel_1.add(secondScrollPane, "2, 10, fill, fill");

		secondList = new JList<String>();
		secondScrollPane.setViewportView(secondList);

		scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, "2, 1, 2, 1, fill, fill");

		playerList = new JList<Player>();
		playerList.setComponentPopupMenu(new PopUpMenu());
		playerList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Player player = playerList.getSelectedValue();
				String mode = (String) comboBox.getSelectedItem();

				if (mode == null || player == null) {
					return;

				} else if (mode.equals(NAME)) {
					dataTitle.setText(NAME + ": " + player.title);

				} else if (mode.equals(IP)) {
					dataTitle.setText(IP + ": " + player.title);

				} else if (mode.equals(HWID)) {
					dataTitle.setText(HWID + ": " + player.title);
				}

				firstList.setModel(getListModel(player.firstList));
				secondList.setModel(getListModel(player.secondList));
			}
		});
		scrollPane_1.setViewportView(playerList);
		
		searchPanel = new JPanel();
		scrollPane_1.setColumnHeaderView(searchPanel);
		searchPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("40px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("86px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,}));
		
		lblSearch = new JLabel("Search: ");
		searchPanel.add(lblSearch, "2, 2, fill, center");
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refreshPlayerList();
			}
		});
		searchPanel.add(txtSearch, "4, 2, fill, fill");
		txtSearch.setColumns(10);

		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshPlayerList();
			}
		});
		topPanel.add(comboBox, "4, 2, fill, default");

		btnReloadInfo = new JButton("Reload Data");
		btnReloadInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBanFolder.setText(txtBanFolder.getText().trim());
				listManager.changeMainDir(txtBanFolder.getText());
				refreshPlayerList();
			}
		});

		lblPlayerBanFolder = new JLabel("Player ban folder:");
		topPanel.add(lblPlayerBanFolder, "8, 2, right, default");

		txtBanFolder = new JTextField();
		txtBanFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBanFolder.setText(txtBanFolder.getText().trim());
				listManager.changeMainDir(txtBanFolder.getText());
				refreshPlayerList();
			}
		});
		txtBanFolder.setText(defaultBanFolder);
		topPanel.add(txtBanFolder, "10, 2, fill, default");
		txtBanFolder.setColumns(10);
		topPanel.add(btnReloadInfo, "12, 2");

		// Setup display modes
		comboBox.addItem(NAME);
		comboBox.addItem(IP);
		comboBox.addItem(HWID);
	}

	public void refreshPlayerList() {
		Player selectedPlayer = playerList.getSelectedValue();
		int selectedPlayerIndex = playerList.getSelectedIndex();
		String mode = (String) comboBox.getSelectedItem();

		if (mode == null) {
			return;

		} else if (mode.equals(NAME)) {
			dataTitle.setText(NAME + ": None");
			firstLabel.setText("Known IPs");
			secondLabel.setText("Known HWIDs");
			playerList.setModel(listManager.makeFilteredListModel(listManager.toListPlayer(listManager.namePlayers), txtSearch.getText()));

		} else if (mode.equals(IP)) {
			dataTitle.setText(IP + ": None");
			firstLabel.setText("Known Nicknames");
			secondLabel.setText("Known HWIDs");
			playerList.setModel(listManager.makeFilteredListModel(listManager.toListPlayer(listManager.ipPlayers), txtSearch.getText()));

		} else if (mode.equals(HWID)) {
			dataTitle.setText(HWID + ": None");
			firstLabel.setText("Known Nicknames");
			secondLabel.setText("Known IPs");
			playerList.setModel(listManager.makeFilteredListModel(listManager.toListPlayer(listManager.hwidPlayers), txtSearch.getText()));
		}

		if (playerList.getModel() != null) {
			int index = getModelIndex(playerList.getModel(), selectedPlayer, selectedPlayerIndex);
			playerList.setSelectedIndex(index >= 0 ? index : 0);
		}
	}

	public int getModelIndex(ListModel<Player> model, Player selectedPlayer, int oldIndex) {
		if (model != null && model.getSize() > 0)
		{
			for (int i = 0; i < model.getSize(); i++) {
				if (model.getElementAt(i).equals(selectedPlayer)) {
					return i;
				}
			}
		}

		return oldIndex;
	}

	public DefaultListModel<String> getListModel(List<String> list) {
		DefaultListModel<String> model = new DefaultListModel<String>();

		for (String item : list) {
			model.addElement(item);
		}

		return model;
	} 
}
