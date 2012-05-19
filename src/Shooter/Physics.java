package Shooter;
import javax.vecmath.Vector3f;
import com.bulletphysics.*;
import com.bulletphysics.collision.broadphase.*;
import com.bulletphysics.collision.dispatch.*;
import com.bulletphysics.dynamics.*;
import com.bulletphysics.dynamics.constraintsolver.*;

public class Physics {
	DiscreteDynamicsWorld ddw;
	BroadphaseInterface broadphase;
	DefaultCollisionConfiguration collision_config = new DefaultCollisionConfiguration();
	CollisionDispatcher dispatcher;
	SequentialImpulseConstraintSolver solver;
	
	
	public Physics()
	{
		broadphase = new DbvtBroadphase();
		dispatcher = new CollisionDispatcher(collision_config);
		solver = new SequentialImpulseConstraintSolver();
		ddw = new DiscreteDynamicsWorld(dispatcher,broadphase, solver, collision_config);
	}
	/**
	 * Sets the gravity. Generally use (0,-10,0), AKA +Y = up
	 * @param gravity
	 */
	public void SetGravity(Vector3f gravity)
	{
		ddw.setGravity(gravity);
	}
}
