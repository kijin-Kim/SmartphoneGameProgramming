package game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import framework.GameObject;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class Player implements GameObject {
    private static final String TAG = Player.class.getSimpleName();

    private float positionX;
    private float positionY;

    private final float speed;


    private float directionX;
    private float directionY;

    private float targetX;
    private float targetY;

    private float lengthX;
    private float lengthY;

    private Bitmap bitmap;
    private final RectF destRect;

    private float lerpt;

    private final float fireDelay;
    private float fireElapsedTime;

    public Player(float x, float y) {
        this.positionX = x;
        this.positionY = y;


        this.targetX = x;
        this.lengthX = 0.0f;


        this.fireDelay = 0.1f;
        this.fireElapsedTime = 0.0f;

        if (this.bitmap == null) {
            Resources resources = GameView.view.getResources();
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inScaled = false;
            this.bitmap = BitmapFactory.decodeResource(resources, R.mipmap.playerblue_frame_01_png_processed, opts);
        }

        this.destRect = new RectF();

        this.speed = 100.0f;


    }

    public void moveTo(float x, float y) {
        this.targetX = x;
        this.lengthX = this.targetX - this.positionX;
        this.lerpt = 0.0f;


    }

    public void Fire() {

        MainGame game = MainGame.get();
        this.fireElapsedTime += game.frameTime;

        if(this.fireDelay >= this.fireElapsedTime) {
            return;
        }

        float fireSocketPositionX = this.positionX;
        float fireSocketPositionY = this.positionY - bitmap.getHeight() / 2.0f * GameView.view.MULTIPLIER;

        this.fireElapsedTime = 0.0f;
        game.add(new Bullet(fireSocketPositionX, fireSocketPositionY, 800.0f));
    }

    public void update() {
        MainGame game = MainGame.get();

        if (this.lengthX != 0.0f) {
            this.lerpt += Math.abs(this.speed / this.lengthX) * game.frameTime;
        }

        this.positionX = lerp(this.positionX, this.targetX, Math.min(this.lerpt, 1.0f));

        Fire();

    }


    float lerp(float a, float b, float t) {
        return a + t * (b - a);
    }

    public void draw(Canvas canvas) {
        if (bitmap == null) {
            return;
        }



        float halfWidth = bitmap.getWidth() / 2.0f * GameView.view.MULTIPLIER;
        float halfHeight = bitmap.getHeight() / 2.0f * GameView.view.MULTIPLIER;
        this.destRect.set(this.positionX - halfWidth, this.positionY - halfHeight, this.positionX + halfWidth, this.positionY + halfHeight);

        canvas.drawBitmap(bitmap, null, this.destRect, null);
    }
}
