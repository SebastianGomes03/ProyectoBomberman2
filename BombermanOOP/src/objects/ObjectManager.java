package objects;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

//import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;
import static utilz.Constants.ObjectConstants.*;
import gamestates.Playing;
import entities.Player;
public class ObjectManager {

    private Player player;
    private Playing playing;
    private BufferedImage[][] potionImgs, containerImgs;
    private ArrayList<Potion> potions;
    private ArrayList<GameContainer> containers;

    public ObjectManager(Playing playing) {
        this.playing = playing;
        loadImgs();
    }

	public void checkObjectTouched(Rectangle2D.Float hitbox) {
		for (Potion p : potions)
			if (p.isActive()) {
				if (hitbox.intersects(p.getHitbox())) {
					p.setActive(false);
					//applyEffectToPlayer(p);
				}
			}
	}
    //public void applyEffectToPlayer(Potion p){
    //    if(p.getObjType() == RED_POTION)
    //        playing.getPlayer().changeHealth(RED_POTION_VALUE);
    //    else
    //        playing.getPlayer().changePower(BLUE_POTION_VALUE);
    //}

    public void checkObjectHit(Rectangle2D.Float hitbox){
        for(GameContainer gc : containers)
            if(gc.isActive() && !gc.doAnimation){
                if(gc.getHitbox( ).intersects(hitbox)){
                    gc.setAnimation(true);
                    int type = 0;
                    if(gc.getObjType() == BOX)
                        type = 1;
                    potions.add(new Potion((int)(gc.getHitbox().x + gc.getHitbox().width / 2), (int)(gc.getHitbox().y -gc.getHitbox().height/2), type));
                    return;
                }
            }
    }

    public void loadObjects(Level newLevel){
        potions = new ArrayList<>(newLevel.getPotions());
        containers = new ArrayList<>(newLevel.getContainers());
    }

    private void loadImgs() {
        BufferedImage potionSprite = LoadSave.GetSpriteAtlas(LoadSave.POTION_ATLAS);
        potionImgs = new BufferedImage[2][7];

        for(int j = 0; j < potionImgs.length; j++)
            for(int i = 0; i < potionImgs[j].length; i++)
                potionImgs[j][i] = potionSprite.getSubimage(i * 12, j * 16, 12, 16);

        BufferedImage containerSprite = LoadSave.GetSpriteAtlas(LoadSave.CONTAINER_ATLAS);
        containerImgs = new BufferedImage[2][8];

        for(int j = 0; j < containerImgs.length; j++)
            for(int i = 0; i < containerImgs[j].length; i++)
                containerImgs[j][i] = containerSprite.getSubimage(i * 40, j * 30, 40, 30);
    }

    public void update(){
        for(Potion p : potions)
            if(p.isActive())
                p.update();
        
        for(GameContainer gc : containers)
            if(gc.isActive())
                gc.update();
    }

    public void draw(Graphics g, int xLvlOffset){
        drawPotions(g, xLvlOffset);
        drawContainers(g, xLvlOffset);
    }

    private void drawContainers(Graphics g, int xLvlOffset) {
        for(GameContainer gc : containers)
            if(gc.isActive()){
                int type = 0;
                if(gc.getObjType() == BOX)
                    type = 1;
                g.drawImage(containerImgs[type][gc.getAniIndex()], (int)(gc.getHitbox().x - gc.getxDrawOffset() - xLvlOffset), (int)(gc.getHitbox().y - gc.getyDrawOffset()), CONTAINER_WIDTH, CONTAINER_HEIGHT, null);
            }
    }

    private void drawPotions(Graphics g, int xLvlOffset) {
        for(Potion p : potions)
            if(p.isActive()){
                int type = 0;
                if(p.getObjType() == RED_POTION)
                    type = 1;
                g.drawImage(potionImgs[type][p.getAniIndex()], (int)(p.getHitbox().x - p.getxDrawOffset() - xLvlOffset), (int)(p.getHitbox().y - p.getyDrawOffset()), POTION_WIDTH, POTION_HEIGHT, null);
            }
    }

    public void resetAllObjects(){

        loadObjects(playing.getLevelManager().getCurrentLevel());

        for(Potion p : potions)
            p.reset();;
        
        for(GameContainer gc : containers)
            gc.reset();
    }

    public boolean checkObjectCollision(Float hitbox, boolean moving) {
        for(GameContainer gc : containers)
            if(gc.isActive() && !gc.doAnimation && moving){
                if(gc.getHitbox( ).intersects(hitbox))
                    return true;        
            }
        return false;
    }
    
}

