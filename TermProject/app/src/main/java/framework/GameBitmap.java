package framework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.HashMap;

import ui.view.GameView;

public class GameBitmap {
    private static HashMap<Integer, Bitmap> bitmaps = new HashMap<Integer, Bitmap>();

    private static Bitmap getBitmap(int resId) {
        Bitmap bitmap = bitmaps.get(resId);
        if(bitmap == null) {
            Resources resources = GameView.view.getResources();
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inScaled = false;
            bitmap = BitmapFactory.decodeResource(resources, resId, opts);
            bitmaps.put(resId, bitmap);
        }
        return bitmap;
    }

    private Bitmap bitmap;
    private final RectF destRect;

    public GameBitmap(int resId) {
        this.bitmap = getBitmap(resId);
        this.destRect = new RectF();
    }


    public void draw(Canvas canvas, float positionX, float positionY) {

        float halfWidth = this.bitmap.getWidth() / 2.0f * GameView.view.MULTIPLIER;
        float halfHeight = this.bitmap.getHeight() / 2.0f * GameView.view.MULTIPLIER;
        this.destRect.set(positionX - halfWidth, positionY - halfHeight, positionX + halfWidth, positionY + halfHeight);

        canvas.drawBitmap(this.bitmap, null, this.destRect, null);
    }


    public int getWidth() {
        return this.bitmap.getWidth();
    }

    public int getHeight() {
        return this.bitmap.getHeight();
    }


}
