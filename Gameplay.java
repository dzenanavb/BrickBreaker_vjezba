import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JPanel;	

public class Gameplay extends JPanel implements KeyListener, ActionListener{
	private static final long serialVersionUID = 1L;
	private boolean play = false;
	
	private int totalBricks = 30;
	private Timer timer;
	
	private int player = 310;
	private int posX = 340;
	private int posY = 350;
	private int ballXdir = -1;
	private double ballYdir = -1.7;
	
	private Blocks map;
	
	private int delay = 4;
	public Gameplay() {
		
		map = new Blocks(3, 10);
		timer = new Timer(delay, this);
		timer.start();
		addKeyListener(this);
		
		setFocusable(true);
		
	}
	
	private int score = 0;
	public void paint(Graphics g) {
		
		//pozadina
		g.setColor(Color.black);
		g.fillRect(1, 1, 700, 600);
		
		//mapa
		map.draw((Graphics2D)g);
		
		//loptica
		g.setColor(Color.yellow);
		g.fillOval(posX, posY, 15, 15);
		
		//paddle
		g.setColor(Color.white);
		g.fillOval(player, 550, 100, 95);
		
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.PLAIN, 25));
		g.drawString("" + score, 590, 30);
		
		//end
		if(totalBricks == 0) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.PLAIN, 30));
			g.drawString("You Won", 250, 350);
		}
		
		if(posY > 560) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.PLAIN, 30));
			g.drawString("GAME OVER", 250, 300);
			g.drawString("Score: "+score, 285, 340);
			
			g.setFont(new Font("serif", Font.PLAIN, 15));
			g.drawString("PRES 1 TO RESTART", 270, 380);}
			g.dispose();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			//kod da "primjeti" paddle
			if(new Rectangle(posX, posY, 20, 20).intersects(new Rectangle(player, 550, 100,8))) {
				ballYdir = -ballYdir;}
			
			B: for(int i = 0; i<map.map.length; i++) {
				for(int j=0; j<map.map[0].length; j++) {
					if(map.map[i][j] >0) {
						int brickX = j*map.width +80;
						int brickY = i*map.heigth +50;
						int brickWidth = map.width;
						int brickHeigth = map.heigth;
						
						Rectangle r = new Rectangle(brickX, brickY, brickWidth, brickHeigth);
						Rectangle ballRect = new Rectangle(posX, posY, 20, 20);
						Rectangle brickRect = r;
						
						if(ballRect.intersects(brickRect)) {
							
							map.setBrickValue(0, i, j);
							totalBricks--;
							score += 1;;
							
							if(posX + 19 <= brickRect.x || posX + 1>= brickRect.x + brickRect.width) {
								ballXdir =- ballXdir;}
							else {ballYdir = -ballYdir;}
							break B;}}}}
			
			posX += ballXdir;
			posY += ballYdir;
			//lijevo
			if(posX < 0) {
				ballXdir = -ballXdir;}
			//top
			if(posY < 0) {
				ballYdir = -ballYdir;}
			//desno
			if(posX > 670) {
				ballXdir = -ballXdir;}}
		
		repaint();}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_A) {
			if(player < 10){
				player = 10;}
			else {moveLeft();}}
		
		if(e.getKeyCode() == KeyEvent.VK_D) {
			if(player >= 600 ){
				player = 600;}
			else {moveRight();}}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(player < 10){
				player = 10;}
			else {moveLeft();}}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(player >= 600 ){
				player = 600;}
			else {moveRight();}}
		
		if(e.getKeyCode() == KeyEvent.VK_1) {
			if(!play) {
				play = true;
				totalBricks = 30;
				score = 0;
				ballXdir = -1;
				ballYdir = -1.7;
				posX = 340;
				posY = 350;
				
				map = new Blocks(3, 10);
				repaint();}}}
	
	public void moveRight() {
		play = true;
		player += 15;}
	public void moveLeft() {
		play = true;
		player -= 15;}}