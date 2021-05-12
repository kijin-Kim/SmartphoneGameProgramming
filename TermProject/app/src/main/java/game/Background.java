package game;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.icu.text.TimeZoneFormat;

import framework.GameBitmap;
import framework.GameObject;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class Background implements GameObject {

    private final GameBitmap backgroundBitmap;
    private final Rect srcRect;
    private final RectF destRect;
    private final RectF destRect2;

    private float deltaY;



    public Background() {
        this.backgroundBitmap = new GameBitmap(R.mipmap.background, 4.5f);
        this.srcRect = new Rect();
        this.srcRect.set(0, 0, this.backgroundBitmap.getWidth(), this.backgroundBitmap.getHeight());

        this.destRect = new RectF();
        destRect.set(0,0, GameView.view.getWidth(), this.backgroundBitmap.getHeight() * this.backgroundBitmap.getMultiplier());

        this.destRect2 = new RectF();
        destRect2.set(0, -this.backgroundBitmap.getHeight() * this.backgroundBitmap.getMultiplier(), GameView.view.getWidth(), 0);

    }

    @Override
    public void update() {
        Player player = MainGame.get().getPlayer();
        if(player == null) {
            return;
        }

        float playerSpeed = player.getSpeed();

        destRect.top += playerSpeed;
        destRect.bottom += playerSpeed;

        destRect2.top += playerSpeed;
        destRect2.bottom += playerSpeed;

        if(destRect.top > GameView.view.getHeight()) {
            destRect.top = -this.backgroundBitmap.getHeight() * this.backgroundBitmap.getMultiplier();
            destRect.bottom = 0;
        }

        if(destRect2.top > GameView.view.getHeight()) {
            destRect2.top = -this.backgroundBitmap.getHeight() * this.backgroundBitmap.getMultiplier();
            destRect2.bottom = 0;
        }
    }

    @Override
    public void draw(Canvas canvas) {



        BitmapRenderer.get().DrawBitmap(canvas, this.backgroundBitmap, srcRect, destRect, 0);
        BitmapRenderer.get().DrawBitmap(canvas, this.backgroundBitmap, srcRect, destRect2, 0);
    }
}
