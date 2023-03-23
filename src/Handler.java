import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	/** for loop to let each object tick */
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			tempObject.tick();
		}
	}

	/** for loop to let each object render */
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

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
