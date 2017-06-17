package screens;
import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;
import chickens.*;
/**
 * A JPanel containing the chickens
 * @author Daniel Margalit
 * @author Saar Scheinkman
 * @param _array Represents the chickens by int (according to chicken number)
 * @param _chickens Represents the chickens by Chicken (visitor)
 */
@SuppressWarnings("serial")
public class Board extends JPanel {
	private static int[][] _array;
	private static Chicken[][] _chickens;
	/**
	 * Board constructor
	 * @param level The level selected
	 */
	public Board(int level){
		super(new GridLayout(4,8,1,0));
		setPreferredSize(new Dimension(134*8,96*4));
		setOpaque(false);
		_chickens = new Chicken[4][8];
		switch (level){
			case 1:
				_array = new int[][]{{1,2,3,1,1,3,2,1},
									 {3,1,2,2,2,2,1,3},
									 {2,3,1,3,3,1,3,2},
									 {1,2,3,4,4,3,2,1}};
				break;
			case 2:
				_array = new int[][]{{5,5,5,6,6,4,4,4},
									 {3,9,1,1,1,1,9,3},
									 {3,1,2,2,2,2,1,3},
									 {6,5,4,3,3,4,5,6}};
				break;
			case 3:
				_array = new int[][]{{5,5,5,3,3,4,4,4},
									 {5,8,5,2,2,6,6,6},
									 {5,5,5,1,1,6,8,6},
									 {4,4,4,8,4,6,6,6}};
				break;
			case 4:
				_array = new int[][]{{5,5,4,4,5,5,4,4},
									 {6,7,6,7,6,7,6,7},
									 {4,3,5,2,4,3,5,2},
									 {1,1,1,1,1,1,1,1}};
				break;
			case 5:
				_array = new int[][]{{1,2,1,11,10,2,1,2},
									 {3,4,11,4,2,10,6,5},
									 {6,11,5,12,12,4,10,2},
									 {11,1,2,3,4,5,6,10}};
				break;
			case 6:
				_array = new int[][]{{7,8,7,8,7,8,7,8},
									 {9,9,9,9,13,9,9,9},
									 {11,10,11,10,11,10,11,10},
									 {11,10,9,8,7,8,9,10}};
				break;
		}
		Chicken c;
		for (int i = 0; i<4; i++){
			for (int j = 0; j<8; j++){
				int chickenNumber = _array[i][j];
				switch (chickenNumber){
				case 1:
					c = new RedChicken(i,j);
					break;
				case 2:
					c = new BlueChicken(i,j);
					break;
				case 3:
					c = new YellowChicken(i,j);
					break;
				case 4:
					c = new OrangeChicken(i,j);
					break;
				case 5:
					c = new PurpleChicken(i,j);
					break;
				case 6:
					c = new GreenChicken(i,j);
					break;
				case 7:
					c = new XChicken(i,j);
					break;
				case 8:
					c = new PlusChicken(i,j);
					break;
				case 9:
					c = new OChicken(i,j);
					break;
				case 10:
					c = new ColumnChicken(i,j);
					break;
				case 11:
					c = new RowChicken(i,j);
					break;
				case 12:
					c = new XORChicken(i,j);
					break;
				case 13:
					c = new AvianFluChicken(i,j);
					break;
				default:
					c = null;
					break;
				}
				add(c.getLabel());
				_chickens[i][j] = c;
			}
		}
	}
	/**
	 * Removes a specified chicken
	 * @param row Chicken's row
	 * @param col Chicken's column
	 */
	public static void removeChicken(int row, int col) {
		Game.addTargetChicken(_chickens[row][col]);
		_chickens[row][col].explode();
		_array[row][col] = -1;
		_chickens[row][col] = null;
		Game.startExplosion();
	}

	public Chicken[][] getChickenArray() {
		return _chickens;
	}
	
	public boolean isEmpty(){
		boolean ans = true;
		for (int i=0; i<_array.length && ans; i++)
			for (int j=0; j<_array[0].length && ans; j++)
				if (_array[i][j] != -1)
					ans = false;
		return ans;
	}
	/**
	 * picks 2 random chickens (in a specified color) and removes them
	 * @param color The chicken's color
	 */
	public static void removeChickensByColor(int color) {
		LinkedList<Chicken> chickens = getChickensByColor(color);
		if (chickens.size() >= 2){
			int[] randoms = randomize2Indexes(chickens.size());
			removeChicken(chickens.get(randoms[0]).getI(),chickens.get(randoms[0]).getJ());
			removeChicken(chickens.get(randoms[1]).getI(),chickens.get(randoms[1]).getJ());
		}
	}

	private static LinkedList<Chicken> getChickensByColor(int colorNum){
		LinkedList<Chicken> chickenList = new LinkedList<Chicken>();
		for (int i=0; i<_chickens.length ; i++)
			for (int j=0; j<_chickens[0].length ; j++)
				if (_chickens[i][j]!=null && _chickens[i][j].getNum() == colorNum || colorNum == 0)
					chickenList.add(_chickens[i][j]);
		return chickenList;
	}
	/**
	 * Removes all chickens that are in the specified area (by the symbol)
	 * @param symbol The chicken's symbol
	 * @param i Chicken's row
	 * @param j Chicken's column
	 */
	public static void removeChickenBySymbol(char symbol, int i, int j){
		LinkedList<Chicken> toExplode = getChickensBySymbol(symbol, i, j);
		for (Chicken c: toExplode)
			removeChicken(c.getI(),c.getJ());
	}
	
