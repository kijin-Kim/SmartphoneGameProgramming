package framework;

import android.graphics.Canvas;
import android.graphics.Color;

import game.GameOverPlayer;
import game.TitlePlayer;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class GameOverLayer extends Layer {

    @Override
    public void start() {
        super.start();
        GameView.view.setBackgroundColor(Color.BLACK);
        player = spawn(GameOverPlayer.class);
    }

    @Override
    public void draw(Canvas canvas) {
        player.draw(canvas);
    }
}
