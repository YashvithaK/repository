package za.ac.up.cs.cos221.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import za.ac.up.cs.cos221.controller.Controller;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 4775907780893361729L;
//	private TextPanel textPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
//	private JFileChooser fileChooser;
	private Controller controller;
	
	private TablePanel tablePanel1;
	private TablePanel2 tablePanel2;
	private TablePanel3 tablePanel3;
	private TablePanel4 tablePanel4;
	private PrefsDialog prefsDialog;
	private Preferences prefs;
	private JSplitPane splitPane;
	private JTabbedPane tabPane;

	public MainFrame() {
		super("Hello Dvd Store");

		setLayout(new BorderLayout());

		toolbar = new Toolbar();
//		textPanel = new TextPanel();
		formPanel = new FormPanel();
		tablePanel1 = new TablePanel();
		tablePanel2 = new TablePanel2();
		prefsDialog = new PrefsDialog(this);
		tabPane = new JTabbedPane();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tabPane);
		
		splitPane.setOneTouchExpandable(true);
		
		tabPane.addTab("Client List", tablePanel1);
		tabPane.addTab("Staff List", tablePanel2);
		tabPane.addTab("Films List", tablePanel3);
		tabPane.addTab("Inventory List", tablePanel4);
		
		prefs = Preferences.userRoot().node("db");

		controller = new Controller();

		tablePanel1.setData(controller.getClients());
		tablePanel2.setData(controller.getStaff());
		
		tablePanel1.setPersonTableListener(new CustomerTableListener() {
			public void rowDeleted(int row) {
				controller.removeCustomer(row);
			}
		});
		
		tabPane.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				int tabIndex = tabPane.getSelectedIndex();
				System.out.println("Selected Tab:"+tabIndex);
			}
		});
		
		prefsDialog.setPrefsListener(new PrefsListener() {
			public void preferencesSet(String user, String password, int port) {
				prefs.put("user", user);
				prefs.put("password", password);
				prefs.putInt("port", port);
			}
		});
		
		String user = prefs.get("user", "");
		String password = prefs.get("password", "");
		Integer port = prefs.getInt("port", 3306);
		
		prefsDialog.setDefaults(user, password, port);

//		fileChooser = new JFileChooser();
//		fileChooser.addChoosableFileFilter(new CustomerFileFilter());

		setJMenuBar(createMenuBar());

		toolbar.setToolbarListener(new ToolbarListener() {
			public void saveEventOccured() {
				connect();
				
				try {
					controller.saveCustomer();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Unable to save to database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
				}
			}

			public void refreshEventOccured() {
				connect();
				
				try {
					controller.loadCustomer();
					controller.loadStaff();
					controller.loadAddress();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Unable to load from database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
				}
				
				tablePanel1.refresh();
				tablePanel2.refresh();
//				formPanel.refresh();
			}
		});

		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(ClientFormEvent e) {
				controller.addPerson(e);
				tablePanel1.refresh();
			}
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				controller.disconnect();
				dispose();
				System.gc();
			}
			
		});

		add(toolbar, BorderLayout.PAGE_START);
		add(splitPane, BorderLayout.CENTER);

		setMinimumSize(new Dimension(500, 400));
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	
	private void connect() {
		try {
			controller.connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainFrame.this, "Cannot connect to database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
		}
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
//		JMenuItem exportDataItem = new JMenuItem("Export Data...");
//		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");

//		fileMenu.add(exportDataItem);
//		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show");
		JMenuItem prefsItem = new JMenuItem("Preferences...");

		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Customer Form");
		showFormItem.setSelected(true);

		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		windowMenu.add(prefsItem);

		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		
		prefsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefsDialog.setVisible(true);
			}
		});

		showFormItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
				
				if(menuItem.isSelected()) {
					splitPane.setDividerLocation((int)formPanel.getMinimumSize().getWidth());
				}
				
				formPanel.setVisible(menuItem.isSelected());
			}
		});

		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);
		
		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.CTRL_MASK));

		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		
//		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
//				ActionEvent.CTRL_MASK));

//		importDataItem.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
//					try {
//						controller.loadFromFile(fileChooser.getSelectedFile());
//						tablePanel1.refresh();
//					} catch (IOException e1) {
//						JOptionPane.showMessageDialog(MainFrame.this,
//								"Could not load data from file.", "Error",
//								JOptionPane.ERROR_MESSAGE);
//					}
//				}
//			}
//		});

//		exportDataItem.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
//					try {
//						controller.saveToFile(fileChooser.getSelectedFile());
//					} catch (IOException e1) {
//						JOptionPane.showMessageDialog(MainFrame.this,
//								"Could not save data to file.", "Error",
//								JOptionPane.ERROR_MESSAGE);
//					}
//				}
//			}
//		});

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int action = JOptionPane.showConfirmDialog(MainFrame.this,
						"Do you really want to exit the application?",
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

				if (action == JOptionPane.OK_OPTION) {
					WindowListener[] listeners = getWindowListeners();
					
					for(WindowListener listener: listeners) {
						listener.windowClosing(new WindowEvent(MainFrame.this, 0));
					}
				}
			}
		});

		return menuBar;
	}
}
