package kr.ac.kpu.game.s2016182006.termproject.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    public boolean running = false;

    public static GameView view;


    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        GameView.view = this;
        running = true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSize: " + w + "," + h);
        requestCallback();
    }

    private void update() {
        Log.d(TAG, "GameView Update Called");

    }


    private void requestCallback() {
        invalidate();

        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long time) {
                update();
                requestCallback();
            }
        });
    }

}
