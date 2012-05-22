package Shooter;

import static org.lwjgl.opengl.GL11.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import javax.vecmath.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import com.bulletphysics.*;
import com.bulletphysics.collision.shapes.*;
import com.bulletphysics.dynamics.*;
import com.bulletphysics.linearmath.*;

/**
 * This is a simple test model created by hand for testing purposes.
 * It was originally designed in order to learn to render a simple object,
 * and grew into a learning experience for VBOs as well. Now it servers as
 * a sort of template for models that will exist in the as yet unnamed
 * parent game. It is still in use for testing purposes at the time of writing.
 * @author Bobbias
 *
 */
public class Cube implements iCollidable, iWorldObject {

	float back;
	float bottom;
	float depth;
	float front;
	float height;
	float left;

	float right;
	float top;
	float width;
	Vector3f center;
	
	int vboid;
	
	
	float mass = 5;
	
	Quat4d heading = new Quat4d();
	float speed = 0;
	float acceleration = Float.NaN;
	
	float[] vbo_vertex_data;
	float[] vbo_color_data;
	
	CollisionShape cshape;
	
	
	RigidBody body;
	
	public Cube(Vector3f pos, float width, float height, float depth) {
		center = new Vector3f(pos.x, pos.y, pos.z);
		this.height = height;
		this.width = width;
		this.depth = depth;
		top = pos.y + (height / 2);
		bottom = pos.y - (height / 2);
		left = pos.x - (width / 2);
		right = pos.x + (width / 2);
		front = pos.z + (depth / 2);
		back = pos.z - (depth / 2);
		vbo_vertex_data = new float[] {
			/*
			 *  X,    Y,     Z
			 *  front face
			 */
				left, bottom, front,	//1
				right, bottom, front,	//2
				right, top, front,		//3
				left, top, front,		//4
				//right face
				right, bottom, front,	//2
				right, bottom, back,	//5
				right, top, back,		//6
				right, top, front,		//4
				//left
				left, bottom, front,	//1
				left, bottom, back,		//7
				left, top, back,		//8
				left, top, front,		//4
				//back
				left, bottom, back,		//9
				right, bottom, back,	//
				right, top, back,
				left, top, back,
				//top
				left, top, front,
				right, top, front,
				right, top, back,
				left, top, back,
				//bottom
				left, bottom, front,	//1
				right, bottom, front,
				right, bottom, back,
				left, bottom, back
				
			};
		vbo_color_data = new float[]
			 // 
			{// R, G, B, A
				1f,0f,0f,1f,
				1f,0f,0f,0.75f,
				1f,0f,0f,0.50f,
				1f,0f,0f,0.25f,
				
				0f,1f,0f,0f,
				0f,1f,0f,0f,
				0f,1f,0f,0f,
				0f,1f,0f,0f,
				
				0f,0f,1f,0f,
				0f,0f,1f,0f,
				0f,0f,1f,0f,
				0f,0f,1f,0f,
				
				1f,0f,1f,0f,
				1f,0f,1f,0f,
				1f,0f,1f,0f,
				1f,0f,1f,0f,
				
				0f,1f,1f,0f,
				0f,1f,1f,0f,
				0f,1f,1f,0f,
				0f,1f,1f,0f,
				
				1f,1f,1f,0f,
				1f,1f,1f,0f,
				1f,1f,1f,0f,
				1f,1f,1f,0f,
			};
		createVBO();
		// identity heading
		Matrix4d m1 = new Matrix4d(1d,0d,0d,0d,0d,1d,0d,0d,0d,0d,1d,0d,0d,0d,0d,1d);
		heading.set(m1);
		// Still broken somehow
		//body = new RigidBody(new RigidBodyConstructionInfo(mass, new CustomMotionState() , cshape));
		//cshape = new BoxShape(new Vector3f(width, height, depth));
	}
	
