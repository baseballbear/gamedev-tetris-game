package gamedev.piece;

import gamedev.tetris.Tetrimino;

import java.awt.image.BufferedImage;

public class RectanglePiece extends Tetrimino{
	
	public RectanglePiece(BufferedImage image, int x, int y, int boardX, int boardY){
		super(x,y);
		setCol(3);
		setRow(3);
		this.boardX = boardX;
		this.boardY = boardY;
		initializeMatrix(image);
		super.imageName = "unknown_block";
	
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 3; j++){
				matrix[j][i].setOccupied(true);
			}
		}
			
	}
	
	
}
