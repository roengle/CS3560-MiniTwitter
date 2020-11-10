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
	private final DefaultMutableTreeNode emptyNode = new DefaultMutableTreeNode("(empty)");
	
	private static AdminController adminController = AdminController.getInstance();

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
				
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        tree.getLastSelectedPathComponent();
				selectedNode = node;
				try {
					String nodeName = (String)node.getUserObject();
					//If user
					if(node.isLeaf()) {
						btnOpenUserView.setEnabled(true);
						
						DefaultMutableTreeNode parent = (DefaultMutableTreeNode)node.getParent();
						String nodeParentName = (String)parent.getUserObject();
						txtUserInput.setText(nodeName);
						txtGroupInput.setText(nodeParentName);
					}else {
						btnOpenUserView.setEnabled(false);
						
						txtUserInput.setText(null);
						txtGroupInput.setText(nodeName);
					}
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		tree.setExpandsSelectedPaths(true);
		tree.setShowsRootHandles(true);
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Root") {
				{
					DefaultMutableTreeNode node_1;
					DefaultMutableTreeNode node_2;
					add(new DefaultMutableTreeNode("President"));
					node_1 = new DefaultMutableTreeNode("CS");
						node_2 = new DefaultMutableTreeNode("CS2640");
							node_2.add(new DefaultMutableTreeNode("David"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("CS3560");
							node_2.add(new DefaultMutableTreeNode("Ashley"));
							node_2.add(new DefaultMutableTreeNode("Bob"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("NewGroup");
							node_2.add(new DefaultMutableTreeNode("(empty)"));
						node_1.add(node_2);
					add(node_1);
				}
			}
		));
		
		DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode)tree.getModel().getRoot();
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
					NewUserDialog userDialog = new NewUserDialog();
					userDialog.setSelectedGroup(txtGroupInput.getText());
					
					userDialog.setModalityType(ModalityType.APPLICATION_MODAL);
					userDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					userDialog.setVisible(true);
					
					String inputID = userDialog.getID();
					//Checks if cancel button was pressed or no text was entered
					if(inputID != null) {
						DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
						DefaultMutableTreeNode insertionNode = selectedNode.isLeaf() ? (DefaultMutableTreeNode)selectedNode.getParent() : selectedNode;
						//Insert element
						model.insertNodeInto(new DefaultMutableTreeNode(inputID), insertionNode, insertionNode.getChildCount());
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
					NewGroupDialog groupDialog = new NewGroupDialog();
					groupDialog.setSelectedGroup(txtGroupInput.getText());
					
					groupDialog.setModalityType(ModalityType.APPLICATION_MODAL);
					groupDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					groupDialog.setVisible(true);
					
					String inputID = groupDialog.getID();
					
					//Checks if cancel button was pressed or no text was entered
					if(inputID != null) {
						//Get tree model
						DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
						//Set target node to insert into
						DefaultMutableTreeNode insertionNode = selectedNode.isLeaf() ? (DefaultMutableTreeNode)selectedNode.getParent() : selectedNode;
						//Insert Node
						model.insertNodeInto(new DefaultMutableTreeNode(inputID), insertionNode, insertionNode.getChildCount());
						
						//Switch insertion node to the most recent node we just added
						insertionNode = insertionNode.getLastLeaf();
						//Insert empty node as a placeholder
						model.insertNodeInto(emptyNode, insertionNode, insertionNode.getChildCount());
					}
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnAddGroup.setBounds(325, 51, 99, 29);
		mainAppWindow.getContentPane().add(btnAddGroup);
		
		btnOpenUserView = new JButton("Open User View");
		//Initially set to disabled
		btnOpenUserView.setEnabled(false);
		btnOpenUserView.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOpenUserView.setBounds(176, 91, 248, 29);
		mainAppWindow.getContentPane().add(btnOpenUserView);
		
		JButton btnShowTotalMessages = new JButton("Show Total Messages");
		btnShowTotalMessages.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShowTotalMessages.setBounds(175, 195, 248, 29);
		mainAppWindow.getContentPane().add(btnShowTotalMessages);
		
		JButton btnShowPositive = new JButton("Show Positive %");
		btnShowPositive.setToolTipText("Shows the percentage of total messages that are considered \"positive.\"");
		btnShowPositive.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShowPositive.setBounds(175, 224, 248, 29);
		mainAppWindow.getContentPane().add(btnShowPositive);
		
		JButton btnShowUserTotal = new JButton("Show User Total");
		btnShowUserTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShowUserTotal.setBounds(175, 137, 248, 29);
		mainAppWindow.getContentPane().add(btnShowUserTotal);
		
		JButton btnShowGroupTotal = new JButton("Show Group Total");
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
		
		//Initialize starting node
		selectedNode = (DefaultMutableTreeNode)tree.getModel().getRoot();
	}
}
