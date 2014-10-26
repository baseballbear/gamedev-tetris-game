package gamedev.piece;

import gamedev.tetris.Block;
import gamedev.tetris.Tetrimino;

import java.awt.image.BufferedImage;

public class TPiece extends Tetrimino{
	
	public TPiece(BufferedImage image, int x, int y, int boardX, int boardY){
		super(x,y);
		setCol(3);
		setRow(3);
		this.boardX = boardX;
		this.boardY = boardY;
		initializeMatrix(image);
		super.imageName = "small_t";
		
		matrix[0][1].setOccupied(true);
		matrix[1][0].setOccupied(true);
		matrix[1][1].setOccupied(true);
		matrix[1][2].setOccupied(true);
	}
	

	
	

}
