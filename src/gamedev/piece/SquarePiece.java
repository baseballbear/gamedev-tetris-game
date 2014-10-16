package gamedev.piece;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gamedev.tetris.Block;
import gamedev.tetris.Tetrimino;

public class SquarePiece extends Tetrimino{
	int row, col;
	
	public void initializeMatrix(BufferedImage image){
		name = "O";
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
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	

	
}
