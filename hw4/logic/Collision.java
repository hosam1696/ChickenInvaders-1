package logic;

import java.awt.*;
import javax.swing.*;

public class Collision {
	/**
	 * This function return true if and only if Comp1 and Comp2 are overlap.
	 * @param Comp1 first JComponent
	 * @param Comp2 second JComponent
	 * @return return true if and only if Comp1 and Comp2 are overlap.
	 */
	public static boolean jComponentlOverlap(JComponent  Comp1, JComponent  Comp2) {
		if(Comp1==null || Comp2==null || ((JLabel)Comp1).getIcon() == null || ((JLabel)Comp2).getIcon() == null)
			return false;
		
		Point[] Comp1_points = CompPoints(Comp1);
		Point[] Comp2_points = CompPoints(Comp2);
		int lowBound_x = Comp2_points[0].x;
		int lowBound_y = Comp2_points[0].y;
		int highBound_x= Comp2_points[3].x;
		int highBound_y= Comp2_points[3].y;		

		for(Point p : Comp1_points){
			if(lowBound_y <= p.y && p.y <= highBound_y
					&& lowBound_x <= p.x && p.x <= highBound_x)
				return true;
		}
		return false;
	}
	
	/**
	 * This function return all the Endpoint of the JComponent comp
	 * @param comp JComponent
	 * @return return array of all Endpoint of the JComponent comp
	 */
	private static Point[] CompPoints(JComponent comp) {
		Point point= comp.getLocationOnScreen();
		Dimension dim = comp.getSize();	
		
		Point[] points = new  Point[4];
		points[0]= new Point(point);
		points[1]= new Point(point.x,point.y+dim.height);
		points[2]= new Point(point.x+dim.width,point.y);
		points[3]= new Point(point.x+dim.width,point.y+dim.height);
		return points;
	}
}
