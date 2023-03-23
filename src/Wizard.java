import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Wizard extends GameObject {

	private float _acc = 1f;
	private float _dcc = 0.5f;
	private KeyInput input;
	private Handler handler;
	private Game game;
	private BufferedImage wizard_image;
	
	public Wizard(float x, float y, ID id, KeyInput input, Handler handler, Game game, SpriteSheet ss) {
		super(x, y, id, ss);
		this.input = input;
		this.handler = handler;
		this.game = game;
		wizard_image = ss.grabImage(1, 1, 32, 48);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		collision();
		
		// reset se tocca i bordi dello schermo
//		if (x > Game.WIDTH) x = 0;
//		if (y > Game.HEIGHT) y = 0;
//		if (x < 0) x = Game.WIDTH;
//		if (y < 0) y = Game.HEIGHT;
		
		//// horizontal movement
		//keys 0 = true right
		//keys 1 = true left
		if (input.keys[0]) {
			velX += _acc;
		} else if (input.keys[1]) {
			velX -= _acc;
		} else if (!input.keys[0] && !input.keys[1]) {
			if (velX > 0) velX -= _dcc;
			else if (velX < 0) velX += _dcc;
		}
		
		//// vertical movement
		//keys 2 = true up
		//keys 3 = true down
		if (input.keys[2]) {
			velY -= _acc;
		} else if (input.keys[3]) {
			velY += _acc;
		} else if (!input.keys[2] && !input.keys[3]) {
			if (velY > 0) velY -= _dcc;
			else if (velY < 0) velY += _dcc;
		}
		
		velX = clamp(velX, 5, -5);
		velY = clamp(velY, 5, -5);
		
	}
	
	private float clamp(float value, float max, float min) {
		if (value > max) {
			value = max;
		}
		else if (value <= min) {
			value = min;
		}
		return value;
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Block) {
				if (getBounds().intersects(tempObject.getBounds())) {
					x += velX * -1;
					y += velY * -1;
				}
			}
			if (tempObject.getId() == ID.Crate) {
				if (getBounds().intersects(tempObject.getBounds())) {
					game.ammo += 10;
					handler.removeObject(tempObject);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
//		g.setColor(Color.blue);
//		g.fillRect((int)x, (int)y, 32, 32);
		g.drawImage(wizard_image, (int) x, (int) y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 48);
	}


}
