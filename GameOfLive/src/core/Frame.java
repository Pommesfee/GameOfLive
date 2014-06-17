package core;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JPanel implements Runnable {

	private JFrame frame = null;
	private Thread thread = null;
	
	private EntityManager entityManager = null;
	
	public Frame() {
		
		this.setPreferredSize(new Dimension(640, 480));

		frame = new JFrame();
		frame.setTitle("Game Of Live");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.pack();		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		initObjects();
		
		thread = new Thread(this);
		thread.start();
	}
	
	private void initObjects() {
		entityManager = new EntityManager(640, 480, 15, 15);
	}
	
	private void updateObjects() {
		entityManager.update();
	}
	
	private void drawObjects(Graphics g) {
		entityManager.drawLines(g);
		entityManager.drawEntities(g);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		drawObjects(g);
	}
	
	@Override
	public void run() {
		while (true) {
			
			updateObjects();
			
			repaint();
			
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
