package core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JPanel implements Runnable, KeyListener,
		MouseListener, ComponentListener {

	private static final long serialVersionUID = 547885688509882231L;

	private JFrame frame = null;
	private Thread thread = null;

	private EntityManager entityManager = null;

	private boolean paused = false;
	
	private long lastTime;
	private long thisTime;
	private double timeSinceLastFrame;

	public Frame() {

		this.setPreferredSize(new Dimension(900, 900));

		frame = new JFrame();
		frame.setTitle("Game Of Live");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(this);
		this.setBackground(Color.GREEN);
		frame.pack();
		frame.addKeyListener(this);
		frame.addMouseListener(this);
		frame.addComponentListener(this);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		initObjects();

		startGame();
	}

	private void initObjects() {
		entityManager = new EntityManager(900, 900, 50, 50);
	}

	private void updateObjects() {
		entityManager.update();
	}

	private void drawObjects(Graphics g) {
		entityManager.drawEntitys(g);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawObjects(g);
	}


	private void startGame() {
		thread = new Thread(this, "GameOfLife");
		thread.start();
	}

	@Override
	public void run() {
		
		setPaused(true);
		lastTime = System.currentTimeMillis();

		while (true) {
			
			thisTime = System.currentTimeMillis();
			timeSinceLastFrame = (thisTime - lastTime);

			repaint();
			
			if (timeSinceLastFrame >= 50) {
				if (!isPaused()) {
					updateObjects();
				}
				lastTime = thisTime;
			} else {
				entityManager.updateMarkedEntities();
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
		if (e.getKeyCode() == KeyEvent.VK_DELETE) {
			entityManager.reset();
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

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		entityManager.resize(getWidth(), getHeight());
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
