package gamedev.piece;

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
	

	
	
}
