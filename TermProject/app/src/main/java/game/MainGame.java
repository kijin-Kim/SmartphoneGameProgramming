package game;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.DeniedByServerException;
import android.util.Log;
import android.view.MotionEvent;

import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import framework.GameObject;
import ui.view.GameView;
import utils.CollisionHelper;

public class MainGame {
    private static final String TAG = MainGame.class.getSimpleName();
    private static MainGame instance;

    public float frameTime;
    private Player player;

    private static HashMap<Class, ArrayList<GameObject>> gameObjects;

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
        player = new Player(width / 2.0f, height - 300);

        gameObjects = new HashMap<>();

        add(new EnemyGenerator());
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
    }

    public void add(GameObject object) {
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
                gameObjects.get(object.getClass()).remove(object);
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
}
