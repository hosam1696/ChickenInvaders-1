package screens;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;
import javax.sound.sampled.*;
import javax.swing.*;
import chickens.*;
import fileHandling.GiveUpException;
import fileHandling.WriteReadFile;
import logic.Collision;
import misc.*;

/**
 * A JPanel representing the Game.
 * Manages the game: updates the status bar, changes the levels, shows the scores and handles timers
 * @author Daniel Margalit
 * @author Saar Scheinkman
 * @param _board JPanel with the chickens (according to the currnet level)
 * @param _statusBar JPanel with the level information
 * @param _spaceShip A JPanel representing the spaceship
 * @param _totalScore The current total score 
 * @param _levelShots Number of shots fired so far
 * @param _shotTimer A timer for the shot animation
 * @param _levelTimer A timer indicating number of seconds passed
 * @param _spaceShipLeftTimer Timer for the spaceship's movement animation
 * @param _spaceShipRightTimer Timer for the spaceship's movement animation
 * @param _explosionTimer A timer for the explosion animation
 * @param _shotIcon JLabel representing a component of the status bar
 * @param _timeText JLabel representing a component of the status bar
 * @param _currShot JLabel representing a component of the status bar
 * @param _shotsLabel JLabel representing a component of the status bar
 * @param _level JLabel representing a component of the status bar
 * @param _runningTime Number of seconds passed since the start of the level
 * @param _animation Number of the times an animation changed
 * @param _targetChickens A list with chickens to be explode
 * @param _writerReader Saves recent games information
 * @param _mainWindow A reference to the main menu of the game
 */
@SuppressWarnings("serial")
public class Game extends JPanel implements KeyListener, ActionListener{
	private Board _board;
	private JPanel _statusBar;
	private SpaceShip _spaceShip;
	private Timer _shotTimer,_levelTimer, _spaceShipLeftTimer, _spaceShipRightTimer;
	private static Timer _explosionTimer;
	private JLabel _shotIcon, _timeText, _currShot, _shotsLabel, _level;
	private int _runningTime, _animation, _currLevel, _levelShots, _totalScore;
	private static LinkedList<Chicken> _targetChickens;
	private WriteReadFile _writerReader;
	private MainWindow _mainWindow;
	/**
	 * Game constructor
	 * @param level Number of the starting level
	 * @param mainWindow A reference to the main menu of the game
	 */
	public Game(int level, MainWindow mainWindow){
		super();
		_currLevel = level;
		_mainWindow = mainWindow;
		setLayout(null);
		_animation = 0;
		_totalScore = 0;
		_runningTime = 0;
		_levelShots = 0;
		_targetChickens = new LinkedList<Chicken>();
		_board = new Board(_currLevel);
		_board.setBounds(100, 61, 1100, 500);
		_spaceShip = new SpaceShip();
		_statusBar = new JPanel(); 
		_statusBar.setLayout(null);
		JLabel shotText = new JLabel("Current Shot: ");
		shotText.setFont(new Font("Arial", Font.BOLD, 15));
		shotText.setBounds(600, 0, 100, 30);
		_level = new JLabel("Current Level: " + _currLevel);
		_level.setFont(new Font("Arial", Font.BOLD, 15));
		_level.setBounds(300, 0, 200, 30);
		_shotIcon = new JLabel();
		_shotIcon.setIcon(_spaceShip.getShot().getLabel().getIcon());
		_shotIcon.setBounds(700, 4, 30, 30);
		_timeText = new JLabel("Time: 0");
		_timeText.setFont(new Font("Arial", Font.BOLD, 15));
		_timeText.setBounds(800, 0, 1276, 30);
		_shotsLabel = new JLabel("Shots: 0");
		_shotsLabel.setFont(new Font("Arial", Font.BOLD, 15));
		_shotsLabel.setBounds(870, 0, 1276, 30);
		_statusBar.setBounds(12, 13, 1276, 35);
		_statusBar.add(shotText);
		_statusBar.add(_shotIcon);
		_statusBar.add(_timeText);
		_statusBar.add(_shotsLabel);
		_statusBar.add(_level);
		JLabel spaceshipLabel = _spaceShip.getLabel();
		spaceshipLabel.setBounds(590, 740, 68,80);
		spaceshipLabel.setOpaque(false);
		add(_statusBar);
		add(_board);
		add(spaceshipLabel);
		_shotTimer = new Timer(12, this);
		_explosionTimer = new Timer(200, this);
		_levelTimer = new Timer(1000, this);
		_levelTimer.start();
		addKeyListener(this);
		setFocusable(true);
		_spaceShipLeftTimer = new Timer(20,this);
		_spaceShipRightTimer = new Timer(20,this);
	}
	
