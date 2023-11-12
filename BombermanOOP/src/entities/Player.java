package entities;

//import static main.Game.SCALE;
import static utilz.Constants.PlayerConstants.*;
//import static utilz.Constants.Directions.*;
import static utilz.HelpMethods.*;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

public class Player extends Entity {
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 25;
	private int playerAction = IDLE;
	private boolean moving = false;
	private boolean left, up, right, down;
	private float playerSpeed = 1.0f * Game.SCALE;
	private int[][] lvlData;
	//private float xDrawOffset = 21 * Game.SCALE;
	//private float yDrawOffset = 4 * Game.SCALE;

	private Playing playing;

	public Player(float x, float y, int width, int height, Playing playing) {
		super(x, y, width, height);
		this.playing = playing;
		this.playerAction = IDLE;
		this.playerSpeed = 1.0f * Game.SCALE;
		loadAnimations();
		initHitbox(32, 32);

	}

	public void setSpawn(Point spawn){
		this.x = spawn.x;
		this.y = spawn.y;
		hitbox.x = x;
		hitbox.y = y;
	}

	public void update() {
		updatePos();
		updateAnimationTick();
		setAnimation();
	}

	public void render(Graphics g, int lvlOffset) {
		//g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x), (int) (hitbox.y),(int)hitbox.width, (int)hitbox.height, null);

		g.drawImage(animations[playerAction][aniIndex], (int)(hitbox.x) - lvlOffset, (int)(hitbox.y), (int)width , (int)height, null);
		//drawHitbox(g, lvlOffset);
	}

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
				//attacking = false;
				//attackChecked = false;
			}
			if(playerAction == IDLE)
				resetAniTick();
		}
	}

	private void setAnimation() {
		int startAni = playerAction;

		if (moving){
			if(isLeft())
				playerAction = LEFTP;
			else if(isRight())
				playerAction = RIGHTP;
			else if(isUp())
				playerAction = UPP;
			else if(isDown())
				playerAction = DOWNP;
		}
		else
			playerAction = IDLE;

		if (startAni != playerAction)
			resetAniTick();
	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePos() {
		moving = false;
		if (!left && !right && !up && !down)
			return;

		float xSpeed = 0, ySpeed = 0;

		if (left)
			xSpeed -= playerSpeed;
		else if (right)
			xSpeed += playerSpeed;

		if(up && left)
			return;
		else if(up && right)
			return;
		
		if(down && left)
			return;
		else if(down && right)
			return;

		if(up && down)
			return;

		if (up)
			ySpeed -= playerSpeed;
		if (down)
			ySpeed += playerSpeed;

		//if (CanMoveHere(x + xSpeed, y + ySpeed, width, height, lvlData)) {
		//	this.x += xSpeed;
		//	this.y += ySpeed;
		//	moving = true;
		//}

		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += xSpeed;
			hitbox.y += ySpeed;
			moving = true;
		}

	}

	private void loadAnimations() {

		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

		animations = new BufferedImage[5][4];
		for (int j = 0; j < animations.length; j++)
			for (int i = 0; i < animations[j].length; i++)
				animations[j][i] = img.getSubimage(i * 32, j * 48, 32, 48);

	}

	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
	}

	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public int[][] getLvlData() {
		return lvlData;
	}

	public void resetAll() {
		resetDirBooleans();
		moving = false;
		playerAction = IDLE;

		hitbox.x = x;
		hitbox.y = y;
    }
	
}
