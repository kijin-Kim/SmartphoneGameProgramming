package game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;

import framework.BoxCollidable;
import framework.GameBitmap;
import framework.GameLayer;
import framework.GameObject;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class TitlePlayer extends Player {
    private static final String TAG = TitlePlayer.class.getSimpleName();

    private float positionX;
    private float positionY;
    private float speed;

    private GameBitmap kpuLogoBitmap;

    public TitlePlayer() {
        kpuLogoBitmap = new GameBitmap(R.mipmap.kpu_logo);
        this.positionX = GameView.view.getWidth() / 2.0f;
        this.positionY = GameView.view.getHeight() / 2.0f;
    }

    @Override
    public void draw(Canvas canvas) {
        BitmapRenderer.get().DrawBitmap(canvas, this.kpuLogoBitmap, this.positionX, this.positionY, 1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MainGame.get().pushLayer(new GameLayer());
        return true;
    }
}
