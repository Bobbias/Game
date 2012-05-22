/*
 * Special thanks to a1k0n from #0x10c-dev on freenode for listening to my million questions and patiently explaining things to me.
 */

package Shooter;

import java.util.Arrays;
import java.util.LinkedList;

import javax.vecmath.Vector3f;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.*;
import org.lwjgl.input.*;
//import org.lwjgl.util.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.*;

import com.bulletphysics.collision.dispatch.*;
import com.bulletphysics.dynamics.*;
import com.bulletphysics.util.*;

public class ShooterGame {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ShooterGame sg = new ShooterGame();
		sg.start();
	}
	
	Physics gamePhysics;
	InputManager im;
	StateManager sm;
	
	int delta;
	int fps;
	long lastfps;
	long lastFrame;
	int mouseDX;
	int mouseDY;

	/**
	 * counts frames, resets every second and updates the counter. 
	 * Probably not the best fps counter method.
	 */
	public void calcFPS() {
		// if it's been more than a second
		long temp = getTime() - lastfps;
		if (temp > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastfps += 1000;
		}
		fps++;
	}

	/**
	 * Get milliseconds between last frame, for timing.
	 * 
	 * @return The milliseconds since last frame
	 */
	public int getDeltaTime() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	/**
	 * 
	 * @return
	 */
	public int getMouseDX() {
		return mouseDX;
	}

	/**
	 * 
	 * @return
	 */
	public int getMouseDY() {
		return mouseDY;
	}

	/**
	 * Get the time in milliseconds. There are two methods here. One's been
	 * commented out.
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		// return System.nanoTime() / 1000000;
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * polls the input
	 */
	private void pollInput() {
		int i = Mouse.getDY();
		int j = Mouse.getDX();
		if (Mouse.isButtonDown(0)) {
			// System.out.println("MOUSE DOWN @ X: " + x + " Y: " + y);
			mouseDX = j;
			mouseDY = i;
			if (mouseDX > 0 || mouseDY > 0) {
				System.out.println("MouseDX: " + mouseDX + " MouseDY: "
						+ mouseDY);
			}
		}
		// triggered on down and up
		while (Keyboard.next()) {

			switch (Keyboard.getEventKey()) {
			// case statements for special keys only
			case Keyboard.KEY_ESCAPE:
				System.exit(0);
				break;
			case Keyboard.KEY_BACK:
				System.out.println("Backspace pressed");
				break;
			case Keyboard.KEY_RETURN:
			case Keyboard.KEY_NUMPADENTER:
				if (Keyboard.getEventKeyState()) {
					if (Mouse.isGrabbed()) {
						Mouse.setGrabbed(false);
					} else {
						Mouse.setGrabbed(true);
					}
					System.out.println("Enter down!");
					break;
				} else {
					System.out.println("Enter up!");
					break;
				}
			default:
				char c = Keyboard.getEventCharacter();
				System.out.println("Pressed: " + c);
			}
		}
	}

	/**
	 * setter for mouseDX
	 * @param mouseDX
	 */
	public void setMouseDX(int mouseDX) {
		this.mouseDX = mouseDX;
	}

	/**
	 * setter for mouseDy
	 * @param mouseDY
	 */
	public void setMouseDY(int mouseDY) {
		this.mouseDY = mouseDY;
	}
	
	/**
	 * handy code ripped form the net
	 * http://stackoverflow.com/questions/80476/how-to-concatenate-two-arrays-in-java
	 * Joachim Sauer
	 * */
	public static <T> T[] concatAll(T[] first, T[]... rest) {
		  int totalLength = first.length;
		  for (T[] array : rest) {
		    totalLength += array.length;
		  }
		  T[] result = Arrays.copyOf(first, totalLength);
		  int offset = first.length;
		  for (T[] array : rest) {
		    System.arraycopy(array, 0, result, offset, array.length);
		    offset += array.length;
		  }
		  return result;
		}
	/**
	 * My implementation of the handy code for float arrays
	 * @param first
	 * @param rest
	 * @return
	 */
	public static float[] concatAll(float[] first, float[]... rest) {
		  int totalLength = first.length;
		  for (float[] array : rest) {
		    totalLength += array.length;
		  }
		  float[] result = Arrays.copyOf(first, totalLength);
		  int offset = first.length;
		  for (float[] array : rest) {
		    System.arraycopy(array, 0, result, offset, array.length);
		    offset += array.length;
		  }
		  return result;
		}
	
	public void InitOpenGL()
	{
		// init OpenGL here
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		// glOrtho(0, 800, 0, 600, 1, -1);
		GLU.gluPerspective(60, (800 / 600), 10, -10);
		glMatrixMode(GL_MODELVIEW);
		glTranslatef(0, 0, -100);
		glEnable(GL_DEPTH_TEST);
	}
	
	
	/**
	 * begin the game!
	 * Main game loop here
	 */
	public void start() {
		im = new InputManager();
		DummyControllable dummy = new DummyControllable();
		for (iController i : im.controllers)
		{
			i.AttachControllable(dummy);
		}
		Mouse.setGrabbed(true);
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		//gamePhysics = new Physics();
		//setup physics here
		
		lastfps = getTime();
		//init graphics here
		InitOpenGL();
		sm = StateManager.newStateManager();
		im = InputManager.getInputManager();
		
		// initialize our timer
		lastFrame = getTime();
		Cube testcube = new Cube(new Vector3f(0,0,0), 20f, 20f, 20f);
		//add cube to list of collision objects
		//gamePhysics.ddw.addCollisionObject(testcube.body);
		
		/*
		 * Game loop. Inside this is where most of the game happens.
		 */
		while (!Display.isCloseRequested()) {
			delta = getDeltaTime();
			//step physics based on how long has passed
			//gamePhysics.ddw.stepSimulation(delta/1000, 10);
			// render OpenGL here
			// clear screen and depth buffer
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			//pollInput();
			im.PollInput();
			// updates phyics then draws
			testcube.UpdateObject(delta);
			// glEnable(GL_CULL_FACE);
			Display.update();
			mouseDX = 0;
			mouseDY = 0;
			calcFPS();
			// force 60FPS
			Display.sync(60);
		}
		Display.destroy();
	}
	
}
