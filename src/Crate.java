import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Crate extends GameObject {

	public Crate(float x, float y, ID id) {
		super(x, y, id);
		
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int) x, (int) y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
