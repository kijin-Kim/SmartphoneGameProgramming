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
        spawn(EnemyGenerator.class);
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
        if(recycleObjectArrayList == null || recycleObjectArrayList.isEmpty()) {
            try {
                newInstance = (T) objectClass.newInstance();
                add(newInstance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "Object Recycled");
            newInstance = (T)recycleObjectArrayList.remove(0);
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
                if(removed) {
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

        ArrayList<GameObject> enemies = gameObjects.get(Enemy.class);
        ArrayList<GameObject> bullets = gameObjects.get(Bullet.class);

        if (enemies != null && !enemies.isEmpty() && bullets != null && !bullets.isEmpty()) {

            // Enemy vs Bullet
            for (GameObject e : enemies) {
                Enemy enemy = (Enemy) e;
                for (GameObject b : bullets) {
                    Bullet bullet = (Bullet) b;
                    if (CollisionHelper.collides(enemy, bullet)) {
                        remove(bullet);
                        remove(enemy);
                    }
                }
            }
        }


    }

    public Player getPlayer() {
        return player;
    }
}
