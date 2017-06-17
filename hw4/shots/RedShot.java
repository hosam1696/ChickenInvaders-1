package shots;

import logic.*;
/**
 * Represents a red shot on screen
 * @author Daniel Margalit
 * @author Saar Scheinkman
 */
public class RedShot extends Shot{
	public RedShot(){
		super(2,"/shots/redShot.png");
	}

	public void shooting(Visitor v) {
		v.visit(this);
	}
}
