package chickens;

import screens.Board;
import shots.BlackShot;
import shots.BlueShot;
import shots.RedShot;
import shots.YellowShot;
/**
 * Represents a yellow chicken on screen
 * @author Daniel Margalit
 * @author Saar Scheinkman
 */
public class YellowChicken extends Chicken {

	public YellowChicken(int i, int j){
		super(3,"/chicken/chicken_yellow.PNG", i, j);
	}

	public void visit(RedShot s) {
		Board.removeChickensByColor(4); // orange
	}

	public void visit(BlackShot s) {
		return;
	}

	public void visit(YellowShot s) {
		Board.removeChicken(_i,_j);
	}

	public void visit(BlueShot s) {
		Board.removeChickensByColor(6); // green
	}

}
