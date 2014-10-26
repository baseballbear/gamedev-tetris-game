package gamedev.tetris;

import gamedev.piece.HookPiece;
import gamedev.piece.JPiece;
import gamedev.piece.LPiece;
import gamedev.piece.LinePiece;
import gamedev.piece.RectanglePiece;
import gamedev.piece.SPiece;
import gamedev.piece.SquarePiece;
import gamedev.piece.TPiece;
import gamedev.piece.ZPiece;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.font.SystemFont;

public class GameFrame extends Game{

	private int width = 10, height = 23, 
			size = 25, boardLocX, boardLocY,
			numOfPieces = 6, timer = 0, speed = 1, saveCount = 0,
				maxLevel = 15, currentLvl, linesToClear, score, quickDropScore = 0,
						middleLeft, middleRight, center;
	private Block[][] board;

	List<Tetrimino> savedPieces, // the pieces that were saved by the user
	nextPieces,  // the pieces that are next on the list
	nextPieces2,
	dropPieces,
	availablePieces; // all the types of tetriminos
	Tetrimino currentPiece, ghostPiece; // current piece falling 
	boolean spawn, extreme;
	int handicap, handicapLvl;
	public enum Direction{
		Left, Down, Right;
	}
	
	
	public enum Screen {
		GAME_SCREEN,
		MAIN_MENU,
		GAME_OVER,
		PAUSE_SCREEN,
		SETTINGS_SCREEN, 
		CHANCE_SCREEN
	}
	Screen currentScreen;
	List<Button> menuButtons,
			pauseButtons,
			settingsButtons,
			chanceButtons;
	
	long fallTime, fallDelay, moveTime, moveDelay, downTime, downDelay;

	GameFont gameFont, scoreFont;
	
	String quickDropSound = "sfx/8.wav";
	
	//empty constructor
	public GameFrame(){};

	@Override
	public void initResources() {

		//Initialize Game Components
		board = new Block[width][height];
		nextPieces = new ArrayList<Tetrimino>();
		nextPieces2 = new ArrayList<Tetrimino>();
		savedPieces = new ArrayList<Tetrimino>();
		boardLocX = (getWidth() / 2) - (width*size)/2;
		boardLocY = (getHeight() / 2) - ((height - 3)*size)/2;
		gameFont = new SystemFont(new Font("Cooper Std Black", Font.PLAIN, 25), Color.black); // Cooper Std Black
		scoreFont = new SystemFont(new Font("Cooper Std Black", Font.PLAIN, 23), Color.black); // Cooper Std Black
		
		middleLeft = getWidth() / 4;
		middleRight = getWidth() * 3 / 4;
		center = getWidth()/2;
		initializeBoard();
		initGameState();
		initializePieces();
		initializeButtons();
		
		extreme = false;
		handicapLvl = 0;
		setHandicap();

		currentScreen = Screen.MAIN_MENU;
		
	}
	
