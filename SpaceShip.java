
import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Sprite{

	int step = 18;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		
	}

	public void move(int direction, int direction2){
		x += (step * direction);
		y += (step * direction2);
		if(x < 0)
			x = 0;
		if(x > 385 - width)
			x = 385 - width;
		if(y < 0)
			y = 0;
		if(y > 600 - height)
			y = 600 - height;
	}
	

}
