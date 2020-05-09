import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		Gameplay gP = new Gameplay();
		JFrame window = new JFrame();
		window.setVisible(true);
		window.setBounds(10,10,700, 620);
		
		window.setResizable(false);
		window.add(gP);
		
	}
}