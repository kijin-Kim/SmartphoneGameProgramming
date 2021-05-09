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

public class Bullet implements GameObject, BoxCollidable {

    private GameBitmap gameBitmap;
    private float positionX;
    private float positionY;
    private float speed;

    private RectF destRect;


    public Bullet() {
        this.positionX = 0;
        this.positionY = 0;
        this.speed = 0;
        this.destRect = new RectF();

        //this.gameBitmap = new GameBitmap(R.mipmap.exhaust_frame_01_png_processed);
        this.gameBitmap = new GameBitmap(R.mipmap.spritesheet_png_processed,
                "spritesheet.json", "minigun_small");

    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        this.positionY -= speed * game.frameTime;

        if (this.positionY < 0) {
            game.remove(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        BitmapRenderer.get().DrawBitmap(canvas,this.gameBitmap, this.positionX, this.positionY, 1);
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

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
