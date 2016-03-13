package com.example.romario.brainapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class TimmerActivity extends AppCompatActivity {

    private TextView timeText;
    private TextView scoreText;
    private Button [] buttons;
    private int answer;
    private int correctAnswers;
    private int turns;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timmer);

        timeText = (TextView)findViewById(R.id.timeText);
        scoreText = (TextView)findViewById(R.id.scoreText);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        buttons = new Button[4];
        for (int i = 0; i < buttons.length; i++){
            buttons [i] = (Button) gridLayout.getChildAt(i);
        }
        correctAnswers = 0;
        turns = 0;
        startTimer();
        createSom();
    }

    private void startTimer(){
        new CountDownTimer(30000 + 100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                int seconden = (int) millisUntilFinished / 1000;
                timeText.setText(String.valueOf(seconden));
            }

            @Override
            public void onFinish() {
                timeText.setText("0");
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("score", correctAnswers);
                startActivity(intent);


            }
        }.start();
    }

    private void createSom (){
        Random randomizer = new Random();
        int getal1 = randomizer.nextInt(50) + 1;
        int getal2 = randomizer.nextInt(50) + 1;
        TextView somText = (TextView)findViewById(R.id.somText);
        somText.setText(String.valueOf(getal1) + "+" + (getal2));
        answer = getal1 + getal2;

        int answerButton = randomizer.nextInt(4);
        buttons [answerButton].setText(String.valueOf(answer));
        for (int i = 0; i < buttons.length; i++){
            if (i != answerButton ){
                int wrongAnswer = randomizer.nextInt(50) + 1;
                while (wrongAnswer == answer){
                    wrongAnswer = randomizer.nextInt(50) + 1;
                }
                buttons[i].setText(String.valueOf(wrongAnswer));
            }
        }
    }

    public void checkUitkomst(View view){
        turns ++;
        Button button = (Button) view;
        int antwoord = Integer.parseInt(button.getText().toString());
        if (antwoord == answer){
            correctAnswers++;
        }

        scoreText.setText(String.valueOf(correctAnswers) + "/" + (turns));
        createSom();

    }
}
