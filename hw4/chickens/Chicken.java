package chickens;

import javax.swing.*;
import logic.*;
/**
 * Represents a chicken on screen
 * @author Daniel Margalit
 * @author Saar Scheinkman
 * @param _num Chicken number (according to her kind)
 * @param _label Chicken image
 * @param _i Row position
 * @param _j Column position
 */
public abstract class Chicken implements Visitor{
	private int _num;
	protected int _i,_j;
	protected JLabel _label;
	
	/**
	 * Chicken constructor
	 * @param num Chicken number (according to her kind)
	 * @param imagePath Chicken image
	 * @param i Row position
	 * @param j Column position
	 */
	public Chicken(int num, String imagePath, int i, int j){
		_num = num;
		_i = i;
		_j = j;
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
	
	public int getI(){
		return _i;
	}
	
	public int getJ(){
		return _j;
	}
	/**
	 * Changes the chicken image to an explosion
	 */
	public void explode() {
		_label.setIcon(new ImageIcon(getClass().getResource("/explosion.png")));
	}
	
}