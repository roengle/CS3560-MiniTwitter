package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewTweetDialog extends JDialog {
	
	private JButton btnPost;
	private JButton btnCancel;
	private JTextArea txtMessage;;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewTweetDialog dialog = new NewTweetDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewTweetDialog() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				txtMessage = null;
			}
		});
		setModal(true);
		setType(Type.POPUP);
		setTitle("Post New Tweet");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(null);
		{
			txtMessage = new JTextArea();
			txtMessage.setWrapStyleWord(true);
			txtMessage.setLineWrap(true);
			txtMessage.setBounds(41, 33, 356, 159);
			getContentPane().add(txtMessage);
		}
		{
			JLabel lblNewLabel = new JLabel("Tweet Message:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setBounds(40, 11, 116, 14);
			getContentPane().add(lblNewLabel);
		}
		{
			btnPost = new JButton("Post Tweet");
			btnPost.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(txtMessage.getText().equals("")) { txtMessage = null; }
					dispose();
				}
			});
			btnPost.setBounds(99, 211, 104, 40);
			getContentPane().add(btnPost);
		}
		{
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtMessage = null;
					dispose();
				}
			});
			btnCancel.setBounds(237, 211, 104, 40);
			getContentPane().add(btnCancel);
		}
		
		getRootPane().setDefaultButton(btnPost);
	}
	
	
	public String getMessage() {
		return txtMessage.getText();
	}
}
