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
	
	public void setLocation(int x, int y){
		matrix[3][0].setX((x + 0) * width + boardX);
		matrix[3][0].setY((y + 3) * height + boardY);
		
		matrix[3][1].setX((x + 1) * width + boardX);
		matrix[3][1].setY((y + 3) * height + boardY);
		
		matrix[3][2].setX((x + 2) * width + boardX);
		matrix[3][2].setY((y + 3) * height + boardY);
		
		matrix[3][3].setX((x + 3) * width + boardX);
		matrix[3][3].setY((y + 3) * height + boardY);
		
	}
		
	
}
