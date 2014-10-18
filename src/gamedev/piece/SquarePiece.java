package gamedev.piece;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gamedev.tetris.Block;
import gamedev.tetris.Tetrimino;

public class SquarePiece extends Tetrimino{
	int row = 4, col = 4;
	
	public SquarePiece(BufferedImage image, int x, int y, int boardX, int boardY){
		name = "O";
		matrix = new Block[row][col];
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				if(i >=1 && i <= 2 && j >= 1 && j <= 2){
					matrix[i][j] = new Block(image, (x + j) * width + boardX, (y + i) * height + boardY);
					matrix[i][j].setOccupied(true);
				}
				else{
					matrix[i][j] = new Block();
					matrix[i][j].setOccupied(false);
				}
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
