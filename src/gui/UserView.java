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

	//Our two main buttons
	private JButton btnFollowUser;
	private JButton btnTweetMessage;
	
	//Text boxes showing our user info
	private JTextField txtIDInput;
	private JTextField txtCreationTime;
	private JLabel lblLastUpdate;
	
	//Our associated user object
	private User user;
	private long creationTime;
	private long lastUpdateTime;
	

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
		//Set default settings
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
		listFeedScrollPane.setBounds(29, 333, 382, 161);
		contentPane.add(listFeedScrollPane);
		
		JScrollPane listFollowingScrollPane = new JScrollPane();
		listFollowingScrollPane.setBounds(29, 120, 382, 134);
		contentPane.add(listFollowingScrollPane);
		
		//Make our two lists
		listFollowing = new JList();
		listFollowing.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listFollowing.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listFollowingScrollPane.setViewportView(listFollowing);
		listFollowing.setBorder(null);
		//Set our list's model manually
		listFollowing.setModel(listFollowingModel);
		
		listFeed = new JList();
		
		listFeed.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listFeed.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listFeed.setBorder(null);
		listFeedScrollPane.setViewportView(listFeed);
		//Set our list's model manually
		listFeed.setModel(listFeedModel);
		
		//Our follow user button
		btnFollowUser = new JButton("Follow User");
		btnFollowUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Follow User dialog
				FollowUserDialog flwUserDialog = new FollowUserDialog();
				//Set out Id in the follow user dialog
				flwUserDialog.setOurID(user.getID());
				
				//Set dialog parameters and make it visible
				flwUserDialog.setModalityType(ModalityType.APPLICATION_MODAL);
				flwUserDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				flwUserDialog.setVisible(true);
				
				//Get validated input ID of user to follow
				String inputID = flwUserDialog.getID();
				//Dont re-follow if we are already following the user
				if(!user.isFollowing(inputID)) {
					//Have the user follow the object
					user.followUser(inputID);
					//Update the JList
					((DefaultListModel)listFollowingModel).addElement(inputID);
				}
				
			}
		});
		btnFollowUser.setEnabled(false);
		btnFollowUser.setBounds(259, 44, 152, 34);
		contentPane.add(btnFollowUser);
		
		txtIDInput = new JTextField();
		txtIDInput.setEditable(false);
		txtIDInput.setBounds(97, 44, 152, 34);
		contentPane.add(txtIDInput);
		txtIDInput.setColumns(10);
		
		JLabel lblYourID = new JLabel("Your ID:");
		lblYourID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblYourID.setBounds(29, 47, 64, 24);
		contentPane.add(lblYourID);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(29, 88, 382, 2);
		contentPane.add(separator);
		
		JLabel lblView = new JLabel("Currently Following");
		lblView.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblView.setBounds(29, 95, 126, 24);
		contentPane.add(lblView);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(29, 264, 382, 2);
		contentPane.add(separator_1);
		
		btnTweetMessage = new JButton("Tweet Message");
		btnTweetMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//New tweet dialog
				NewTweetDialog tweetDialog = new NewTweetDialog();
				//Set window defaults
				tweetDialog.setModalityType(ModalityType.APPLICATION_MODAL);
				tweetDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				tweetDialog.setVisible(true);
				
				//Get input tweet from the dialog
				String tweet = tweetDialog.getMessage();
				//Add to GUI
				((DefaultListModel)listFeedModel).addElement((user.getID() + ":"+ tweet));
				//Have the user object post the tweet
				user.postTweet(tweet);
			}
		});
		btnTweetMessage.setEnabled(false);
		btnTweetMessage.setToolTipText("Click to open a dialog that allows for messages to be tweeted from your account.");
		btnTweetMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTweetMessage.setBounds(29, 272, 382, 34);
		contentPane.add(btnTweetMessage);
		
		JLabel lblFeed = new JLabel("Tweet Feed");
		lblFeed.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFeed.setBounds(29, 308, 170, 24);
		contentPane.add(lblFeed);
		
		txtCreationTime = new JTextField();
		txtCreationTime.setEditable(false);
		txtCreationTime.setBounds(141, 7, 268, 33);
		contentPane.add(txtCreationTime);
		txtCreationTime.setColumns(10);
		
		JLabel lblCreationTime = new JLabel("Creation Time:");
		lblCreationTime.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCreationTime.setBounds(29, 11, 112, 23);
		contentPane.add(lblCreationTime);
		
		JLabel lblNewLabel = new JLabel("Last Update:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(202, 95, 84, 24);
		contentPane.add(lblNewLabel);
		
		lblLastUpdate = new JLabel("");
		lblLastUpdate.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblLastUpdate.setBounds(284, 95, 126, 24);
		contentPane.add(lblLastUpdate);
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
	 * Set's the creation time for the user. Called after the associated user object is set.
	 * Retrieves creation time from associated user, and sets the text box.
	 */
	private void setCreationTime() {
		this.creationTime = user.getCreationTime();
		txtCreationTime.setText(Long.toString(this.creationTime));
	}
	
	/**
	 * Set's the view's last update time. Is updated when the associated User object tweets or 
	 * has it's tweet feed updated. Also initially shows the User creation time in the instance 
	 * of the User being initialized with no tweets or tweet feed updates yet.
	 * 
	 * @param updateTime the update time 
	 */
	public void setLastUpdateTime(long updateTime) {
		this.lastUpdateTime = updateTime;
		lblLastUpdate.setText(Long.toString(this.lastUpdateTime));
	}
	
	/**
	 * Set's the UserView's user field. Also initializes following list to User's
	 * followings list
	 * 
	 * @param user the User object to set the user field to
	 */
	private void setUser(User user) {
		this.user = user;
		setTitle("User View - " + user.getID());
		//Unlock buttons
		btnFollowUser.setEnabled(true);
		btnTweetMessage.setEnabled(true);
		txtIDInput.setText(user.getID());
		//Update following view
		listFollowing = new JList(user.getFollowings().toArray());
		//Update tweet feed
		listFeed = new JList(user.getFeed().toArray());
		//Set the User obbject's user view to us
		user.setUserView(this);
		//Set the creation time for the text box
		setCreationTime();
		//Set the last update time for the user
		setLastUpdateTime(user.getLastUpdateTime());
	}
	
	/**
	 * Updates the feed by adding the message from our following to the feed list
	 * 
	 * @param msg the message to update the feed with
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateFeed(String msg) { ((DefaultListModel)listFeedModel).addElement(msg); }
}
