package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import user.User;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Dialog.ModalityType;
import java.util.List;

import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class UserView extends JFrame {

	private JPanel contentPane;
	
	@SuppressWarnings("rawtypes")
	private JList listFollowing;
	private ListModel listFollowingModel = new DefaultListModel();
	@SuppressWarnings("rawtypes")
	private JList listFeed;
	private ListModel listFeedModel = new DefaultListModel();

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
		setType(Type.POPUP);
		setTitle("User View");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane listFeedScrollPane = new JScrollPane();
		listFeedScrollPane.setBounds(29, 314, 382, 180);
		contentPane.add(listFeedScrollPane);
		
		JScrollPane listFollowingScrollPane = new JScrollPane();
		listFollowingScrollPane.setBounds(29, 81, 382, 134);
		contentPane.add(listFollowingScrollPane);
		
		listFollowing = new JList();
		listFollowing.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listFollowing.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listFollowingScrollPane.setViewportView(listFollowing);
		listFollowing.setBorder(null);
		listFollowing.setModel(listFollowingModel);
		
		listFeed = new JList();
		listFeed.setModel(listFeedModel);
		listFeed.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listFeed.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listFeed.setBorder(null);
		listFeedScrollPane.setViewportView(listFeed);
		
		btnFollowUser = new JButton("Follow User");
		btnFollowUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Follow User dialog
				FollowUserDialog flwUserDialog = new FollowUserDialog();
				flwUserDialog.setOurID(user.getID());
				
				//Set dialog parameters and make it visible
				flwUserDialog.setModalityType(ModalityType.APPLICATION_MODAL);
				flwUserDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				flwUserDialog.setVisible(true);
				
				//Get validated input ID of user to follow
				String inputID = flwUserDialog.getID();
				//Have the user follow the object
				user.followUser(inputID);
				
				//List<String> followings = user.getFollowings();
				((DefaultListModel)listFollowingModel).addElement(inputID);
			}
		});
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
		
		JLabel lblView = new JLabel("View (Currently Following)");
		lblView.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblView.setBounds(29, 56, 170, 24);
		contentPane.add(lblView);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(29, 231, 382, 2);
		contentPane.add(separator_1);
		
		btnTweetMessage = new JButton("Tweet Message");
		btnTweetMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewTweetDialog tweetDialog = new NewTweetDialog();
				
				tweetDialog.setModalityType(ModalityType.APPLICATION_MODAL);
				tweetDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				tweetDialog.setVisible(true);
				
				//Get input tweet from the dialog
				String tweet = tweetDialog.getMessage();
				//Add to GUI
				((DefaultListModel)listFeedModel).addElement((user.getID() + ":"+ tweet));
				
				user.postTweet(tweet);
			}
		});
		btnTweetMessage.setEnabled(false);
		btnTweetMessage.setToolTipText("Click to open a dialog that allows for messages to be tweeted from your account.");
		btnTweetMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTweetMessage.setBounds(29, 244, 382, 34);
		contentPane.add(btnTweetMessage);
		
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
		txtIDInput.setText(user.getID());
		//Update following view
		listFollowing = new JList(user.getFollowings().toArray());
		//TODO:Update tweet feed
		listFeed = new JList(user.getFeed().toArray());
		
		user.setUserView(this);
	}
	
	public void updateFeed(String msg) {
		((DefaultListModel)listFeedModel).addElement(msg);
	}
}
