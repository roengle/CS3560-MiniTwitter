package driver;

import java.awt.EventQueue;

import gui.AdminControlPanel;

public class Driver {

	/* Launch the Application */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminControlPanel window = new AdminControlPanel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
