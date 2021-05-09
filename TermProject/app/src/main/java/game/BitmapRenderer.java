package game;

import android.graphics.Canvas;

import androidx.arch.core.util.Function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import framework.GameBitmap;
import ui.view.GameView;

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

    void DrawBitmap(Canvas canvas, GameBitmap bitmap,  float positionX, float positionY, int drawOrder) {
        runnables.get(drawOrder).add(() -> bitmap.draw(canvas, positionX, positionY));
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
