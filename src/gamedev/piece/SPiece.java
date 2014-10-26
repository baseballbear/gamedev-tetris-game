package gamedev.piece;

import gamedev.tetris.Tetrimino;

import java.awt.image.BufferedImage;

public class SPiece extends Tetrimino{

	public SPiece(BufferedImage image, int x, int y, int boardX, int boardY){
		super(x,y);
		setCol(3);
		setRow(3);
		super.imageName = "small_s";
		this.boardX = boardX;
		this.boardY = boardY;
		initializeMatrix(image);
		
		matrix[1][1].setOccupied(true);
		matrix[1][2].setOccupied(true);
		matrix[2][0].setOccupied(true);
		matrix[2][1].setOccupied(true);
	}
	
	public void resetOrientation() {
		super.resetOrientation();
		matrix[1][1].setOccupied(true);
		matrix[1][2].setOccupied(true);
		matrix[2][0].setOccupied(true);
		matrix[2][1].setOccupied(true);
	}
	
}
