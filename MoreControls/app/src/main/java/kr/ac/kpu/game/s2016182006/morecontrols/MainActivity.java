package kr.ac.kpu.game.s2016182006.morecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private CheckBox firewallCheckBox;
    private TextView outTextView;
    private EditText userEditText;
    private TextView editTextView;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            editTextView.setText("String Length = " + s.length());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firewallCheckBox = (CheckBox)findViewById(R.id.checkbox);
        outTextView = findViewById(R.id.outTextView);

        userEditText = findViewById(R.id.userEditText);
        editTextView = findViewById(R.id.editTextView);

        userEditText.addTextChangedListener(textWatcher);

    }

    public void onBtnApply(View view) {
        boolean checked = firewallCheckBox.isChecked();
        String text = checked ? "Using Firewall" : "Not using Firewall";
        outTextView.setText(text);

        String user = userEditText.getText().toString();
        editTextView.setText("User Info = " + user);

    }

    public void onCheckFirewall(View view) {
        boolean checked = firewallCheckBox.isChecked();
        String text = checked ? "Using Firewall" : "Unchecked Firewall";
        outTextView.setText(text);
    }

}