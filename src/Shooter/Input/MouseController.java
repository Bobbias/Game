package Shooter.Input;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;


public class MouseController implements iController {

	ArrayList<iControllable> controllables;
	
	public MouseController()
	{
		controllables = new ArrayList<iControllable>();
	}
	
	@Override
	public void HandleInput() {
		// TODO Auto-generated method stub
		InputEvent i = new InputEvent();
		int tmp = Mouse.getEventButton();
		if (tmp != -1)
		{
			i.type = EventType.MOUSE_EVENT;
			i.MouseKey = tmp;
			i.keyState = Mouse.getEventButtonState();
		}
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
