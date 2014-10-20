package gamedev.tetris;

import java.awt.Graphics2D;

public abstract class Tetrimino {
	
	protected final int width = 25;
	protected final int height = 25;
	//x and y in the board
	protected int x, y;
	protected Block[][] matrix;
	protected int row, col;
	protected String imageName;
	protected int boardX, boardY;
	
	abstract public void rotateLeft();
	abstract public void rotateRight();
	
	public Tetrimino(int x, int y){
		this.x = x;
		this.y = y;
	}
	
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
		--x;
		for(int i = 0; i < row; i++){
			col = matrix[i].length;
			for(int j = 0; j < col; j++){
				if(matrix[i][j].isOccupied()){
					matrix[i][j].setX(matrix[i][j].getX() - width);
				}
			}
		}
	}
	public void moveRight(int boardX) {
		++x;
		row = matrix.length;
		for(int i = 0; i < row; i++){
			col = matrix[i].length;
			for(int j = 0; j < col; j++){
				if(matrix[i][j].isOccupied()){
					matrix[i][j].setX(matrix[i][j].getX() + width);
				}
			}
		}
	}
	
	public void moveDown(double speed) {
		++y;
		row = matrix.length;
		
		for(int i = 0; i < row; i++){
			col = matrix[i].length;
			for(int j = 0; j < col; j++){
				matrix[i][j].setY(matrix[i][j].getY() + speed*height);
			}
		}
	}
	
	public void setLocation(int x, int y){
		
	}
	public Block[][] getMatrix() {
		return matrix;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getRow() {
		return row;
	}
	protected void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	protected void setCol(int col) {
		this.col = col;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
	
}
