import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {

	public Player(float x, float y, ID id) {
		super(x, y, id);
		velX = 4;
		velY = 4;
		
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if (x > Game.WIDTH) x = 0;
		if (y > Game.HEIGHT) y = 0;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect((int)x, (int)y, 32, 32);
	}


}
