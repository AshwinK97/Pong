import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	
	private int frameX, frameY;
	private int x, y, radius, velX, velY;
	
	public Ball(int frameX, int frameY) {
		this.frameX = frameX;
		this.frameY = frameY;
		
		radius = 20;
		velX = 3; velY = 3;
		x = frameX/2 - radius/2;
		y = frameY/2 - radius/2;
	}
	
	public void update() {
		move();
	}
	
	public void reverseDirection() {
		velY = -velY;
	}
	
	public void move() {
		if (x+velX >= frameX-(radius))
			velX=-velX;
		else if (x <= 0)
			velX=-velX;
		if (y+velY > frameY-(radius+25))
			velY=-velY;
		else if (y <= 0)
			velY=-velY;
		x+=velX;
		y+=velY;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.orange);
		g2d.fillOval(x, y, radius, radius);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,radius, radius);
	}

	public int getvelY() {
		return velY;
	}

	public void setvelY(int velY) {
		this.velX = velY;
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public int getRadius() {
		return radius;
	}
}
