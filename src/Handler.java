import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		
		for (GameObject tempObject : object) {
			tempObject.tick();
		}
		
	}
	
	public void render(Graphics g) {
		for (GameObject tempObject : object) {
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject tempObject) {
		object.add(tempObject);
	}
	
	public void removeObject(GameObject remObject) {
		object.remove();
	}
	
}
