package game;

import framework.GameObject;
import ui.view.GameView;

public class FastEnemy extends ConstantMovingObject {

    public FastEnemy() {
        setPositionX(GameView.view.getWidth() / 2.0f);
        setSpeedX(0.0f);
        setHealth(4);
    }

    @Override
    public void onHit(GameObject object) {
        if(object.getClass() == Bullet.class) {
            this.health -= 1;
        }

        if(this.health <=0 || object.getClass() == GamePlayer.class) {
            MainGame.get().remove(this);
        }
    }
}