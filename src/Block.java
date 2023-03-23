import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block extends GameObject {
	
	private BufferedImage block_image;
	
	public Block(float x, float y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		block_image = ss.grabImage(5, 2, 32, 32);
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
//		g.setColor(Color.black);
//		g.fillRect((int)x, (int)y, 32, 32);
		g.drawImage(block_image, (int) x, (int) y, null);
		
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}
