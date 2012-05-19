package Shooter;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Collection;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBVertexBufferObject;
import org.lwjgl.opengl.GLContext;
//import org.lwjgl.util.vector.*;
import javax.vecmath.*;
import com.bulletphysics.*;
import com.bulletphysics.collision.shapes.CollisionShape;

public class Model implements iCollidable, iWorldObject {
	// the vbo id for the model data
	private int vboid;
	// the vertex data for the model
	private float[] vertex_data;
	// the normals for the model
	private float[] normals;
	// the vertex colors for the model
	private float[] colors;
	// the texture coodinates for the model (how does this work?)
	private float[] texcoords;
	//
	private VboFormat format;
	// need to come up with a texture system sometime, because I WILL use textures eventually.

	private Quat4d orientation;
	
	// position in world space coordinates
	private Vector3f position;
	
	private float speed;
	
	private CollisionShape cshape;
	
	@Override
	public void Rotate(Quat4d qu)
	{
		orientation.mul(qu);
	}
	@Override
	public void FacePoint(Vector3f point, Vector3f Up)
	{
		Vector3f diff = new Vector3f();
		diff.sub(position, point);
		Vector3f Right = new Vector3f();
		Right.cross(Up, diff);
		Vector3f Backwards = new Vector3f();
		Backwards.cross(Right, Up);
		Vector3f NewUp = new Vector3f();
		NewUp.cross(Backwards, Right);
		Right.normalize();
		Backwards.normalize();
		NewUp.normalize();
		Matrix4f rot = new Matrix4f(Right.x, Right.y, Right.z, 0, NewUp.x, NewUp.y, NewUp.z, 0, Backwards.x, Backwards.y, Backwards.z, 0, 0, 0, 0, 1);
		Quat4d qrot = new Quat4d();
		qrot.set(rot);
		orientation.mul(qrot);
	}
	
	@Override
	public float getAcceleration() {
		// TODO Auto-generated method stub
		return 0f;
	}

	@Override
	public Quat4d GetOrientation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float GetHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float GetSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void SetSpeed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void UpdateObject(int deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void DrawObject() {
		// TODO Auto-generated method stub

	}

	@Override
	public int createVBOID() {
		if (GLContext.getCapabilities().GL_ARB_vertex_buffer_object)
		{
			IntBuffer buffer = BufferUtils.createIntBuffer(1);
			ARBVertexBufferObject.glGenBuffersARB(buffer);
			return buffer.get(0);
		}
		return 0;
	}
	public void bufferData(int id, FloatBuffer buffer)
	{
		if (GLContext.getCapabilities().GL_ARB_vertex_buffer_object)
		{
			ARBVertexBufferObject.glBindBufferARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, id);
			ARBVertexBufferObject.glBufferDataARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, buffer, ARBVertexBufferObject.GL_STATIC_DRAW_ARB);
		}
	}
	public void bufferElementData(int id, IntBuffer buffer)
	{
		if (GLContext.getCapabilities().GL_ARB_vertex_buffer_object)
		{
			ARBVertexBufferObject.glBindBufferARB(ARBVertexBufferObject.GL_ELEMENT_ARRAY_BUFFER_ARB, id);
			ARBVertexBufferObject.glBufferDataARB(ARBVertexBufferObject.GL_ELEMENT_ARRAY_BUFFER_ARB, buffer, ARBVertexBufferObject.GL_STATIC_DRAW_ARB);
		}
	}
	public void createVBO(Collection<float[]> objects) {
		// TODO find a way to figure out what order the objects are passed
		float[] vbo_data = null;
		int total_length = 0;
		
		vboid = createVBOID();
		
		// add up total number of passed objects
		for (float[] o : objects)
		{
			total_length += o.length;
			vbo_data = ShooterGame.concatAll(vbo_data, o);
		}
		
		FloatBuffer fb = BufferUtils.createFloatBuffer(total_length);
		fb.put(vbo_data);
		fb.rewind();
		// this stores our VBO in vram!
		bufferData(vboid, fb);
	}
	
	enum VboFormat
	{
		VERTEX,
		VERTEX_NORMAL,
		VERTEX_NORMAL_COLOR,
		VERTEX_NORMAL_COLOR_TEXTURE,
		VERTEX_COLOR,
		VERTEX_NORMAL_TEXTURE
	}

	@Override
	public void SetOrientation(Quat4d qu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void MoveObject(Vector3f loc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SetAcceleration(float acc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SetHealth() {
		// TODO Auto-generated method stub
		
	}
}
