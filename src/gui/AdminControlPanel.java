package gui;

import java.awt.Dialog.ModalityType;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import admin.AdminController;
import user.TreeEntry;
import user.User;
import user.UserGroup;
import visitor.ShowGroupTotalVisitor;
import visitor.ShowMessageTotalVisitor;
import visitor.ShowPosMessageTotalVisitor;
import visitor.ShowUserTotalVisitor;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class AdminControlPanel {

	private JFrame mainAppWindow;
	
	private JTextField txtUserInput;
	private JTextField txtGroupInput;
	
	private JTree tree;
	
	private JButton btnAddUser;
	private JButton btnAddGroup;
	private JButton btnOpenUserView;
	
	private DefaultMutableTreeNode selectedNode;
	private final DefaultMutableTreeNode emptyNode = new DefaultMutableTreeNode(new User("(empty)"));

	/**
	 * Create the application.
	 */
	public AdminControlPanel() {
		initialize();
		mainAppWindow.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		mainAppWindow = new JFrame();
		mainAppWindow.setResizable(false);
		mainAppWindow.setTitle("MiniTwitter Admin");
		mainAppWindow.setBounds(100, 100, 450, 300);
		mainAppWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainAppWindow.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 156, 242);
		mainAppWindow.getContentPane().add(scrollPane);
		
		
		tree = new JTree();
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				//Enable buttons when any element in the tree is chosen
				btnAddUser.setEnabled(true);
				btnAddGroup.setEnabled(true);
				
				//Get the tree node that is selected
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        tree.getLastSelectedPathComponent();
				//Set our selected node
				selectedNode = node;
				try {
					//Get the node's name
					String nodeName = node.getUserObject().toString() == null ? "" : node.getUserObject().toString();
					//If user
					if(node.isLeaf()){
						//If the node isn't the (empty) placeholder...
						if(!nodeName.equals("(empty)")) {
							btnOpenUserView.setEnabled(true);
						}else {
							btnOpenUserView.setEnabled(false);
						}
						//Get the node's parent
						DefaultMutableTreeNode parent = (DefaultMutableTreeNode)node.getParent();
						//Get the parent's name
						String nodeParentName = (String)parent.getUserObject().toString();
						//Set the user ID field to the nodes name
						txtUserInput.setText(nodeName);
						//Sets the group's ID field to the parent's name
						txtGroupInput.setText(nodeParentName);
					}
					//If UserGroup
					else {
						//Disable open user view button
						btnOpenUserView.setEnabled(false);
						//Set the user text field to empty;
						txtUserInput.setText(null);
						//Set the text of the group field to the name of the node
						txtGroupInput.setText(nodeName);
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		tree.setExpandsSelectedPaths(true);
		tree.setShowsRootHandles(true);
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(AdminController.getRootEntry());
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode(root) {
				{
					add(new DefaultMutableTreeNode(emptyNode));
				}
			}
		));
		
		scrollPane.setViewportView(tree);
		
		txtUserInput = new JTextField();
		txtUserInput.setEditable(false);
		txtUserInput.setBounds(214, 11, 101, 29);
		mainAppWindow.getContentPane().add(txtUserInput);
		txtUserInput.setColumns(10);
		
		btnAddUser = new JButton("Add User");
		//Initially set to disabled until a tree element is selected
		btnAddUser.setEnabled(false);
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//New User Dialogg
					NewUserDialog userDialog = new NewUserDialog();
					userDialog.setSelectedGroup(txtGroupInput.getText());
					
					userDialog.setModalityType(ModalityType.APPLICATION_MODAL);
					userDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					userDialog.setVisible(true);
					
					//Get the input ID
					String inputID = userDialog.getID();
					//Checks if cancel button was pressed or no text was entered. Also checks if user doesn't already exist
					if(inputID != null && AdminController.getUserByID(inputID) == null) {
						//Get the model for the tree
						DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
						//Figure out which node we need to insert a new user into
						DefaultMutableTreeNode insertionNode = selectedNode.isLeaf() ? (DefaultMutableTreeNode)selectedNode.getParent() : selectedNode;
						//Remove node "(empty)" if it exists
						if(insertionNode.getChildCount() == 1) {
							DefaultMutableTreeNode child = (DefaultMutableTreeNode) insertionNode.getChildAt(0);
							if(child.getUserObject().toString().equals("(empty)")) {
								model.removeNodeFromParent(child);
							}
						}
						//Insert node
						model.insertNodeInto(new DefaultMutableTreeNode(inputID), insertionNode, insertionNode.getChildCount());
						//Get the parent UserGroup
						UserGroup parentGroup = AdminController.getGroupByID(insertionNode.getUserObject().toString());
						//Make a new user based on the input id and parent group
						User newUser = new User(inputID, parentGroup);
						//Add the user to the parent
						parentGroup.addUser(newUser);
						//DEBUGGING
						AdminController.printAllEntries();
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnAddUser.setBounds(325, 11, 99, 29);
		mainAppWindow.getContentPane().add(btnAddUser);
		
		txtGroupInput = new JTextField();
		txtGroupInput.setEditable(false);
		txtGroupInput.setColumns(10);
		txtGroupInput.setBounds(214, 51, 101, 29);
		mainAppWindow.getContentPane().add(txtGroupInput);
		
		btnAddGroup = new JButton("Add Group");
		//Initially set to disabled until a tree element is selected
		btnAddGroup.setEnabled(false);
		btnAddGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//New group dialog
					NewGroupDialog groupDialog = new NewGroupDialog();
					groupDialog.setSelectedGroup(txtGroupInput.getText());
					
					groupDialog.setModalityType(ModalityType.APPLICATION_MODAL);
					groupDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					groupDialog.setVisible(true);
					
					//Get group input ID
					String inputID = groupDialog.getID();
					
					//Checks if cancel button was pressed or no text was entered and if the group doesn't already exist
					if(inputID != null && AdminController.getGroupByID(inputID) == null) {
						//Get tree model
						DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
						//Set target node to insert into
						DefaultMutableTreeNode insertionNode = selectedNode.isLeaf() ? (DefaultMutableTreeNode)selectedNode.getParent() : selectedNode;
						//Remove node "(empty)" if it exists
						if(insertionNode.getChildCount() == 1) {
							DefaultMutableTreeNode child = (DefaultMutableTreeNode) insertionNode.getChildAt(0);
							if(child.getUserObject().toString().equals("(empty)")) {
								model.removeNodeFromParent(child);
							}
						}
						//Insert Node
						model.insertNodeInto(new DefaultMutableTreeNode(inputID), insertionNode, insertionNode.getChildCount());
						//Switch insertion node to the most recent node we just added
						DefaultMutableTreeNode insertionNodeNext = insertionNode.getLastLeaf();
						//Insert empty node as a placeholder so it appears as a folder in the tree view
						DefaultMutableTreeNode emptyCopy = new DefaultMutableTreeNode(new User("(empty)"));
						model.insertNodeInto(emptyCopy, insertionNodeNext, insertionNodeNext.getChildCount());
						
						//Get the parent group
						UserGroup parentGroup = AdminController.getGroupByID(insertionNode.getUserObject().toString());
						//Create a new group based on input ID and parent group
						UserGroup newGroup = new UserGroup(inputID, parentGroup);
						//Add the new group into the parent group
						parentGroup.addUserGroup(newGroup);
					}
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				// DEBUGGING
				AdminController.printAllEntries();
			}
		});
		btnAddGroup.setBounds(325, 51, 99, 29);
		mainAppWindow.getContentPane().add(btnAddGroup);
		
		btnOpenUserView = new JButton("Open User View");
		btnOpenUserView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Get ID of node that is selected.
				//Instantiate a new UserView class and set its user
				String userID = selectedNode.getUserObject().toString();
				User userToBringUp = AdminController.getUserByID(userID);
				
				//New UserView object
				UserView newView = new UserView();
				
				newView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				newView.setUser(userToBringUp);
				newView.setVisible(true);
			}
		});
		//Initially set to disabled
		btnOpenUserView.setEnabled(false);
		btnOpenUserView.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOpenUserView.setBounds(176, 91, 248, 29);
		mainAppWindow.getContentPane().add(btnOpenUserView);
		
		JButton btnShowTotalMessages = new JButton("Show Total Messages");
		btnShowTotalMessages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Have the root entry visit accept the show user total visitor method
				AdminController.getRootEntry().accept(new ShowMessageTotalVisitor());
				//Value is in AdminController.visitorOutput
				int amtMessageTotal = AdminController.getVisitorOutput();
				//Format string to contain amount of users.
				String messageAmt = String.format("There are %d total messages.", amtMessageTotal);
				InfoDialog infoDialog = new InfoDialog(messageAmt);
				infoDialog.setTitle("Total Messages");
				
				infoDialog.setVisible(true);
			}
		});
		btnShowTotalMessages.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShowTotalMessages.setBounds(175, 195, 248, 29);
		mainAppWindow.getContentPane().add(btnShowTotalMessages);
		
		JButton btnShowPositive = new JButton("Show Positive %");
		btnShowPositive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Have the root entry visit accept the show user total visitor method
				AdminController.getRootEntry().accept(new ShowMessageTotalVisitor());
				//Value is in AdminController.visitorOutput
				int amtMessageTotal = AdminController.getVisitorOutput();
				//Have the root entry visitor accept the show user pos message total visitor method
				AdminController.getRootEntry().accept(new ShowPosMessageTotalVisitor());
				//Value is in AdminController.visitorOutput
				int amtPosMessages = AdminController.getVisitorOutput();
				//Calculate percentage of positive messages
				double posMessagePercent = 0.0;
				if(amtMessageTotal != 0) {
					posMessagePercent = ((amtPosMessages + 0.0)/(amtMessageTotal + 0.0)) * 100;
				}else {
					posMessagePercent = 0;
				}
				//Format a message to show this
				String formattedText = String.format("Out of all messages, %.2f percent are positive messages.", posMessagePercent);
				InfoDialog infoDialog = new InfoDialog(formattedText);
				infoDialog.setTitle("Positive Message Percent");
				
				infoDialog.setVisible(true);
			}
		});
		btnShowPositive.setToolTipText("Shows the percentage of total messages that are considered \"positive.\"");
		btnShowPositive.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShowPositive.setBounds(175, 224, 248, 29);
		mainAppWindow.getContentPane().add(btnShowPositive);
		
		JButton btnShowUserTotal = new JButton("Show User Total");
		btnShowUserTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Have the root entry visit accept the show user total visitor method
				AdminController.getRootEntry().accept(new ShowUserTotalVisitor());
				//Value is in AdminController.visitorOutput
				int amtUserTotal = AdminController.getVisitorOutput();
				//Format string to contain amount of users.
				String userAmt = String.format("There are %d total users.", amtUserTotal);
				InfoDialog infoDialog = new InfoDialog(userAmt);
				infoDialog.setTitle("Total Users");
				
				infoDialog.setVisible(true);
			}
		});
		btnShowUserTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShowUserTotal.setBounds(175, 137, 248, 29);
		mainAppWindow.getContentPane().add(btnShowUserTotal);
		
		JButton btnShowGroupTotal = new JButton("Show Group Total");
		btnShowGroupTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Have the root entry visitor accept the show group total visitor method
				AdminController.getRootEntry().accept(new ShowGroupTotalVisitor());
				//Value in AdminController.visitorOutput
				int amtGroupTotal = AdminController.getVisitorOutput();
				//Format string to contain amount of groups
				String groupAmt = String.format("There are %d total groups", amtGroupTotal);
				InfoDialog infoDialog = new InfoDialog(groupAmt);
				infoDialog.setTitle("Total Groups");
				
				infoDialog.setVisible(true);
			}
		});
		btnShowGroupTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShowGroupTotal.setBounds(175, 166, 248, 29);
		mainAppWindow.getContentPane().add(btnShowGroupTotal);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(176, 131, 248, 2);
		mainAppWindow.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("User:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(176, 10, 39, 27);
		mainAppWindow.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Group:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(168, 51, 47, 27);
		mainAppWindow.getContentPane().add(lblNewLabel_1);
		
	}
	
}
