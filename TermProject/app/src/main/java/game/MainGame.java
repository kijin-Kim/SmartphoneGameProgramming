package game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import framework.GameObject;
import ui.view.GameView;
import utils.CollisionHelper;

public class MainGame {
    private static final String TAG = MainGame.class.getSimpleName();
    private static MainGame instance;

    public float frameTime;
    private Player player;

    private static HashMap<Class, ArrayList<GameObject>> gameObjects;
    private static HashMap<Class, ArrayList<GameObject>> recycleObjects;


    public static MainGame get() {
        if (instance == null) {
            instance = new MainGame();
        }
        return instance;
    }

    public void initialize() {
        Log.d(TAG, "initialize Called");
        int width = GameView.view.getWidth();
        int height = GameView.view.getHeight();

        gameObjects = new HashMap<>();
        recycleObjects = new HashMap<>();

        player = spawn(Player.class);
        player.setPositionX(width / 2.0f);
        player.setPositionY(height - 300);

        spawn(Background.class);
        spawn(ObstacleSpawner.class);

    }

    public void update() {
        player.update();


        for (Map.Entry<Class, ArrayList<GameObject>> entries : gameObjects.entrySet()) {
            for (GameObject gameObject : entries.getValue()) {
                gameObject.update();
            }
        }

        handleCollision();

    }

    public void draw(Canvas canvas) {
        player.draw(canvas);
        for (Map.Entry<Class, ArrayList<GameObject>> entries : gameObjects.entrySet()) {
            for (GameObject gameObject : entries.getValue()) {
                gameObject.draw(canvas);
            }
        }

        BitmapRenderer.get().Execute();
    }

    public <T extends GameObject> T spawn(Class objectClass) {
        T newInstance = null;
        ArrayList<GameObject> recycleObjectArrayList = recycleObjects.get(objectClass);
        if (recycleObjectArrayList == null || recycleObjectArrayList.isEmpty()) {
            try {
                newInstance = (T) objectClass.newInstance();
                add(newInstance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "Object Recycled");
            newInstance = (T) recycleObjectArrayList.remove(0);
            add(newInstance);
        }


        return newInstance;
    }


    private void add(GameObject object) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> gameObjectArrayList = MainGame.gameObjects.get(object.getClass());
                if (gameObjectArrayList == null) {
                    gameObjectArrayList = new ArrayList<>();
                    gameObjects.put(object.getClass(), gameObjectArrayList);
                }
                gameObjectArrayList.add(object);
            }
        });
    }

    public void remove(GameObject object) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                boolean removed = gameObjects.get(object.getClass()).remove(object);
                if (removed) {
                    ArrayList<GameObject> recyleObjectList = recycleObjects.get(object.getClass());
                    if (recyleObjectList == null) {
                        recyleObjectList = new ArrayList<>();
                        recycleObjects.put(object.getClass(), recyleObjectList);
                    }
                    recyleObjectList.add(object);
                }


            }
        });
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            this.player.moveTo(event.getX(), event.getY());
            return true;
        }

        return false;
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

    public Player getPlayer() {
        return player;
    }
}
