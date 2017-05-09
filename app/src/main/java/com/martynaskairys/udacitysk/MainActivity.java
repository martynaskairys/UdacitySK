package com.martynaskairys.udacitysk;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.concurrent.TimeUnit;

import static com.martynaskairys.udacitysk.R.id.timer;


public class MainActivity extends AppCompatActivity {

    int scoreHome = 0;
    int scoreGuests = 0;

    private Button cancelbtn, startbtn;
    private ToggleButton togbtn;


    private boolean isPaused = false;
    private boolean isCancelled = false;
    private long remainingTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv1 = (TextView) findViewById(timer);

        togbtn = (ToggleButton) findViewById(R.id.pauseButton);
        startbtn = (Button) findViewById(R.id.startGame);
        cancelbtn = (Button) findViewById(R.id.cancelGame);


        cancelbtn.setEnabled(false);
        togbtn.setEnabled(false);

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startbtn.setEnabled(false);
                cancelbtn.setEnabled(true);
                togbtn.setEnabled(true);

                isPaused = false;
                isCancelled = false;

                long millisInFuture = 600000; //10 minutes
                long countDownInterval = 1000; //1 sec


                new CountDownTimer(millisInFuture, countDownInterval) {

                    private static final String FORMAT = "%02d:%02d";

                    @Override
                    public void onTick(long millisUntilFinished) {

                        if (isPaused || isCancelled) {

                            cancel();
                        } else {
                            tv1.setText("" + String.format(FORMAT,
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                            remainingTime = millisUntilFinished;
                        }
                    }


                    @Override
                    public void onFinish() {

                        tv1.setText("time is up");
                    }

                }.start();
            }

        });


        togbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (togbtn.isChecked()) {
                    isPaused = true;
                } else {
                    isPaused = false;

                    long millisInFuture = remainingTime;
                    long countDownInterval = 1000; //1 sec

                    new CountDownTimer(millisInFuture, countDownInterval) {

                        private static final String FORMAT = "%02d:%02d";

                        @Override
                        public void onTick(long millisUntilFinished) {


                            if (isPaused || isCancelled) {

                                cancel();
                            } else {
                                tv1.setText("" + String.format(FORMAT,
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                                remainingTime = millisUntilFinished;
                            }
                        }


                        @Override
                        public void onFinish() {

                            tv1.setText("time is up");
                        }

                    }.start();

                }
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCancelled = true;
                tv1.setText("00:00");
                startbtn.setEnabled(true);
                togbtn.setEnabled(false);
                cancelbtn.setEnabled(false);

                scoreGuests = 0;
                displayScoreGuests(scoreGuests);
                scoreHome = 0;
                displayScoreHome(scoreHome);
            }
        });

    }

    public void addThreePointsHomeTeam(View view) {

        if (startbtn.isEnabled()) {

            Toast.makeText(this, "Start Game first", Toast.LENGTH_SHORT).show();

        } else if (isPaused) {

            Toast.makeText(this, "Resume time first", Toast.LENGTH_SHORT).show();

        } else {
            scoreHome = scoreHome + 3;
            displayScoreHome(scoreHome);
        }
    }


    public void addTwoPointsHomeTeam(View view) {


        if (startbtn.isEnabled()) {

            Toast.makeText(this, "Start Game first", Toast.LENGTH_SHORT).show();

        } else if (isPaused) {

            Toast.makeText(this, "Resume time first", Toast.LENGTH_SHORT).show();

        } else {
            scoreHome = scoreHome + 2;
            displayScoreHome(scoreHome);
        }


    }

    public void addOnePointHomeTeam(View view) {
        if (startbtn.isEnabled()) {

            Toast.makeText(this, "Start Game first", Toast.LENGTH_SHORT).show();

        } else if (isPaused) {

            Toast.makeText(this, "Resume time first", Toast.LENGTH_SHORT).show();

        } else {
            scoreHome = scoreHome + 1;
            displayScoreHome(scoreHome);
        }


    }

    public void displayScoreHome(int score) {

        TextView scoreTextView = (TextView) findViewById(R.id.scoreTextHome);
        scoreTextView.setText(String.valueOf(score));

    }

    public void addThreePointsGuestsTeam(View view) {
        if (startbtn.isEnabled()) {

            Toast.makeText(this, "Start Game first", Toast.LENGTH_SHORT).show();

        } else if (isPaused) {

            Toast.makeText(this, "Resume time first", Toast.LENGTH_SHORT).show();

        } else {
            scoreGuests = scoreGuests + 3;
            displayScoreGuests(scoreGuests);
        }


    }

    public void addTwoPointsGuestsTeam(View view) {
        if (startbtn.isEnabled()) {

            Toast.makeText(this, "Start Game first", Toast.LENGTH_SHORT).show();

        } else if (isPaused) {

            Toast.makeText(this, "Resume time first", Toast.LENGTH_SHORT).show();

        } else {
            scoreGuests = scoreGuests + 2;
            displayScoreGuests(scoreGuests);
        }


    }

    public void addOnePointGuestsTeam(View view) {
        if (startbtn.isEnabled()) {

            Toast.makeText(this, "Start Game first", Toast.LENGTH_SHORT).show();

        } else if (isPaused) {

            Toast.makeText(this, "Resume time first", Toast.LENGTH_SHORT).show();

        } else {
            scoreGuests = scoreGuests + 1;
            displayScoreGuests(scoreGuests);
        }

    }

    public void displayScoreGuests(int score) {

        TextView scoreTextView = (TextView) findViewById(R.id.scoreTextGuests);
        scoreTextView.setText(String.valueOf(score));

    }

}
