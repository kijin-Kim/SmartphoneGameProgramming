package framework;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;

import game.MainGame;
import game.Player;
import ui.view.GameView;

public class Layer {

    private static final String TAG = MainGame.class.getSimpleName();

    protected Player player;

    protected HashMap<Class, ArrayList<GameObject>> gameObjects;
    protected HashMap<Class, ArrayList<GameObject>> recycleObjects;

    public Layer() {
        gameObjects = new HashMap<>();
        recycleObjects = new HashMap<>();
    }


    public void update() {

    }


    public void draw(Canvas canvas){

    }

    public Player getPlayer() {
        return player;
    }

    public boolean onTouchEvent(MotionEvent event) {
        return this.player.onTouchEvent(event);
    }

    public void remove(GameObject object) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                boolean removed = gameObjects.get(object.getClass()).remove(object);
                if (removed) {
                    ArrayList<GameObject> recyleObjectList = recycleObjects.get(object.getClass());
                    if (recyleObjectList == null) {
                        recyleObjectList = new ArrayList<>();
                        recycleObjects.put(object.getClass(), recyleObjectList);
                    }
                    recyleObjectList.add(object);
                }


            }
        });
    }


    public <T extends GameObject> T spawn(Class objectClass) {
        T newInstance = null;
        ArrayList<GameObject> recycleObjectArrayList = recycleObjects.get(objectClass);
        if (recycleObjectArrayList == null || recycleObjectArrayList.isEmpty()) {
            try {
                newInstance = (T) objectClass.newInstance();
                add(newInstance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "Object Recycled");
            newInstance = (T) recycleObjectArrayList.remove(0);
            add(newInstance);
        }


        return newInstance;

    }

    private void add(GameObject object) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> gameObjectArrayList = gameObjects.get(object.getClass());
                if (gameObjectArrayList == null) {
                    gameObjectArrayList = new ArrayList<>();
                    gameObjects.put(object.getClass(), gameObjectArrayList);
                }
                gameObjectArrayList.add(object);
            }
        });
    }

    public void start() {
    }

    public void end() {
    }
}

