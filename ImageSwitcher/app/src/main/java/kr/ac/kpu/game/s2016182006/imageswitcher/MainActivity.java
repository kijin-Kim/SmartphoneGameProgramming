package kr.ac.kpu.game.s2016182006.imageswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mainTextView;
    private ImageView mainImageView;

    private ImageButton previousImageButton;
    private boolean bPreviousButtonDisabled = false;

    private ImageButton nextImageButton;
    private boolean bNextButtonDisabled = false;


    private final int[] imageIds = {
            R.mipmap.cat1,
            R.mipmap.cat2,
            R.mipmap.cat3,
            R.mipmap.cat4,
            R.mipmap.cat5,
    };

    private int currentImageIndex = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTextView = findViewById(R.id.mainTextView);
        mainImageView = findViewById(R.id.mainImageView);
        previousImageButton = findViewById(R.id.imageButtonPrevious);
        nextImageButton = findViewById(R.id.imageButtonNext);

        previousImageButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!bPreviousButtonDisabled)
                    previousImageButton.setImageResource(R.mipmap.prev_p);
                return false;
            }
        });
        nextImageButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!bNextButtonDisabled)
                    nextImageButton.setImageResource(R.mipmap.next_p);
                return false;
            }
        });

        updateViews();
    }

    public void onButtonPrevious(View view) {
        if (!bPreviousButtonDisabled) {
            previousImageButton.setImageResource(R.mipmap.prev);
            currentImageIndex--;
        }

        updateViews();
    }

    public void onButtonNext(View view) {

        if (!bNextButtonDisabled) {
            currentImageIndex++;
            nextImageButton.setImageResource(R.mipmap.next);
        }

        updateViews();
    }

    @SuppressLint("SetTextI18n")
    public void updateViews() {
        int maxImageCount = 5;
        mainTextView.setText((currentImageIndex + 1) + " / " + maxImageCount);
        mainImageView.setImageResource(imageIds[currentImageIndex]);


        boolean bShouldDisablePreviousButton = currentImageIndex - 1 < 0;
        if (bShouldDisablePreviousButton && !bPreviousButtonDisabled) {
            previousImageButton.setImageResource(R.mipmap.prev_d);
            bPreviousButtonDisabled = true;
        } else if (!bShouldDisablePreviousButton && bPreviousButtonDisabled) {
            previousImageButton.setImageResource(R.mipmap.prev);
            bPreviousButtonDisabled = false;
        }

        boolean bShouldDisableNextButton = currentImageIndex + 1 >= maxImageCount;
        if (bShouldDisableNextButton && !bNextButtonDisabled) {
            nextImageButton.setImageResource(R.mipmap.next_d);
            bNextButtonDisabled = true;
        } else if (!bShouldDisableNextButton && bNextButtonDisabled) {
            nextImageButton.setImageResource(R.mipmap.next);
            bNextButtonDisabled = false;
        }


    }


}