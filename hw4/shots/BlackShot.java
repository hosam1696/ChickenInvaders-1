package shots;

import logic.*;
/**
 * Represents a black shot on screen
 * @author Daniel Margalit
 * @author Saar Scheinkman
 */
public class BlackShot extends Shot{
	
	public BlackShot(){
		super(1,"/shots/blackShot.png");
	}
	
	public void shooting(Visitor v) {
		v.visit(this);
	}

}
