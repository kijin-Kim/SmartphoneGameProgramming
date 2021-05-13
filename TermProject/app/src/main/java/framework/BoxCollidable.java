package framework;

import android.graphics.RectF;

public interface BoxCollidable {
    public void getBoundingRect(RectF rect);
    public void onHit(GameObject object);
}
