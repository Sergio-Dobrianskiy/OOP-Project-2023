import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		
		for (int i = 0; i < object.size(); i++) {
			object.get(i).tick();
		}
		
	}
	
	public void render(Graphics g) {
		for (GameObject tempObject : object) {
			tempObject.render(g);
		}
	}
	
	public GameObject addObject(GameObject tempObject) {
		object.add(tempObject);
		return tempObject;
	}
	
	public void removeObject(GameObject remObject) {
		object.remove(remObject);
	}
	
}
