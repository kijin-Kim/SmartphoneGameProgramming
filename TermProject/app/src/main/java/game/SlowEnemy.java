package game;

import framework.GameObject;

public class SlowEnemy extends ConstantMovingObject {

    public SlowEnemy() {
        setSpeedX(200.0f);
        setSpeedY(100.0f);
    }

    @Override
    public void onHit(GameObject object) {
        if(object.getClass() == Bullet.class) {
            this.health -= 1;
        }

        if(this.health <=0 || object.getClass() == GamePlayer.class) {
            MainGame.get().remove(this);
            GamePlayer player = (GamePlayer)MainGame.get().getPlayer();
            player.addScore();
        }
    }
}