	private static LinkedList<Chicken> getChickensBySymbol(char symbol, int i, int j){
		LinkedList<Chicken> chickenList = new LinkedList<Chicken>();
		switch (symbol){
		case 'X'://X
			for (Chicken c:getChickensByDirection(i,j,-1,-1))
				chickenList.add(c);
			for (Chicken c:getChickensByDirection(i,j,1,1))
				chickenList.add(c);
			for (Chicken c:getChickensByDirection(i,j,1,-1))
				chickenList.add(c);
			for (Chicken c:getChickensByDirection(i,j,-1,1))
				chickenList.add(c);
			break;
		case 'O'://O
			for (Chicken c:getChickensByCircle(i,j))
				chickenList.add(c);
			break;
		case '+'://Plus
			for (Chicken c:getChickensByDirection(i,j,1,0))
				chickenList.add(c);
			for (Chicken c:getChickensByDirection(i,j,-1,0))
				chickenList.add(c);
			for (Chicken c:getChickensByDirection(i,j,0,-1))
				chickenList.add(c);
			for (Chicken c:getChickensByDirection(i,j,0,1))
				chickenList.add(c);
			break;
		case '|': //Column
			for (Chicken c:getChickensByDirection(i,j,1,0))
				chickenList.add(c);
			for (Chicken c:getChickensByDirection(i,j,-1,0))
				chickenList.add(c);
			break;
		case '-': //Row
			for (Chicken c:getChickensByDirection(i,j,0,-1))
				chickenList.add(c);
			for (Chicken c:getChickensByDirection(i,j,0,1))
				chickenList.add(c);
			break;
		case '*': //XOR
			for (Chicken c:getChickensByCircle(i,j))
				chickenList.add(c);
			for (Chicken c:getChickensByDirection(i,j,0,-1)){
				if (!chickenList.contains(c)){
					chickenList.add(c);
				}
			}
			for (Chicken c:getChickensByDirection(i,j,0,1)){
				if (!chickenList.contains(c)){
					chickenList.add(c);
				}
			}
			for (Chicken c:getChickensByDirection(i,j,1,0)){
				if (!chickenList.contains(c)){
					chickenList.add(c);
				}
			}
			for (Chicken c:getChickensByDirection(i,j,-1,0)){
				if (!chickenList.contains(c)){
					chickenList.add(c);
				}
			}
			break;
		}
		return chickenList;
	}
	
	private static LinkedList<Chicken> getChickensByCircle(int i, int j) {
		LinkedList<Chicken> chickens = new LinkedList<Chicken>();
		i--;
		j--;
		if (i>=0 && j >=0 && i < 4 && j < 8 && _chickens[i][j] != null)
			chickens.add(_chickens[i][j]);
		j++;
		if (i>=0 && j >=0 && i < 4 && j < 8 && _chickens[i][j] != null)
			chickens.add(_chickens[i][j]);
		j++;
		if (i>=0 && j >=0 && i < 4 && j < 8 && _chickens[i][j] != null)
			chickens.add(_chickens[i][j]);
		i++;
		if (i>=0 && j >=0 && i < 4 && j < 8 && _chickens[i][j] != null)
			chickens.add(_chickens[i][j]);
		i++;
		if (i>=0 && j >=0 && i < 4 && j < 8 && _chickens[i][j] != null)
			chickens.add(_chickens[i][j]);
		j--;
		if (i>=0 && j >=0 && i < 4 && j < 8 && _chickens[i][j] != null)
			chickens.add(_chickens[i][j]);
		j--;
		if (i>=0 && j >=0 && i < 4 && j < 8 && _chickens[i][j] != null)
			chickens.add(_chickens[i][j]);
		i--;
		if (i>=0 && j >=0 && i < 4 && j < 8 && _chickens[i][j] != null)
			chickens.add(_chickens[i][j]);
		return chickens;
	}
	/**
	 * Creates a list of all chickens in the specified direction
	 * @param i The current chicken's location (row)
	 * @param j The current chicken's location (column)
	 * @param addToRows The direction (-1=left, 1=right, 0=none)
	 * @param addToCols The direction (-1=up, 1=down, 0=none)
	 * @return A list of all chickens in the specified direction
	 */
	private static LinkedList<Chicken> getChickensByDirection(int i, int j, int addToRows, int addToCols){
		LinkedList<Chicken> chickens = new LinkedList<Chicken>();
		int newI = i+addToRows;
		int newJ = j+addToCols;
		while(newI>=0 && newJ >=0 && newI < 4 && newJ < 8){
			if (_chickens[newI][newJ] != null)
				chickens.add(_chickens[newI][newJ]);
			newI+=addToRows;
			newJ+=addToCols;
		}
		return chickens;
	}
	/**
	 * Picks 2 random indexes (from a list length)
	 * @param size The list length
	 * @return An array with the 2 indexes
	 */
	private static int[] randomize2Indexes(int size) {
		int[] ans = new int[2];
		ans[0] = (int)(Math.random() * size);
		ans[1] = (int)(Math.random() * size);
		while (ans[0] == ans[1])
			ans[1] = (int)(Math.random() * size);
		return ans;
	}

	public static void removeAllChickens() {
		for (int i=0; i<_chickens.length ; i++){
			for (int j=0; j<_chickens[0].length ; j++){
				if (_chickens[i][j] != null){
					removeChicken(_chickens[i][j].getI(),_chickens[i][j].getJ());
				}
			}
		}
	}
}
