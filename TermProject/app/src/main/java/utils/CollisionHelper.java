package utils;

import android.graphics.RectF;

import framework.BoxCollidable;

public class CollisionHelper {
    private static final String TAG = CollisionHelper.class.getSimpleName();
    private static RectF rect1 = new RectF();
    private static RectF rect2 = new RectF();
    public static boolean collides(BoxCollidable o1, BoxCollidable o2) {
        o1.getBoundingRect(rect1);
        o2.getBoundingRect(rect2);

        if(!rectIsValid(rect1) || !rectIsValid(rect2)) {
            return false;
        }

        if (rect1.left > rect2.right) return false;
        if (rect1.top > rect2.bottom) return false;
        if (rect1.right < rect2.left) return false;
        if (rect1.bottom < rect2.top) return false;

        return true;
    }


    static private boolean rectIsValid(RectF rect) {
        return rect.left != 0 && rect.top != 0 && rect.right != 0 && rect.bottom != 0;
    }
}