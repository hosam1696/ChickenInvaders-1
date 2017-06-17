package shots;

import logic.*;
/**
 * Represents a yellow shot on screen
 * @author Daniel Margalit
 * @author Saar Scheinkman
 */
public class YellowShot extends Shot {
	public YellowShot(){
		super(4,"/shots/yellowShot.png");
	}

	public void shooting(Visitor v) {
		v.visit(this);
	}
}
