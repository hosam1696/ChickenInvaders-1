package screens;
import java.awt.*;
import javax.swing.*;

/**
 * JPanel representing the game menu
 * @author Daniel Margalit
 * @author Saar Scheinkman
 * @param _menu Menu of the game
 */
@SuppressWarnings("serial")
public class Menu extends JPanel {
	private JButton[] _buttons;
	private JRadioButton[] _levelRadios;
	/**
	 * Menu constructor
	 * @param mainWindow A reference to the main window
	 */
	public Menu(MainWindow mainWindow){
		Font defFont = new Font("Arial",Font.BOLD, 22);
		
		JLabel header = new JLabel();
		header.setIcon(new ImageIcon(getClass().getResource("/header.png")));
		header.setAlignmentX(CENTER_ALIGNMENT);
		
		add(header);
		
		_buttons = new JButton[3];
		_buttons[0] = new JButton();
		_buttons[0].setIcon(new ImageIcon(getClass().getResource("/buttons/newGameButton.png")));
		_buttons[0].setBorderPainted(false);
		_buttons[0].setContentAreaFilled(false);
		_buttons[0].setFocusPainted(false); 
		_buttons[0].addActionListener(mainWindow);
		_buttons[0].setAlignmentX(CENTER_ALIGNMENT);
		add(_buttons[0]);
		
		JLabel chooseLevel = new JLabel("Please choose your starting level:");
		chooseLevel.setFont(defFont);
		chooseLevel.setAlignmentX(CENTER_ALIGNMENT);
		chooseLevel.setForeground(Color.white);
		add(chooseLevel);
		
		ButtonGroup levels = new ButtonGroup();
		_levelRadios = new JRadioButton[6];
		for (int i=0; i<6; i++)
		{
			_levelRadios[i] = new JRadioButton("Level "+(i+1));
			_levelRadios[i].setOpaque(false);
			_levelRadios[i].setForeground(Color.white);
			_levelRadios[i].setFont(defFont);
			_levelRadios[i].setSelected(true);
			_levelRadios[i].setAlignmentX(CENTER_ALIGNMENT);
			add(_levelRadios[i]);
			levels.add(_levelRadios[i]);
		}
		
		_buttons[1] =  new JButton();
		_buttons[1].addActionListener(mainWindow);
		_buttons[1].setIcon(new ImageIcon(getClass().getResource("/buttons/recentButton.png")));
		_buttons[1].setBorderPainted(false);
		_buttons[1].setContentAreaFilled(false);
		_buttons[1].setFocusPainted(false); 
		_buttons[1].setAlignmentX(CENTER_ALIGNMENT);
		add(_buttons[1]);
		
		_buttons[2] =  new JButton();
		_buttons[2].addActionListener(mainWindow);
		_buttons[2].setIcon(new ImageIcon(getClass().getResource("/buttons/quitButton.png")));
		_buttons[2].setBorderPainted(false);
		_buttons[2].setContentAreaFilled(false);
		_buttons[2].setFocusPainted(false); 
		_buttons[2].setAlignmentX(CENTER_ALIGNMENT);
		add(_buttons[2]);
		
		setBackground(Color.DARK_GRAY);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
	public JButton[] getButtons(){
		return _buttons;
	}
	
	public int getSelectedLevel(){
		int ans=-1;
		for (int i=0; i<6 && ans==-1; i++){
			if (_levelRadios[i].isSelected()){
				ans = i+1;
			}
		}
		return ans;
	}
	
	public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    // Draw the background image.
		    g.drawImage(new ImageIcon(getClass().getResource("/menuBackground.jpg")).getImage(), 0, 0, this);
	 }
}
