import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends GameObject {
	
	private Handler handler;
	private Random r;
	private int choose = 0;
	private int hp;
	

	public Enemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		r = new Random();
		this.hp = 100;
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
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int) x, (int) y, 32, 32);
		
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.green);
//		g2d.draw(getBoundsBig());
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public Rectangle getBoundsBig() {
		return new Rectangle((int)x - 16, (int)y - 16, 64, 64);
	}

}
