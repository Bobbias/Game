package Shooter;

import java.util.ArrayList;
/**
 * The InputManager is responsible for keeping track of every
 * object which is either controllable or a controller.
 * it is also responsible for polling every iController object. 
 * @author Bobbias
 *
 */
public class InputManager {
	// maybe use Sets instead?
	ArrayList<iController> controllers;
	ArrayList<iControllable> controllables;
	
	private static InputManager _instance = null;
	
	private static boolean exists = false;
	/**
	 * Creates an InputManager with a KeyboardController and MouseController
	 * added the the iController ArrayList, and a empty iControllable ArrayList.
	 */
	protected InputManager()
	{
		exists = true;
		controllers = new ArrayList<iController>();
		controllables = new ArrayList<iControllable>();
		controllers.add(new KeyboardController());
		controllers.add(new MouseController());
	}
	/**
	 * Calls HandleInput on every iController object.
	 * The iController objects are responsible for sending the messages to
	 * the iControllable objects attached to them.
	 */
	public void PollInput()
	{
		for (iController c : controllers)
		{
			c.HandleInput();
		}
	}
	public static InputManager getInputManager()
	{
		if (exists)
			return null;
		_instance = new InputManager();
		return _instance;
	}
	/**
	 * Returns the current instance of InputManager
	 * @return
	 */
	public static InputManager getCurrentInputManager()
	{
		return _instance;
	}
}
