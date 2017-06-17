package chickens;

import screens.Board;
import shots.BlackShot;
import shots.BlueShot;
import shots.RedShot;
import shots.YellowShot;
/**
 * Represents a circle chicken on screen
 * @author Daniel Margalit
 * @author Saar Scheinkman
 */
public class OChicken extends Chicken{
	
	public OChicken(int i, int j){
		super(9,"/chicken/special/chicken_circle.PNG", i, j);
	}

	public void visit(RedShot s) {
		return;
	}

	public void visit(BlackShot s) {
		Board.removeChickenBySymbol('O', _i, _j);
		Board.removeChicken(_i,_j);
	}

	public void visit(YellowShot s) {
		return;
	}

	public void visit(BlueShot s) {
		return;
	}

}
