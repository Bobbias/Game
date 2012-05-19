package Shooter;

import static org.lwjgl.opengl.GL11.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import javax.vecmath.Quat4d;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Vector3f;
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
	float x;
	float y;
	float z;
	
	int vboid;
	
	
	float mass = 5;
	
	Vector3f heading = new Vector3f();
	float speed = 0;
	float acceleration = Float.NaN;
	
	float[] vbo_vertex_data;
	float[] vbo_color_data;
	
	CollisionShape cshape;
	
	
	RigidBody body;
	
	public Cube(float x, float y, float z, float width, float height,
			float depth) {
		// empty for now, bitch
		this.x = x;
		this.y = y;
		this.z = z;
		center = new Vector3f(x,y,z);
		this.height = height;
		this.width = width;
		this.depth = depth;
		top = y + (height / 2);
		bottom = y - (height / 2);
		left = x - (width / 2);
		right = x + (width / 2);
		front = z + (depth / 2);
		back = z - (depth / 2);
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
		// Still broken somehow
		body = new RigidBody(new RigidBodyConstructionInfo(mass, new CustomMotionState() , cshape));
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
		float seconds = deltaTime/1000;
		if (acceleration != Float.NaN)
			speed = acceleration * seconds;
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
			GL11.glDrawArrays(GL_QUADS, 0, vbo_vertex_data.length);
		}
		catch (OpenGLException e)
		{
			e.printStackTrace();
		}
		
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
	public void FacePoint(javax.vecmath.Vector3f point,
			javax.vecmath.Vector3f Up) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Rotate(Quat4d qu) {
		// TODO Auto-generated method stub
		
	}

}
