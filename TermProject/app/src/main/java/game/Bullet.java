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


    public Bullet(float x, float y, float speed) {
        this.positionX = x;
        this.positionY = y;
        this.speed = speed;
        this.destRect = new RectF();

        //this.gameBitmap = new GameBitmap(R.mipmap.exhaust_frame_01_png_processed);
        this.gameBitmap = new GameBitmap(R.mipmap.spritesheet_png_processed,
                "spritesheet_png_processed.json", "proton_small_png_processed");

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
        this.gameBitmap.draw(canvas, this.positionX, this.positionY);
    }


    @Override
    public void getBoundingRect(RectF rect) {
        this.gameBitmap.getBoundingRect(this.positionX, this.positionY, rect);
    }
}
