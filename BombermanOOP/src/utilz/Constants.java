package utilz;

import main.Game;

public class Constants {

	public static class Directions {
		public static final int UP = 0;
		public static final int DOWN = 4;
		public static final int LEFT = 2;
		public static final int RIGHT = 3;
	}

		public static class ObjectConstants {

		//public static final int BOMB = 0;
		public static final int RED_POTION = 0;
		public static final int BLUE_POTION = 1;
		public static final int BARREL = 2;
		public static final int BOX = 3;

		//public static final int BOMB_VALUE = 15;
		public static final int RED_POTION_VALUE = 15;
		public static final int BLUE_POTION_VALUE = 10;

		public static final int CONTAINER_WIDTH_DEFAULT = 40;
		public static final int CONTAINER_HEIGHT_DEFAULT = 30;
		public static final int CONTAINER_WIDTH = (int) (Game.SCALE * CONTAINER_WIDTH_DEFAULT);
		public static final int CONTAINER_HEIGHT = (int) (Game.SCALE * CONTAINER_HEIGHT_DEFAULT);

		public static final int POTION_WIDTH_DEFAULT = 12;
		public static final int POTION_HEIGHT_DEFAULT = 16;
		public static final int POTION_WIDTH = (int) (Game.SCALE * POTION_WIDTH_DEFAULT);
		public static final int POTION_HEIGHT = (int) (Game.SCALE * POTION_HEIGHT_DEFAULT);


		public static int GetSpriteAmount(int object_type) {
			switch (object_type) {
			//case BOMB:
			//case RED_POTION:
			case BLUE_POTION:
				return 7;
			case BARREL:
			case BOX:
				return 8;
			}
			return 1;
		}
	}

	public static class Ui{
		public static class Buttons{
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int)(B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int)(B_HEIGHT_DEFAULT * Game.SCALE);
		}
		
		public static class PauseButtons{
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int)(SOUND_SIZE_DEFAULT * Game.SCALE);
		}

		public static class UrmButtons{
			public static final int URM_DEFAULT_SIZE = 56;
			public static final int URM_SIZE = (int)(URM_DEFAULT_SIZE * Game.SCALE);
		}

		public static class VolumeButtons{
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215;

			public static final int VOLUME_WIDTH = (int)(VOLUME_DEFAULT_WIDTH * Game.SCALE);
			public static final int VOLUME_HEIGHT = (int)(VOLUME_DEFAULT_HEIGHT * Game.SCALE);
			public static final int SLIDER_WIDTH = (int)(SLIDER_DEFAULT_WIDTH * Game.SCALE);
		}	
	}

	public static class PlayerConstants {
		//public static final int RUNNING = 3;
		public static final int LEFTP = 2;
		public static final int RIGHTP = 3;
		public static final int UPP = 0;
		public static final int IDLE = 4;
		public static final int DOWNP = 1;
		public static final int DEAD = 4;
		//public static final int JUMP = 2;
		//public static final int FALLING = 3;
		//public static final int GROUND = 4;
		//public static final int HIT = 5;
		//public static final int ATTACK_1 = 6;
		//public static final int ATTACK_JUMP_1 = 7;
		//public static final int ATTACK_JUMP_2 = 8;

		public static int GetSpriteAmount(int player_action) {
			switch (player_action) {
			
			case LEFTP:
			case RIGHTP:
			case UPP:
			case DOWNP:
			case DEAD:
				return 4;
			//case IDLE:
			//	return 1;
			//case JUMP:
			//case ATTACK_1:
			//case ATTACK_JUMP_1:
			//case ATTACK_JUMP_2:
			//	return 3;
			//case GROUND:
			//	return 2;
			//case FALLING:
			default:
				return 1;
			}
		}


	}
}
