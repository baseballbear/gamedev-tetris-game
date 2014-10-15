package gamedev.tetris;

import java.awt.image.BufferedImage;

public class LinePiece extends Tetrimino{

	public LinePiece(BufferedImage image, int x, int y){
		matrix = new Block[4][4];
		matrix[3][0] = new Block(image, x * width, ((y + 3) * height + 3) * height);
		matrix[3][1] = new Block(image, (x + 1) * width, (y + 3) * height);
		matrix[3][2] = new Block(image, (x + 2) * width, (y + 3) * height);
		matrix[3][3] = new Block(image, (x + 3) * width, (y + 3) * height);
		
		matrix[0][0].setOccupied(true);
		matrix[0][1].setOccupied(true);
		matrix[0][2].setOccupied(true);
		matrix[0][3].setOccupied(true);
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
