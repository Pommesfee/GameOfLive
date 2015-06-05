package core;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class GameOfLife {

	/*
	 * Todo List:
	 * 
	 * Major ---> Only update relevant entities...
	 * 
	 * Config File (Color Dead/Alive) Add User Panel (tileCount Chooser) Clean
	 * up code and optimize Add documentation
	 */

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}

				@SuppressWarnings("unused")
				JPanel frame = new Frame(750, 750, "Game Of Live", 8, 8, 50);
			}

		});

	}

}
