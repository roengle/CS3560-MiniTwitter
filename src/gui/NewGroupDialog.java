package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewGroupDialog extends JDialog {
	
	private JTextField txtGroupIDInput;
	private JTextField txtGroupInput;
	private JButton btnOk;
	private JButton btnCancel;
	
	private String selectedGroup;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewGroupDialog dialog = new NewGroupDialog();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewGroupDialog() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//"X" button is clicked, set group id input to null so a new group isn't added
				txtGroupIDInput = null;
			}
		});
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setType(Type.POPUP);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setResizable(false);
		setTitle("New Group Dialog");
		setBounds(100, 100, 300, 200);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Group ID:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(47, 12, 69, 17);
		getContentPane().add(lblNewLabel);
		
		txtGroupIDInput = new JTextField();
		txtGroupIDInput.setHorizontalAlignment(SwingConstants.LEFT);
		txtGroupIDInput.setColumns(8);
		txtGroupIDInput.setBounds(122, 11, 115, 20);
		getContentPane().add(txtGroupIDInput);
		
		JLabel lblGroup = new JLabel("Parent Group:");
		lblGroup.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroup.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGroup.setBounds(10, 45, 106, 17);
		getContentPane().add(lblGroup);
		
		txtGroupInput = new JTextField();
		txtGroupInput.setHorizontalAlignment(SwingConstants.LEFT);
		txtGroupInput.setEditable(false);
		txtGroupInput.setColumns(8);
		txtGroupInput.setBounds(122, 45, 115, 20);
		getContentPane().add(txtGroupInput);
		
		JTextArea txtrtoChangeGroup = new JTextArea();
		txtrtoChangeGroup.setWrapStyleWord(true);
		txtrtoChangeGroup.setText("*To change group, click on a different group in the Admin panel.");
		txtrtoChangeGroup.setLineWrap(true);
		txtrtoChangeGroup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrtoChangeGroup.setEditable(false);
		txtrtoChangeGroup.setBackground(SystemColor.menu);
		txtrtoChangeGroup.setBounds(86, 73, 151, 49);
		getContentPane().add(txtrtoChangeGroup);
		
		btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ok button clicked. Check if text is empty. If so, set to null so new group isn't created.
				if(txtGroupIDInput.getText() == "") { txtGroupIDInput = null; }
				dispose();
			}
		});
		btnOk.setBounds(79, 123, 67, 29);
		getContentPane().add(btnOk);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Cancel button clicked. Invalidate the text input so a new group isn't created.
				txtGroupIDInput = null;
				dispose();
			}
		});
		btnCancel.setBounds(156, 123, 81, 29);
		getContentPane().add(btnCancel);
		
		//Set default button so enter key submits
		getRootPane().setDefaultButton(btnOk);
	}
	
	/**
	 * Set's the selected group to display in the second text box.
	 * 
	 * @param group the ID of the group we are adding this group to
	 */
	public void setSelectedGroup(String group) {
		this.selectedGroup = group;
		txtGroupInput.setText(this.selectedGroup);
	}
	
	/**
	 * Gets the ID that we prompted the user for.
	 * 
	 * @return the ID that we prompted for
	 */
	public String getID() { return this.txtGroupIDInput != null ? this.txtGroupIDInput.getText() : null; }
}
