import java.awt.Canvas;

public class Game extends Canvas implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 800, HEIGHT = 608;
	public String title = "Zombie Game";
	
	
	public Game() {
		//Construct
		new Window(WIDTH, HEIGHT, title, this);
	}
	

	@Override
	public void run() {
		
		
	}
	
	public static void main(String args[]) {
		new Game();
	}

}
