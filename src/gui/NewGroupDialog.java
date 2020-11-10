package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.Dialog.ModalityType;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Window.Type;

public class NewGroupDialog extends JDialog {
	private JTextField txtGroupIDInput;
	private JTextField txtGroupInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewGroupDialog dialog = new NewGroupDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewGroupDialog() {
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
		
		JLabel lblGroup = new JLabel("Group:");
		lblGroup.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroup.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGroup.setBounds(59, 45, 57, 17);
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
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(79, 123, 67, 29);
		getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(156, 123, 81, 29);
		getContentPane().add(btnCancel);
	}
}
