package misc;

import javax.swing.*;

import shots.*;
/**
 * Represents a spaceship on screen
 * @author Daniel Margalit
 * @author Saar Scheinkman
 * @param _currShot The selected shot
 * @param _shots An array of all shot kinds
 * @param _label Spaceship image
 */
public class SpaceShip {
	private Shot _currShot;
	private Shot[] _shots;
	private JLabel _label;
	/**
	 * Spaceship default constructor
	 */
	public SpaceShip(){
		_shots = new Shot[5];
		_shots[1] = new BlackShot();
		_shots[2] = new RedShot();
		_shots[3] = new BlueShot();
		_shots[4] = new YellowShot();
		_currShot = _shots[1];  //default black shot
		_label = new JLabel();
		_label.setIcon(new ImageIcon(getClass().getResource("/spaceship.PNG")));
		_label.setHorizontalAlignment(JLabel.CENTER);
	}
	
	public JLabel getLabel(){
		return _label;
	}
	
	public Shot getShot(){
		return _currShot;
	}
	
	public void setShotByNum(int shotNum){
		_currShot = _shots[shotNum];
	}
}