	public void DrawObjectOld()
	{
		//begin drawing
		glBegin(GL_QUADS);
		// draw front face - confirmed
		glColor3f(1.0f, 1.0f, 1.0f);
		glVertex3f(left, bottom, front);
		glVertex3f(right, bottom, front);
		glVertex3f(right, top, front);
		glVertex3f(left, top, front);
		// draw right side
		glColor3f(0.5f, 0.0f, 0.0f);
		glVertex3f(right, bottom, front);
		glVertex3f(right, bottom, back);
		glVertex3f(right, top, back);
		glVertex3f(right, top, front);
		// draw left side
		glColor3f(1.0f, 1f, 1f);
		glVertex3f(left, bottom, front);
		glVertex3f(left, bottom, back);
		glVertex3f(left, top, back);
		glVertex3f(left, top, front);
		// draw back side
		glColor3f(1.0f, 1.0f, 1.0f);
		glVertex3f(left, bottom, back);
		glVertex3f(right, bottom, back);
		glVertex3f(right, top, back);
		glVertex3f(left, top, back);
		// draw top side
		glColor3f(1.0f, 1.0f, 1.0f);
		glVertex3f(left, top, front);
		glVertex3f(right, top, front);
		glVertex3f(right, top, back);
		glVertex3f(left, top, back);
		// draw bottom side
		glColor3f(1.0f, 1.0f, 1.0f);
		glVertex3f(left, bottom, front);
		glVertex3f(right, bottom, front);
		glVertex3f(right, bottom, back);
		glVertex3f(left, bottom, back);
		glEnd();
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
	public void SetHealth() {
		// TODO Auto-generated method stub
	}

	@Override
	public void SetSpeed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpdateObject(int deltaTime) {
		// currently the out-dated draw format.
		//DrawObjectOld();
		DrawObject();
		//float seconds = deltaTime/1000;
		//if (acceleration != Float.NaN)
		//	speed = acceleration * seconds;
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

	
	public void createVBO() {
		// TODO Auto-generated method stub
		vboid = createVBOID();
		FloatBuffer fb = BufferUtils.createFloatBuffer(vbo_vertex_data.length+vbo_color_data.length);
		float[] vbo_data = ShooterGame.concatAll(vbo_vertex_data, vbo_color_data);
		fb.put(vbo_data);
		fb.rewind();
		// this stores our VBO in vram!
		bufferData(vboid, fb);
	}

	@Override
	public void DrawObject() {
		// TODO Auto-generated method stub
		try
		{
			glEnableClientState(GL_VERTEX_ARRAY);
			glEnableClientState(GL_COLOR_ARRAY);
			//bind our VBO
			ARBVertexBufferObject.glBindBufferARB(ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB, vboid);
			glVertexPointer(3, GL_FLOAT, 0, 0);
			glColorPointer(4, GL_FLOAT, 0, vbo_vertex_data.length*4);
			float[] temp = QuatToMatrix(heading);
			temp = ShooterGame.concatAll(temp, new float[] {0f, 0f, 0f, 1f});
			FloatBuffer fb = BufferUtils.createFloatBuffer(16);
			fb.put(temp);
			fb.rewind();
			GL11.glMultMatrix(fb);
			GL11.glDrawArrays(GL_QUADS, 0, vbo_vertex_data.length);
		}
		catch (OpenGLException e)
		{
			e.printStackTrace();
		}
		
	}
	public float[] QuatToMatrix(Quat4d q)
	{
		return new float[] {(float) (1-2*(q.x*q.x-q.z*q.z)),(float) (2*(q.x*q.y)+2*(q.w*q.z)),(float)(2*(q.x*q.z)-2*(q.w*q.y)),(float) (2*(q.x*q.y)-2*(q.w*q.z)),(float) (1-2*q.x*q.x-2*q.z*q.z),(float) (2*(q.y*q.z)+2*(q.w*q.x)),(float) (2*(q.x*q.z)+2*(q.w*q.y)),(float) (2*(q.y*q.z)-2*(q.w*q.x)),(float) (1-2*(q.x*q.x)-2*(q.y*q.y))};	
	}

	@Override
	public void MoveObject(javax.vecmath.Vector3f loc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SetAcceleration(float acc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SetOrientation(Quat4d qu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Quat4d GetOrientation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getAcceleration() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void FacePoint(Vector3f point, Vector3f Up)
	{/*
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
		orientation.mul(qrot);*/
	}

	@Override
	public void Rotate(Quat4d qu) {
		// TODO Auto-generated method stub
		heading.mul(qu);
	}

}
