package chickens;

import screens.Board;
import shots.BlackShot;
import shots.BlueShot;
import shots.RedShot;
import shots.YellowShot;
/**
 * Represents an avian flu chicken on screen
 * @author Daniel Margalit
 * @author Saar Scheinkman
 */
public class AvianFluChicken extends Chicken {

	public AvianFluChicken(int i, int j){
		super(13,"/chicken/Bonus/the_chicken_flu.png", i, j);
	}

	public void visit(RedShot s) {
		return;
	}

	public void visit(BlackShot s) {
		Board.removeAllChickens();
	}

	public void visit(YellowShot s) {
		return;
	}

	public void visit(BlueShot s) {
		return;
	}

}
