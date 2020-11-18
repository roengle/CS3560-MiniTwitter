package gui;

import javax.swing.JButton;
import javax.swing.JDialog;

import admin.AdminController;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FollowUserDialog extends JDialog {
	
	private JTextField inputID;
	private JButton btnOk;
	private JButton btnCancel;
	private String ourID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FollowUserDialog dialog = new FollowUserDialog();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FollowUserDialog() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//"X" button clicked, set input to null so a new user isn't followed
				inputID = null;
			}
		});
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setTitle("Follow New User");
		setType(Type.POPUP);
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 300, 200);
		getContentPane().setLayout(null);
		
		btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If no text is entered, set our input id to null so a user isn't followed
				if(inputID.getText() == "") { inputID = null; }
				//Dispose and close window
				dispose();
			}
		});
		btnOk.setEnabled(false);
		btnOk.setBounds(61, 116, 68, 23);
		getContentPane().add(btnOk);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Cancel button was pressed, so set input id to null so a user isn't followed
				inputID = null;
				//Cancel button closes dialog and discards data.
				dispose();
			}
		});
		btnCancel.setBounds(139, 116, 89, 23);
		getContentPane().add(btnCancel);
		
		inputID = new JTextField();
		inputID.setColumns(10);
		inputID.setBounds(109, 25, 119, 28);
		inputID.getDocument().addDocumentListener(new DocumentListener() {
			/*Same code in both methods since we want to check when text is changed*/
			public void insertUpdate(DocumentEvent e) {
				//Enable the ok button if a user with the given ID actually exists
				btnOk.setEnabled(AdminController.getUserByID(inputID.getText()) != null && !inputID.getText().equals(ourID));
			}

			public void removeUpdate(DocumentEvent e) {
				//Enable the ok button if a user with the given ID actually exists
				btnOk.setEnabled(AdminController.getUserByID(inputID.getText()) != null && !inputID.getText().equals(ourID));
			}

			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
		getContentPane().add(inputID);
		
		JLabel lblInputId = new JLabel("Input ID:");
		lblInputId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInputId.setBounds(49, 23, 58, 28);
		getContentPane().add(lblInputId);
		
		JTextArea txtrPleaseInputThe = new JTextArea();
		txtrPleaseInputThe.setFont(new Font("Arial", Font.PLAIN, 12));
		txtrPleaseInputThe.setText("*Please input the user ID of the user you wish to follow above.");
		txtrPleaseInputThe.setBackground(UIManager.getColor("Button.background"));
		txtrPleaseInputThe.setWrapStyleWord(true);
		txtrPleaseInputThe.setForeground(Color.BLACK);
		txtrPleaseInputThe.setEditable(false);
		txtrPleaseInputThe.setLineWrap(true);
		txtrPleaseInputThe.setBounds(51, 73, 177, 32);
		getContentPane().add(txtrPleaseInputThe);
	}
	
	/**
	 * Gets the ID of the user to follow
	 * 
	 * @return the ID of the user to follow
	 */
	public String getID() {
		return this.inputID != null ? this.inputID.getText() : null;
	}
	
	/**
	 * Sets the ID of the user that is going to be following another user using this dialog.
	 * It is important to know *our* ID since we don't want to follow ourself.
	 * 
	 * @param ourID the ID of the user who will be following another
	 */
	public void setOurID(String ourID) {
		this.ourID = ourID;
	}
}
