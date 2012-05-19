package Shooter.Ship;



public interface iShipComponent {
	/*
	 * Thoughts:
	 * There should be multiple attachment locations
	 * Maybe attachment sizes
	 * Weapon types (laser, guided rocket, mine, bomb, etc.)
	 * Other component types (shield, cloaking, boost, generator, etc.)
	 * Requirements (power, CPU, etc.)
	 * 
	 * */
	enum ResourceType {
		// TODO Fill with resources
	}
	enum AttachmentType {
		// TODO fill in with types
	}
	enum AttachmentLocation {
		// TODO fill with locations
	}
	/*
	 * Each resource will have a getter resources counted as floats (possibly as
	 * numbers like 0.1 k, 25.62 k)
	 */
	public float GetResourceCost(ResourceType resource);
	public int GetAttachmentLocation();

	public float GetAttachmentSize();
	
	
}
