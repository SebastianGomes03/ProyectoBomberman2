package entities;



import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import main.Game;

public class Bomb extends Entity{
    Playing playing;

    public Bomb(float x, float y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
    }

}
