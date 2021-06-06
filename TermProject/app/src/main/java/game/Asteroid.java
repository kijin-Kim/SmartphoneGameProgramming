package game;

import java.util.Random;

import framework.GameBitmap;
import framework.GameObject;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class Asteroid extends ConstantMovingObject {

    public boolean canDivide = true;
    private static final Random random = new Random();
    public Asteroid() {
        int rand = random.nextInt(3);
        switch (rand) {
            case 0:
                this.gameBitmap = new GameBitmap(R.mipmap.asteroid_01);
                break;
            case 1:
                this.gameBitmap = new GameBitmap(R.mipmap.asteroid_02);
                break;
            case 2:
                this.gameBitmap = new GameBitmap(R.mipmap.asteroid_03);
                break;
            case 3:
                this.gameBitmap = new GameBitmap(R.mipmap.asteroid_04);
                break;
        }

        setPositionX(GameView.view.getWidth() / 2.0f);

        int randomX = random.nextInt(100);
        int randomY = random.nextInt(100);


        setSpeedX(50.0f + randomX);
        setSpeedY(400.0f + randomY);
    }

    @Override
    public void onHit(GameObject object) {

        MainGame game = MainGame.get();

        if(object.getClass() == Bullet.class) {
            if(this.canDivide) {
                game.remove(this);
                this.canDivide = false;

                Asteroid childAsteroid1 = (Asteroid) game.spawn(Asteroid.class);
                childAsteroid1.setPositionX(this.positionX);
                childAsteroid1.setPositionY(this.positionY);
                childAsteroid1.setSpeedX(this.speedX);
                childAsteroid1.canDivide = false;

                Asteroid childAsteroid2 = (Asteroid) game.spawn(Asteroid.class);
                childAsteroid2.setPositionX(this.positionX);
                childAsteroid2.setPositionY(this.positionY);
                childAsteroid2.setSpeedX(-this.speedX);
                childAsteroid2.canDivide = false;
            }
        } else if(object.getClass() == GamePlayer.class) {
            this.canDivide = true;
            game.remove(this);
            GamePlayer player = (GamePlayer)MainGame.get().getPlayer();
            player.addScore();
        }

    }
}

