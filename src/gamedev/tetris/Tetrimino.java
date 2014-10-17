package gamedev.tetris;

import java.awt.Graphics2D;

public abstract class Tetrimino {
	
	protected final int width = 25;
	protected final int height = 25;
	protected Block[][] matrix;
	protected String name;
	private int row, col;
	
	abstract public void rotateLeft();
	abstract public void rotateRight();
	abstract public void draw();
	
	public void render(Graphics2D g){
		int row = matrix.length;
		int col;
		for(int i = 0; i < row; i++){
			col = matrix[i].length;
			for(int j = 0; j < col; j++){
				if(matrix[i][j].isOccupied())
					matrix[i][j].render(g);
			}
		}
	}
	
	public void moveLeft(int boardX) {
		row = matrix.length;
		
		for(int i = 0; i < row; i++){
			col = matrix[i].length;
			for(int j = 0; j < col; j++){
				if(matrix[i][j].isOccupied()){
					if(!(matrix[i][j].getX() < (1 * width) + boardX)){
						matrix[i][j].setX(matrix[i][j].getX() - 1*width);
					}
				}
			}
		}
	}
	public void moveRight(int boardX) {
		row = matrix.length;
		
		for(int i = 0; i < row; i++){
			col = matrix[i].length;
			for(int j = 0; j < col; j++){
				if(matrix[i][j].isOccupied()){
					if(!(matrix[i][j].getX() > (8 * width) + boardX)){
						matrix[i][j].setX(matrix[i][j].getX() + 1*width);
					}
				}
			}
		}
	}
	
	public void moveDown(double speed) {
		row = matrix.length;
		
		for(int i = 0; i < row; i++){
			col = matrix[i].length;
			for(int j = 0; j < col; j++){
				matrix[i][j].setY(matrix[i][j].getY() + speed*height);
			}
		}
	}
	
	public boolean collision() {
		row = matrix.length;
		
		for(int i = 0; i < row; i++){
			col = matrix[i].length;
			for(int j = 0; j < col; j++){
				if(matrix[i][j].isOccupied()){
					if(matrix[i][j].getY() > (21 * height)){
						return true;
					}
				}
				
			}
		}
		return false;
	}
	
	public void quickDrop(){
		row = matrix.length;
		
		for(int i = 0; i < row; i++){
			col = matrix[i].length;
			for(int j = 0; j < col; j++){
				if(matrix[i][j].isOccupied()){
					while(!collision())
					moveDown(1);
				}
			}
		}
	
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
