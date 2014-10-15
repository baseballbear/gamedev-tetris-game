package gamedev.tetris;

import java.awt.Dimension;

import gamedev.tetris.GameFrame;
import com.golden.gamedev.GameLoader;

public class TetrisGame {


	public static GameFrame gameFrame = new GameFrame();
	public static GameLoader game = new GameLoader();
	
	public static void main(String[] args){

		game.setup(gameFrame, new Dimension(640, 640), false);
		game.start();
		
	}

}
