package Shooter;

//import org.lwjgl.util.vector.*;
import javax.vecmath.*;

public interface iWorldObject {
	
	
	public void SetHealth();
	public float GetHealth();
	
	public void SetSpeed();
	public float GetSpeed();

	public void MoveObject(Vector3f loc);
 
	public void SetAcceleration(float acc);
	public float getAcceleration();
	
	public void SetOrientation(Quat4d qu);
	public Quat4d GetOrientation();

	public void UpdateObject(int deltaTime);
	
	public void DrawObject();
	
	public int createVBOID();
	void FacePoint(Vector3f point, Vector3f Up);
	void Rotate(Quat4d qu);
}
