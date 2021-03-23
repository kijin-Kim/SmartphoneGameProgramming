package kr.ac.kpu.game.s2016182006.PairGame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int[] buttonIds = {
            R.id.card00, R.id.card01, R.id.card02, R.id.card03,
            R.id.card10, R.id.card11, R.id.card12, R.id.card13,
            R.id.card20, R.id.card21, R.id.card22, R.id.card23,
            R.id.card30, R.id.card31, R.id.card32, R.id.card33,
            R.id.card40, R.id.card41, R.id.card42, R.id.card43,
    };

    private static final int[] imageIds = {
            R.mipmap.pae1, R.mipmap.pae2, R.mipmap.pae3, R.mipmap.pae4,
            R.mipmap.pae5, R.mipmap.pae6, R.mipmap.pae7, R.mipmap.pae8,
            R.mipmap.pae9, R.mipmap.pae10, R.mipmap.pae1, R.mipmap.pae2,
            R.mipmap.pae3, R.mipmap.pae4, R.mipmap.pae5, R.mipmap.pae6,
            R.mipmap.pae7, R.mipmap.pae8, R.mipmap.pae9, R.mipmap.pae10,
    };
    private ImageButton previousImageButtonId;
    private TextView flipCountTextView;
    private int flipCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flipCountTextView = (TextView) findViewById(R.id.flipCountTextView);

        OnGameStart();
    }

    public void onBtnCard(View view) {
        final ImageButton currentImageButtonId = (ImageButton) view;

        final boolean bClickedSameButton = currentImageButtonId == previousImageButtonId;
        if (bClickedSameButton)
            return;

        final int currentImageId = (Integer) currentImageButtonId.getTag();

        final boolean bShouldCompare = previousImageButtonId != null;
        if (bShouldCompare) {
            final int previousImageId = (Integer) previousImageButtonId.getTag();

            // Check whether previous and current image is same or not

            final boolean bImageIsSame = currentImageId == previousImageId;
            if (bImageIsSame) {
                previousImageButtonId.setVisibility(View.INVISIBLE);
                currentImageButtonId.setVisibility(View.INVISIBLE);
            } else {
                setFlipCount(flipCount + 1);
                previousImageButtonId.setImageResource(R.mipmap.new_card_back);
                currentImageButtonId.setImageResource(R.mipmap.new_card_back);
            }
            // comparing is over. should not compare on next button click
            previousImageButtonId = null;

        } else {
            currentImageButtonId.setImageResource(currentImageId);
            previousImageButtonId = currentImageButtonId;
        }
    }

    public void OnGameStart() {
        Random random = new Random();
        for (int i = 0; i < imageIds.length; i++) {
            ImageButton button = (ImageButton) findViewById(buttonIds[i]);
            button.setVisibility(View.VISIBLE);
            button.setImageResource(R.mipmap.new_card_back);

            int randomIndex = random.nextInt(imageIds.length);
            int temp = imageIds[i];
            imageIds[i] = imageIds[randomIndex];
            imageIds[randomIndex] = temp;

            button.setTag(imageIds[i]);
        }

        setFlipCount(0);
    }


    public void onBtnRestart(View view) {
        OnGameStart();
    }

    private int getButtonIndex(int resId) {
        for (int i = 0; i < buttonIds.length; i++) {
            if (buttonIds[i] == resId) {
                return i;
            }
        }
        return -1;
    }


    public void setFlipCount(int flipCount) {
        this.flipCount = flipCount;
        flipCountTextView.setText("Flips : " + this.flipCount);
    }
}