	public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    // Draw the background image.
		    g.drawImage(new ImageIcon(getClass().getResource("/background.jpg")).getImage(), 0, 0, this);
	 }

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT){
			_spaceShipLeftTimer.start();
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			_spaceShipRightTimer.start();
		}
		else if ((e.getKeyCode() == KeyEvent.VK_1 || e.getKeyCode() == KeyEvent.VK_NUMPAD1) && !_shotTimer.isRunning()){ // change to shot #1
			_spaceShip.setShotByNum(1);
			_shotIcon.setIcon(_spaceShip.getShot().getLabel().getIcon());
		}
		else if ((e.getKeyCode() == KeyEvent.VK_2 || e.getKeyCode() == KeyEvent.VK_NUMPAD2) && !_shotTimer.isRunning()){ // change to shot #2
			_spaceShip.setShotByNum(2);
			_shotIcon.setIcon(_spaceShip.getShot().getLabel().getIcon());
		}
		else if ((e.getKeyCode() == KeyEvent.VK_3 || e.getKeyCode() == KeyEvent.VK_NUMPAD3) && !_shotTimer.isRunning()){ // change to shot #3
			_spaceShip.setShotByNum(3);
			_shotIcon.setIcon(_spaceShip.getShot().getLabel().getIcon());
		}
		else if ((e.getKeyCode() == KeyEvent.VK_4 || e.getKeyCode() == KeyEvent.VK_NUMPAD4) && !_shotTimer.isRunning()){ // change to shot #4
			_spaceShip.setShotByNum(4);
			_shotIcon.setIcon(_spaceShip.getShot().getLabel().getIcon());
		}
		else if (e.getKeyCode() == KeyEvent.VK_SPACE && !_shotTimer.isRunning()){
			playSound("shot.wav");
			_levelShots++;
			int tX = _spaceShip.getLabel().getX() + (_spaceShip.getLabel().getIcon().getIconWidth() / 2) - 8;
			int tY = _spaceShip.getLabel().getY()  - _spaceShip.getLabel().getIcon().getIconHeight() + 55;
			_currShot = _spaceShip.getShot().getLabel();
			_currShot.setBounds(tX,tY,20,20);
			add(_currShot);
			_shotTimer.start();
		}
		_shotsLabel.setText("Shots: "+_levelShots);
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT){
			_spaceShipLeftTimer.stop();
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			_spaceShipRightTimer.stop();
		}
	}

	public void keyTyped(KeyEvent e) {
		return;
	}
	/**
	 * Implements ActionListener
	 * Showing animations and sets the spaceship and shot locations
	 */
	public void actionPerformed(ActionEvent e) {
		int currX = _spaceShip.getLabel().getLocation().x;
		int yLocation = _spaceShip.getLabel().getLocation().y;
		int rightBound = getSize().width-_spaceShip.getLabel().getIcon().getIconWidth();
		if (e.getSource() == _shotTimer){
			if (_animation <70){ //didn't reach the top of the screen yet
				Chicken[][] chickens = _board.getChickenArray();
				if (_animation>18){ //reached first chicken line (for efficiency)
					for (int i=0; i<chickens.length; i++){
						for (int j=0; j<chickens[0].length; j++){
							boolean collided = false;
							try{
							collided = chickens[i][j] !=null && !_explosionTimer.isRunning() && Collision.jComponentlOverlap(_currShot,chickens[i][j].getLabel());
							}
							catch (Exception exp){
								break;
							}
							if (collided){
								_spaceShip.getShot().shooting(chickens[i][j]);
								playSound("chick.wav");
								_shotTimer.stop();
								_animation = 0;
								remove(_currShot);
								_currShot = null;
								break;
							}
						}
					}
				}
				if (_currShot != null){
					_currShot.setLocation(_currShot.getX(), _currShot.getY() - 10);
				}
				_animation++;
			}
			else{
				_shotTimer.stop();
				_animation = 0;
				remove(_currShot);
			}
			revalidate();
			repaint();
	    }
		else if (e.getSource() == _explosionTimer){
			_explosionTimer.stop();
			playSound("explosion.wav");
			for(Chicken c: _targetChickens){
				c.getLabel().setIcon(null);
			}
			_targetChickens.clear();
			levelChanger();
		}
		else if (e.getSource() == _levelTimer){
			_runningTime ++;
			_timeText.setText("Time: "+_runningTime);
		}
		else if (e.getSource() == _spaceShipRightTimer){
			if (currX <= rightBound){
				currX+=15;
				_spaceShip.getLabel().setLocation(currX, yLocation);
			}
		}
		else if (e.getSource() == _spaceShipLeftTimer){
			if(currX > 0){
				currX-=15;
				_spaceShip.getLabel().setLocation(currX, yLocation);
			}
		}

	}
	
	private void playSound(String string) {
		 try {
	         java.net.URL url =this.getClass().getClassLoader().getClass().getResource("/sound/"+string);
	         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	         Clip clip = AudioSystem.getClip();
	         clip.open(audioIn);
	         clip.start();
	      } catch (UnsupportedAudioFileException exp) {
	    	  exp.printStackTrace();
	      } catch (IOException exp) {
	    	  exp.printStackTrace();
	      } catch (LineUnavailableException exp) {
	    	  exp.printStackTrace();
	      }
	}
	/**
	 * Changes the board to the next level and shows the level summary
	 * Shows the game summary if game ended
	 */
	private void levelChanger() {
		if (_board.isEmpty()){
			_spaceShipRightTimer.stop();
			_spaceShipLeftTimer.stop();
			_levelTimer.stop();
			int levelScore = Math.max(0, 600 - _levelShots*10 - _runningTime);
			_totalScore += levelScore;
			JOptionPane.showMessageDialog(null, "*****CHICKEN SLAYER!*****\n\n    Total Time: " + _runningTime + "\n    Total Shots: " + _levelShots + "\n    Level Score: " + levelScore + "\n\n    TOTAL SCORE: " + _totalScore + "\n\n Press OK to SLAY more chickens!");
			if (_currLevel < 6){
				_currLevel++;
				_board = new Board(_currLevel);
				add(_board);
				_board.setBounds(100, 61, 1100, 500);
				_spaceShip.getLabel().setLocation(590, 740); // center
				_level.setText("Current Level: " + _currLevel);
				_levelTimer.start();
				Refresh();
			}
			else{
				String name = JOptionPane.showInputDialog(null, "*****CHICKEN DOMINATOR!*****\n\n    Total Time: " + _runningTime + "\n    Total Shots: " + _levelShots + "\n    Level Score: " + levelScore + "\n\n    TOTAL SCORE: " + _totalScore + "\n\nPlease enter your name: ");
				if (name == null)
					name = "Unknown";
				Refresh();
				_mainWindow.showMainMenu();
				String playerStats = "Name: "+name+" Score:"+_totalScore+" Time: "+_runningTime;
				String toSave = "";
				_writerReader= new WriteReadFile();
				String saved = _writerReader.loadToString();
				String[] lines = saved.split("\r\n|\r|\n");
				if (lines.length < 5){
					toSave = saved + playerStats;
				}
				else{ // 5 saves
					int lengthToRemove = lines[0].length();
					lines[0] = lines[1];
					lines[1] = lines[2];
					lines[2] = lines[3];
					lines[3] = lines[4];
					lines[4] = playerStats;
					toSave = saved.substring(lengthToRemove+2) + lines[4];
					try {
						_writerReader.saveScore(toSave);
					} catch (GiveUpException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void startExplosion(){
		_explosionTimer.start();
	}
	
	public static void addTargetChicken(Chicken target){
		_targetChickens.add(target);
	}
	/**
	 * Repaints the game and sets status bar information to his default values
	 */
	public void Refresh(){	
		_shotsLabel.setText("Shots: "+_levelShots);
		_timeText.setText("Time: "+_runningTime); 
		_runningTime = 0;
		_levelShots = 0;
		_currShot = null;
		invalidate();
		 validate();
		 repaint();
	}
}
