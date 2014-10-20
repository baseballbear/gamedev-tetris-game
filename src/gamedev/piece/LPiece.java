package gamedev.piece;

import gamedev.tetris.Block;
import gamedev.tetris.Tetrimino;

import java.awt.image.BufferedImage;

public class LPiece extends Tetrimino{
	int row = 3, col = 3;
	
	public LPiece(BufferedImage image, int x, int y, int boardX, int boardY){
		super(x,y);
		initializeMatrix(image);
		super.imageName = "small_L";
		this.boardX = boardX;
		this.boardY = boardY;
		matrix[1][2] = new Block(image, (x + 2) * width + boardX, (y + 1) * height + boardY);
		matrix[2][0] = new Block(image, (x + 0) * width + boardX, (y + 2) * height + boardY);
		matrix[2][1] = new Block(image, (x + 1) * width + boardX, (y + 2) * height + boardY);
		matrix[2][2] = new Block(image, (x + 2) * width + boardX, (y + 2) * height + boardY);
		
		matrix[1][2].setOccupied(true);
		matrix[2][0].setOccupied(true);
		matrix[2][1].setOccupied(true);
		matrix[2][2].setOccupied(true);
	}
	
	public void setLocation(int x, int y){
		matrix[1][2].setX((x + 2) * width + boardX);
		matrix[1][2].setY((y + 1) * height + boardY);
		
		matrix[2][0].setX((x + 0) * width + boardX);
		matrix[2][0].setY((y + 2) * height + boardY);
		
		matrix[2][1].setX((x + 1) * width + boardX);
		matrix[2][1].setY((y + 2) * height + boardY);
		
		matrix[2][2].setX((x + 2) * width + boardX);
		matrix[2][2].setY((y + 2) * height + boardY);
		
	}
	public void initializeMatrix(BufferedImage image){
		matrix = new Block[row][col];
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				matrix[i][j] = new Block(image, 0, 0);
				matrix[i][j].setOccupied(false);
			}
		}
	}
	
	@Override
	public void rotateLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotateRight() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
