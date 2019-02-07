package Main;

import java.awt.EventQueue;

public class Main {

	private static Control control;
	
	public static void main(String[] args) {
		
		control= new Control(args);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui(control);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
