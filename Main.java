
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("Space War");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 650);
		frame.getContentPane().setLayout(new BorderLayout());
		
		SpaceShip v = new SpaceShip(110, 550, 20, 20);
		SpaceShip v1 = new SpaceShip(250, 550, 20, 20);
		
		GamePanel gp = new GamePanel();
		GameEngine engine = new GameEngine(gp, v, v1);
		
		frame.addKeyListener(engine);
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
		
		engine.start();
		
	}
}
