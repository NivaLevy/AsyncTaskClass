package com.example.niva.asynctaskclass;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout background;
    private int color;
    private final static int MAX = 20, SLEEPING_TIME = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        background = (RelativeLayout)findViewById(R.id.background);
        color = Color.BLUE;
        background.setBackgroundColor(color);
        (new BackgroundTask()).execute();
    }



    private class BackgroundTask extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {
            int val = (new Random()).nextInt(MAX);

            for (int i = 0; i < val ; i++) {
                publishProgress(generateColor());
                try {
                    Thread.sleep(SLEEPING_TIME);
                } catch (InterruptedException e) {}
            }
            return val;
        }

        private Integer generateColor() {
            return  (new Random()).nextInt(Integer.MAX_VALUE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            background.setBackgroundColor(values[0]);
        }

        @Override
        protected void onPostExecute(Integer aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getBaseContext(), "The color has changed " + aVoid + " times.", Toast.LENGTH_SHORT).show();
        }
    }
}
