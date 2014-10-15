package gamedev.tetris;

import java.awt.image.BufferedImage;

public class SPiece extends Tetrimino{

	
	public SPiece(BufferedImage image, int x, int y){
		matrix = new Block[3][3];
		matrix[1][1] = new Block(image, (x + 1) * width, (y + 1) * height);
		matrix[1][2] = new Block(image, (x + 2) * width, (y + 1) * height);
		matrix[2][0] = new Block(image, x * width, (y + 2) * height);
		matrix[2][1] = new Block(image, (x + 1) * width, (y + 2) * height);
		
		matrix[1][1].setOccupied(true);
		matrix[1][2].setOccupied(true);
		matrix[2][0].setOccupied(true);
		matrix[2][1].setOccupied(true);
	}
	
	@Override
	public void rotateLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotateRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

}
