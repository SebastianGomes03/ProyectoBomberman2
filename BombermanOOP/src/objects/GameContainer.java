package objects;

import static utilz.Constants.ObjectConstants.*;

import main.Game;

public class GameContainer extends GameObject{

    public GameContainer(int x, int y, int objType) {
        super(x, y, objType);
        //initHitbox(32,32 );
        createHitbox();
    }

    private void createHitbox() {

        if(objType == BOX){
            initHitbox(25, 18);
            
            xDrawOffset = (int)(7 * Game.SCALE);
            yDrawOffset = (int)(12 * Game.SCALE);

        }else{
            initHitbox(23, 25);

            xDrawOffset = (int)(8 * Game.SCALE);
            yDrawOffset = (int)(5 * Game.SCALE);
        }

        hitbox.y += yDrawOffset + (int)(2 * Game.SCALE);
        hitbox.x += xDrawOffset / 2;
    }

    public void update(){
        if(doAnimation)
            updateAnimationTick();
    }
}
