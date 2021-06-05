package framework;

import android.graphics.Canvas;
import android.view.MotionEvent;

import game.BitmapRenderer;
import game.MainGame;
import game.TitlePlayer;

public class TitleLayer extends Layer {

    @Override
    public void start() {
        super.start();
        player = spawn(TitlePlayer.class);
    }

    @Override
    public void draw(Canvas canvas) {
        player.draw(canvas);
    }
}
