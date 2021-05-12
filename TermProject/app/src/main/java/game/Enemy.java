package game;

import android.graphics.Canvas;
import android.graphics.RectF;

import framework.BoxCollidable;
import framework.GameBitmap;
import framework.GameObject;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class Enemy implements GameObject, BoxCollidable {

    protected GameBitmap gameBitmap;
    protected float positionX;
    protected float positionY;

    protected float speedX;
    protected float speedY;
    protected int health;
    protected Runnable runnable;

    public Enemy() {
        this.health = 1;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();

        if(this.gameBitmap == null) {
            return;
        }

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
        if(this.gameBitmap != null) {
            BitmapRenderer.get().DrawBitmap(canvas, this.gameBitmap, this.positionX, this.positionY, 2);
        }
    }

    @Override
    public void getBoundingRect(RectF rect) {
        if(this.gameBitmap != null) {
            this.gameBitmap.getBoundingRect(this.positionX, this.positionY, rect);
        }
    }

    public void onHit() {
        if(this.health <=0) {
            MainGame.get().remove(this);
        }
        if(this.runnable != null) {
            this.runnable.run();
        }
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public void setGameBitmap(GameBitmap gameBitmap) {
        this.gameBitmap = gameBitmap;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
