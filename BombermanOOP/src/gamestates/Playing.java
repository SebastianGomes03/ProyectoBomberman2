package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import entities.Player;
import levels.LevelManager;
import main.Game;
import objects.ObjectManager;

import ui.PauseOverlay;
import utilz.LoadSave;

public class Playing extends State implements Statemethods {
	private Player player;
	private LevelManager levelManager;
	private ObjectManager objectManager;
	private PauseOverlay pauseOverlay;
	private boolean paused = false;
	
	private int xLvlOffset;
	private int leftBorder = (int)(0.2 * Game.GAME_WIDTH);
	private int rightBorder = (int)(0.8 * Game.GAME_WIDTH);
	private int maxLvlOffsetX;

    private boolean gameOver = false;

	private BufferedImage backgroundImg;
	private Random rnd = new Random();

	public Playing(Game game) {
		super(game);
		initClasses();
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.BACKGROUND);
		loadStartLevel();
	}

	public void loadNextLevel(){
		resetAll();
		levelManager.loadNextLevel();
		player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());
	}

	private void loadStartLevel() {
		objectManager.loadObjects(levelManager.getCurrentLevel());
	}

	private void initClasses() {
		levelManager = new LevelManager(game);
		objectManager = new ObjectManager(this);

		player = new Player(200, 200, (int) (32 * Game.SCALE), (int) (48 * Game.SCALE), this);
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
		player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());
		pauseOverlay = new PauseOverlay(this);
	}

	@Override
	public void update() {
		if(paused){
			pauseOverlay.update();
		}else{
			levelManager.update();
			objectManager.update();
			player.update();
			checkCloseToBorder();
		}
	}

	private void checkCloseToBorder() {
		int playerX = (int)player.getHitbox().x;
		int diff = playerX - xLvlOffset;

		if(diff > rightBorder)
			xLvlOffset += diff - rightBorder;
		else if (diff < leftBorder)
			xLvlOffset += diff - leftBorder;

		if(xLvlOffset > maxLvlOffsetX)
			xLvlOffset = maxLvlOffsetX;
		else if(xLvlOffset < 0)
			xLvlOffset = 0;
	}

	@Override
	public void draw(Graphics g) {
		
		g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);

		levelManager.draw(g, xLvlOffset);
		player.render(g, xLvlOffset);
		objectManager.draw(g, xLvlOffset);

		if (paused){
			g.setColor(new Color(0,0,0,150));
			g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
			pauseOverlay.draw(g);
	    }
    }

	public void resetAll(){
		//reset playing enemy, lvl, etc
		gameOver = false;
		paused = false;
		player.resetAll();
		objectManager.resetAllObjects();
	}

	public void setGameOver(boolean gameOver){
		this.gameOver = gameOver;
	}

	public void checkObjectHit(Rectangle2D.Float attackBox){
		objectManager.checkObjectHit(attackBox);
	}




	@Override
	public void mouseClicked(MouseEvent e) {
		if(!gameOver)
			if (e.getButton() == MouseEvent.BUTTON1){}
				//player.setAttacking(true);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
        //if(gameOver)
        //gameOverOverlay.keyPressed(e);
		//else
			switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				player.setLeft(true);
				break;
			case KeyEvent.VK_D:
				player.setRight(true);
				break;
			case KeyEvent.VK_W:
				player.setUp(true);
				break;
			case KeyEvent.VK_S:
				player.setDown(true);
				break;
			//case KeyEvent.VK_SPACE:
			//	player.setJump(true);
			//	break;
			case KeyEvent.VK_ESCAPE:
				paused = !paused;
				break;
			}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(!gameOver)
			switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				player.setLeft(false);
				break;
			case KeyEvent.VK_D:
				player.setRight(false);
				break;
			case KeyEvent.VK_W:
				player.setUp(false);
				break;
			case KeyEvent.VK_S:
				player.setDown(false);
				break;
			//case KeyEvent.VK_SPACE:
			//	player.setJump(false);
			//	break;
			}

	}

	public void mouseDragged(MouseEvent e){
		if(!gameOver)
			if(paused)
				pauseOverlay.mouseDragged(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!gameOver){
			if(paused)
				pauseOverlay.mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(!gameOver){
			if(paused)
				pauseOverlay.mouseReleased(e);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(!gameOver){
			if(paused)
				pauseOverlay.mouseMoved(e);
		}
	}

	public void setMaxLvlOffset(int lvlOffset){
		this.maxLvlOffsetX = lvlOffset;
	}

	public void unpauseGame(){
		paused = false;
	}

	public void windowFocusLost() {
		player.resetDirBooleans();
	}

	public Player getPlayer() {
		return player;
	}

	public ObjectManager getObjectManager(){
		return objectManager;
	}

	public LevelManager getLevelManager(){
		return levelManager;
	}
}
