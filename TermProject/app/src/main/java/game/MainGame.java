package game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import androidx.constraintlayout.widget.ConstraintSet;

import ui.view.GameView;

public class MainGame {
    private static final String TAG = MainGame.class.getSimpleName();
    private static MainGame instance;

    public float frameTime;
    private Player player;


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
    }

    public void update() {
        player.update();
    }

    public void draw(Canvas canvas) {
        player.draw(canvas);
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
