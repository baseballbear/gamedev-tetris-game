package gamedev.piece;

import gamedev.tetris.Block;
import gamedev.tetris.Tetrimino;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class LinePiece extends Tetrimino{
	
	public LinePiece(BufferedImage image, int x, int y, int boardX, int boardY){
		super(x,y);
		setCol(4);
		setRow(4);
		initializeMatrix(image);
		matrix[3][0] = new Block(image, (x + 0)* width + boardX,  (y + 3) * height + boardY);
		matrix[3][1] = new Block(image, (x + 1) * width + boardX, (y + 3) * height + boardY);
		matrix[3][2] = new Block(image, (x + 2) * width + boardX, (y + 3) * height + boardY);
		matrix[3][3] = new Block(image, (x + 3) * width + boardX, (y + 3) * height + boardY);
	
		matrix[3][0].setOccupied(true);
		matrix[3][1].setOccupied(true);
		matrix[3][2].setOccupied(true);
		matrix[3][3].setOccupied(true);
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
