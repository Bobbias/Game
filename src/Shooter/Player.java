package Shooter;

import Shooter.Ship.*;

public class Player {

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
	
	
}
