package gamedev.piece;

import gamedev.tetris.Block;
import gamedev.tetris.Tetrimino;

import java.awt.image.BufferedImage;

public class SquarePiece extends Tetrimino{
	
	public SquarePiece(BufferedImage image, int x, int y, int boardX, int boardY){
		super(x,y);
		setRow(2);
		setCol(2);
		super.imageName = "small_square";
		this.boardX = boardX;
		this.boardY = boardY;
		
		matrix = new Block[row][col];
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
					matrix[i][j] = new Block(image, (x + j) * width + boardX, (y + i) * height + boardY);
					matrix[i][j].setOccupied(true);
			}
		}
		
	}
	
	

	
}
