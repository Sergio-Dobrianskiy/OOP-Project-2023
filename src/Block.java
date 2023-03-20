import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends GameObject {

	public Block(float x, float y, ID id) {
		super(x, y, id);
		
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect((int)x, (int)y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {

		return null;
	}

}
