


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.Timer;


public class GameEngine extends MyWindowsForm implements KeyListener, GameReporter{
	GamePanel gp;
	
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private SpaceShip v,v1;	
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.1;
	
	public GameEngine(GamePanel gp, SpaceShip v, SpaceShip v1) {

		this.gp = gp;
		this.v = v;	
		this.v1 = v1;
		gp.sprites.add(v);
		gp.sprites.add(v1); //add SpaceShip v1
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		
		gp.sprites.add(e);
		enemies.add(e);

	}
	
	private void process(){
		if(getCount()%30 == 0){  //hard level when Over time 30 sec
			difficulty += 0.01;
		}

		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double vr1 = v1.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr) || er.intersects(vr1)){ // if v or v1 die Game Over
				die(); 
			}
		}
	}
	
	public void die(){
		timer.stop();
		JOptionPane.showMessageDialog(null, "YOU LOSE \n Your score is "+ getScore());
     }
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v1.move(-1,0);
			break;
		case KeyEvent.VK_RIGHT:
			v1.move(1,0);
			break;
		case KeyEvent.VK_UP:
			v1.move(0,-1);
			break;
		case KeyEvent.VK_DOWN:
			v1.move(0,1);
			break;

		case KeyEvent.VK_A:
			v.move(-1,0);
			break;
		case KeyEvent.VK_D:
			v.move(1,0);
			break;
		case KeyEvent.VK_W:
			v.move(0,-1);
			break;
		case KeyEvent.VK_S:
			v.move(0,1);
			break;
		}
	}

	public long getScore(){
		return score;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
