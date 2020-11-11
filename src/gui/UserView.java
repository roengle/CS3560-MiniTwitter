package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import user.User;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class UserView extends JFrame {

	private JPanel contentPane;
	
	@SuppressWarnings("rawtypes")
	private JList listFollowing;
	@SuppressWarnings("rawtypes")
	private JList listFeed;
	
	private List<String> localFollowings;
	private List<String> localFeed;

	private JButton btnFollowUser;
	private JButton btnTweetMessage;
	
	private JTextField txtIDInput;
	
	private User user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserView frame = new UserView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UserView() {
		setTitle("User View");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnFollowUser = new JButton("Follow User");
		btnFollowUser.setEnabled(false);
		btnFollowUser.setBounds(259, 11, 152, 34);
		contentPane.add(btnFollowUser);
		
		txtIDInput = new JTextField();
		txtIDInput.setEditable(false);
		txtIDInput.setBounds(97, 11, 152, 34);
		contentPane.add(txtIDInput);
		txtIDInput.setColumns(10);
		
		JLabel lblYourID = new JLabel("Your ID:");
		lblYourID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblYourID.setBounds(29, 14, 64, 24);
		contentPane.add(lblYourID);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(29, 56, 382, 2);
		contentPane.add(separator);
		
		JScrollPane listFollowingScrollPane = new JScrollPane();
		listFollowingScrollPane.setBounds(29, 81, 382, 134);
		contentPane.add(listFollowingScrollPane);
		
		listFollowing = new JList();
		listFollowing.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listFollowing.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listFollowingScrollPane.setViewportView(listFollowing);
		listFollowing.setBorder(null);
		listFollowing.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JLabel lblView = new JLabel("View (Currently Following)");
		lblView.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblView.setBounds(29, 56, 170, 24);
		contentPane.add(lblView);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(29, 231, 382, 2);
		contentPane.add(separator_1);
		
		btnTweetMessage = new JButton("Tweet Message");
		btnTweetMessage.setEnabled(false);
		btnTweetMessage.setToolTipText("Click to open a dialog that allows for messages to be tweeted from your account.");
		btnTweetMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTweetMessage.setBounds(29, 244, 382, 34);
		contentPane.add(btnTweetMessage);
		
		JScrollPane listFeedScrollPane = new JScrollPane();
		listFeedScrollPane.setBounds(29, 314, 382, 180);
		contentPane.add(listFeedScrollPane);
		
		listFeed = new JList();
		listFeed.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listFeed.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listFeed.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listFeed.setBorder(null);
		listFeedScrollPane.setViewportView(listFeed);
		
		JLabel lblFeed = new JLabel("Tweet Feed");
		lblFeed.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFeed.setBounds(29, 289, 170, 24);
		contentPane.add(lblFeed);
	}
	
	/**
	 * UserView constructor that takes in a User object. Calls the other constructor like normal,
	 * and sets the UserView's user field.
	 * 
	 * @param user the User object
	 */
	public UserView(User user) {
		this();
		setUser(user);
	}
	
	/**
	 * Set's the UserView's user field. Also initializes following list to User's
	 * followings list
	 * 
	 * @param user the User object to set the user field to
	 */
	public void setUser(User user) {
		this.user = user;
		setTitle("User View - " + user.getID());
		//Unlock buttons
		btnFollowUser.setEnabled(true);
		btnTweetMessage.setEnabled(true);
		//Update following view
		DefaultListModel listModel = (DefaultListModel)listFollowing.getModel();
		for(String s : user.getFollowings()) {
			listModel.addElement(s);
		}
		//TODO:Update tweet feed
		listModel = (DefaultListModel)listFeed.getModel();
		for(String s : user.getFeed()) {
			
		}
	}
}
