package game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;

import framework.GameBitmap;

public class BitmapRenderer {

    private static BitmapRenderer instance;
    private static ArrayList<ArrayList<Runnable>> runnables;

    private static final int LAYER_COUNT = 3;

    public static BitmapRenderer get() {
        if (instance == null) {
            instance = new BitmapRenderer();
        }
        return instance;
    }

    private BitmapRenderer() {
        runnables = new ArrayList<>();
        for(int i = 0; i< LAYER_COUNT; i++) {
            runnables.add(new ArrayList<>());
        }

    }

    void record(Runnable runnable, int drawOrder) {
        runnables.get(drawOrder).add(runnable);
    }

    void DrawBitmap(Canvas canvas, GameBitmap bitmap, Rect srcRect, RectF destRect, int drawOrder) {
        Bitmap rawBitmap = bitmap.getRaw();
        record(() -> canvas.drawBitmap(rawBitmap, srcRect, destRect, null), drawOrder);
    }

    void DrawBitmap(Canvas canvas, GameBitmap bitmap,  float positionX, float positionY, int drawOrder) {
        record(() -> bitmap.draw(canvas, positionX, positionY), drawOrder);
    }

    void Execute() {
        for(ArrayList<Runnable> runnableArrayList : runnables) {
            for(Runnable runnable : runnableArrayList) {
                runnable.run();
            }
        }

        for(int i = 0; i< LAYER_COUNT; i++) {
            runnables.get(i).clear();
        }
    }

}
