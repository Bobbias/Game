package Shooter.Ship;

import java.util.*;

public class BoxGun implements iShipWeapon, iShipComponent {

	private AttachmentType type;
	private AttachmentLocation location;
	private Map<ResourceType, Integer> cost = new HashMap<ResourceType, Integer>();
	private Object bulletType;
	
	@Override
	public void Fire() {
		// TODO Generate a box, give it a direction and speed

	}

	@Override
	public int GetAttachmentLocation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float GetAttachmentSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float GetDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float GetResourceCost(ResourceType resource) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String GetDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
