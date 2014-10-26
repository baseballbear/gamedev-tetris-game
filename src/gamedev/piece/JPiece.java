package gamedev.piece;

import gamedev.tetris.Tetrimino;

import java.awt.image.BufferedImage;

public class JPiece extends Tetrimino{
	
	public JPiece(BufferedImage image, int x, int y, int boardX, int boardY){
		super(x,y);
		setCol(3);
		setRow(3);
		this.boardX = boardX;
		this.boardY = boardY;
		
		initializeMatrix(image);
		super.imageName = "small_j";
		
		matrix[0][0].setOccupied(true);
		matrix[1][0].setOccupied(true);
		matrix[1][1].setOccupied(true);
		matrix[1][2].setOccupied(true);
	}
	
	public void resetOrientation() {
		super.resetOrientation();
		matrix[0][0].setOccupied(true);
		matrix[1][0].setOccupied(true);
		matrix[1][1].setOccupied(true);
		matrix[1][2].setOccupied(true);
	}
}
