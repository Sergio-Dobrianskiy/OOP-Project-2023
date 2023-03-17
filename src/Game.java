import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 800, HEIGHT = 608;
	public String title = "Zombie Game";
	
	private Thread thread;
	private boolean isRunning = false;
	
	// Instances
	private Handler handler;
	
	
	public Game() {
		//Construct
		new Window(WIDTH, HEIGHT, title, this);
		start();
		
		init();
		// Below here
		handler.addObject(new Player(100, 100, ID.Player));
		
		
	}
	
	private void init() {
		handler = new Handler();
	}
	
	private synchronized void start() {
		if (isRunning) return;
		
		thread = new Thread(this);
		thread.start();
		isRunning = true;
		
	}
	
	private synchronized void stop() {
		if ( ! isRunning) return;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isRunning = false;
	}
	
	@Override
	public void run() {
		this.requestFocus(); // cosa fa?
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000 ) {
				timer += 1000;
				frames = 0;
			}
		}
	}
	
	private void tick() {
		// Updates the game
	}
	
	private void render() {
		// Renders the game
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3); 
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		// Meat and Bones of our rendering 
		g.setColor(Color.green);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		bs.show();
		g.dispose();
	}
	
	public static void main(String args[]) {
		new Game();
	}

}
