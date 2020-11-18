package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Window.Type;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InfoDialog extends JDialog {
	
	private JButton btnOk;
	
	private JTextArea mainText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoDialog dialog = new InfoDialog();
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public InfoDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setType(Type.POPUP);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 300, 150);
		getContentPane().setLayout(null);
		
		btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOk.setBounds(111, 72, 73, 23);
		getContentPane().add(btnOk);
		
		mainText = new JTextArea();
		mainText.setFont(new Font("Arial", Font.PLAIN, 12));
		mainText.setText("The");
		mainText.setBackground(UIManager.getColor("Button.background"));
		mainText.setLineWrap(true);
		mainText.setWrapStyleWord(true);
		mainText.setEditable(false);
		mainText.setBounds(33, 11, 216, 50);
		getContentPane().add(mainText);
	}
	
	public InfoDialog(String text) {
		this();
		setMainText(text);
		mainText.validate();
	}
	
	/**
	 * Sets the text inside of the main text box.
	 * 
	 * @param text the text to set the text box to
	 */
	public void setMainText(String text) {
		mainText.setText(text);
	}
}
