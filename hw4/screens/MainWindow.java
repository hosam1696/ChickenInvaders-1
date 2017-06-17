package screens;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import fileHandling.WriteReadFile;

/**
 * Main class of the program. Showing the menu, creates new games and showing recent games statistics
 * @author Daniel Margalit
 * @author Saar Scheinkman
 * @param _menu Menu of the game
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame implements ActionListener {
	private Menu _menu;
	/**
	 * MainWindow default constructor
	 */
	public MainWindow(){
		super("Chicken Invaders");
		ImageIcon icon = new ImageIcon(getClass().getResource("/chicken/chicken_red.PNG"));
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_menu = new Menu(this);
		add(_menu);
		setSize(1300,850);
		setResizable(false);
		setLocationRelativeTo(null);
		setFocusable(true);
	}
	
	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
		mainWindow.requestFocusInWindow();
	}
	/**
	 * Implements ActionListener
	 * Creates a new game and shows recent games scores
	 */
	public void actionPerformed(ActionEvent e) {
		JButton[] menuButtons = _menu.getButtons();
		JButton source = (JButton)e.getSource();
		if (source == menuButtons[0]){ // new game button
			getContentPane().removeAll();
			Game game = new Game(_menu.getSelectedLevel(),this);
			addKeyListener(game);
			add(game);
			Refresh();
		}
		else if (source == menuButtons[1]){ // winner table button
			WriteReadFile writerReader= new WriteReadFile();
			String saved = writerReader.loadToString();
			String[] players = saved.split("\r\n|\r|\n");
			JPanel winnerTable = new JPanel(new FlowLayout(FlowLayout.CENTER));
			winnerTable.setOpaque(false);
			String message = "    ***** RECENT GAMES: *****";
			for (int i=0; i<5; i++){
				message+= "\n\n ** "+players[i]+" ** ";
			}
			JOptionPane.showMessageDialog(null, message);
			
		}
		else if (source == menuButtons[2]) // quit button
			System.exit(0);
	}
	/**
	 * Repaints the main windows
	 */
	public void Refresh(){				
		 invalidate();
		 validate();
		 repaint();				
	}
	
	public void showMainMenu(){
		getContentPane().removeAll();
		add(_menu);
		Refresh();
	}
	
}
