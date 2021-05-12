package game;

import android.graphics.Canvas;

import framework.GameBitmap;
import framework.GameObject;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class EnemyGenerator implements GameObject {

    private static final float spawnDelay = 2.0f;
    private static float spawnElapsedTime = 0.0f;

    enum EnemyColor {
        Green, Teal, Red
    }


    public EnemyGenerator() {
    }

    @Override
    public void update() {
        spawn();
    }

    private void spawn() {
        MainGame game = MainGame.get();
        spawnElapsedTime += game.frameTime;

        if (spawnDelay > spawnElapsedTime) {
            return;
        }

        spawnElapsedTime = 0.0f;

        spawnSlowEnemy(EnemyColor.Green);
        spawnFastEnemy(EnemyColor.Red);

    }

    @Override
    public void draw(Canvas canvas) {
    }

    public void spawnSlowEnemy(EnemyColor color) {

        MainGame game = MainGame.get();
        Enemy newEnemy = game.spawn(Enemy.class);
        newEnemy.setPositionX(GameView.view.getWidth() / 2.0f);
        newEnemy.setPositionY(0);
        newEnemy.setSpeedX(100.0f);
        newEnemy.setSpeedY(50.0f);

        switch (color) {
            case Green:
                newEnemy.setGameBitmap(new GameBitmap(R.mipmap.spritesheet_png_processed,
                        "spritesheet.json", "enemy01_green"));
                newEnemy.setHealth(3);
                break;
            case Teal:
                newEnemy.setGameBitmap(new GameBitmap(R.mipmap.spritesheet_png_processed,
                        "spritesheet.json", "enemy01_teal"));
                newEnemy.setHealth(5);
                break;
            case Red:
                newEnemy.setGameBitmap(new GameBitmap(R.mipmap.spritesheet_png_processed,
                        "spritesheet.json", "enemy01_red"));
                newEnemy.setHealth(7);
                break;
        }
    }

    public void spawnFastEnemy(EnemyColor color) {
        MainGame game = MainGame.get();
        Enemy newEnemy = game.spawn(Enemy.class);
        newEnemy.setPositionX(GameView.view.getWidth() / 2.0f);
        newEnemy.setPositionY(0);
        newEnemy.setSpeedX(0.0f);
        newEnemy.setHealth(1);

        switch (color) {
            case Green:
                newEnemy.setGameBitmap(new GameBitmap(R.mipmap.spritesheet_png_processed,
                        "spritesheet.json", "enemy01_green"));
                newEnemy.setSpeedY(500f);
                break;
            case Teal:
                newEnemy.setGameBitmap(new GameBitmap(R.mipmap.spritesheet_png_processed,
                        "spritesheet.json", "enemy01_teal"));
                newEnemy.setSpeedY(700.0f);
                break;
            case Red:
                newEnemy.setGameBitmap(new GameBitmap(R.mipmap.spritesheet_png_processed,
                        "spritesheet.json", "enemy01_red"));
                newEnemy.setSpeedY(900.0f);
                break;
        }
    }


}
