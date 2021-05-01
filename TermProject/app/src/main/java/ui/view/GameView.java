package ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.io.InputStream;

import game.MainGame;
import game.Player;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    public boolean running = false;

    public static GameView view;

    private long lastFrame;

    public float MULTIPLIER = 2.0f;


    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        GameView.view = this;
        running = true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        MainGame game = MainGame.get();
        game.initialize();
        requestCallback();
    }

    private void update() {
        MainGame game = MainGame.get();
        game.update();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        MainGame game = MainGame.get();
        game.draw(canvas);
    }


    public boolean onTouchEvent(MotionEvent event) {
        MainGame game = MainGame.get();
        return game.onTouchEvent(event);
    }

    private void requestCallback() {

        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long time) {
                if (lastFrame == 0) {
                    lastFrame = time;
                }

                MainGame game = MainGame.get();
                game.frameTime = (float) (time - lastFrame) / 1_000_000_000;
                update();
                lastFrame = time;
                requestCallback();
            }
        });
    }


}
