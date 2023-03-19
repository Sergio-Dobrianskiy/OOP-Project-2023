
public class Camera {
	
	private int x, y;
	private Handler handler;
	private GameObject tempPlayer = null;

	public Camera(int x, int y, Handler handler) {
		super();
		this.x = x;
		this.y = y;
		this.handler = handler;
		
		findPlayer();
		
	}
	
	// TODO: creare una classe per il metodo findPlayer
	public void findPlayer() {
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) {
				tempPlayer = handler.object.get(i);
				break;
			}
		}
	}
	
	public void tick() {
		if (tempPlayer != null) {
//			x = (int) tempPlayer.x - Game.WIDTH / 2 + Game.PLAYER_WIDTH / 2; //sistemare i valori
//			y = (int) tempPlayer.y - Game.HEIGHT / 2 + Game.PLAYER_HEIGHT / 2;;
			x = (int) tempPlayer.x - Game.WIDTH / 2;
			y = (int) tempPlayer.y - Game.HEIGHT / 2;
		} else {
			findPlayer();
		}
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


	
}
