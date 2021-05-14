package breakout;

import javax.swing.JFrame;

public class Main {
	public static JFrame frame;
	
	public static void main(String[] args) {
		frame = new JFrame();
		Game game = new Game();
		frame.setBounds(10, 10, 700, 600);
		frame.setTitle("Breakout Game");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
	}

}