	private void initializeButtons() {
		int offset = 200;
		menuButtons = new ArrayList<Button>();
		menuButtons.add(new Button(getImage("img/buttons/play.png"), center - 75, 0 + offset, "Start"));
		menuButtons.add(new Button(getImage("img/buttons/highscores2.png"), center - 75, 70 + offset, "Highscores"));
		menuButtons.add(new Button(getImage("img/buttons/help2.png"), center - 75, 140 + offset, "Chance"));
		menuButtons.add(new Button(getImage("img/buttons/settings2.png"), center - 75, 210 + offset, "Settings"));

		pauseButtons = new ArrayList<Button>();
		pauseButtons.add(new Button(getImage("img/buttons/resume.png"), center - 75, 0 + offset, "Resume"));
		pauseButtons.add(new Button(getImage("img/buttons/restart.png"), center - 75, 70 + offset, "Restart"));
		pauseButtons.add(new Button(getImage("img/buttons/quit.png"), center - 75, 140 + offset, "ExitToMainMenu"));
		
		settingsButtons = new ArrayList<Button>();
		settingsButtons.add(new Button(getImage("img/buttons/handicap2.png"), middleLeft - 75, 0 + offset, "handicaplabel"));
		settingsButtons.add(new Button(getImage("img/buttons/one2.png"), middleLeft - 75, 70 + offset, "handicap1"));
		settingsButtons.add(new Button(getImage("img/buttons/two2.png"), middleLeft - 75, 140 + offset, "handicap2"));
		settingsButtons.add(new Button(getImage("img/buttons/three2.png"), middleLeft - 75, 210 + offset, "handicap3"));
		settingsButtons.add(new Button(getImage("img/buttons/back.png"), middleLeft - 75, 320 + offset, "settings_back"));
		
		settingsButtons.add(new Button(getImage("img/buttons/mode.png"), middleRight - 75, 0 + offset, "GameMode"));
		settingsButtons.add(new Button(getImage("img/buttons/classic2_pressed.png"), middleRight - 75, 70 + offset, "Classic"));
		settingsButtons.add(new Button(getImage("img/buttons/extreme2.png"), middleRight - 75, 140 + offset, "Extreme"));
		
		chanceButtons = new ArrayList<Button>();
		offset = 100;
		chanceButtons.add(new Button(getImage("img/Slider.png"), middleLeft, 0 + offset, "unknown_block -"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), middleLeft, 50 + offset, "small_j -"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), middleLeft, 100 + offset, "small_L -"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), middleLeft, 150 + offset, "small_t -"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), middleLeft, 200 + offset, "small_s -"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), middleLeft, 250 + offset, "small_z -"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), middleLeft, 300 + offset, "small_square -"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), middleLeft, 350 + offset, "small_i3 -"));
		
		chanceButtons.add(new Button(getImage("img/Slider.png"), center, 0 + offset, "unknown_block"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), center, 50 + offset, "small_j"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), center, 100 + offset, "small_L"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), center, 150 + offset, "small_t"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), center, 200 + offset, "small_s"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), center, 250 + offset, "small_z"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), center, 300 + offset, "small_square"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), center, 350 + offset, "small_i3"));
		
		chanceButtons.add(new Button(getImage("img/Slider.png"), middleRight, 0 + offset, "unknown_block +"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), middleRight, 50 + offset, "small_j +"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), middleRight, 100 + offset, "small_L +"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), middleRight, 150 + offset, "small_t +"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), middleRight, 200 + offset, "small_s +"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), middleRight, 250 + offset, "small_z +"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), middleRight, 300 + offset, "small_square +"));
		chanceButtons.add(new Button(getImage("img/Slider.png"), middleRight, 350 + offset, "small_i3 +"));
		
		chanceButtons.add(new Button(getImage("img/buttons/back.png"), center - 40, 425 + offset, "chance_back"));
	}

	private void initGameState() {
		spawn = true;
		saveCount = 0;
		fallTime = 0;
		moveTime = 0;
		currentLvl = 1;
		linesToClear = currentLvl * 5;
		score = 0;
		savedPieces.clear();
		downTime = 0;
		downDelay = 15 - currentLvl;
		moveDelay = 90;
		fallDelay = 960 - 60*currentLvl;

	}

	private void initializeBoard(){
		String image = "img/empty.png";

		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				board[i][j] = new Block(getImage(image), i*size + boardLocX, j*size + boardLocY - 3*size);
			}
		}
		
	}
	
	private void setHandicap() {
		handicap = handicapLvl * 3;
		for(int i = height - handicap; i < height; i++)
			for(int j = 0; j < width; j++) {
				board[j][i].setOccupied(true);
				board[j][i].setImage(getImage("img/occupied_block.png"));
			}
	}

	@Override
	public void render(Graphics2D gd) {
		switch(currentScreen) {
			case GAME_SCREEN:
				gd.setColor(Color.black);
				gd.fillRect(0, 0, getWidth(), getHeight());
		
				gd.setColor(Color.white);
				
				String boardImg = "img/board_classic.png";
				if(extreme)
					boardImg = "img/board_extreme.png";
				gd.drawImage(getImage(boardImg), boardLocX - 126, boardLocY - 15, null);
		
				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {
						if(j >= 3)
							board[i][j].render(gd);
					}
				}
				
				if(ghostPiece != null)
					ghostPiece.render(gd);
		
				if(!nextPieces.isEmpty())
					displayNext(gd);
				if(!savedPieces.isEmpty())
					displaySaved(gd);
				if(!nextPieces.isEmpty())
				if(isTopOccupied()){
					gameOver(gd);
				}
				else if(currentPiece != null){
						currentPiece.render(gd);		
				}

				
				scoreFont.drawString(gd, score+"", GameFont.CENTER, 70, 360, 120);
				gameFont.drawString(gd, currentLvl+"", GameFont.CENTER, 70, 450, 120);
				gameFont.drawString(gd, linesToClear+"", GameFont.CENTER, 70, 530, 120);
			
				
				break;
			case MAIN_MENU:
				gd.setColor(Color.black);
				gd.fillRect(0, 0, getWidth(), getHeight());
					
				for(Button b : menuButtons)
					b.render(gd);
				break;
			case GAME_OVER:
				break;
			case PAUSE_SCREEN:
				for(Button b : pauseButtons)
					b.render(gd);
				break;
			case SETTINGS_SCREEN:
				gd.setColor(Color.black);
				gd.fillRect(0, 0, getWidth(), getHeight());
				
				for(Button b : settingsButtons)
					b.render(gd);
				break;
			case CHANCE_SCREEN:
				gd.setColor(Color.black);
				gd.fillRect(0, 0, getWidth(), getHeight());
				
				for(Button b : chanceButtons)
					b.render(gd);
				break;
			default:
				break;
		}
	}

	@Override
	public void update(long time) {
		switch(currentScreen) {
			case GAME_SCREEN:
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
				getInput(time);
				break;
			case MAIN_MENU:
				getMainMenuInput();
				break;
			case GAME_OVER:
				break;
			case PAUSE_SCREEN:
				getPauseMenuInput();
				break;
			case SETTINGS_SCREEN:
				getSettingScreenInput();
				break;
			case CHANCE_SCREEN:
				getChanceScreenInput();
				break;
			default:
				break;
		}
	}

	private void checkLine() {
		boolean lineComplete = true;
		int lines = 0;
		for(int i = 0; i < height - handicap; i++) {
			for(int j = 0; j < width; j++) {
				if(!board[j][i].isOccupied())
					lineComplete = false;
			}
			if(lineComplete) {
				lines++;
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
		if(lines > 0) {
			linesToClear -= lines;
			if(linesToClear <= 0) {
				currentLvl++;
				fallDelay = 960 - 60*currentLvl;
				if(currentLvl == 15) {
					// TODO: Game over
				}
				linesToClear = currentLvl * 5;
			}
			score += lines * 200 + currentLvl * 50;
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
			setGhostPiece();
		}
		if(fallTime >= fallDelay) {

			if(currentPiece.getY() < 0){
				while(currentPiece.getY() < 0)
					currentPiece.moveDown(speed);
				fallTime -= fallDelay;
			}
			else{
				if(!checkCollision(GameFrame.Direction.Down, currentPiece)){
					currentPiece.moveDown(speed);
				}
				else{
					for(int i = 0; i < currentPiece.row; i++)
						for(int j = 0; j < currentPiece.col; j++)
							if(currentPiece.matrix[i][j].isOccupied())
								board[currentPiece.getX() + j][currentPiece.getY() + i] = currentPiece.matrix[i][j];
					saveCount = 0;
					spawn = true;
				}
				fallTime -= fallDelay;
			}
		}
	}

	public boolean isTopOccupied(){
		
		for(int i = 0; i < currentPiece.getRow(); i++){
			for(int j = 0; j < currentPiece.getCol(); j++){
				if(currentPiece.getY() < 3 && currentPiece.getX() + j >= 0 && currentPiece.getX() + j < width){
					if(board[currentPiece.getX() + j][currentPiece.getY() + 1].isOccupied()){
						return true;
					}
				}
			}
		}
		return false;
	}

	//temporary lang to 
	private void gameOver(Graphics2D gd){
		spawn = false;
		Font big = new Font("Berlin Sans FB Demi", Font.BOLD, 60);

		gd.setColor(Color.black);
		gd.fillRect(0, 0, getWidth(), getHeight());
		gd.setColor(Color.yellow);
		gd.setFont(big);
		gd.drawString("Game Over", getWidth()/2 - 150, getHeight()/2);
	}

	private void getInput(long time) {
		/*
		 * The piece wont move if the key is pressed very fast (if we only use keyDown) (because of the delay in keyDown)
		 */
		if(keyPressed(KeyEvent.VK_LEFT)) {
			moveTime = 0;
			if(currentPiece.getY() >= 0){
				if(!checkCollision(Direction.Left, currentPiece)){
					currentPiece.moveLeft();
					moveGhostPiece();
				}
			}
		}
		else if(keyPressed(KeyEvent.VK_RIGHT)) {
			moveTime = 0;
			if(currentPiece.getY() >= 0){
				if(!checkCollision(Direction.Right, currentPiece)){
					currentPiece.moveRight();
					moveGhostPiece();
				}
			}
		}
		
		
		if(keyDown(KeyEvent.VK_LEFT)) {
			if(moveTime >= moveDelay) {
				if(currentPiece.getY() >= 0){
					if(!checkCollision(Direction.Left, currentPiece)){
						currentPiece.moveLeft();
						moveGhostPiece();
					}
				}
				moveTime = 0;
			}
			moveTime += time;
		}
		else if(keyDown(KeyEvent.VK_RIGHT)) {
			if(moveTime >= moveDelay) {
				if(currentPiece.getY() >= 0){
					if(!checkCollision(Direction.Right, currentPiece)){
						currentPiece.moveRight();
						moveGhostPiece();
					}
				}
				moveTime = 0;
			}
			moveTime += time;
		}
		
		if(keyDown(KeyEvent.VK_DOWN)) {
			if(downTime >= downDelay){
				if(currentPiece.getY() >= 0){
					if(!checkCollision(Direction.Down, currentPiece)) {
						currentPiece.moveDown(speed);
						score++;
						fallTime = 0;
					}
				}
				downTime = 0;
			}
			downTime += time;
		}
		
		if(keyPressed(KeyEvent.VK_SPACE)) {
			playSound(quickDropSound);
			currentPiece.setLocation(ghostPiece.getX(), ghostPiece.getY());
			score += quickDropScore;
			fallTime = 999;
		}else if(keyPressed(KeyEvent.VK_R)) {
			//	initResources();
		}
		if(keyPressed(KeyEvent.VK_SHIFT) || keyPressed(KeyEvent.VK_C)){
			if(!extreme) {
				if(saveCount < 3){
					saveCount++;
					if(savedPieces.size() < 3){
						currentPiece.setLocation(3, 0);
						currentPiece.resetOrientation();
						savedPieces.add(currentPiece);
						spawn = true;
				}
					else{
					//	if(saveCount < 2){
						Tetrimino piece = savedPieces.get(0);
						savedPieces.remove(0);
						savedPieces.add(currentPiece);
						piece.setLocation(3, 0);
						piece.resetOrientation();
						currentPiece = piece;
						setGhostPiece();
					//	}
					}
				}
			}
		}
		if(keyPressed(KeyEvent.VK_ESCAPE)) {
			currentScreen = Screen.PAUSE_SCREEN;
			fallTime = 0;
		}


		if(keyPressed('Z') || keyPressed(KeyEvent.VK_UP)) {
			if(!checkRotation(Direction.Left)){
				currentPiece.rotateLeft();
				ghostPiece.rotateLeft();
				moveGhostPiece();
			}
		}
		else if(keyPressed('X')) {
			if(!checkRotation(Direction.Right)){
				currentPiece.rotateRight();
				ghostPiece.rotateRight();
				moveGhostPiece();
			}
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

	private void displaySaved(Graphics2D gd){
		int locx = boardLocX - 100;
		int locy = 0;
		for(int i = 0; i < savedPieces.size(); i++){
			locy = boardLocY + 10 + i * 60;
			gd.drawImage(getImage("img/smallpieces/" + savedPieces.get(i).getImageName() + ".png"), locx, locy, null);
		}
	}
	
	public boolean checkRotation(Direction d){
		int row = currentPiece.matrix.length;
		int col = currentPiece.matrix[0].length;
		
		
		switch(d){
		case Left:
			for(int i = 0; i < row; i++){
				for(int j = col - 1, k = 0; j >= 0 && k < col; j--, k++){
					if(currentPiece.getMatrix()[i][k].isOccupied()){
						if(currentPiece.getX() + i >= width || currentPiece.getX() + i < 0)
							return true;
						if(currentPiece.getY() + j >= height || board[currentPiece.getX() + i][currentPiece.getY() + j].isOccupied())
							return true;
					}
				}
			}
			return false;
			
		case Right:
			for(int i = 0, j = row - 1; i < row && j >= 0; i++, j--){
				for(int k = 0; k < col; k++){
					if(currentPiece.getMatrix()[i][k].isOccupied()){
						if(currentPiece.getX() + j >= width || currentPiece.getX() + j < 0)
							return true;
						if(currentPiece.getY() + k >= height || board[currentPiece.getX() + j][currentPiece.getY() + k].isOccupied())
							return true;
					}
				}
			}
			
			return false;
			
		default:
			return false;
		}
		
	}

	public boolean checkCollision(Direction d, Tetrimino currentPiece){
		int row = currentPiece.matrix.length;
		int col = currentPiece.matrix[0].length;
		int i;

		switch(d){
		case Left:
			if(currentPiece.getX() <= 0){
				for(i = 0; i < col; i++){
					for(int j = 0; j < row; j++){
						if(currentPiece.matrix[j][i].isOccupied()){
							if(currentPiece.getX() + i <= 0)
								return true;
							if(currentPiece.getX() + i > 0){
								if(board[currentPiece.getX() + i - 1][currentPiece.getY() + j].isOccupied())
									return true;
							}
						}
					}
				}
				return false;
				
			}
			
			for(i = 0; i < col; i++){
				for(int j = 0; j < row; j++){
					if(currentPiece.matrix[j][i].isOccupied())
						if(board[currentPiece.getX() + i - 1][currentPiece.getY() + j].isOccupied())
							return true;
				}
			}
			return false;

		case Down:
			if(currentPiece.getY() + currentPiece.getRow() >= height){
				for(i = 0; i < col; i++)
					if(currentPiece.matrix[currentPiece.getRow() - 1][i].isOccupied() || currentPiece.getY() + currentPiece.getRow() > height)
						return true;
			}
			for(int j = 0; j < col; j++){
				for(i = 0; i < row; i++)
					if(currentPiece.matrix[i][j].isOccupied()) {
						if(board[currentPiece.getX() + j][currentPiece.getY() + i + 1].isOccupied()){
							return true;
						}
					}
			}
			return false;
		case Right:
			if(currentPiece.getX() >= width - currentPiece.getCol()){
				for(i = col - 1; i >= 0; i--){
					for(int j = 0; j < row; j++){
						if(currentPiece.matrix[j][i].isOccupied()){
							if(currentPiece.getX() + i + 1 >= width)
								return true;
							if(currentPiece.getX() + i < width - currentPiece.getCol()){
								if(board[currentPiece.getX() + i - 1][currentPiece.getY() + j].isOccupied())
									return true;
							}
						}
						
					}
				}
				return false;
			}
			for(i = col - 1; i >= 0; i--){
				for(int j = 0; j < row; j++){
					if(currentPiece.matrix[j][i].isOccupied())
						if(board[currentPiece.getX() + i + 1][currentPiece.getY() + j].isOccupied())
							return true;
				}
			}
			return false;

		}

		return false;
	}
	
	public void setGhostPiece(){
		String img = "img/ghost.png";
		if(currentPiece instanceof HookPiece){
			ghostPiece = new HookPiece(getImage(img), currentPiece.getX(), currentPiece.getY(), boardLocX, boardLocY - 3 * size);
		}
		else if(currentPiece instanceof JPiece){
			ghostPiece = new JPiece(getImage(img), currentPiece.getX(), currentPiece.getY(), boardLocX, boardLocY - 3 * size);
		}
		else if(currentPiece instanceof LinePiece){
			ghostPiece = new LinePiece(getImage(img), currentPiece.getX(), currentPiece.getY(), boardLocX, boardLocY - 3 * size);
		}
		else if(currentPiece instanceof LPiece){
			ghostPiece = new LPiece(getImage(img), currentPiece.getX(), currentPiece.getY(), boardLocX, boardLocY - 3 * size);
		}
		else if(currentPiece instanceof RectanglePiece){
			ghostPiece = new RectanglePiece(getImage(img), currentPiece.getX(), currentPiece.getY(), boardLocX, boardLocY - 3 * size);
		}
		else if(currentPiece instanceof SPiece){
			ghostPiece = new SPiece(getImage(img), currentPiece.getX(), currentPiece.getY(), boardLocX, boardLocY - 3 * size);
		}
		else if(currentPiece instanceof SquarePiece){
			ghostPiece = new SquarePiece(getImage(img), currentPiece.getX(), currentPiece.getY(), boardLocX, boardLocY - 3 * size);
		}
		else if(currentPiece instanceof TPiece){
			ghostPiece = new TPiece(getImage(img), currentPiece.getX(), currentPiece.getY(), boardLocX, boardLocY - 3 * size);
		}
		else if(currentPiece instanceof ZPiece){
			ghostPiece = new ZPiece(getImage(img), currentPiece.getX(), currentPiece.getY(), boardLocX, boardLocY - 3 * size);
		}
		moveGhostPiece();
	}
	
	public void moveGhostPiece(){
		ghostPiece.setLocation(currentPiece.getX(), currentPiece.getY());
		quickDropScore = 0;
		while(!checkCollision(Direction.Down, ghostPiece)) {
			ghostPiece.moveDown(speed);
			quickDropScore++;
		}
	}

	// put every type of tetriminos in availablepieces List
	private void initializePieces(){
		Random rand = new Random();
		availablePieces = new ArrayList<Tetrimino>();
		Tetrimino t;
		int x = 3, y = 1;
		String block = "img/I block.png";
		t = new LinePiece(getImage(block), x, y, boardLocX, boardLocY - 3*size);
		
		if(!extreme){
			availablePieces.add(t);
		}
		else if(rand.nextInt(100) + 1 <= t.getChance()){
			availablePieces.add(t);
		}
		
		block = "img/rectangle block.png";
		t = new RectanglePiece(getImage(block), x, y, boardLocX, boardLocY - 3*size);
		if(rand.nextInt(100) + 1 <= t.getChance() && extreme){
			availablePieces.add(t);
		}
		
		block = "img/hook block.png";
		t = new HookPiece(getImage(block), x, y, boardLocX, boardLocY - 3*size);
		
		if(rand.nextInt(100) + 1 <= t.getChance() && extreme){
			availablePieces.add(t);
		}
		else if(rand.nextInt(100) + 1 <= t.getChance() && extreme){
			availablePieces.add(t);
		}
		
		block = "img/J block.png";
		t = new JPiece(getImage(block), x, y, boardLocX, boardLocY - 3*size);
		
		if(!extreme){
			availablePieces.add(t);
		}
		else if(rand.nextInt(100) + 1 <= t.getChance()){
			availablePieces.add(t);
		}
		
		block = "img/L block.png";
		t = new LPiece(getImage(block), x, y, boardLocX, boardLocY - 3*size);
		if(!extreme){
			availablePieces.add(t);
		}
		else if(rand.nextInt(100) + 1 <= t.getChance()){
			availablePieces.add(t);
		}
		
		block = "img/T block.png";
		t = new TPiece(getImage(block), x, y, boardLocX, boardLocY - 3*size);
		if(!extreme){
			availablePieces.add(t);
		}
		else if(rand.nextInt(100) + 1 <= t.getChance()){
			availablePieces.add(t);
		}
		
		y = 0;

		block = "img/S block.png";
		t = new SPiece(getImage(block), x, y, boardLocX, boardLocY - 3*size);
		if(!extreme){
			availablePieces.add(t);
		}
		else if(rand.nextInt(100) + 1 <= t.getChance()){
			availablePieces.add(t);
		}
		
		block = "img/Z block.png";
		t = new ZPiece(getImage(block), x, y, boardLocX, boardLocY - 3*size);
		if(!extreme){
			availablePieces.add(t);
		}
		else if(rand.nextInt(100) + 1 <= t.getChance()){
			availablePieces.add(t);
		}
		
		block = "img/square Block.png";
		t = new SquarePiece(getImage(block), x + 1, y + 1, boardLocX, boardLocY - 3*size);
		if(!extreme){
			availablePieces.add(t);
		}
		else if(rand.nextInt(100) + 1 <= t.getChance()){
			availablePieces.add(t);
		}
		

	}

	private void getPauseMenuInput() {
		if(click()) {
			int x = getMouseX(), y = getMouseY();
			for(Button b : pauseButtons)
				if(b.contains(x, y))
					if(b.getBtnName().equals("Restart")) {
						initializeBoard();
						initGameState();
						initializePieces();
						currentScreen = Screen.GAME_SCREEN;
					} else if(b.getBtnName().equals("ExitToMainMenu")) {
						initializeBoard();
						initGameState();
						initializePieces();
						currentScreen = Screen.MAIN_MENU;
					} else if(b.getBtnName().equals("Resume")) {
						currentScreen = Screen.GAME_SCREEN;
					}
		}
			
	}

	private void getMainMenuInput() {
		if(click()) {
			int x = getMouseX(), y = getMouseY();
			for(Button b : menuButtons)
				if(b.contains(x, y)) {
					if(b.getBtnName().equals("Start")) {
						currentScreen = Screen.GAME_SCREEN;
					}
					else if(b.getBtnName().equals("Settings")) {
						currentScreen = Screen.SETTINGS_SCREEN;
					} else if(b.getBtnName().equals("Chance")) {
						currentScreen = Screen.CHANCE_SCREEN;

						for(Button btn : chanceButtons) {
							for(Tetrimino t : availablePieces) {
								if(btn.getBtnName().equals(t.imageName)) {
									btn.setLocation(center - 150 + (300 * t.getChance() / 100), btn.getY());
								}
							}
						}
					}
				}
		}
	}
	
	private void getSettingScreenInput() {
		if(click()) {
			int x = getMouseX(), y = getMouseY();
			for(Button b : settingsButtons) {
				if(b.contains(x, y)) {
					if(b.getBtnName().equals("handicap1")) {
						if(handicapLvl == 1) {
							handicapLvl = 0;
							b.setImage(getImage("img/buttons/one2.png"));
						}
						else {
							handicapLvl = 1;
							settingsButtons.get(2).setImage(getImage("img/buttons/two2.png"));
							settingsButtons.get(3).setImage(getImage("img/buttons/three2.png"));
							
							b.setImage(getImage("img/buttons/one2_pressed.png"));
						}
					}
					else if(b.getBtnName().equals("handicap2")) {
						if(handicapLvl == 2) {
							handicapLvl = 0;
							b.setImage(getImage("img/buttons/two2.png"));
						}
						else {
							handicapLvl = 2;
							settingsButtons.get(1).setImage(getImage("img/buttons/one2.png"));
							settingsButtons.get(3).setImage(getImage("img/buttons/three2.png"));
							
							b.setImage(getImage("img/buttons/two2_pressed.png"));
						}
					}
					else if(b.getBtnName().equals("handicap3")) {
						if(handicapLvl == 3) {
							handicapLvl = 0;
							b.setImage(getImage("img/buttons/three2.png"));
						}
						else {
							handicapLvl = 3;
							settingsButtons.get(1).setImage(getImage("img/buttons/one2.png"));
							settingsButtons.get(2).setImage(getImage("img/buttons/two2.png"));
							
							b.setImage(getImage("img/buttons/three2_pressed.png"));
						}
					}
					else if(b.getBtnName().equals("settings_back")) {
						currentScreen = Screen.MAIN_MENU;
					}
					
					if(b.getBtnName().equals("Classic")) {
						extreme = false;
						b.setImage(getImage("img/buttons/classic2_pressed.png"));
						settingsButtons.get(7).setImage(getImage("img/buttons/extreme2.png"));
					}
					else if(b.getBtnName().equals("Extreme")) {
						extreme = true;
						b.setImage(getImage("img/buttons/extreme2_pressed.png"));
						settingsButtons.get(6).setImage(getImage("img/buttons/classic2.png"));
					}
				}
				setHandicap();
			}
				
		}
	}
	


	private void getChanceScreenInput() {
		if(click()) {
			int x = getMouseX(), y = getMouseY();
			for(Button b : chanceButtons)
				if(b.contains(x, y)) {
					if(b.getBtnName().equals("chance_back")) {
						for(Tetrimino t : availablePieces)
							for(Button btn : chanceButtons)
								if(btn.getBtnName().equals(t.getImageName()))
									t.setChance((int) ((btn.getX() - (center - 150)) / 3));
						currentScreen = Screen.MAIN_MENU;
					}else if(b.getBtnName().endsWith(" +")) {
						for(Button btn : chanceButtons)
							if(b.getBtnName().equals(btn.getBtnName() + " +"))
								btn.setLocation(Math.min(btn.getX() + 10, center + 150), btn.getY());
					} else if(b.getBtnName().endsWith(" -")) {
						for(Button btn : chanceButtons)
							if(b.getBtnName().equals(btn.getBtnName() + " -"))
								btn.setLocation(Math.max(btn.getX() - 10, center - 150), btn.getY());
					}
				}
		}
	}
	
}
