package game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;

import framework.BoxCollidable;
import framework.GameBitmap;
import framework.GameObject;
import framework.GameOverLayer;
import framework.Sound;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class GamePlayer extends Player {
    private static final String TAG = GamePlayer.class.getSimpleName();
    private int health;




    private float targetX;
    private float targetY;

    private float lengthX;
    private float lengthY;

    private GameBitmap gameBitmap;

    private float lerpt;

    private final float fireDelay;
    private float fireElapsedTime;
    private float bulletSpeed;

    private GameBitmap bulletBitmap;
    private GameBitmap smallBullet;
    private GameBitmap bigBullet;

    private final Score score;

    public GamePlayer() {


        this.smallBullet =new GameBitmap(R.mipmap.spritesheet_png_processed,
                "spritesheet.json", "minigun_small");
        this.bigBullet = new GameBitmap(R.mipmap.spritesheet_png_processed,
                "spritesheet.json", "minigun_big");


        this.bulletBitmap = this.smallBullet;


        this.bulletSpeed = 500.0f;

        this.targetX = 0;
        this.targetY = 0;
        this.lengthX = 0.0f;
        this.lengthY = 0.0f;


        this.fireDelay = 0.5f;
        this.fireElapsedTime = 0.0f;

        this.gameBitmap = new GameBitmap(R.mipmap.spritesheet_png_processed,
                "spritesheet.json", "playerblue_frame_01");
        this.speed = 10.0f;


        this.health = 1;

        MainGame game = MainGame.get();
        this.score = game.spawn(Score.class);
        this.score.setScore(health);
    }

    public void moveTo(float x, float y) {
        this.targetX = x;
        this.targetY = y;
        this.lengthX = Math.abs(this.targetX - this.positionX);
        this.lengthY = Math.abs(this.targetY - this.positionY);
        this.lerpt = 0.0f;

    }

    public void Fire() {
        MainGame game = MainGame.get();
        this.fireElapsedTime += game.frameTime;

        if (this.fireDelay >= this.fireElapsedTime) {
            return;
        }

        Sound.play(R.raw.fire);
        float leftFireSocketPositionX = this.positionX - 16.0f * GameView.view.MULTIPLIER;
        float leftFireSocketPositionY = this.positionY;

        float rightFireSocketPositionX = this.positionX + 16.0f * GameView.view.MULTIPLIER;
        float rightFireSocketPositionY = this.positionY;

        this.fireElapsedTime = 0.0f;


        Bullet leftBullet = game.spawn(Bullet.class);
        leftBullet.setPositionX(leftFireSocketPositionX);
        leftBullet.setPositionY(leftFireSocketPositionY);
        leftBullet.setSpeed(this.bulletSpeed);
        leftBullet.setGameBitmap(this.bulletBitmap);

        Bullet rightBullet = game.spawn(Bullet.class);
        rightBullet.setPositionX(rightFireSocketPositionX);
        rightBullet.setPositionY(rightFireSocketPositionY);
        rightBullet.setSpeed(this.bulletSpeed);
        rightBullet.setGameBitmap(this.bulletBitmap);

    }

    public void update() {
        MainGame game = MainGame.get();

        if (this.lengthX != 0.0f || this.lengthY != 0.0f) {
            this.lerpt += Math.abs(this.speed / (this.lengthX + this.lengthY)) * game.frameTime;
        }

        this.positionX = lerp(this.positionX, this.targetX, Math.min(this.lerpt, 1.0f));
        this.positionY = lerp(this.positionY, this.targetY, Math.min(this.lerpt, 1.0f));

        Fire();

    }


    float lerp(float a, float b, float t) {
        return a + t * (b - a);
    }

    public void draw(Canvas canvas) {
        BitmapRenderer.get().DrawBitmap(canvas, this.gameBitmap, this.positionX, this.positionY, 2);
    }

    @Override
    public void getBoundingRect(RectF rect) {
        this.gameBitmap.getBoundingRect(this.positionX, this.positionY, rect);
    }

    @Override
    public void onHit(GameObject object) {
        MainGame game = MainGame.get();

        if (object.getClass() != HealthItem.class && object.getClass() != PowerItem.class) {
            this.health -= 1;
        }

        if(object.getClass() == HealthItem.class) {
            this.health +=1;
        }

        if(object.getClass() == PowerItem.class && this.bulletSpeed < 800.0f) {
            this.bulletSpeed = 800.0f;
            this.bulletBitmap = this.bigBullet;
        }

        this.score.setScore(this.health);


        if (this.health <= 0) {
            game.remove(this);
            game.popLayer();
            game.pushLayer(new GameOverLayer());
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            moveTo(event.getX(), event.getY());
            return true;
        }
        return false;

    }

}
