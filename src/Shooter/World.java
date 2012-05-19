package Shooter;

import java.util.LinkedList;
import com.bulletphysics.*;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.*;

public class World {
	CollisionShape c_shape;
	LinkedList<iWorldObject> objects;
	
	public void addObject(iWorldObject obj)
	{
		objects.add(obj);
	}
	/**
	 * returns the first instance of the parameter obj.
	 * @param obj
	 * @return
	 */
	public iWorldObject getObject(iWorldObject obj)
	{
		return objects.get(objects.indexOf(obj));
	}
	public void removeObject(iWorldObject obj)
	{
		objects.remove(obj);
	}
}
