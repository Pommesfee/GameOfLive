package core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JPanel implements Runnable, KeyListener, MouseListener{

	private JFrame frame = null;
	private Thread thread = null;

	private EntityManager entityManager = null;
	
	private boolean paused = false;

	public Frame() {

		this.setPreferredSize(new Dimension(640, 480));

		frame = new JFrame();
		frame.setTitle("Game Of Live");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.pack();
		frame.addKeyListener(this);
		frame.addMouseListener(this);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		initObjects();
		
		startGame();
	}

	private void initObjects() {
		entityManager = new EntityManager(640, 480, 16, 16);
	}

	private void updateObjects() {
		entityManager.update();
	}

	private void drawObjects(Graphics g) {
		entityManager.drawEntitys(g);
	}

	@Override
	protected void paintComponent(Graphics g) {
		drawObjects(g);
	}

	private void startGame() {
		thread = new Thread(this, "GameOfLife");
		thread.start();
	}
	
	@Override
	public void run() {
		
		setPaused(true);
		
		while (true) {
			try {
				Thread.sleep(375);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			repaint();
			
			entityManager.updateMarkedEntities();
			
			if (!isPaused()) {
				updateObjects();
			}
		}
	}

	private boolean isPaused() {
		return paused;
	}

	private void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			setPaused(!isPaused());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			entityManager.selectEntity(e.getPoint());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

}
