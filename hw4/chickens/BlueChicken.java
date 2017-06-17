package chickens;

import screens.Board;
import shots.*;
/**
 * Represents a blue chicken on screen
 * @author Daniel Margalit
 * @author Saar Scheinkman
 */
public class BlueChicken extends Chicken {

	
	public BlueChicken(int i, int j){
		super(2,"/chicken/chicken_blue.PNG", i, j);
	}

	public void visit(RedShot s) {
		Board.removeChickensByColor(5); // purple
	}

	public void visit(BlackShot s) {
		return;
	}

	public void visit(YellowShot s) {
		Board.removeChickensByColor(6); //green
	}

	public void visit(BlueShot s) {
		Board.removeChicken(_i,_j); //removes itself
	}

}
