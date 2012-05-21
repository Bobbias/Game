package Shooter;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

public class KeyboardController implements iController{

	ArrayList<iControllable> controllables;
	
	@Override
	public void HandleInput() {
		// TODO Auto-generated method stub
		InputEvent i = new InputEvent();
		i.KeyCode = Keyboard.getEventKey();
		i.keyState = Keyboard.getEventKeyState();
		for (iControllable j : controllables)
		{
			j.HandleInput(i);
		}
	}

	@Override
	public void AttachControllable(iControllable c) {
		// TODO Auto-generated method stub
		controllables.add(c);
	}

	@Override
	public void RemoveControllable(iControllable c) {
		// TODO Auto-generated method stub
		controllables.remove(c);
	}

}
