package game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Debug;
import android.util.Log;

import framework.GameObject;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class Player implements GameObject {
    private static final String TAG = Player.class.getSimpleName();

    private float positionX;
    private float positionY;

    private float speed = 800.0f;

    private float directionX;
    private float directionY;

    private float targetX;
    private float targetY;

    private Bitmap bitmap;
    private Rect srcRect;
    private RectF destRect;

    public Player(float x, float y) {
        this.positionX = x;
        this.positionY = y;

        this.directionX = 0.0f;
        this.directionY = 0.0f;

        if (this.bitmap == null) {
            Resources resources = GameView.view.getResources();
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inScaled = false;
            this.bitmap = BitmapFactory.decodeResource(resources, R.mipmap.playerblue_frame_01_png_processed, opts);
        }

        this.destRect = new RectF();

        this.speed = 10.0f;
    }

    public void moveTo(float x, float y) {
        this.targetX = x;
        this.targetY = y;

        float lengthX = this.targetX - this.positionX;
        float lengthY = this.targetY - this.positionY;

        float magnitude = (float) (Math.sqrt(lengthX * lengthX + lengthY * lengthY));

        this.directionX = lengthX / magnitude;
        this.directionY = lengthY / magnitude;

    }

    public void update() {
        MainGame game = MainGame.get();

        this.positionX += this.speed * this.directionX * game.frameTime;
        this.positionY += this.speed * this.directionY * game.frameTime;

        
    }

    public void draw(Canvas canvas) {
        if (bitmap == null) {
            return;
        }

        float halfWidth = bitmap.getWidth() / 2.0f;
        float halfHeight = bitmap.getHeight() / 2.0f;
        this.destRect.set(this.positionX - halfWidth, this.positionY - halfHeight, this.positionX + halfWidth, this.positionY + halfHeight);

        canvas.drawBitmap(bitmap, null, this.destRect, null);
    }
}
