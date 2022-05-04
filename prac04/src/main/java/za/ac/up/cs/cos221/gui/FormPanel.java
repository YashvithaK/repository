package za.ac.up.cs.cos221.gui;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import za.ac.up.cs.cos221.model.Address;
import za.ac.up.cs.cos221.model.AddressDao;

public class FormPanel extends JPanel {

	/**
	 * Customer / Client Form  
	 */
	private static final long serialVersionUID = -1553244924457900114L;
	private JLabel firstnameLabel;
	private JLabel lastnameLabel;
	private JLabel emailLabel;
	
	private JTextField firstnameField;
	private JTextField lastnameField;
	private JTextField emailField;
	
	private JButton okBtn;
	private FormListener formListener;
//	private JList ageList;
	private JComboBox empCombo;
	private JCheckBox isActiveCheck;
//	private JTextField taxField;

	DefaultListModel addressModel = new DefaultListModel();

	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		setMinimumSize(dim);

		firstnameLabel = new JLabel("First Name: ");
		lastnameLabel = new JLabel("Last Name: ");
		firstnameField = new JTextField(10);
		lastnameField = new JTextField(10);
		emailField = new JTextField(10);

		isActiveCheck = new JCheckBox();
		
//		ageList = new JList();
		empCombo = new JComboBox();

//		taxField = new JTextField(10);
//		taxLabel = new JLabel("Tax ID: ");
		
		okBtn = new JButton("OK");
		
		// Set up mnemomics
		okBtn.setMnemonic(KeyEvent.VK_O);
		
		firstnameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		firstnameLabel.setLabelFor(firstnameField);

		lastnameLabel.setDisplayedMnemonic(KeyEvent.VK_L);
		lastnameLabel.setLabelFor(lastnameField);

		// Set up Address Combo Box
		AddressDao dao = new AddressDao();
    	try {
			dao.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	List<Address> addrList = dao.getAddress();

		// Set up combo box.
    	DefaultComboBoxModel addrModel = new DefaultComboBoxModel();
		for (Address address : addrList) {
			addrModel.addElement(new Addr(address.getId(), address.getAddress1()));
    	}
		empCombo.setModel(addrModel);
		try {
			empCombo.setSelectedIndex(0);
		} catch (Exception e) {
		}
		empCombo.setEditable(false);
		empCombo.setPreferredSize(new Dimension(120, 20));
		
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstname = firstnameField.getText();
				String lastname = lastnameField.getText();
				String email = emailField.getText();
				Addr addre =  (Addr)empCombo.getSelectedItem();
				System.out.println("addr id selected:"+addre.getId());
				boolean isActive = isActiveCheck.isSelected();
				
				ClientFormEvent ev = new ClientFormEvent(this, firstname, lastname, email, isActive, 2, addre.getId());

				if (formListener != null) {
					formListener.formEventOccurred(ev);
				}
			}
		});

		Border innerBorder = BorderFactory.createTitledBorder("Add Customer");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		layoutComponents();
	}

	public void layoutComponents() {

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		// ////////// First row ///////////////////////////////////

		gc.gridy = 0;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(firstnameLabel, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(firstnameField, gc);

		// //////////Second row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(lastnameLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(lastnameField, gc);

		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Email: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
//		add(ageList, gc);
		add(emailField, gc);

		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Address: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(empCombo, gc);

		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Customer Active: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(isActiveCheck, gc);

		// //////////Next row ///////////////////////////////////

//		gc.gridy++;
//
//		gc.weightx = 1;
//		gc.weighty = 0.2;
//
//		gc.gridx = 0;
//		gc.insets = new Insets(0, 0, 0, 5);
//		gc.anchor = GridBagConstraints.FIRST_LINE_END;
//		add(taxLabel, gc);
//
//		gc.gridx = 1;
//		gc.anchor = GridBagConstraints.FIRST_LINE_START;
//		gc.insets = new Insets(0, 0, 0, 0);
//		add(taxField, gc);
		
		// //////////Next row ///////////////////////////////////

//		gc.gridy++;
//
//		gc.weightx = 1;
//		gc.weighty = 0.05;
//
//		gc.gridx = 0;
//		gc.insets = new Insets(0, 0, 0, 5);
//		gc.anchor = GridBagConstraints.LINE_END;
//		add(new JLabel("Gender: "), gc);
//
//		gc.gridx = 1;
//		gc.anchor = GridBagConstraints.FIRST_LINE_START;
//		gc.insets = new Insets(0, 0, 0, 0);
//		add(maleRadio, gc);
		
		// //////////Next row ///////////////////////////////////

//		gc.gridy++;
//
//		gc.weightx = 1;
//		gc.weighty = 0.2;
//
//		gc.gridx = 1;
//		gc.anchor = GridBagConstraints.FIRST_LINE_START;
//		gc.insets = new Insets(0, 0, 0, 0);
//		add(femaleRadio, gc);

		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 2.0;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(okBtn, gc);
	}

	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
	
//	public void refresh() {
//		addressModel.fireTableDataChanged();
//	}
}

class Addr{
	private int id;
	private String text;

	public Addr(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public String toString() {
		return text;
	}

	public int getId() {
		return id;
	}
}