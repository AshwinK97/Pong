import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Paddle {
	
	private int frameX, frameY, id;
	private int x, y, width, height, vel;
	private String name;
	private int points;
	
	public Paddle(int frameX, int frameY, int id) {
		this.frameX = frameX;
		this.frameY = frameY;
		this.id = id;
		width = 75;
		height = 10;
		vel = 0;
		x = frameX/2 - width/2;
		if (id==0)
			y = 15;
		else
			y = frameY - 55;
		name = createPlayer();
		points = 0;
	}
	
	private String createPlayer() {
		System.out.println("Enter player " + (id+1) + " name: ");
		Scanner in = new Scanner(System.in);
		return in.nextLine();
	}
	
	public void checkBoundry() {
		if (x <= 3) {
			x = 1;
		} else if (x + width >= frameX-10) {
			x = frameX-width-15;
		}
	}
	
	public void update() {
		checkBoundry();
		x+=vel;
	}
	
	public void draw(Graphics2D g2d) {
		/* print the paddle and scores */
		if (id==0) {
			g2d.setColor(Color.BLUE);
			g2d.drawString(name + ": " + points, 5, 25);
		} else {
			g2d.setColor(Color.RED);
			g2d.drawString(name + ": " + points, frameX - 75, frameY - 45);
		}
		g2d.fillRect(x, y, width, height);
	}
	
	public void keyReleased(KeyEvent e) {
		if (id==0) {
			if (e.getKeyCode() == KeyEvent.VK_A)
				vel = 0;
			if (e.getKeyCode() == KeyEvent.VK_D)
				vel = 0;
		} else {
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
				vel = 0;
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				vel = 0;
		}
	}

	public void keyPressed(KeyEvent e) {
		if (id==0) {
			if (e.getKeyCode() == KeyEvent.VK_A)
				vel = -4;
			if (e.getKeyCode() == KeyEvent.VK_D)
				vel = 4;
		} else {
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
				vel = -4;
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				vel = 4;
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,width, height);
	}
	
	public int getPoints() {
		return points;
	}
	
	public void addPoint() {
		points++;
	}

	public String getName() {
		return name;
	}
}
