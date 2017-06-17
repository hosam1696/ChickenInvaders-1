package chickens;

import screens.Board;
import shots.BlackShot;
import shots.BlueShot;
import shots.RedShot;
import shots.YellowShot;
/**
 * Represents a purple chicken on screen
 * @author Daniel Margalit
 * @author Saar Scheinkman
 */
public class PurpleChicken extends Chicken {

	public PurpleChicken(int i, int j){
		super(5,"/chicken/chicken_purple.PNG", i, j);
	}

	public void visit(RedShot s) {
		Board.removeChicken(_i,_j);
	}

	public void visit(BlackShot s) {
		return;
	}

	public void visit(YellowShot s) {
		return;
	}

	public void visit(BlueShot s) {
		Board.removeChicken(_i,_j);
	}

}
