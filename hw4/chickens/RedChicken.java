package chickens;

import screens.Board;
import shots.BlackShot;
import shots.BlueShot;
import shots.RedShot;
import shots.YellowShot;
/**
 * Represents a red chicken on screen
 * @author Daniel Margalit
 * @author Saar Scheinkman
 */
public class RedChicken extends Chicken {

	public RedChicken(int i, int j){
		super(1,"/chicken/chicken_red.PNG", i, j);
	}

	public void visit(RedShot s) {
		Board.removeChicken(_i,_j); //removes itself
	}

	public void visit(BlackShot s) {
		return;
	}

	public void visit(YellowShot s) { // kill 2 orange chickens
		Board.removeChickensByColor(4); // orange
	}

	public void visit(BlueShot s) { // kill 2 purple chickens
		Board.removeChickensByColor(5);// purple
	}
}
 