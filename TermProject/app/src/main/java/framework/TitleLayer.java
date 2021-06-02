package framework;

import android.view.MotionEvent;

import game.MainGame;

public class TitleLayer extends Layer {

    @Override
    public void start() {
        super.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MainGame.get().pushLayer(new GameLayer());
        return true;
    }
}
