package gamedev.tetris;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public class Block extends Sprite{
	
	private boolean occupied;

	public Block(){};
	
	public Block(BufferedImage img, double x, double y){
		super(img, x, y);
	}
	
	public Block(BufferedImage img, double x, double y, boolean occupied){
		super(img, x, y);
		this.occupied = occupied;
	}
	
	
	//copy constructor
	public Block(Block block) {
		super(block.getImage(), block.getX(), block.getY());
		this.occupied = false;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

}
