package game;

import android.graphics.Canvas;
import android.graphics.RectF;

import framework.BoxCollidable;
import framework.GameBitmap;
import framework.GameObject;
import kr.ac.kpu.game.s2016182006.termproject.R;

public class EnemyGenerator implements GameObject {

    private static final float spawnDelay = 2.0f;
    private static float spawnElapsedTime = 0.0f;

    public EnemyGenerator() {
    }

    @Override
    public void update() {
        spawn();
    }

    private void spawn() {
        MainGame game = MainGame.get();
        spawnElapsedTime += game.frameTime;

        if(spawnDelay > spawnElapsedTime) {
            return;
        }

        spawnElapsedTime = 0.0f;

        Enemy newEnemy = game.spawn(Enemy.class);
        newEnemy.setPositionY(0);

    }

    @Override
    public void draw(Canvas canvas) {
    }


}
