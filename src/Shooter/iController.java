package Shooter;
/**
 * Defines an interface for controller objects. Controller objects are objects
 * that represent any sort of controller which may be used to send input to
 * the game, such as keyboards, joysticks, XBOX Controllers, etc. Each controller
 * object 
 * @author Bobbias
 *
 */
public interface iController {
	public void HandleInput();
	public void AttachControllable(iControllable c);
	public void RemoveControllable(iControllable c);
	
}
