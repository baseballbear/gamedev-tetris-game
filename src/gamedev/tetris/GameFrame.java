package gamedev.tetris;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;

import com.golden.gamedev.Game;

public class GameFrame extends Game{

	int width = 10, height = 20, 
			size = 25, boardLocX, boardLocY;
	
	List<Tetrimino> savedPieces, // the pieces that were saved by the user
	nextPieces,  // the pieces that are next on the list
	availablePieces; // all the types of tetriminos
	Tetrimino currentPiece; // current piece falling 
	boolean spawn = !false;
 	
	long fallTime, fallDelay = 500;
	
	//empty constructor
	public GameFrame(){};

	@Override
	public void initResources() {
		boardLocX = (getWidth() / 2) - (width*size)/2;
		boardLocY = (getHeight() / 2) - (height*size)/2;
	
		initGameState();
	}
	
	private void initGameState() {
		fallTime = 0;
		fallDelay = 500;
	}

	@Override
	public void render(Graphics2D gd) {
		gd.setColor(Color.black);
		gd.fillRect(0, 0, getWidth(), getHeight());
		
		gd.setColor(Color.white);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				gd.drawImage(getImage("img/empty.png"), i*size + boardLocX, j*size + boardLocY, null);
			}
		}
	//	drawSquare(gd, 5, 17);
		drawJ(gd, 4, 17);
		drawJ(gd, 9, 17);
	}
	
	private void drawSquare(Graphics2D gd, int x, int y) {
		gd.drawImage(getImage("img/square Block.png"), x*size + boardLocX, x*size + boardLocY, null);
		gd.drawImage(getImage("img/square Block.png"), y*size + boardLocX, x*size + boardLocY, null);
		gd.drawImage(getImage("img/square Block.png"), x*size + boardLocX, y*size + boardLocY, null);
		gd.drawImage(getImage("img/square Block.png"), y*size + boardLocX, y*size + boardLocY, null);
	}
	
	private void drawJ(Graphics2D gd, int x, int y) {
		String block = "img/S block.png";
		gd.drawImage(getImage(block), x*size + boardLocX, y*size + boardLocY, null);
		gd.drawImage(getImage(block), x*size + boardLocX, (y+1)*size + boardLocY, null);
		gd.drawImage(getImage(block), x*size + boardLocX, (y+2)*size + boardLocY, null);
		gd.drawImage(getImage(block), (x-1)*size + boardLocX, (y+2)*size + boardLocY, null);
	}
	@Override
	public void update(long time) {
		// TODO Auto-generated method stub
		getInput();
		
		fallTime += time;
		if(fallTime >= fallDelay) {
			fallTime -= fallDelay;
		}
		spawn(time);
	}
	
	public void spawn(long time){
		
		if(spawn){
		//	currentPiece
		}
	}
	

	private void getInput() {
		if(keyPressed(KeyEvent.VK_LEFT)) {
			
			
		}
		else if(keyPressed(KeyEvent.VK_RIGHT)) {
			
		}
		else if(keyPressed(KeyEvent.VK_UP)) {
			
		}
		else if(keyPressed(KeyEvent.VK_DOWN)) {
			
		}
		
		if(keyPressed('Z')) {
			
		}
		else if(keyPressed('X')) {
			
		}
	}
	
	
	// put every type of tetriminos in availablepieces List
	public void initializePieces(){
		
	}

}
