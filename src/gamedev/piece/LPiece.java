package gamedev.piece;

import gamedev.tetris.Block;
import gamedev.tetris.Tetrimino;

import java.awt.image.BufferedImage;

public class LPiece extends Tetrimino{
	int row = 3, col = 3;
	
	public LPiece(BufferedImage image, int x, int y, int boardX, int boardY){
		super(x,y);
		setCol(3);
		setRow(3);
		super.imageName = "small_L";
		this.boardX = boardX;
		this.boardY = boardY;
		initializeMatrix(image);
		
		matrix[0][2].setOccupied(true);
		matrix[1][0].setOccupied(true);
		matrix[1][1].setOccupied(true);
		matrix[1][2].setOccupied(true);
	}
	
	public void setLocation(int x, int y){
		matrix[1][2].setX((x + 2) * width + boardX);
		matrix[1][2].setY((y + 1) * height + boardY);
		
		matrix[2][0].setX((x + 0) * width + boardX);
		matrix[2][0].setY((y + 2) * height + boardY);
		
		matrix[2][1].setX((x + 1) * width + boardX);
		matrix[2][1].setY((y + 2) * height + boardY);
		
		matrix[2][2].setX((x + 2) * width + boardX);
		matrix[2][2].setY((y + 2) * height + boardY);
		
	}

	

	
	
}
