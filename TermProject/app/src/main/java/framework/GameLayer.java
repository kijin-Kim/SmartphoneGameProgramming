package framework;


import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import framework.GameObject;
import framework.Layer;
import game.Asteroid;
import game.Background;
import game.BitmapRenderer;
import game.Bullet;
import game.FastEnemy;
import game.GamePlayer;
import game.HealthItem;
import game.MainGame;
import game.ObstacleSpawner;
import game.Player;
import game.PowerItem;
import game.SlowEnemy;
import ui.view.GameView;
import utils.CollisionHelper;

public class GameLayer extends Layer {

    @Override
    public void start() {
        player = spawn(GamePlayer.class);

        int width = GameView.view.getWidth();
        int height = GameView.view.getHeight();


        player.setPositionX(width / 2.0f);
        player.setPositionY(height - 300);

        spawn(Background.class);
        spawn(ObstacleSpawner.class);
    }

    @Override
    public void update() {
        player.update();
        for (Map.Entry<Class, ArrayList<GameObject>> entries : gameObjects.entrySet()) {
            for (GameObject gameObject : entries.getValue()) {
                gameObject.update();
            }
        }

        handleCollision();
    }

    @Override
    public void draw(Canvas canvas) {
        player.draw(canvas);
        for (Map.Entry<Class, ArrayList<GameObject>> entries : gameObjects.entrySet()) {
            for (GameObject gameObject : entries.getValue()) {
                gameObject.draw(canvas);
            }
        }
    }




    private void handleCollision() {

        ArrayList<GameObject> slowEnemies = gameObjects.get(SlowEnemy.class);
        ArrayList<GameObject> bullets = gameObjects.get(Bullet.class);

        if (slowEnemies != null && !slowEnemies.isEmpty() && bullets != null && !bullets.isEmpty()) {
            for (GameObject e : slowEnemies) {
                SlowEnemy enemy = (SlowEnemy) e;
                for (GameObject b : bullets) {
                    Bullet bullet = (Bullet) b;
                    if (CollisionHelper.collides(enemy, bullet)) {
                        bullet.onHit(enemy);
                        enemy.onHit(bullet);
                    }
                }
            }
        }

        ArrayList<GameObject> fastEnemies = gameObjects.get(FastEnemy.class);

        if (fastEnemies != null && !fastEnemies.isEmpty() && bullets != null && !bullets.isEmpty()) {
            for (GameObject e : fastEnemies) {
                FastEnemy enemy = (FastEnemy) e;
                for (GameObject b : bullets) {
                    Bullet bullet = (Bullet) b;
                    if (CollisionHelper.collides(enemy, bullet)) {
                        bullet.onHit(enemy);
                        enemy.onHit(bullet);
                    }
                }
            }
        }

        ArrayList<GameObject> asteroids = gameObjects.get(Asteroid.class);

        if (asteroids != null && !asteroids.isEmpty() && bullets != null && !bullets.isEmpty()) {
            for (GameObject e : asteroids) {
                Asteroid asteroid = (Asteroid) e;
                for (GameObject b : bullets) {
                    Bullet bullet = (Bullet) b;
                    if (CollisionHelper.collides(asteroid, bullet)) {
                        bullet.onHit(asteroid);
                        asteroid.onHit(bullet);
                    }
                }
            }
        }

        if (asteroids != null && !asteroids.isEmpty()) {
            for (GameObject enemy : asteroids) {
                Asteroid asteroid = (Asteroid) enemy;
                if (CollisionHelper.collides(player, asteroid)) {
                    asteroid.onHit(player);
                    player.onHit(asteroid);
                }
            }
        }


        ArrayList<GameObject> healthItems = gameObjects.get(HealthItem.class);

        if (healthItems != null && !healthItems.isEmpty()) {
            for (GameObject item : healthItems) {
                HealthItem healthItem = (HealthItem) item;
                if (CollisionHelper.collides(player, healthItem)) {
                    healthItem.onHit(player);
                    player.onHit(healthItem);
                }
            }
        }

        ArrayList<GameObject> powerItems = gameObjects.get(PowerItem.class);

        if (powerItems != null && !powerItems.isEmpty()) {
            for (GameObject item : powerItems) {
                PowerItem powerItem = (PowerItem) item;
                if (CollisionHelper.collides(player, powerItem)) {
                    powerItem.onHit(player);
                    player.onHit(powerItem);
                }
            }
        }



        if (slowEnemies != null && !slowEnemies.isEmpty()) {
            for (GameObject enemy : slowEnemies) {
                SlowEnemy slowEnemy = (SlowEnemy) enemy;
                if (CollisionHelper.collides(player, slowEnemy)) {
                    slowEnemy.onHit(player);
                    player.onHit(slowEnemy);
                }
            }
        }

        if (fastEnemies != null && !fastEnemies.isEmpty()) {
            for (GameObject enemy : fastEnemies) {
                FastEnemy fastEnemy = (FastEnemy) enemy;
                if (CollisionHelper.collides(player, fastEnemy)) {
                    fastEnemy.onHit(player);
                    player.onHit(fastEnemy);
                }
            }
        }

    }
}
