package gamedev.piece;

import gamedev.tetris.Block;
import gamedev.tetris.Tetrimino;

import java.awt.image.BufferedImage;



public class ZPiece extends Tetrimino{
	
	public ZPiece(BufferedImage image, int x, int y, int boardX, int boardY){
		super(x,y);
		setCol(3);
		setRow(3);
		super.imageName = "small_z";
		this.boardX = boardX;
		this.boardY = boardY;
		initializeMatrix(image);
		
		matrix[1][0] = new Block(image, (x + 0) * width + boardX, (y + 1) * height + boardY);
		matrix[1][1] = new Block(image, (x + 1) * width + boardX, (y + 1) * height + boardY);
		matrix[2][1] = new Block(image, (x + 1) * width + boardX, (y + 2) * height + boardY);
		matrix[2][2] = new Block(image, (x + 2) * width + boardX, (y + 2) * height + boardY);
		
		matrix[1][0].setOccupied(true);
		matrix[1][1].setOccupied(true);
		matrix[2][1].setOccupied(true);
		matrix[2][2].setOccupied(true);
	}
	
	
}
