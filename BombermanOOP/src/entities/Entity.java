package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import main.Game;

public abstract class Entity {

	protected float x, y;
	protected int width, height;
	protected Rectangle2D.Float hitbox;
	protected int state;
	protected int aniIndex;

	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	protected void drawHitbox(Graphics g, int xLvlOffset){
		//debugging hitbox
		g.setColor(Color.PINK);
		g.drawRect((int)hitbox.x - xLvlOffset, (int)hitbox.y, (int)hitbox.width, (int)hitbox.height);
	}

	protected void initHitbox(int width, int height) {
		hitbox = new Rectangle2D.Float(x, y, (int)(width * Game.SCALE)-2, (int)(height * Game.SCALE));
	}

//	protected void updateHitbox() {
//		hitbox.x = (int) x;
//		hitbox.y = (int) y;
//	}

	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}

	public int getState(){
        return state;
    }

	public int getAniIndex(){
        return aniIndex;
    }
}
