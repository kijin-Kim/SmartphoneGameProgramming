package game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import framework.GameLayer;
import framework.GameObject;
import framework.Layer;
import framework.TitleLayer;
import ui.view.GameView;
import utils.CollisionHelper;

public class MainGame {
    private static final String TAG = MainGame.class.getSimpleName();
    private static MainGame instance;

    public float frameTime;


    private static Stack<Layer> layers;

    private MainGame() {
        layers = new Stack<>();
    }

    public static MainGame get() {
        if (instance == null) {
            instance = new MainGame();
        }
        return instance;
    }

    public void initialize() {
        pushLayer(new TitleLayer());
    }

    public void update() {
        layers.peek().update();
    }

    public void draw(Canvas canvas) {
        layers.peek().draw(canvas);
        BitmapRenderer.get().Execute();
    }

    public <T extends GameObject> T spawn(Class objectClass) {
        return layers.peek().spawn(objectClass);
    }

    public void remove(GameObject object) {
        layers.peek().remove(object);
    }

    public boolean onTouchEvent(MotionEvent event) {
        return layers.peek().onTouchEvent(event);
    }

    public Player getPlayer() {
        return layers.peek().getPlayer();
    }

    public void pushLayer(Layer layer) {
        if(!layers.empty()) {
            layers.peek().end();
        }

        layers.push(layer);
        layers.peek().start();
    }

    public void popLayer() {
        Layer popped = layers.pop();
        popped.end();
    }
}
