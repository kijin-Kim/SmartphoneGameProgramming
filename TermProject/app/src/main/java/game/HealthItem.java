package game;

import java.util.Random;

import framework.GameBitmap;
import framework.GameObject;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class HealthItem extends ConstantMovingObject {

    public boolean canDivide = true;
    private static final Random random = new Random();
    public HealthItem() {
        this.gameBitmap = new GameBitmap(R.mipmap.spritesheet_png_processed,
                "spritesheet.json", "health_item");

        setPositionX(GameView.view.getWidth() / 2.0f);

        setSpeedX(random.nextInt(100) + 100.0f);
        setSpeedY(random.nextInt(300) + 400.0f);
    }

    @Override
    public void onHit(GameObject object) {
        MainGame game = MainGame.get();
        if(object.getClass() == Player.class) {
            game.remove(this);
        }
    }
}

