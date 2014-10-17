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
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.golden.gamedev.Game;

public class GameFrame extends Game{

	private int width = 10, height = 20, 
			size = 25, boardLocX, boardLocY,
			numOfPieces = 6, timer = 0, speed = 1;
	private Block[][] board;
	
	List<Tetrimino> savedPieces, // the pieces that were saved by the user
	nextPieces,  // the pieces that are next on the list
	
	availablePieces; // all the types of tetriminos
	Tetrimino currentPiece; // current piece falling 
	boolean spawn = !false, move = true, shuffle = true;
 	
	long fallTime, fallDelay = 500;
	
	//empty constructor
	public GameFrame(){};

	@Override
	public void initResources() {
		board = new Block[width][height];
		nextPieces = new ArrayList<Tetrimino>();
		boardLocX = (getWidth() / 2) - (width*size)/2;
		boardLocY = (getHeight() / 2) - (height*size)/2;
		initializeBoard();
		initGameState();
		initializePieces();
	}
	
	private void initGameState() {
		fallTime = 0;
		fallDelay = 900;
		
	}
	
	public void initializeBoard(){
		String image = "img/empty.png";
		
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				board[i][j] = new Block(getImage(image), i*size + boardLocX, j*size + boardLocY);
			}
		}
	}
	
	@Override
	public void render(Graphics2D gd) {
		gd.setColor(Color.black);
		gd.fillRect(0, 0, getWidth(), getHeight());
		
		gd.setColor(Color.white);
		gd.drawImage(getImage("img/board.png"), boardLocX - 15, boardLocY - 15, null);
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				board[i][j].render(gd);
			}
		}
		currentPiece.render(gd);
	}
	
	@Override
	public void update(long time) {
		// TODO Auto-generated method stub
		getInput();
		
		fallTime += time;
		
		if(nextPieces.isEmpty()){
			setUpNextPieces();
			shuffle = false;
		}
	
		if(!nextPieces.isEmpty()){
			spawn(time);
		}
		
	}
	
	public void setUpNextPieces(){
		initializePieces();
		for(int i = 0; i < availablePieces.size(); i++){
			nextPieces.add(availablePieces.get(i));
			Collections.shuffle(nextPieces);
		}
	}
	
	public void spawn(long time){
		
		if(spawn){
			currentPiece = nextPieces.get(0);
			nextPieces.remove(0);
			spawn = false;
			
		}
		if(fallTime >= fallDelay) {
			if(!currentPiece.onBottom()){
				currentPiece.moveDown(speed);
			}
			else{
				spawn = true;
			}
			fallTime -= fallDelay;
		}
		
	}
	
	private void getInput() {
		if(keyPressed(KeyEvent.VK_LEFT)) {
			if(!currentPiece.leftClear(boardLocX, boardLocY) && !currentPiece.onBottom()){
				currentPiece.moveLeft();
			}
		}
		else if(keyPressed(KeyEvent.VK_RIGHT)) {
			if(!currentPiece.rightClear(boardLocX, boardLocY) && !currentPiece.onBottom()){
				currentPiece.moveRight();
			}
		}
		else if(keyPressed(KeyEvent.VK_UP)) {
			
		}
		else if(keyDown(KeyEvent.VK_DOWN)) {
			if(!currentPiece.onBottom())
				currentPiece.moveDown(speed);
		}
		else if(keyPressed(KeyEvent.VK_SPACE)) {
			currentPiece.quickDrop();
		}
		
		
		if(keyPressed('Z')) {
			
		}
		else if(keyPressed('X')) {
			
		}
	}
	
	
	// put every type of tetriminos in availablepieces List
	public void initializePieces(){
		availablePieces = new ArrayList<Tetrimino>();
		Tetrimino t;
		int x = 3, y = 0;
		String block = "img/I block.png";
		t = new LinePiece(getImage(block), x, y, boardLocX, boardLocY);
		availablePieces.add(t);
		
		x = 3; y = -1;
		block = "img/J block.png";
		t = new JPiece(getImage(block), x, y, boardLocX, boardLocY);
		availablePieces.add(t);
		
		block = "img/S block.png";
		t = new SPiece(getImage(block), x, y, boardLocX, boardLocY);
		availablePieces.add(t);
		
		block = "img/Z block.png";
		t = new ZPiece(getImage(block), x, y, boardLocX, boardLocY);
		availablePieces.add(t);	
		
		block = "img/L block.png";
		t = new LPiece(getImage(block), x, y, boardLocX, boardLocY);
		availablePieces.add(t);
/*		
		block = "img/T block.png";
		t = new TPiece(getImage(block), x, y, boardLocX, boardLocY);
		availablePieces.add(t);
*/
		block = "img/square Block.png";
		t = new SquarePiece(getImage(block), x, y, boardLocX, boardLocY);
		availablePieces.add(t);
		
	}

}
