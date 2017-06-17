package logic;
import shots.*;
/**
 * Visitor interface. All chickens implement the visit function on hit
 * @author Daniel Margalit
 * @author Saar Scheinkman
 */
public interface Visitor {
	void visit(RedShot s);
	void visit(BlackShot s);
	void visit(YellowShot s);
	void visit(BlueShot s);
}
