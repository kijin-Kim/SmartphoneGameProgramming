package game;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import java.lang.reflect.Array;
import java.util.ArrayList;

import framework.GameBitmap;
import framework.GameObject;
import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class Score implements GameObject {
    private final GameBitmap gameBitmap;

    private final ArrayList<RectF> destRects;
    private final ArrayList<Rect> srcRects;


    private int score;

    public Score() {

        gameBitmap = new GameBitmap(R.mipmap.number_24x32);

        this.destRects = new ArrayList<>();
        this.srcRects = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            this.srcRects.add(new Rect(0 + i * 24, 0, 24 + i * 24, 32));
        }
        for (int i = 0; i < 10; i++) {
            this.destRects.add(new RectF(24 * this.gameBitmap.getMultiplier() * i,
                    0,
                    24 * this.gameBitmap.getMultiplier() + 24 * this.gameBitmap.getMultiplier() * i,
                    32 * this.gameBitmap.getMultiplier()));
        }


        this.destRects.add(new RectF());
        this.score = 9;


    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        int score = this.score;
        int digitCount = 2;

        if (score <= 0) {
            for (int i = 0; i < digitCount; i++) {
                BitmapRenderer.get().DrawBitmap(canvas, this.gameBitmap, this.srcRects.get(0), this.destRects.get(i), 2);
            }
            return;
        }

        while (digitCount > 0) {
            int num = score % 10;
            score = score / 10;
            BitmapRenderer.get().DrawBitmap(canvas, this.gameBitmap, this.srcRects.get(num), this.destRects.get(digitCount - 1), 2);
            --digitCount;
        }


    }


}