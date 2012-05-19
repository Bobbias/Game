package Shooter.Ship;

import java.util.LinkedList;
import org.lwjgl.util.vector.*;
import Shooter.Player;

public interface iPlayerShip {
	// utility method
	public void CalcAcceleration();

	/*
	 * get a list of available locations or get a list of all locations?
	 */
	public void GetComponentLocations();

	public LinkedList<iShipComponent> GetComponents();

	// return the player object which owns this ship.
	// this is meant for multiplayer, but will be testable by
	// flipping between player objects in debug mode
	public Player GetPlayer();

	// returns a linked list of the weapons available on the ship
	public LinkedList<iShipWeapon> GetWeapons();

	// example: head towards the mouse by passing the mouse x/y location.
	// behavior can also change mechanics
	// if you do not let it move forward, only side to side with this command,
	// you effectively create a rail shooter
	// you could make it rotate, in which case it does not move, it simply
	// rotates in place, allowing for movement via other input means
	// or you could do the traditional
	// "always looking up/sideways, try to move to mouse location" style shooter
	// with a fixed firing direction
	public void HeadTowards();

	// allows you to modify the list of connected weapons... still not entirely
	// sure how this will be handled
	public void SetWeapons(LinkedList<iShipWeapon> weapons);

}
