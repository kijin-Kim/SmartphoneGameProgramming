package kr.ac.kpu.game.s2016182006.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    private Bitmap ballBitmap;
    private long lastFrame;
    private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>(1000);

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initResources();
        startUpdating();
    }

    private void startUpdating() {
        doGameFrame(0);
    }

    private void doGameFrame(float frameTime) {


        for (GameObject object :  gameObjects)
            object.update(frameTime);

        invalidate();

        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long time) {
                if (lastFrame == 0) {
                    lastFrame = time;
                }
                float frameTime = (float) (time - lastFrame) / 1_000_000_000;
                doGameFrame(frameTime);
                lastFrame = time;
            }
        });
    }

    private void initResources() {


        final int ballGenerationCount = 100;

        Random random = new Random();
        for(int i = 0; i < ballGenerationCount; i++)  {
            Ball ball = new Ball(this);
            ball.setX(random.nextInt(500));
            ball.setY(random.nextInt(500));
            ball.setVelocityX(random.nextInt(500));
            ball.setVelocityY(random.nextInt(500));
            gameObjects.add(ball);
        }

        Player player =new Player(this);
        player.setX(500);
        player.setY(500);
        gameObjects.add(player);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (GameObject object :  gameObjects)
            object.draw(canvas);
    }
}












