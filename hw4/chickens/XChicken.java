package chickens;

import screens.Board;
import shots.BlackShot;
import shots.BlueShot;
import shots.RedShot;
import shots.YellowShot;
/**
 * Represents an x chicken on screen
 * @author Daniel Margalit
 * @author Saar Scheinkman
 */
public class XChicken extends Chicken {

	public XChicken(int i, int j){
		super(7,"/chicken/special/chicken_x.png", i, j);
	}

	public void visit(RedShot s) {
		return;
	}

	public void visit(BlackShot s) {
		Board.removeChickenBySymbol('X', _i, _j);
		Board.removeChicken(_i,_j);
	}

	public void visit(YellowShot s) {
		return;
	}

	public void visit(BlueShot s) {
		return;
	}

}
