package gamedev.piece;

import gamedev.tetris.Block;
import gamedev.tetris.Tetrimino;

import java.awt.image.BufferedImage;

public class LinePiece extends Tetrimino{
	
	public LinePiece(BufferedImage image, int x, int y, int boardX, int boardY){
		super(x,y);
		setCol(4);
		setRow(4);
		this.boardX = boardX;
		this.boardY = boardY;
		initializeMatrix(image);
		super.imageName = "small_i3";
	
		matrix[2][0].setOccupied(true);
		matrix[2][1].setOccupied(true);
		matrix[2][2].setOccupied(true);
		matrix[2][3].setOccupied(true);
	}
	
	
}
