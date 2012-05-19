package Shooter;

import com.bulletphysics.linearmath.*;

public class CustomMotionState extends MotionState {

	Transform WorldTransform;
	
	@Override
	public Transform getWorldTransform(Transform out) {
		// TODO Auto-generated method stub
		out = WorldTransform;
		return null;
	}

	@Override
	public void setWorldTransform(Transform worldTrans) {
		// TODO Auto-generated method stub
		
	}

}
