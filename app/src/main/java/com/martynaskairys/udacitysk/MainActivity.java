package com.martynaskairys.udacitysk;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    int scoreHome = 0;
    int scoreGuests = 0;
    private long remainingTime = 0;

    private boolean isPaused = false;
    private boolean isCancelled = false;

    @BindView(R.id.pauseButton)
    ToggleButton togbtn;
    @BindView(R.id.startGame)
    Button startbtn;
    @BindView(R.id.cancelGame)
    Button cancelbtn;
    @BindView(R.id.timer)
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        cancelbtn.setEnabled(false);
        togbtn.setEnabled(false);
    }

    @OnClick(R.id.startGame)
    public void startGameButtonClick() {

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
                    tv1.setText("" + String.format(Locale.getDefault(), FORMAT,
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                                    TimeUnit.HOURS.toMinutes(
                                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                    TimeUnit.MINUTES.toSeconds(
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

    @OnClick(R.id.pauseButton)
    public void pauseGameButtonClick() {

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
                        tv1.setText("" + String.format(Locale.getDefault(), FORMAT,
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                                                .toHours(millisUntilFinished)),
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                        TimeUnit.MINUTES.toSeconds(
                                                TimeUnit.MILLISECONDS
                                                        .toMinutes(millisUntilFinished))));
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


    @OnClick(R.id.cancelGame)
    public void cancelGameButtonClick() {

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
            scoreHome = scoreHome + 1;
            displayScoreHome(scoreHome);
        } else {
            Toast.makeText(this, "Stop time first", Toast.LENGTH_SHORT).show();
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
            scoreGuests = scoreGuests + 1;
            displayScoreGuests(scoreGuests);
        } else {
            Toast.makeText(this, "Stop time first", Toast.LENGTH_SHORT).show();
        }
    }

    public void displayScoreGuests(int score) {

        TextView scoreTextView = (TextView) findViewById(R.id.scoreTextGuests);
        scoreTextView.setText(String.valueOf(score));
    }
}