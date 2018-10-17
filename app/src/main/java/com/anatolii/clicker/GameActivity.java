package com.anatolii.clicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private static final int _60;
    private static int timerCounter;
    private int counter;
    private Timer timer;
    public static final String COUNTER;

    static {

        _60 = 60;
        COUNTER = "COUNTER";

    }

    @Override
    protected void onStart() {
        super.onStart();
        timer = new Timer("counter", true);


        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (timerCounter < _60) {
                    timerCounter++;
                    final int newTimeValue = _60 - timerCounter;
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if(null == timer){
                                return;
                            }
                            final TextView timerTextView = (TextView) findViewById(R.id.timer);
                            timerTextView.setText("00:00:" + String.valueOf(newTimeValue));


                        }
                    });

                }else {
                    timer.cancel();
                    timer.purge();
                    timer = null;

                    final Intent intent = new Intent(GameActivity.this, ResultsActivity.class);
                    intent.putExtra(COUNTER, counter);
                    startActivity(intent);
                    counter = 0;
                    timerCounter = 0;
                }



            }
        }, 10L, 1000L);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

    }

    public void doClick(View view) {

        counter++;
        final TextView scoreTextView = (TextView) findViewById(R.id.score);
        scoreTextView.setText(String.valueOf(counter));

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if(null != timer){
            timer.cancel();
            timer.purge();
            timer = null;
        }


        counter = 0;
        timerCounter = 0;
    }
}
