package com.example.romario.brainapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView highScoreText;
    TextView newHighScore;
    int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences prefs = this.getPreferences(Context.MODE_PRIVATE);
        highScore = prefs.getInt("highScore",0);

        highScoreText = (TextView) findViewById(R.id.highScoreText);
        newHighScore = (TextView) findViewById(R.id.newHighScore);
        newHighScore.setText("");

        Intent intent = getIntent();
        int score = intent.getIntExtra("score",0);

        if (score > highScore) {
            highScore = score ;
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highScore",highScore);
            editor.commit();
            newHighScore.setText("Gefeliciteerd je hebt een nieuwe highscore!");
        }

        highScoreText.setText("High score:" + highScore);

    }

    public void start (View view){
        Intent intent = new Intent(this,TimmerActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
