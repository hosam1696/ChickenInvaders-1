package shots;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import logic.*;
/**
 * Represents a spaceship shot on screen
 * @author Daniel Margalit
 * @author Saar Scheinkman
 * @param _num Number of shot(according to kind)
 * @param _label A label that contains the shot image
 */
public abstract class Shot implements Visited {
	private int _num;
	private JLabel _label;
	
	/**
	 * Shot constructor
	 * @param num Number of shot(according to kind)
	 * @param imagePath Shot image
	 */
	public Shot(int num, String imagePath){
		_num = num;
		_label = new JLabel();
		_label.setOpaque(false);
		_label.setIcon(new ImageIcon(getClass().getResource(imagePath)));
	}

	public JLabel getLabel() {
		return _label;
	}
	
	public int getNum(){
		return _num;
	}
	
	public abstract void shooting(Visitor v);
}
