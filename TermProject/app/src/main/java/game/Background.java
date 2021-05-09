package game;

import android.graphics.Canvas;

import framework.GameBitmap;
import framework.GameObject;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class Background implements GameObject {

    private final GameBitmap backgroundBitmap;

    public Background() {
        this.backgroundBitmap = new GameBitmap(R.mipmap.background);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        this.backgroundBitmap.draw(canvas, GameView.view.getWidth() / 2.0f, GameView.view.getHeight() / 2.0f );
    }
}
