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

    private float speedX;
    private float speedY;

    public Enemy() {
        this.gameBitmap = new GameBitmap(R.mipmap.playerblue_frame_01_png_processed);
        this.positionX = GameView.view.getWidth() / 2.0f;

        this.speedX = 400.0f;
        this.speedY = 100.0f;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();

        // Bounce off screen
        if (this.positionX + this.gameBitmap.getWidth() * this.gameBitmap.getMultiplier() / 2.0f >= GameView.view.getWidth()
                || this.positionX - this.gameBitmap.getWidth() * this.gameBitmap.getMultiplier() / 2.0f <= 0) {
            this.speedX = -this.speedX;
        }

        this.positionX += speedX * game.frameTime;
        this.positionY += speedY * game.frameTime;

        if (this.positionX >= GameView.view.getHeight()) {
            game.remove(this);
        }
    }

    @Override
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
}
