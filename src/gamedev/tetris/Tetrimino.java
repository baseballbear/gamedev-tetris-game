package gamedev.tetris;

import java.awt.Graphics2D;

public abstract class Tetrimino {
	
	protected final int size = 25;
	protected final int width = 25;
	protected final int height = 25;
	protected Block[][] matrix;
	
	abstract public void rotateLeft();
	abstract public void rotateRight();
	abstract public void draw();
	abstract public void render(Graphics2D g);
	abstract public String getName();
	abstract public void setName(String name);
	
	public void moveLeft() {
		int row = matrix.length;
		int col;
		for(int i = 0; i < row; i++){
			col = matrix[i].length;
			for(int j = 0; j < col; j++){
				matrix[i][j].setX(matrix[i][j].getX() - 1*size);
			}
		}
	}

	public void moveRight() {
		int row = matrix.length;
		int col;
		for(int i = 0; i < row; i++){
			col = matrix[i].length;
			for(int j = 0; j < col; j++){
				matrix[i][j].setX(matrix[i][j].getX() + 1*size);
			}
		}
	}
	
	public void moveDown() {
		int row = matrix.length;
		int col;
		for(int i = 0; i < row; i++){
			col = matrix[i].length;
			for(int j = 0; j < col; j++){
				matrix[i][j].setY(matrix[i][j].getY() + 1*size);
			}
		}
	
	
	}
	
	
	public boolean onBorder() {
		int row = matrix.length;
		int col; 
		for(int i = 0; i < row; i++){
			col = matrix[i].length;
			for(int j = 0; j < col; j++){
				if(matrix[i][j].isOccupied()){
					if(matrix[i][j].getY() > 19 * size){
						System.out.println("TRUE");
						return true;
					}else if(matrix[i][j].getX() < 0 * size || matrix[i][j].getX() > 9 * size){
						System.out.println("TRUE");
						
						return true;
					}
				}
				
			}
		}
		return false;
	}
}
