package game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import framework.BoxCollidable;
import framework.GameBitmap;
import framework.GameObject;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class Player implements GameObject, BoxCollidable {
    private static final String TAG = Player.class.getSimpleName();

    private float positionX;
    private float positionY;

    private final float speed;



    private float targetX;

    private float lengthX;

    private GameBitmap gameBitmap;

    private float lerpt;

    private final float fireDelay;
    private float fireElapsedTime;

    public Player() {
        this.positionX = 0;
        this.positionY = 0;


        this.targetX = 0;
        this.lengthX = 0.0f;


        this.fireDelay = 0.1f;
        this.fireElapsedTime = 0.0f;

        this.gameBitmap = new GameBitmap(R.mipmap.spritesheet_png_processed,
                "spritesheet.json", "playerblue_frame_01");
        this.speed = 10.0f;


    }

    public void moveTo(float x, float y) {
        this.targetX = x;
        this.lengthX = this.targetX - this.positionX;
        this.lerpt = 0.0f;
    }

    public void Fire() {
        MainGame game = MainGame.get();
        this.fireElapsedTime += game.frameTime;

        if (this.fireDelay >= this.fireElapsedTime) {
            return;
        }

        float leftFireSocketPositionX = this.positionX - 16.0f * GameView.view.MULTIPLIER;
        float leftFireSocketPositionY = this.positionY;

        float rightFireSocketPositionX = this.positionX + 16.0f * GameView.view.MULTIPLIER;
        float rightFireSocketPositionY = this.positionY;

        this.fireElapsedTime = 0.0f;


        Bullet leftBullet = game.spawn(Bullet.class);
        leftBullet.setPositionX(leftFireSocketPositionX);
        leftBullet.setPositionY(leftFireSocketPositionY);
        leftBullet.setSpeed(800.0f);

        Bullet rightBullet = game.spawn(Bullet.class);
        rightBullet.setPositionX(rightFireSocketPositionX);
        rightBullet.setPositionY(rightFireSocketPositionY);
        rightBullet.setSpeed(800.0f);

    }

    public void update() {
        MainGame game = MainGame.get();

        if (this.lengthX != 0.0f) {
            this.lerpt += Math.abs(this.speed / this.lengthX) * game.frameTime;
        }

        this.positionX = lerp(this.positionX, this.targetX, Math.min(this.lerpt, 1.0f));

        //Fire();
    }


    float lerp(float a, float b, float t) {
        return a + t * (b - a);
    }

    public void draw(Canvas canvas) {
        BitmapRenderer.get().DrawBitmap(canvas, this.gameBitmap, this.positionX, this.positionY, 2);
    }

    @Override
    public void getBoundingRect(RectF rect) {
        this.gameBitmap.getBoundingRect(this.positionX, this.positionY, rect);
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public float getSpeed() {
        return this.speed;
    }
}
