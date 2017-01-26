package com.artan.secretnumber;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView mTvAttemped;

    int length = 49;

    int min = 1;
    int max = length;

    Random ran = new Random();
    int secretNumber = ran.nextInt(length) + 1;

    int buttonClicked;
    int timesClicked;

    boolean hardLevel = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvAttemped = (TextView) findViewById(R.id.tv_attempted);
        reset();

    }

    public void onClick(View v) {
        timesClicked++;
        mTvAttemped.setText(timesClicked + " times attempted!");

        Button button = (Button) v;

        String num = (String) button.getText().toString();
        buttonClicked = Integer.parseInt(num);


        if (hardLevel) {

            if (secretNumber == buttonClicked) {
                button.setBackgroundColor(Color.parseColor("#33cc33"));
                bigger();
                smaller();

            } else {
                button.setEnabled(false);
            }

        } else {

            if (secretNumber == buttonClicked) {
                button.setBackgroundColor(Color.parseColor("#33cc33"));
                bigger();
                smaller();
            } else if (secretNumber > buttonClicked) {
                bigger();
                min = buttonClicked + 1;
            } else {
                smaller();
                max = buttonClicked - 1;
            }

        }
    }

    public void bigger() {
        //disable from min to the buttonClicked
        for (int i = min; i <= buttonClicked; i++) {

            int mId = getResources().getIdentifier("btn" + i, "id", getPackageName());
            Button buttons = (Button) findViewById(mId);

            buttons.setEnabled(false);

        }
    }

    public void smaller() {
        //disable from buttonClicked to the max
        for (int i = buttonClicked; i <= max; i++) {

            int mId = getResources().getIdentifier("btn" + i, "id", getPackageName());
            Button buttons = (Button) findViewById(mId);

            buttons.setEnabled(false);

        }
    }

    public void newGame(View v) {
        length = 49;

        min = 1;
        max = length;

        Random ran = new Random();
        secretNumber = ran.nextInt(length) + 1;

        buttonClicked = 0;
        timesClicked = 0;
        mTvAttemped.setText(timesClicked + " times attempted!");
        hardLevel = false;


        for (int i = min; i <= max; i++) {

            int mId = getResources().getIdentifier("btn" + i, "id", getPackageName());
            Button buttons = (Button) findViewById(mId);

            buttons.setEnabled(true);
            buttons.setBackgroundResource(android.R.drawable.btn_default);

        }


    }

    public void reset() {
        for (int i = min; i <= max; i++) {

            int mId = getResources().getIdentifier("btn" + i, "id", getPackageName());
            Button buttons = (Button) findViewById(mId);

            buttons.setEnabled(true);
            buttons.setBackgroundResource(android.R.drawable.btn_default);

        }
    }

    public void Level(View v) {
        hardLevel = true;
        Toast.makeText(this, "Hard Level activated!", Toast.LENGTH_SHORT).show();
    }

}
