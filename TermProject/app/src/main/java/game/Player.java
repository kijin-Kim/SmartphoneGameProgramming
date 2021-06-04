package game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.nfc.cardemulation.OffHostApduService;
import android.view.MotionEvent;

import framework.BoxCollidable;
import framework.GameBitmap;
import framework.GameObject;
import framework.Sound;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class Player implements GameObject, BoxCollidable {
    private static final String TAG = Player.class.getSimpleName();

    private float positionX;
    private float positionY;
    private float speed;


    public Player() {

    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
    }

    @Override
    public void getBoundingRect(RectF rect) {
    }

    @Override
    public void onHit(GameObject object) {
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

    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
