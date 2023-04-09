import java.awt.Graphics;
import java.awt.Rectangle;

public class OrbitGun extends GameObject {
	
	private Handler handler;
	private Game game;
	private Camera cam;
	private SpriteSheet ss;
	
	private int orbitGunLvl;
	private float rotationSpeed;
	private GameObject tempPlayer;
	
	private double angle;
	private float speed;
	private float radius;
	
	private float centerX;
	private float centerY;
	private GameObject tempBullet;
	
	

	public OrbitGun(float x, float y, ID id, Handler handler, SpriteSheet ss, Game game, Camera cam) {
		super(x, y, id, ss);
		this.handler = handler;
		this.game = game;
		this.cam = cam;
		this.ss = ss;
		this.orbitGunLvl = 1;
		this.findPlayer();
		this.angle = 0;
		this.radius = 150;
		this.speed = 0.015f;
		this.centerX = (float) (this.tempPlayer.getX() + (Math.cos(angle) * this.radius));
		this.centerY = (float) (this.tempPlayer.getY() + (Math.sin(angle) * this.radius));
		this.tempBullet = handler.addObject(new Bullet(this.centerX, this.centerY, ID.Bullet, handler, ss));
	}
	
	
	public void findPlayer() {
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) {
				this.tempPlayer = handler.object.get(i);
				break;
			}
		}
	}
	

	@Override
	public void tick() {
//		System.out.println(this.tempBullet);
		if (this.tempBullet == null) {
			this.tempBullet = handler.addObject(new Bullet(this.centerX, this.centerY, ID.Bullet, handler, ss));
		}
		
		
		this.angle += this.speed;
		this.centerX = (float) (this.tempPlayer.getX() + (Math.cos(angle) * this.radius));
		this.centerY = (float) (this.tempPlayer.getY() + (Math.sin(angle) * this.radius));
		this.tempBullet.setX(this.centerX);
		this.tempBullet.setY(this.centerY);	
		
		
	}

	@Override
	public void render(Graphics g) {
	}

	@Override
	public Rectangle getBounds() {
		
		return null;
	}

}
