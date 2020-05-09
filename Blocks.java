import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Blocks {
	
	public int map[][];
	public int width;
	public int heigth;
	
	public Blocks(int row, int col) {
		map = new int[row][col];
		for(int i = 0; i <map.length; i++) {
			for(int j = 0; j<map[0].length; j++) {
				map[i][j] = 2;}}
		
		width = 550/col;
		heigth = 130/row;}
	
	public void draw (Graphics2D g) {
		for(int i = 0; i <map.length; i++) {
			for(int j = 0; j<map[1].length; j++) {
				if(map[i][j] > 0) {
					if(j%2 == 0 && i%2 == 0)
					{g.setColor(Color.green);
					g.fillOval(j * width + 80, i* heigth +50 , width, heigth);}
					else
					{g.setColor(Color.yellow);
					g.fillOval(j * width + 80, i* heigth +50, width, heigth);}
						
					
					g.setStroke(new BasicStroke(5));
					g.setColor(Color.black);
					g.drawRect(j * width + 80, i* heigth +50, width, heigth);
				}}}}
	
	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;}}