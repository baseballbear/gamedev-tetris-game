package gamedev.tetris;

import gamedev.piece.JPiece;
import gamedev.piece.LPiece;
import gamedev.piece.LinePiece;
import gamedev.piece.SPiece;
import gamedev.piece.SquarePiece;
import gamedev.piece.TPiece;
import gamedev.piece.ZPiece;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
	nextPieces2,
	dropPieces,
	availablePieces; // all the types of tetriminos
	Tetrimino currentPiece; // current piece falling 
	boolean spawn = !false, move = true, shuffle = true;
	public enum Direction{
		Left, Down, Right;
	}
 	
	long fallTime, fallDelay = 500;
	
	//empty constructor
	public GameFrame(){};

	@Override
	public void initResources() {
		board = new Block[width][height];
		nextPieces = new ArrayList<Tetrimino>();
		nextPieces2 = new ArrayList<Tetrimino>();
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
		gd.drawImage(getImage("img/board.png"), boardLocX - 126, boardLocY - 15, null);

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				board[i][j].render(gd);
			}
		}
		displayNext(gd);
		currentPiece.render(gd);
	}
	
	@Override
	public void update(long time) {
		// TODO Auto-generated method stub
		
		fallTime += time;
		
		if(nextPieces2.isEmpty()){
			setUpNextPieces("nextPieces2");
		}
		if(nextPieces.isEmpty()){
			setUpNextPieces("nextPieces");
			spawn(time);
		}
		else{
			spawn(time);
		}
		getInput();

		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(board[j][i].isOccupied())
					System.out.print("*");
				else System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	private void checkLine() {
		boolean lineComplete = true;
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(!board[j][i].isOccupied())
					lineComplete = false;
			}
			if(lineComplete) {
				for(int n = i; n > 0; n--) {
					for(int m = 0; m < width; m++) {
						board[m][n].setOccupied(board[m][n - 1].isOccupied());
						board[m][n].setImage(board[m][n - 1].getImage());
					}
				}
				String image = "img/empty.png";
				
				for(int m = 0; m < width; m++) {
					board[m][0].setOccupied(false);
					board[m][0].setImage(getImage(image));
				}
			}
			lineComplete = true;
		}
		
	}

	public void setUpNextPieces(String listName){
		initializePieces();
		
		for(int i = 0; i < availablePieces.size(); i++){
			if(listName.equals("nextPieces")){
				nextPieces.add(availablePieces.get(i));
				Collections.shuffle(nextPieces);
			}
			else if(listName.equals("nextPieces2")){
				nextPieces2.add(availablePieces.get(i));
				Collections.shuffle(nextPieces2);
			}
		}
		
	}
	
	public void spawn(long time){
		
		checkLine();
		
		if(spawn){
			currentPiece = nextPieces.get(0);
			nextPieces.remove(0);
			if(nextPieces.size() < 5){
				nextPieces.add(nextPieces2.get(0));	
				nextPieces2.remove(0);
			}
			spawn = false;
			
		}
		if(fallTime >= fallDelay) {
			if(!checkCollision(GameFrame.Direction.Down)){
				currentPiece.moveDown(speed);
			}
			else{
				for(int i = 0; i < currentPiece.row; i++)
					for(int j = 0; j < currentPiece.col; j++)
						if(currentPiece.matrix[i][j].isOccupied())
							board[currentPiece.getX() + j][currentPiece.getY() + i] = currentPiece.matrix[i][j];
				spawn = true;
			}
			fallTime -= fallDelay;
		}
		
	}
	
	private void getInput() {
		if(keyPressed(KeyEvent.VK_LEFT)) {
			if(!checkCollision(Direction.Left)){
				currentPiece.moveLeft(boardLocX);
			}
		}
		else if(keyPressed(KeyEvent.VK_RIGHT)) {
			if(!checkCollision(Direction.Right))
				currentPiece.moveRight(boardLocX);
		}
		else if(keyPressed(KeyEvent.VK_UP)) {
			
		}
		else if(keyDown(KeyEvent.VK_DOWN)) {
			if(!checkCollision(Direction.Down))
				currentPiece.moveDown(speed);
		}
		else if(keyPressed(KeyEvent.VK_SPACE)) {
			//currentPiece.quickDrop();
			//spawn = true;
		}
		
		
		if(keyPressed('Z')) {
			
		}
		else if(keyPressed('X')) {
			
		}
	}
	private void displayNext(Graphics2D gd){
		int locx = boardLocX + 280;
		int locy = 0;
		for(int i = 0; i < 5; i++){
		
				locy = boardLocY + 170 + i*70;
			
			gd.drawImage(getImage("img/smallpieces/" + nextPieces.get(i).getImageName() + ".png"), locx, locy, null);
		}
	}
	
	public boolean checkCollision(Direction d){
		int row = currentPiece.matrix.length;
		int col = currentPiece.matrix[0].length;
		int i;
		switch(d){
		case Left:
			if(currentPiece.getX() <= 0)
				return true;
			for(i = 0; i < col; i++){
				for(int j = 0; j < row; j++){
					if(currentPiece.matrix[j][i].isOccupied())
						if(board[currentPiece.getX() + i - 1][currentPiece.getY() + j].isOccupied())
							return true;
				}
			}
			return false;
			
		case Down:
			if(currentPiece.getY() + currentPiece.getRow() >= height)
				return true;
			for(int j = 0; j < col; j++){
				for(i = row - 2; i <= row - 1; i++)
					if(currentPiece.matrix[i][j].isOccupied())
						if(board[currentPiece.getX() + j][currentPiece.getY() + i + 1].isOccupied())
							return true;
			}
			return false;
		case Right:
			if(currentPiece.getX() >= width - currentPiece.getCol())
				return true;
			for(i = col - 1; i >= 0; i--){
				for(int j = 0; j < row; j++){
					if(currentPiece.matrix[j][i].isOccupied())
						if(board[currentPiece.getX() + col][currentPiece.getY() + j].isOccupied())
							return true;
				}
			}
			return false;
			
		}
		
		return false;
	}
	
	// put every type of tetriminos in availablepieces List
	public void initializePieces(){
		availablePieces = new ArrayList<Tetrimino>();
		Tetrimino t;
		int x = 3, y = -3;
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
		
		block = "img/T block.png";
		t = new TPiece(getImage(block), x, y, boardLocX, boardLocY);
		availablePieces.add(t);
		
		block = "img/square Block.png";
		t = new SquarePiece(getImage(block), x + 1, y + 1, boardLocX, boardLocY);
		availablePieces.add(t);
		
	}

}
