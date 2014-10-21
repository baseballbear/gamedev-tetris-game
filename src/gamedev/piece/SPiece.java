package gamedev.piece;

import gamedev.tetris.Block;
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
		matrix[1][1] = new Block(image, (x + 1) * width + boardX, (y + 1) * height + boardY);
		matrix[1][2] = new Block(image, (x + 2) * width + boardX, (y + 1) * height + boardY);
		matrix[2][0] = new Block(image, (x + 0) * width + boardX, (y + 2) * height + boardY);
		matrix[2][1] = new Block(image, (x + 1) * width + boardX, (y + 2) * height + boardY);
		
		matrix[1][1].setOccupied(true);
		matrix[1][2].setOccupied(true);
		matrix[2][0].setOccupied(true);
		matrix[2][1].setOccupied(true);
	}
	public void setLocation(int x, int y){
		matrix[1][1].setX((x + 1) * width + boardX);
		matrix[1][1].setY((y + 1) * height + boardY);
		
		matrix[1][2].setX((x + 2) * width + boardX);
		matrix[1][2].setY((y + 1) * height + boardY);
		
		matrix[2][0].setX((x + 0) * width + boardX);
		matrix[2][0].setY((y + 2) * height + boardY);
		
		matrix[2][1].setX((x + 1) * width + boardX);
		matrix[2][1].setY((y + 2) * height + boardY);
		
	}
	
}
