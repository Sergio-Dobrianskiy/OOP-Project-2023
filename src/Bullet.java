import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends GameObject {

	public Bullet(float x, float y, ID id) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int) x, (int) y, 8, 8);
	}

}
