package Shooter;
/**
 * Singleton object. The Sate Manager provides an interface for game states to interact either eachother, to store the required state stack etc.
 * @author Bobbias
 *
 */
public class StateManager
{
	static boolean manager_exists = false;
	
	/**
	 * Protected constructor.
	 */
	protected StateManager()
	{
		manager_exists = true;
	}
	/**
	 * Creator method that only returns a StateManager if one has not already been instantiated. 
	 * @return A StateManager object
	 */
	public static StateManager newStateManager()
	{
		if (manager_exists)
			return null;
		return new StateManager();
	}
}
