package com.example.guessgame;

import android.app.MediaRouteButton;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    int randomnumber;
    int answer, guess;
    int MAX_try = 5;
    int tries;
    int limit = 20;

    TextView scoreText;
    EditText typedTextbox;
    TextView clap;

    private void generateNumber() {
        tries = MAX_try;

        Random rand = new Random();
        randomnumber = rand.nextInt(limit) + 1;

        TextView showTriesTextview = (TextView) findViewById(R.id.showTriesTextview);
        showTriesTextview.setText("Tries" + tries);

    }

    public void RestartGame(View view){
        Button RestartgameB = (Button) view;
        generateNumber();


        RestartgameB.setVisibility(View.INVISIBLE);
    }

    public void playgame(View view) {
        //      Log.d("DEBUG_PLAY_GAME", "BUTTON PRESSED");
        //      Log.d("DEBUG_PLAY_GAME", ""+"randomnumber");


        EditText ediText = (EditText) findViewById(R.id.editTextNumber);
        String message;
        Button RestartgameB = (Button) findViewById(R.id.RestartgameB);
        RestartgameB.setVisibility(View.VISIBLE);
        RestartgameB.setTranslationX(-2000);

        if (ediText.getText().toString().isEmpty()) {
            message = "Enter the Number";
        }
        else {


            if (tries > 0) {
                int editTextNumber = Integer.parseInt(ediText.getText().toString());

                if (editTextNumber > randomnumber) {
                    message = "Try Lower";
                } else if (editTextNumber < randomnumber) {
                    message = "Try Higher";
                } else {
                    message = "You Won. Your Point is - " + (tries * 10);
                    RestartgameB.setVisibility(View.VISIBLE);
                    RestartgameB.animate().translationX(0).setDuration(300);
                }
                tries--;

                TextView showTriesTextview = (TextView) findViewById(R.id.showTriesTextview);
                showTriesTextview.setText("Tries" + tries );
            }


           else {
               message = "Try Again";
                RestartgameB.setVisibility(View.VISIBLE);
               RestartgameB.animate().translationX(0).setDuration(300);
            }
           
            tries--;

            TextView showTriesTextview = (TextView) findViewById(R.id.showTriesTextview);
            showTriesTextview.setText("Tries" + tries );

        }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateNumber();
        TextView showTriesTextview = (TextView) findViewById(R.id.showTriesTextview);
        showTriesTextview.setText("Tries" + tries );
    }
}
