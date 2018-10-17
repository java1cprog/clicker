package com.anatolii.clicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {
    private static String BAD;
    private static String OK;
    private static String SUPER;

    static {
        BAD = "Bad";
        OK = "OK";
        SUPER = "SUPER";

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        final Intent intent = getIntent();

        final int value = intent.getIntExtra(GameActivity.COUNTER, 0);


        //set score
        {
            final TextView scoreTextView = (TextView) findViewById(R.id.score);
            scoreTextView.setText(String.valueOf(value));
        }
        //set description
        {
            final TextView descriptionTextView = (TextView) findViewById(R.id.description);
            String result = BAD;
            if(value > 0 && value <= 50) {
                result = OK;
            }else if(value > 50){
                result = SUPER;
            }
            descriptionTextView.setText(result);
        }



    }

    public void reset(View view) {
        final Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}
