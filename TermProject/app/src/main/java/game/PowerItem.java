package game;

import java.util.Random;

import framework.GameBitmap;
import framework.GameObject;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class PowerItem extends ConstantMovingObject {

    public boolean canDivide = true;
    private static final Random random = new Random();
    public PowerItem() {
        this.gameBitmap = new GameBitmap(R.mipmap.spritesheet_png_processed,
                "spritesheet.json", "power_item");

        setPositionX(GameView.view.getWidth() / 2.0f);
        setSpeedX(100.0f);
        setSpeedY(800.0f);
    }

    @Override
    public void onHit(GameObject object) {
        MainGame game = MainGame.get();
        if(object.getClass() == Player.class) {
            game.remove(this);
        }
    }
}

