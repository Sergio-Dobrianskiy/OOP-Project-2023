import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class AutoGun extends GameObject {
	
	private Handler handler;
	private int autoGunLvl = 0;
	private GameObject tempPlayer;
	private GameObject closestEnemy;
	private Game game;
	private Camera cam;
	private SpriteSheet ss;
	
	private long lastTime;
	private float closestEnemyDistance;
	
	
	public AutoGun(float x, float y, ID id, Handler handler, SpriteSheet ss, Game game, Camera cam) {
		super(x, y, id, ss);
		this.handler = handler;
		this.game = game;
		this.cam = cam;
		this.ss = ss;
		this.lastTime = System.nanoTime();
	}

	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (tempPlayer != null && game.ammo > 0) {
			GameObject tempBullet = handler.addObject(new Bullet(tempPlayer.x + 16, tempPlayer.y + 16, ID.Bullet, handler, ss));			
			
			float angle = (float) Math.atan2(my - tempPlayer.y - 16 + cam.getY(), mx - tempPlayer.x - 16 + cam.getX());
			int bulletVelocity = 10;
			tempBullet.velX = (float) ((bulletVelocity) * Math.cos(angle));
			tempBullet.velY = (float) ((bulletVelocity) * Math.sin(angle));
			
			game.ammo--;
			
		} else {
			findPlayer();
		}
		
	}
	
	public void findClosestEnemy( ) {
		
		float closestDistance = Float.MAX_VALUE;
		GameObject closestEnemy = null;
		float minDistance = 0;
		float distance;
		
		float px, py;
		px = this.tempPlayer.getX();
		py = this.tempPlayer.getY();
		
		
		for (int i = 0; i < handler.object.size(); i++) {
			
			if (handler.object.get(i).getId() == ID.Enemy) {
				float ex, ey;
				
				ex = handler.object.get(i).getX();
				ey = handler.object.get(i).getY();
				
				
				distance = (float) Point2D.distance(px, py, ex, ey);
				if (distance < closestDistance) {
					closestDistance = distance;
					this.closestEnemy = handler.object.get(i);
				}
			}
		}
		this.closestEnemyDistance = closestDistance;
		
	}
	
	public void findPlayer() {
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) {
				tempPlayer = handler.object.get(i);
				break;
			}
		}
	}
	

	@Override
	public void tick() {

		double amountOfTicks = 60.0;
		double second = 1000000000;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
//		long timer = System.currentTimeMillis();
		if (autoGunLvl >= 0) { // TODO: sistemare
			long now = System.nanoTime();
			delta = (now - this.lastTime) / second;
			if (delta >= 1) {
				this.lastTime = now;
				this.findPlayer();
				this.findClosestEnemy();
				System.out.println("distance" + this.closestEnemyDistance);
				
				
				if (this.closestEnemyDistance <= 400) {
					
					int mx = (int) this.closestEnemy.getX();
					int my = (int) this.closestEnemy.getY();
					
					GameObject tempBullet = handler.addObject(new Bullet(tempPlayer.x + 16, tempPlayer.y + 16, ID.Bullet, handler, ss));			
					
//					float angle = (float) Math.atan2(my - tempPlayer.y - 16 + cam.getY(), mx - tempPlayer.x - 16 + cam.getX());
					float angle = (float) Math.atan2(my - tempPlayer.y - 16, mx - tempPlayer.x - 16);
					int bulletVelocity = 10;
					tempBullet.velX = (float) ((bulletVelocity) * Math.cos(angle));
					tempBullet.velY = (float) ((bulletVelocity) * Math.sin(angle));
					
				}
				
			}
		}
	}

	@Override
	public void render(Graphics g) {
	}

	@Override
	public Rectangle getBounds() {
		
		return null;
	}
	
	
}
