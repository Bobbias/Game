package Shooter;

import org.lwjgl.input.Keyboard;

public class DummyControllable implements iControllable {

	@Override
	public void HandleInput(InputEvent e) {
		// TODO Auto-generated method stub
		if (e.type == EventType.KEYBOARD_EVENT)
		{
			if (e.keyState)
			{
				System.out.println("Pressed: "+e.KeyCode);
			}
			if (e.KeyCode == Keyboard.KEY_ESCAPE)
				System.exit(0);
		}
	}

}
