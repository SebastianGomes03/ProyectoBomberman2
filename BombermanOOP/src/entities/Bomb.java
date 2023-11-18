package entities;



import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import entities.Player;
import gamestates.Playing;
import main.Game;

public class Bomb extends Entity{
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 25;
    private Player player;
    
    Playing playing;

    public Bomb(float x, float y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
    }

}
