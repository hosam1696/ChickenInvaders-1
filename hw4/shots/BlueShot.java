package shots;

import logic.*;
/**
 * Represents a blue shot on screen
 * @author Daniel Margalit
 * @author Saar Scheinkman
 */
public class BlueShot extends Shot{
	
	public BlueShot(){
		super(3,"/shots/blueShot.png");
	}

	public void shooting(Visitor v) {
		v.visit(this);
	}
}
