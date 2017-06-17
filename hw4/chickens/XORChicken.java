package chickens;

import screens.Board;
import shots.BlackShot;
import shots.BlueShot;
import shots.RedShot;
import shots.YellowShot;
/**
 * Represents a XOR chicken on screen
 * @author Daniel Margalit
 * @author Saar Scheinkman
 */
public class XORChicken extends Chicken {

	public XORChicken(int i, int j){
		super(12,"/chicken/Bonus/chicken_xor.PNG", i, j);
	}
	
	public void visit(RedShot s) {
		return;
	}

	public void visit(BlackShot s) {
		Board.removeChickenBySymbol('*', _i, _j);
		Board.removeChicken(_i,_j);
	}

	public void visit(YellowShot s) {
		return;
	}

	public void visit(BlueShot s) {
		return;
	}

}
