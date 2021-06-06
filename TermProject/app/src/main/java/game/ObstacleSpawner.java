package game;

import android.graphics.Canvas;
import android.graphics.RadialGradient;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Random;

import framework.GameBitmap;
import framework.GameObject;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class ObstacleSpawner implements GameObject {

    private static final float spawnDelay = 2.0f;
    private static float spawnElapsedTime = 0.0f;
    private static ArrayList<EnemyColor> colors;
    private static Random random = new Random();

    private int bound = 3;
    enum EnemyColor {
        Green, Teal, Red
    }


    public ObstacleSpawner() {
        colors = new ArrayList<>();
        colors.add(EnemyColor.Green);
        colors.add(EnemyColor.Teal);
        colors.add(EnemyColor.Red);
    }

    @Override
    public void update() {
        spawn();
    }

    private void spawn() {
        MainGame game = MainGame.get();
        spawnElapsedTime += game.frameTime;

        if (spawnDelay <= spawnElapsedTime) {
            spawnElapsedTime = 0.0f;

            EnemyColor slowColor = EnemyColor.Green;
            EnemyColor fastColor = EnemyColor.Green;

            if (game.gameTime > 20 && game.gameTime <= 40) {
                slowColor = EnemyColor.Teal;
                fastColor = EnemyColor.Teal;

            } else if (game.gameTime > 60) {
                slowColor = EnemyColor.Red;
                fastColor = EnemyColor.Red;
            }


            int randNum = random.nextInt(12);

            switch (randNum) {
                case 0:
                case 1:
                case 2:
                case 3:
                    spawnFastEnemy(fastColor);
                    break;
                case 4:
                case 5:
                case 6:
                    spawnSlowEnemy(slowColor);
                case 7:
                case 8:
                    spawnAsteroid();
                    break;
                case 9:
                    PowerItem powerItem = game.spawn(PowerItem.class);
                    powerItem.setPositionY(0);
                    break;
                case 10:
                    HealthItem healthItem = game.spawn(HealthItem.class);
                    healthItem.setPositionY(0);
                    break;
                case 11:
                    bound += 1;
                    break;

            }


        }


    }

    @Override
    public void draw(Canvas canvas) {
    }

    public void spawnAsteroid() {
        int count = random.nextInt(bound);



        MainGame game = MainGame.get();

        for (int i = 0; i < count; i++) {
            int x = random.nextInt(GameView.view.getWidth() - 100);
            x += 100;
            Asteroid asteroid = game.spawn(Asteroid.class);
            asteroid.setPositionY(0);
            asteroid.setPositionX(x);
        }

    }

    public void spawnSlowEnemy(EnemyColor color) {

        int count = random.nextInt(bound);

        MainGame game = MainGame.get();

        for (int i = 0; i < count; i++) {
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

            int x = random.nextInt(GameView.view.getWidth() - 100);
            x += 100;
            newEnemy.setPositionX(x);
        }


    }

    public void spawnFastEnemy(EnemyColor color) {

        int count = random.nextInt(bound);
        MainGame game = MainGame.get();

        for (int i = 0; i < count; i++) {
            FastEnemy newEnemy = game.spawn(FastEnemy.class);
            newEnemy.setPositionY(0);

            switch (color) {
                case Green:
                    newEnemy.setGameBitmap(new GameBitmap(R.mipmap.spritesheet_png_processed,
                            "spritesheet.json", "fast_enemy_green"));
                    newEnemy.setSpeedY(500f);
                    break;
                case Teal:
                    newEnemy.setGameBitmap(new GameBitmap(R.mipmap.spritesheet_png_processed,
                            "spritesheet.json", "fast_enemy_teal"));
                    newEnemy.setSpeedY(700.0f);
                    break;
                case Red:
                    newEnemy.setGameBitmap(new GameBitmap(R.mipmap.spritesheet_png_processed,
                            "spritesheet.json", "fast_enemy_red"));
                    newEnemy.setSpeedY(900.0f);
                    break;
            }

            int x = random.nextInt(GameView.view.getWidth() - 100);
            x += 100;
            newEnemy.setPositionX(x);
        }


    }


}
