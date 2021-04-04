package kr.ac.kpu.game.s2016182006.samplegame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class Player implements GameObject {



    private float x = 100;
    private float y = 100;
    private float velocityX = 300;
    private float velocityY = 200;

    private Bitmap bitmap;
    private View parentView;

    public Player(View parentView) {
        this.parentView = parentView;
        Resources res = parentView.getResources();
        this.bitmap = BitmapFactory.decodeResource(res, R.mipmap.plane_240);
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

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x, y, null);
    }
}
