package gamedev.piece;

import gamedev.tetris.Block;
import gamedev.tetris.Tetrimino;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class LinePiece extends Tetrimino{
	int row = 1, col = 4;
	public LinePiece(BufferedImage image, int x, int y, int boardX, int boardY){
		initializeMatrix(image);
		
		matrix[0][0] = new Block(image, (x + 0)* width + boardX,  (y + 0) * height + boardY);
		matrix[0][1] = new Block(image, (x + 0) * width + boardX, (y + 1) * height + boardY);
		matrix[0][2] = new Block(image, (x + 0) * width + boardX, (y + 2) * height + boardY);
		matrix[0][3] = new Block(image, (x + 0) * width + boardX, (y + 3) * height + boardY);
	
		matrix[0][0].setOccupied(true);
		matrix[0][1].setOccupied(true);
		matrix[0][2].setOccupied(true);
		matrix[0][3].setOccupied(true);
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
	

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	private String name = "|";
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
