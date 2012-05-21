package Shooter;

public class Viewport {
	private Camera cam;
	private Player owner;
	
	private int x;
	private int y;
	private int height;
	private int width;
	
	public Player getOwner() {
		return owner;
	}
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	/**
	 * Returns the camera associated with the viewport.
	 * @return
	 */
	public Camera getCam() {
		return cam;
	}
	/**
	 * Sets the camera associated with the viewport.
	 * @param cam
	 */
	public void setCam(Camera cam) {
		this.cam = cam;
	}
	/**
	 * Sets the size of the viewport.
	 * @param height
	 * @param width
	 */
	public void setSize(int height, int width)
	{
		this.height = height;
		this.width = width;
	}
	/**
	 * Sets the LOWER LEFT corner location of the viewport.
	 * @param x
	 * @param y
	 */
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
