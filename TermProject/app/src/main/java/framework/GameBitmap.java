package framework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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
    private final RectF destRect;

    public GameBitmap(int resId) {
        this.bitmap = getBitmap(resId);
        this.destRect = new RectF();
    }

    public GameBitmap(int resId, String jsonFileName, String subregionName) {

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


        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray subregions = jsonObject.getJSONArray("Subregions");

            JSONObject subregion = subregions.getJSONObject(0);
            Log.d(TAG, subregion.getString("title"));
        } catch (JSONException exception) {
            exception.printStackTrace();
        }

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

    public void getBoundingRect(float positionX, float positionY, RectF rect) {
        float halfWidth = this.bitmap.getWidth() / 2.0f * GameView.view.MULTIPLIER;
        float halfHeight = this.bitmap.getHeight() / 2.0f * GameView.view.MULTIPLIER;
        rect.set(positionX - halfWidth, positionY - halfHeight, positionX + halfWidth, positionY + halfHeight);
    }


}
