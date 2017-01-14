import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Table extends JPanel {

	private static final int frameX = 640, frameY = 480;
	private static final String gameName = "Pong v1.0";
	public static boolean isPlaying;
	Ball ball;
	Paddle paddle1, paddle2;

	public Table() {
		reset();
	}
	
	public void reset() {
		ball = new Ball(frameX, frameY);
		paddle1 = new Paddle(frameX, frameY, 0);
		paddle2 = new Paddle(frameX, frameY, 1);
		isPlaying = true;
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				paddle1.keyReleased(e);
				paddle2.keyReleased(e);
			}
			public void keyPressed(KeyEvent e) {
				paddle1.keyPressed(e);
				paddle2.keyPressed(e);
			}
		});
		setFocusable(true);
	}
	
	public void win() {
		Scanner in = new Scanner(System.in);
		System.out.println("Play again?");
		String answer = in.nextLine();
		if (answer.toLowerCase().equals("yes"))
			reset();
		else
			isPlaying = false;
	}
	
	public void checkWin() {
		if (paddle1.getPoints() >= 5) {
			System.out.println(paddle1.getName() + " wins!");
			win();
		} else if (paddle2.getPoints() >= 5) {
			System.out.println(paddle2.getName() + " wins!");
			win();
		}
	}
	
	public void checkPoints() {
		if (ball.getY()<=0) {
			paddle2.addPoint();
//			System.out.println("player 2 points: " + paddle2.getPoints());
		} else if (ball.getY() + ball.getvelY()>=frameY-(ball.getRadius()+25)) { // TODO fix this shit
			paddle1.addPoint();
//			System.out.println("player 1 points: " + paddle1.getPoints());
		}
	}
	
	public void checkIntersection() {
		if (ball.getBounds().intersects(paddle1.getBounds()))
			ball.reverseDirection();
		else if (ball.getBounds().intersects(paddle2.getBounds()))
			ball.reverseDirection();
	}

	public void update() {
		paddle1.update();
		paddle2.update();
		ball.update();
		checkIntersection();
		checkPoints();
		checkWin();
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		super.paint(g);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		
		paddle1.draw(g2d);
		paddle2.draw(g2d);
		ball.draw(g2d);
	}
	
	public static void main(String args[]) throws InterruptedException {
		JFrame frame = new JFrame(gameName);
		Table table = new Table();
		frame.add(table);
		frame.setSize(frameX, frameY);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(table.isPlaying) {
			table.update();
			table.repaint();
			Thread.sleep(15);
		}
		System.exit(0);
	}
}
