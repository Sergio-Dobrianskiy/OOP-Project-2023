import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject {
	
	private Handler handler;
	private Random r;
	private int choose = 0;
	private int hp;
	private BufferedImage[] enemy_image;
	Animation anim;
	

	public Enemy(float x, float y, ID id, Handler handler, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		r = new Random();
		this.hp = 100;
//		enemy_image = ss.grabImage(4, 1, 32, 32);
		this.enemy_image = new BufferedImage[3];
		this.enemy_image[0] = ss.grabImage(4, 1, 32, 32);
		this.enemy_image[1] = ss.grabImage(5, 1, 32, 32);
		this.enemy_image[2] = ss.grabImage(6, 1, 32, 32);
		anim = new Animation(3, enemy_image[0], enemy_image[1], enemy_image[2]);

	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		choose = r.nextInt(10);
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.Block) {
				
				if (getBounds().intersects(tempObject.getBounds())) {
					x += (velX * 2) * -1;
					y += (velY * 2) * -1;
					velX *= -1;
					velY *= -1;
				}
			}
			
			if (tempObject.getId() == ID.Bullet) {
				if (getBounds().intersects(tempObject.getBounds())) {
					hp -= 50;
					handler.removeObject(tempObject);
					System.out.println("bullet -> enemy");
				}
			}
			
		}
		
		if (choose == 0) {
			velX = (r.nextInt(4 - -4) + -4);
			velY = (r.nextInt(4 - -4) + -4);
		}
		
		if (hp <= 0) {
			handler.removeObject(this);
		}
		
		anim.runAnimation();
		
	}

	@Override
	public void render(Graphics g) {
//		g.setColor(Color.yellow);
//		g.fillRect((int) x, (int) y, 32, 32);
		
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.green);
//		g2d.draw(getBoundsBig());
//		g.drawImage(enemy_image, (int) x, (int) y, null);
		anim.drawAnimation(g, x, y, 0);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public Rectangle getBoundsBig() {
		return new Rectangle((int)x - 16, (int)y - 16, 64, 64);
	}

}
