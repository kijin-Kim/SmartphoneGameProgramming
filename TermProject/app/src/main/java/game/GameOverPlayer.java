package game;

import android.graphics.Canvas;
import android.icu.text.CaseMap;
import android.view.MotionEvent;

import framework.GameBitmap;
import framework.GameLayer;
import framework.TitleLayer;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class GameOverPlayer extends Player {
    private static final String TAG = GameOverPlayer.class.getSimpleName();

    private float positionX;
    private float positionY;
    private float speed;

    private GameBitmap gameoverBitmap;

    public GameOverPlayer() {
        gameoverBitmap = new GameBitmap(R.mipmap.gameover);
        this.positionX = GameView.view.getWidth() / 2.0f;
        this.positionY = GameView.view.getHeight() / 2.0f;
    }

    @Override
    public void draw(Canvas canvas) {
        BitmapRenderer.get().DrawBitmap(canvas, this.gameoverBitmap, this.positionX, this.positionY, 1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            MainGame game = MainGame.get();
            game.pushLayer(new TitleLayer());
            return true;
        }

        return false;
    }
}
