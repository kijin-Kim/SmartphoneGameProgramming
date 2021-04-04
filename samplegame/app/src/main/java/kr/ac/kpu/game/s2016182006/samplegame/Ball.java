package kr.ac.kpu.game.s2016182006.samplegame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

import static android.content.ContentValues.TAG;

public class Ball implements GameObject {



    private float x = 100;
    private float y = 100;
    private float velocityX = 300;
    private float velocityY = 200;

    private Bitmap bitmap;
    private View parentView;

    public Ball(View parentView) {
        this.parentView = parentView;
        Resources res = parentView.getResources();
        this.bitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public float getVelocityX() { return velocityX; }
    public float getVelocityY() { return velocityY; }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public void setVelocityX(float velocityX) { this.velocityX = velocityX; }
    public void setVelocityY(float velocityY) { this.velocityY = velocityY; }


    @Override
    public void update(float frameTime) {
        x += velocityX * frameTime;
        y += velocityY * frameTime;

        if(x <= 0 || x > parentView.getWidth() - bitmap.getWidth())  {
            velocityX = -velocityX;
        }
        if(y <= 0 || y > parentView.getHeight() - bitmap.getHeight()) {
            velocityY = -velocityY;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x, y, null);
    }
}
