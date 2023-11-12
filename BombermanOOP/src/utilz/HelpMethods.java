package utilz;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import main.Game;

public class HelpMethods {

	public static boolean CanMoveHere(float x, float y, float width, float heigth, int[][] lvlData){
		if(!IsSolid(x, y, lvlData))
			if(!IsSolid(x + width, y + heigth, lvlData))
				if(!IsSolid(x + width, y, lvlData))
					if(!IsSolid(x, y + heigth, lvlData))
							if(!IsSolid(x, y + heigth/2, lvlData))
								if(!IsSolid(x + width, y + heigth/2, lvlData))
									return true;
		return false;
    }

    private static boolean IsSolid(float x, float y, int[][] lvlData){
        int maxWidth = lvlData[0].length * Game.TILES_SIZE;
        if(x < 0 || x >= maxWidth)
            return true;
        if (y < 0 || y >= Game.GAME_HEIGHT)
            return true;

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        return IsTileSolid((int)xIndex, (int)yIndex, lvlData);
    }

    public static boolean IsTileSolid(int xTile, int yTile, int[][] lvlData){
        int value = lvlData[yTile][(xTile)];

		
        if(value >= 48 || value < 0 || value != 11)
			return true;
        return false;
    }
	
    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed){
        int currentTile = (int)(hitbox.x / Game.TILES_SIZE);
        if(xSpeed > 0){
            //right
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int)(Game.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1;
        }else
            //left
            return currentTile * Game.TILES_SIZE;
    }  

	public static float GetEntityYPosNextToWall(Rectangle2D.Float hitbox, float ySpeed){
		int currentTile = (int)(hitbox.y / Game.TILES_SIZE);
		if(ySpeed > 0){
			//down
			int tileYPos = currentTile * Game.TILES_SIZE;
			int yOffset = (int)(Game.TILES_SIZE - hitbox.height);
			return tileYPos + yOffset - 1;
		}else
			//up
			return currentTile * Game.TILES_SIZE;
	}

	public static int[][] GetLevelData(BufferedImage img) {
		int[][] lvlData = new int[img.getHeight()][img.getWidth()];
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
				if (value >= 48)
					value = 0;
				lvlData[j][i] = value;
			}
		return lvlData;
	}

	public static Point GetPlayerSpawn(BufferedImage img){
        for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if (value == 100)
					return new Point(i * Game.TILES_SIZE, j * Game.TILES_SIZE);
			}
        return new Point(1 * Game.TILES_SIZE, 1 * Game.TILES_SIZE);
    }
}

