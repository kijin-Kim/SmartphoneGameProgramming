package game;

import android.graphics.Canvas;
import android.util.Log;

public class MainGame {
    private static final String TAG = MainGame.class.getSimpleName();
    private static MainGame instance;

    public static MainGame get() {
        if (instance == null) {
            instance = new MainGame();
        }
        return instance;
    }

    public void update() {
        Log.d(TAG, "Game Update Called");
    }

    public void draw(Canvas canvas) {
        Log.d(TAG, "Game Draw Called");
    }

}
