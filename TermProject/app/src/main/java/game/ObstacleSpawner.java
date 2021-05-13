package game;

import android.graphics.Canvas;

import framework.GameBitmap;
import framework.GameObject;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class ObstacleSpawner implements GameObject {

    private static final float spawnDelay = 2.0f;
    private static float spawnElapsedTime = 0.0f;

    enum EnemyColor {
        Green, Teal, Red
    }


    public ObstacleSpawner() {
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

//        PowerItem item = game.spawn(PowerItem.class);
//        item.setPositionY(0);


    }

    @Override
    public void draw(Canvas canvas) {
    }

    public void spawnAsteroid() {
        MainGame game = MainGame.get();
        Asteroid asteroid = game.spawn(Asteroid.class);
        asteroid.setPositionY(0);
    }

    public void spawnSlowEnemy(EnemyColor color) {

        MainGame game = MainGame.get();
        SlowEnemy newEnemy = game.spawn(SlowEnemy.class);
        newEnemy.setPositionX(GameView.view.getWidth() / 2.0f);
        newEnemy.setPositionY(0);


        switch (color) {
            case Green:
                newEnemy.setGameBitmap(new GameBitmap(R.mipmap.spritesheet_png_processed,
                        "spritesheet.json", "enemy01_green"));
                newEnemy.setHealth(8);
                break;
            case Teal:
                newEnemy.setGameBitmap(new GameBitmap(R.mipmap.spritesheet_png_processed,
                        "spritesheet.json", "enemy01_teal"));
                newEnemy.setHealth(14);
                break;
            case Red:
                newEnemy.setGameBitmap(new GameBitmap(R.mipmap.spritesheet_png_processed,
                        "spritesheet.json", "enemy01_red"));
                newEnemy.setHealth(20);
                break;
        }
    }

    public void spawnFastEnemy(EnemyColor color) {
        MainGame game = MainGame.get();
        FastEnemy newEnemy = game.spawn(FastEnemy.class);
        newEnemy.setPositionY(0);

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
