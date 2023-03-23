import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	private Handler handler;
	private Camera cam;
	private GameObject tempPlayer = null;
	private Game game;

	
	public MouseInput(Handler handler, Camera cam, Game game) {
		this.handler = handler;
		this.cam = cam;
		this.game = game;
	}
	
	public void findPlayer() {
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) {
				tempPlayer = handler.object.get(i);
				break;
			}
		}
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (tempPlayer != null && game.ammo > 0) {
			GameObject tempBullet = handler.addObject(new Bullet(tempPlayer.x + 16, tempPlayer.y + 16, ID.Bullet, handler));			
			
			float angle = (float) Math.atan2(my - tempPlayer.y - 16 + cam.getY(), mx - tempPlayer.x - 16 + cam.getX());
			int bulletVelocity = 10;
			tempBullet.velX = (float) ((bulletVelocity) * Math.cos(angle));
			tempBullet.velY = (float) ((bulletVelocity) * Math.sin(angle));
			
			game.ammo--;
			
		} else {
			findPlayer();
		}
		
	}
}
