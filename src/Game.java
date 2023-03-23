import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;

public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static int WIDTH = 1000, HEIGHT = 563;
	public static int PLAYER_WIDTH = 100, PLAYER_HEIGHT = 100;
	public String title = "Wizard Game";

	private Thread thread;
	private boolean isRunning = false;

	// Instances
	private Handler handler;
	private KeyInput input;
	private MouseInput mInput;
	private Camera cam;
	private SpriteSheet ss;
	
	public int ammo;
	public int hp;
	
	private BufferedImage level = null;
	private BufferedImage sprite_sheet = null;
	private BufferedImage floor = null;
	

	public Game() {
		// Construct
		new Window(WIDTH, HEIGHT, title, this);

		// prima carico il gioco
		init();
		// poi faccio partire i thread
		start();
		
		this.ammo = 100;
		this.hp = 100;
		

	}

	private void init() {
		handler = new Handler();
		input = new KeyInput();
		cam = new Camera(0, 0, handler);
		this.addKeyListener(input);
		mInput = new MouseInput(handler, cam, this, ss);
		this.addMouseListener(mInput);

		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("wizard_level.png");
		sprite_sheet = loader.loadImage("sprite_sheet2.png");
		
		ss = new SpriteSheet(sprite_sheet);
		
		floor = ss.grabImage(4, 2, 32, 32);
		
		
		loadLevel(level);
		
		

		mInput.findPlayer();
	}

	private synchronized void start() {
		if (isRunning)
			return;

		thread = new Thread(this);
		thread.start();
		isRunning = true;

	}

	private synchronized void stop() {
		if (!isRunning)
			return;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isRunning = false;
	}

	@Override
	public void run() {
		// minecraft game loop
		this.requestFocus(); // cosa fa?
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
	}

	private void tick() {
		// Updates the game, updates 1000-2000 times per second
		handler.tick();
		cam.tick();
	}

	private void render() {
		// Renders the game, updates 60 times per second
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		//////////// after here we draw to the game

		Graphics2D g2d = (Graphics2D) g;

		// Meat and Bones of our rendering
//		g.setColor(Color.gray);
//		g.fillRect(0, 0, WIDTH, HEIGHT);

		g2d.translate(-cam.getX(), -cam.getY());
		
		// sprite pavimento
		for (int xx = 0; xx < 30 * 72; xx += 32) {
			for (int yy = 0; yy < 30 * 72; yy += 32) {
				g.drawImage(floor, xx, yy, null);
			}
		}
		
		
		handler.render(g);
		
		
		g2d.translate(cam.getX(), cam.getY());
		g.setColor(Color.black);
		g.fillRect(2,  1,  206,  38);
		g.setColor(Color.gray);
		g.fillRect(5,  5,  200,  32);
		g.setColor(Color.green);
		g.fillRect(5,  5,  this.hp * 2,  32);
		
		g.setColor(Color.white);
		g.drawString("Ammo: " + ammo, 5, 50);
		//////////// above here we draw to the game
		
		bs.show();
		g.dispose();
	}

	// loading the level
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
//		System.out.println("w"+w+"h"+h);

		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red > 250 && blue == 0 && green == 0) {
					handler.addObject(new Block((int) xx * 32, (int) yy * 32, ID.Block, ss));
					System.out.print("o");
				}
				if (red == 0 && blue >= 250 && green == 0) {
					handler.addObject(new Wizard((int) xx * 32, (int) yy * 32, ID.Player, input, handler, this, ss));
					System.out.print("B");
				}
				if (red == 0 && blue == 0 && green >= 255) {
					handler.addObject(new Enemy((int) xx * 32, (int) yy * 32, ID.Enemy, handler, ss));
					System.out.print("e");
				}
				if (red == 0 && blue == 0 && green == 0) {
					System.out.print(" ");
				}
				
				if (red == 0 && blue > 250 && green >= 250) {
					handler.addObject(new Crate((int) xx * 32, (int) yy * 32, ID.Crate, ss));
					System.out.print("C");
				}
			}
			System.out.println("");
		}
	}

	public static void main(String args[]) {
		new Game();
	}

}
