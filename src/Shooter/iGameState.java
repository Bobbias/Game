package Shooter;
/**
 * A GameState object defines a game state. That is to say it defines all the default information for
 * the input manager to handle inputs for whatever can be controlled in the given state.
 * @author Bobbias
 *
 */
public interface iGameState {
	
	public void Pause();
	public void Resume();
	//unnecessary?
	public void Cleanup();
	
	public void HanleInput();
	public void Update();
	public void Draw();
}