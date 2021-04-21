package game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import framework.GameObject;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class Bullet implements GameObject {

    private Bitmap bitmap;
    private float positionX;
    private float positionY;
    private float speed;

    private RectF destRect;


    public Bullet(float x, float y, float speed) {
        this.positionX = x;
        this.positionY = y;
        this.speed = 50.0f;
        this.destRect = new RectF();
        if (this.bitmap == null) {
            Resources resources = GameView.view.getResources();
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inScaled = false;
            this.bitmap = BitmapFactory.decodeResource(resources, R.mipmap.exhaust_frame_01_png_processed, opts);
        }
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        this.positionY -= speed * game.frameTime;
    }

    @Override
    public void draw(Canvas canvas) {
        float halfWidth = bitmap.getWidth() / 2.0f * GameView.view.MULTIPLIER;
        float halfHeight = bitmap.getHeight() / 2.0f * GameView.view.MULTIPLIER;
        this.destRect.set(this.positionX - halfWidth, this.positionY - halfHeight, this.positionX + halfWidth, this.positionY + halfHeight);
        canvas.drawBitmap(this.bitmap, null, destRect, null);
    }
}
