package Shooter;

import Shooter.Camera.Camera;
import Shooter.Input.InputEvent;
import Shooter.Input.iControllable;
import Shooter.Ship.*;

public class Player implements iControllable{

	private Camera camera;
	private iPlayerShip ship;
	
	

	public iPlayerShip getShip() {
		return ship;
	}

	public void setShip(iPlayerShip ship) {
		this.ship = ship;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	@Override
	public void HandleInput(InputEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
