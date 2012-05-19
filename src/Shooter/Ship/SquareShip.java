package Shooter.Ship;

import java.util.LinkedList;

import Shooter.Player;

public class SquareShip implements iPlayerShip {

	LinkedList<iShipComponent> Components;
	
	@Override
	public void CalcAcceleration() {
		// TODO Auto-generated method stub

	}

	@Override
	public void GetComponentLocations() {
		// TODO Auto-generated method stub

	}

	@Override
	public LinkedList<iShipComponent> GetComponents() {
		return Components;
	}

	@Override
	public Player GetPlayer() {
		return null;
	}

	@Override
	public LinkedList<iShipWeapon> GetWeapons() {
		LinkedList<iShipWeapon> ret = new LinkedList<iShipWeapon>();
		for (iShipComponent i : Components)
		{
			if (i instanceof iShipWeapon)
			{
				ret.add((iShipWeapon) i);
			}
		}
		return ret;
	}

	@Override
	public void HeadTowards() {
		// TODO Auto-generated method stub

	}

	@Override
	public void SetWeapons(LinkedList<iShipWeapon> weapons) {
		// TODO Auto-generated method stub

	}

}
