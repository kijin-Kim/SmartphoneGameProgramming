package framework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import kr.ac.kpu.game.s2016182006.termproject.R;
import ui.view.GameView;

public class GameBitmap {
    private static final String TAG = GameBitmap.class.getSimpleName();
    private static HashMap<Integer, Bitmap> bitmaps = new HashMap<Integer, Bitmap>();
    private final float multiplier;

    private static Bitmap getBitmap(int resId) {
        Bitmap bitmap = bitmaps.get(resId);
        if (bitmap == null) {
            Resources resources = GameView.view.getResources();
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inScaled = false;
            bitmap = BitmapFactory.decodeResource(resources, resId, opts);
            bitmaps.put(resId, bitmap);

        }
        return bitmap;
    }

    private Bitmap bitmap;
    private Rect srcRect; // can be null
    private final RectF destRect;


    public GameBitmap(int resId) {
        this.multiplier = GameView.view.MULTIPLIER;
        this.bitmap = getBitmap(resId);
        this.destRect = new RectF();
    }

    public GameBitmap(int resId, float multiplierOverride) {
        this.multiplier = multiplierOverride;
        this.bitmap = getBitmap(resId);
        this.destRect = new RectF();
    }

    public GameBitmap(int resId, String jsonFileName, String subregionName) {
        this.multiplier = GameView.view.MULTIPLIER;
        String jsonString = "";

        try {
            InputStream inputStream = GameView.view.getContext().getAssets().open(jsonFileName);
            int fileSize = inputStream.available();

            byte[] buffer = new byte[fileSize];
            inputStream.read(buffer);
            inputStream.close();

            jsonString = new String(buffer, "UTF-8");

        } catch (IOException exception) {
            exception.printStackTrace();
        }


        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject subregion = jsonObject.getJSONObject(subregionName);
            left = subregion.getInt("left");
            top = subregion.getInt("top");
            right = subregion.getInt("right");
            bottom = subregion.getInt("bottom");
            this.srcRect = new Rect();
            this.srcRect.set(left, top, right, bottom);

        } catch (JSONException exception) {
            exception.printStackTrace();
        }

        this.bitmap = getBitmap(resId);
        this.destRect = new RectF();
    }

    public GameBitmap(int resId, String jsonFileName, String subregionName, float multiplierOverride) {
        this.multiplier = multiplierOverride;
        String jsonString = "";

        try {
            InputStream inputStream = GameView.view.getContext().getAssets().open(jsonFileName);
            int fileSize = inputStream.available();

            byte[] buffer = new byte[fileSize];
            inputStream.read(buffer);
            inputStream.close();

            jsonString = new String(buffer, "UTF-8");

        } catch (IOException exception) {
            exception.printStackTrace();
        }


        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject subregion = jsonObject.getJSONObject(subregionName);
            left = subregion.getInt("left");
            top = subregion.getInt("top");
            right = subregion.getInt("right");
            bottom = subregion.getInt("bottom");
            this.srcRect = new Rect();
            this.srcRect.set(left, top, right, bottom);

        } catch (JSONException exception) {
            exception.printStackTrace();
        }

        this.bitmap = getBitmap(resId);
        this.destRect = new RectF();
    }


    public void draw(Canvas canvas, float positionX, float positionY) {

        float halfWidth = getWidth() / 2.0f * GameView.view.MULTIPLIER;
        float halfHeight = getHeight() / 2.0f * GameView.view.MULTIPLIER;
        this.destRect.set(positionX - halfWidth, positionY - halfHeight, positionX + halfWidth, positionY + halfHeight);

        canvas.drawBitmap(this.bitmap, srcRect, this.destRect, null);
    }


    public int getWidth() {
        if(this.srcRect == null) {
            return this.bitmap.getWidth();
        }

        return this.srcRect.right - this.srcRect.left;
    }

    public int getHeight() {
        if(this.srcRect == null) {
            return this.bitmap.getHeight();
        }

        return this.srcRect.bottom - this.srcRect.top;
    }

    public void getBoundingRect(float positionX, float positionY, RectF rect) {
        float halfWidth = getWidth() / 2.0f * this.multiplier;
        float halfHeight = getHeight() / 2.0f * this.multiplier;
        rect.set(positionX - halfWidth, positionY - halfHeight, positionX + halfWidth, positionY + halfHeight);
    }


}
