package gamedev.piece;

import gamedev.tetris.Tetrimino;

import java.awt.image.BufferedImage;

public class HookPiece extends Tetrimino{
	
	public HookPiece(BufferedImage image, int x, int y, int boardX, int boardY){
		super(x,y);
		setCol(4);
		setRow(4);
		this.boardX = boardX;
		this.boardY = boardY;
		initializeMatrix(image);
		super.imageName = "unknown_block";
	
		matrix[0][1].setOccupied(true);
		matrix[0][2].setOccupied(true);
		matrix[1][2].setOccupied(true);
		matrix[2][1].setOccupied(true);
		matrix[2][2].setOccupied(true);
		matrix[3][1].setOccupied(true);	
	}
	
	
}
