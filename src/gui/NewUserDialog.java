package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewUserDialog extends JDialog {
	
	private JTextField txtID;
	private JTextField txtGroup;
	
	private JButton btnCancel;
	private JButton btnOk;
	
	private String selectedGroup;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewUserDialog dialog = new NewUserDialog();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewUserDialog() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//"X" button clicked. Invalidate input so a new user isn't added
				txtID = null;
			}
		});
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setResizable(false);
		setType(Type.POPUP);
		setTitle("New User Dialog");
		setBounds(100, 100, 300, 200);
		getContentPane().setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("User ID:");
			lblNewLabel.setBounds(53, 12, 57, 17);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			getContentPane().add(lblNewLabel);
		}
		{
			txtID = new JTextField();
			txtID.setBounds(116, 11, 115, 20);
			txtID.setHorizontalAlignment(SwingConstants.LEFT);
			getContentPane().add(txtID);
			txtID.setColumns(8);
		}
		{
			JLabel lblGroup = new JLabel("Group:");
			lblGroup.setBounds(53, 45, 57, 17);
			lblGroup.setHorizontalAlignment(SwingConstants.CENTER);
			lblGroup.setFont(new Font("Tahoma", Font.BOLD, 14));
			getContentPane().add(lblGroup);
		}
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Cancel button clicked. Invalide ID so a new user isn't created.
				txtID = null;
				//Cancel button closes dialog and discards data.
				dispose();
			}
		});
		btnCancel.setBounds(150, 119, 81, 29);
		getContentPane().add(btnCancel);
		{
			btnOk = new JButton("Ok");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Ok button clicked. Check if ID field is empty. If so, invalidate it so a new user isn't created
					if(txtID.getText() == "") { txtID = null; }
					dispose();
				}
			});
			btnOk.setBounds(73, 119, 67, 29);
			getContentPane().add(btnOk);
		}
		{
			txtGroup = new JTextField();
			txtGroup.setEditable(false);
			txtGroup.setHorizontalAlignment(SwingConstants.LEFT);
			txtGroup.setColumns(8);
			txtGroup.setBounds(116, 45, 115, 20);
			
			getContentPane().add(txtGroup);
		}
		
		//Set default button so enter key works
		getRootPane().setDefaultButton(btnOk);
		
		JTextArea txtrtoChangeGroup = new JTextArea();
		txtrtoChangeGroup.setBackground(UIManager.getColor("Button.background"));
		txtrtoChangeGroup.setEditable(false);
		txtrtoChangeGroup.setWrapStyleWord(true);
		txtrtoChangeGroup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrtoChangeGroup.setLineWrap(true);
		txtrtoChangeGroup.setText("*To change group, click on a different group in the Admin panel.");
		txtrtoChangeGroup.setBounds(73, 73, 151, 49);
		getContentPane().add(txtrtoChangeGroup);
	}
	
	/**
	 * Set's the selected group to display in the second text box
	 * 
	 * @param group the ID of the group we are adding this user to
	 */
	public void setSelectedGroup(String group) {
		this.selectedGroup = group;
		txtGroup.setText(this.selectedGroup);
	}
	
	/**
	 * Gets the ID that we prompted the user for.
	 * 
	 * @return the ID we prompted for
	 */
	public String getID() { return this.txtID != null ? this.txtID.getText() : null; }
}
