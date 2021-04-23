package game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;

import framework.GameObject;
import ui.view.GameView;

public class MainGame {
    private static final String TAG = MainGame.class.getSimpleName();
    private static MainGame instance;

    public float frameTime;
    private Player player;

    ArrayList<GameObject> gameObjects;

    public static MainGame get() {
        if (instance == null) {
            instance = new MainGame();
        }
        return instance;
    }

    public void initialize() {
        int width = GameView.view.getWidth();
        int height = GameView.view.getHeight();
        player = new Player(width / 2.0f, height - 300);

        gameObjects = new ArrayList<>();
    }

    public void update() {
        player.update();

        for(GameObject object : gameObjects) {
            object.update();
        }
    }

    public void draw(Canvas canvas) {
        player.draw(canvas);

        for(GameObject object : gameObjects) {
            object.draw(canvas);
        }
    }

    public void add(GameObject object) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                gameObjects.add(object);
            }
        });
    }

    public void remove(GameObject object) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                gameObjects.remove(object);
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


}
