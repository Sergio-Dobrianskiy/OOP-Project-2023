import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Crate extends GameObject {
	
	private BufferedImage crate_image;

	public Crate(float x, float y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		this.crate_image = ss.grabImage(6, 2, 32, 32);
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
//		g.setColor(Color.cyan);
//		g.fillRect((int) x, (int) y, 32, 32);
		g.drawImage(crate_image, (int) x, (int) y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
