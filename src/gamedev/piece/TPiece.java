package gamedev.piece;

import gamedev.tetris.Block;
import gamedev.tetris.Tetrimino;

import java.awt.image.BufferedImage;

public class TPiece extends Tetrimino{
	
	public TPiece(BufferedImage image, int x, int y, int boardX, int boardY){
		super(x,y);
		setCol(3);
		setRow(3);
		initializeMatrix(image);
		super.imageName = "small_t";
		
		matrix[1][1] = new Block(image, (x + 1) * width + boardX, (y + 1) * height + boardY);
		matrix[2][0] = new Block(image, (x + 0) * width + boardX, (y + 2) * height + boardY);
		matrix[2][1] = new Block(image, (x + 1) * width + boardX, (y + 2) * height + boardY);
		matrix[2][2] = new Block(image, (x + 2) * width + boardX, (y + 2) * height + boardY);
		
		matrix[1][1].setOccupied(true);
		matrix[2][0].setOccupied(true);
		matrix[2][1].setOccupied(true);
		matrix[2][2].setOccupied(true);
	}
	
	public void initializeMatrix(BufferedImage image){
		matrix = new Block[row][col];
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				matrix[i][j] = new Block(image, 0, 0);
				matrix[i][j].setOccupied(false);
			}
		}
	}
	@Override
	public void rotateLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotateRight() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	

}