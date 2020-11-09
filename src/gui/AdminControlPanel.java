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
	private DefaultMutableTreeNode selectedNode;
	private AdminController adminController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminControlPanel window = new AdminControlPanel();
					window.mainAppWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminControlPanel() {
		initialize();
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
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        tree.getLastSelectedPathComponent();
				selectedNode = node;
				//If user
				if(node.isLeaf()) {
					DefaultMutableTreeNode parent = (DefaultMutableTreeNode)node.getParent();
					String nodeParentName = (String)parent.getUserObject();
					String nodeName = (String)node.getUserObject();
					txtUserInput.setText(nodeName);
					txtGroupInput.setText(nodeParentName);
				}else {
					txtUserInput.setText(null);
					String nodeName = (String)node.getUserObject();
					txtGroupInput.setText(nodeName);
				}
				
				
			}
		});
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
		scrollPane.setViewportView(tree);
		
		txtUserInput = new JTextField();
		txtUserInput.setEditable(true);
		txtUserInput.setBounds(214, 11, 101, 29);
		mainAppWindow.getContentPane().add(txtUserInput);
		txtUserInput.setColumns(10);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					NewUserDialog userDialog = new NewUserDialog();
					userDialog.setSelectedGroup(txtGroupInput.getText());
					
					userDialog.setModalityType(ModalityType.APPLICATION_MODAL);
					userDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					userDialog.setVisible(true);
					
					String inputID = userDialog.getID();
					System.out.println(inputID);
					
					DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
					DefaultMutableTreeNode insertionNode = selectedNode.isLeaf() ? (DefaultMutableTreeNode)selectedNode.getParent() : selectedNode;
					model.insertNodeInto(new DefaultMutableTreeNode(inputID), insertionNode, insertionNode.getChildCount());
					
				}catch (Exception ex) {
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
		
		JButton btnAddGroup = new JButton("Add Group");
		btnAddGroup.setBounds(325, 51, 99, 29);
		mainAppWindow.getContentPane().add(btnAddGroup);
		
		JButton btnOpenUserView = new JButton("Open User View");
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
