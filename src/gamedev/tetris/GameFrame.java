package gamedev.tetris;

import gamedev.piece.JPiece;
import gamedev.piece.LPiece;
import gamedev.piece.LinePiece;
import gamedev.piece.SPiece;
import gamedev.piece.SquarePiece;
import gamedev.piece.ZPiece;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.golden.gamedev.Game;

public class GameFrame extends Game{

	int width = 10, height = 20, 
			size = 25, boardLocX, boardLocY,
			numOfPieces = 6, timer = 0, speed = 0;
	
	List<Tetrimino> savedPieces, // the pieces that were saved by the user
	nextPieces,  // the pieces that are next on the list
	
	availablePieces; // all the types of tetriminos
	Tetrimino currentPiece; // current piece falling 
	boolean spawn = !false, move = true;
 	
	long fallTime, fallDelay = 500;
	
	//empty constructor
	public GameFrame(){};

	@Override
	public void initResources() {
		availablePieces = new ArrayList<Tetrimino>();
		boardLocX = (getWidth() / 2) - (width*size)/2;
		boardLocY = (getHeight() / 2) - (height*size)/2;
	
		initGameState();
	}
	
	private void initGameState() {
		fallTime = 0;
		fallDelay = 900;
		initializePieces();
		
	}

	@Override
	public void render(Graphics2D gd) {
		gd.setColor(Color.black);
		gd.fillRect(0, 0, getWidth(), getHeight());
		
		gd.setColor(Color.white);
		gd.drawImage(getImage("img/board.png"), boardLocX - 15, boardLocY - 15, null);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				gd.drawImage(getImage("img/empty.png"), i*size + boardLocX, j*size + boardLocY, null);
			}
		}
		
		for(int i = 0; i < availablePieces.size(); i++){
			//System.out.println(availablePieces.size());
	//		availablePieces.get(i).render(gd);
		}
		
		currentPiece.render(gd);
		
	//	drawSquare(gd, 5, 17);
	//	drawJ(gd, 4, 17);
	//	drawJ(gd, 5, 0);
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
		
		spawn(time);
		
	}
	
	public void spawn(long time){
		
		if(spawn){
			nextPieces = availablePieces;
			Collections.shuffle(nextPieces);
			currentPiece = nextPieces.get(0);
			spawn = false;
		}
		if(fallTime >= fallDelay) {
		//	if(!currentPiece.onBorder())
			currentPiece.moveDown();
			fallTime -= fallDelay;
		}
		
	}
	
	private void getInput() {
		if(keyPressed(KeyEvent.VK_LEFT)) {
			if(!currentPiece.onBorder())
			currentPiece.moveLeft();
		}
		else if(keyPressed(KeyEvent.VK_RIGHT)) {
			if(!currentPiece.onBorder())
			currentPiece.moveRight();
		}
		else if(keyPressed(KeyEvent.VK_UP)) {
			
		}
		else if(keyPressed(KeyEvent.VK_DOWN)) {
			if(!currentPiece.onBorder())
			currentPiece.moveDown();
		}
		
		if(keyPressed('Z')) {
			
		}
		else if(keyPressed('X')) {
			
		}
	}
	
	
	// put every type of tetriminos in availablepieces List
	public void initializePieces(){
		Tetrimino t;
		int x = 3, y = 0;
		String block = "img/J block.png";
		t = new JPiece(getImage(block), x, y, boardLocX, boardLocY);
		availablePieces.add(t);
	//	x = 9; y = 16;
		block = "img/I block.png";
		t = new LinePiece(getImage(block), x, y, boardLocX, boardLocY);
		availablePieces.add(t);
	//	x = 6; y = 17;
		block = "img/S block.png";
		t = new SPiece(getImage(block), x, y, boardLocX, boardLocY);
		availablePieces.add(t);
	//	x = 3; y = 17;
		block = "img/Z block.png";
		t = new ZPiece(getImage(block), x, y, boardLocX, boardLocY);
		availablePieces.add(t);	
		
/*		block = "img/L block.png";
		t = new LPiece(getImage(block), x, y, boardLocX, boardLocY);
		availablePieces.add(t);
		
		block = "img/T block.png";
		t = new TPiece(getImage(block), x, y, boardLocX, boardLocY);
		availablePieces.add(t);
*/	
	}

}
