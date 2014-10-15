package gamedev.tetris;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public class Block extends Sprite{
	
	private boolean occupied;

	public Block(BufferedImage img, double x, double y){
		super(img, x, y);
	}
	
	public Block(BufferedImage img, double x, double y, boolean occupied){
		super(img, x, y);
		this.occupied = occupied;
	}
	
	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

}
