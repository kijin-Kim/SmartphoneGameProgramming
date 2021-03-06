package framework;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import game.BitmapRenderer;
import game.MainGame;
import game.TitlePlayer;
import ui.view.GameView;

public class TitleLayer extends Layer {

    @Override
    public void start() {
        super.start();
        GameView.view.setBackgroundColor(Color.WHITE);
        player = spawn(TitlePlayer.class);
    }

    @Override
    public void draw(Canvas canvas) {
        player.draw(canvas);
    }
}
