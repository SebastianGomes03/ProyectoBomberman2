package utilz;

//import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

//import main.Game;

public class LoadSave {

	public static final String PLAYER_ATLAS = "bomber2.png";
	//public static final String PLAYER_ATLAS = "player_sprites.png";
	public static final String LEVEL_ATLAS = "outside.png";
	public static final String LEVEL_ONE_DATA = "level_one_data.png";
	public static final String ICON = "icon.png";
	public static final String IDLEP = "p1.png";
	public static final String BACKGROUND = "playing_bg.png";
	public static final String BOMB = "bomb.png";
	public static final String EXPLOSION = "explosion.png";
	public static final String CONTAINER = "softWall.png";
	public static final String BOMB_PIERCE = "bomb_pierce.png";
	public static final String POTION_ATLAS = "potions_sprites.png";
	public static final String CONTAINER_ATLAS = "objects_sprites.png";
	public static final String MENU_BUTTONS = "button_atlas.png";
	//public static final String MENU_BACKGROUND = "menu_background.png";
	public static final String PAUSE_BACKGROUND = "pause_menu.png";
	public static final String SOUND_BUTTONS = "sound_button.png";
	public static final String URM_BUTTONS = "urm_buttons.png";
	public static final String VOLUME_BUTTONS = "volume_buttons.png";
	public static final String MENU_BACKGROUND_IMG = "bg1.png";

	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

//	public static int[][] GetLevelData() {
//		int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
//		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
//
//		for (int j = 0; j < img.getHeight(); j++)
//			for (int i = 0; i < img.getWidth(); i++) {
//				Color color = new Color(img.getRGB(i, j));
//				int value = color.getRed();
//				if (value >= 48)
//					value = 0;
//				lvlData[j][i] = value;
//			}
//		return lvlData;
//
//	}

	public static BufferedImage[] GetAllLevels() {
		URL url = LoadSave.class.getResource("/lvls");
		File file = null;

		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		File[] files = file.listFiles();
		File[] filesSorted = new File[files.length];

		for(int i = 0; i < filesSorted.length; i++)
			for(int j = 0; j < files.length; j++){
				if(files[j].getName().equals((i + 1) + ".png"))
					filesSorted[i] = files[j];
			}

		BufferedImage[] imgs = new BufferedImage[filesSorted.length];

		for(int i = 0;  i < imgs.length; i++)
			try {
				imgs[i] = ImageIO.read(filesSorted[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}

		return imgs;
	}
}
