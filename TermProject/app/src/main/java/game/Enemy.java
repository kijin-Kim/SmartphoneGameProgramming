package game;

import android.graphics.Canvas;
import android.graphics.RectF;

import framework.BoxCollidable;
import framework.GameBitmap;
import framework.GameObject;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class Enemy implements GameObject, BoxCollidable {

    private GameBitmap gameBitmap;
    private float positionX;
    private float positionY;

    private float speed;

    public Enemy() {
        this.gameBitmap = new GameBitmap(R.mipmap.playerblue_frame_01_png_processed);
        this.positionX = GameView.view.getWidth() / 2.0f;

        this.speed = 800.0f;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        this.positionY += speed * game.frameTime;
    }

    @Override
    public void draw(Canvas canvas) {
        this.gameBitmap.draw(canvas, this.positionX, this.positionY);
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
}
