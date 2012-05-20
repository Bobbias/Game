package Shooter;

import java.util.ArrayList;

public class KeyboardController implements iController{

	ArrayList<iControllable> controllables;
	
	@Override
	public void HandleInput() {
		// TODO Auto-generated method stub
		
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
