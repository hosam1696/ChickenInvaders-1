package chickens;

import screens.Board;
import shots.BlackShot;
import shots.BlueShot;
import shots.RedShot;
import shots.YellowShot;
/**
 * Represents an orange chicken on screen
 * @author Daniel Margalit
 * @author Saar Scheinkman
 */
public class OrangeChicken extends Chicken {

	public OrangeChicken(int i, int j){
		super(4,"/chicken/chicken_orange.PNG", i, j);
	}

	public void visit(RedShot s) {
		Board.removeChicken(_i,_j);
	}

	public void visit(BlackShot s) {
		return;
	}

	public void visit(YellowShot s) {
		Board.removeChicken(_i,_j);
	}

	public void visit(BlueShot s) {
		return;
	}

}
