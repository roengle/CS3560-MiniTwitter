package driver;

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

public class NewUserDialog extends JDialog {
	
	private JTextField txtID;
	private String selectedGroup;
	private JTextField txtGroup;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewUserDialog dialog = new NewUserDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewUserDialog() {
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
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtID = null;
				//Cancel button closes dialog and discards data.
				dispose();
			}
		});
		btnCancel.setBounds(150, 119, 81, 29);
		getContentPane().add(btnCancel);
		{
			JButton btnOk = new JButton("Ok");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
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
	
	public void setSelectedGroup(String group) {
		this.selectedGroup = group;
		txtGroup.setText(this.selectedGroup);
	}
	
	public String getID() {
		return this.txtID != null ? this.txtID.getText() : null;
	}
}
