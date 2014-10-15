package gamedev.tetris;

public abstract class Tetrimino {
	
	protected final int width = 25;
	protected final int height = 25;
	protected Block[][] matrix;
	
	abstract public void rotateLeft();
	abstract public void rotateRight();
	abstract public void update();
	abstract public void draw();
	

}
