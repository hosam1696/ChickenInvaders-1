package chickens;

import screens.Board;
import shots.BlackShot;
import shots.BlueShot;
import shots.RedShot;
import shots.YellowShot;
/**
 * Represents a green chicken on screen
 * @author Daniel Margalit
 * @author Saar Scheinkman
 */
public class GreenChicken extends Chicken {
	
	public GreenChicken(int i, int j){
		super(6,"/chicken/chicken_green.PNG", i, j);
	}

	public void visit(RedShot s) {
		return;
	}

	public void visit(BlackShot s) {
		return;
	}

	public void visit(YellowShot s) {
		Board.removeChicken(_i,_j);
	}

	public void visit(BlueShot s) {
		Board.removeChicken(_i,_j);
	}

}
