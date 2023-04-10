import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

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
	private GameObject bullet;
	ArrayList<GameObject> bullets;
	private int numberOfBullets;
	private int counter;
	

	public OrbitGun(float x, float y, ID id, Handler handler, SpriteSheet ss, Game game, Camera cam) {
		super(x, y, id, ss);
		this.handler = handler;
		this.game = game;
		this.cam = cam;
		this.ss = ss;
		this.orbitGunLvl = 1;
		this.findPlayer();
		this.angle = 0d;
		this.radius = 150;
		this.speed = 0.015f;
		this.bullets = new ArrayList<>();
		this.numberOfBullets = 0;
		this.addBullet();
		this.addBullet();
		this.addBullet();
		this.addBullet();
		this.addBullet();
		this.addBullet();
		this.addBullet();
		this.addBullet();
		this.addBullet();
		this.addBullet();
		this.addBullet();
		this.addBullet();
		
	}
	
	
	public void findPlayer() {
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) {
				this.tempPlayer = handler.object.get(i);
				break;
			}
		}
	}
	
	public void addBullet() {
		this.bullet = handler.addObject(new Bullet(this.getXPos(angle), this.getYPos(angle), ID.IBullet, handler, ss));
		this.bullets.add(this.bullet);
		this.numberOfBullets++;
	}
	

	@Override
	public void tick() {
		this.angle += this.speed;
		
		double angleDifference = 6.28319 / this.numberOfBullets; // 360 in radianti = 6.28319
		this.counter = 0; // TODO: trovare un modo migliore
		
		this.bullets.forEach( b -> {
			double angle2 = this.angle + (angleDifference * this.counter);
			b.setX(this.getXPos(angle2));
			b.setY(this.getYPos(angle2));
			this.counter++;
		});
		
		
	}
	
	// Math.sin e cos funzionano in radianti
	public float getXPos(double angle) {
		return (float) (16 + this.tempPlayer.getX() + (Math.cos(angle) * this.radius));
	}
	
	public float getYPos(double angle) {
		return (float) (16 + this.tempPlayer.getY() + (Math.sin(angle) * this.radius));
	}

	@Override
	public void render(Graphics g) {
	}

	@Override
	public Rectangle getBounds() {
		
		return null;
	}

}
