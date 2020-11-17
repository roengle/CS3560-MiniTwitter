package driver;

import java.awt.EventQueue;

import admin.AdminController;
import gui.AdminControlPanel;

public class Driver {

	/* Launch the Application */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Running getInstance here guarantees that AdminController has a running instance
					AdminController.getInstance();
					//Enter program through the admin control panel gui
					new AdminControlPanel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
