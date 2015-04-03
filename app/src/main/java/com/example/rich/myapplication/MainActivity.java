package com.example.rich.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Locale;


import java.util.Date;


public class MainActivity extends ActionBarActivity {

    public static int NUM_ELEMENTS = 10;

    TextView t;
    TextView t2;

    String names[] = {"Roger Staubach", "Roger Rabbit", "Jessica Rabbit", "Eddie Rabbit", "Eddie Haskell",
            "Flo N. Eddy", "Florence Nightengale", "Nurse Ratched", "Billy Bibbit", "Grima Wormtongue"};

    TextView[]  names_array = new TextView[10];
    TextView[]  scores_array = new TextView[10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);
//        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
//        screenHeight = 640;
//        System.out.println("height is: " + screenHeight);

        NUM_ELEMENTS = screenHeight/(2 * 10 * 6);
        NUM_ELEMENTS = Math.min(NUM_ELEMENTS, 10);

        t = new TextView(this);
        t2 = new TextView(this);

        names_array[0] = (TextView) findViewById(R.id.hi_score1);
        names_array[1] = (TextView) findViewById(R.id.hi_score2);
        names_array[2] = (TextView) findViewById(R.id.hi_score3);
        names_array[3] = (TextView) findViewById(R.id.hi_score4);
        names_array[4] = (TextView) findViewById(R.id.hi_score5);
        names_array[5] = (TextView) findViewById(R.id.hi_score6);
        names_array[6] = (TextView) findViewById(R.id.hi_score7);
        names_array[7] = (TextView) findViewById(R.id.hi_score8);
        names_array[8] = (TextView) findViewById(R.id.hi_score9);
        names_array[9] = (TextView) findViewById(R.id.hi_score10);

        scores_array[0] = (TextView) findViewById(R.id.hi_score_num1);
        scores_array[1] = (TextView) findViewById(R.id.hi_score_num2);
        scores_array[2] = (TextView) findViewById(R.id.hi_score_num3);
        scores_array[3] = (TextView) findViewById(R.id.hi_score_num4);
        scores_array[4] = (TextView) findViewById(R.id.hi_score_num5);
        scores_array[5] = (TextView) findViewById(R.id.hi_score_num6);
        scores_array[6] = (TextView) findViewById(R.id.hi_score_num7);
        scores_array[7] = (TextView) findViewById(R.id.hi_score_num8);
        scores_array[8] = (TextView) findViewById(R.id.hi_score_num9);
        scores_array[9] = (TextView) findViewById(R.id.hi_score_num10);

        for(int i = 0; i < NUM_ELEMENTS; i++)
        {
            t = names_array[i];
            t.setText(names[i]);
            t2 = scores_array[i];
            t2.setText("04-01 12:31:14");
        }

        for(int i = 0; i < 10; i++)
        {
            if(i >= NUM_ELEMENTS) {
                t = names_array[i];
                t.setVisibility(View.INVISIBLE);
                t = scores_array[i];
                t.setVisibility(View.INVISIBLE);
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Button button = (Button) findViewById(R.id.buy_button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                t = (TextView) findViewById(R.id.enter_name);
                int v = t.getVisibility();
                if (v == View.INVISIBLE)
                    t.setVisibility(View.VISIBLE);
                t.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

                Button done_button = (Button) findViewById(R.id.name_done_button);
                v = done_button.getVisibility();
                if (v == View.INVISIBLE)
                    done_button.setVisibility(View.VISIBLE);
            }
        });
        final EditText editText = (EditText) findViewById(R.id.enter_name);
        final Button done_button = (Button) findViewById(R.id.name_done_button);
        done_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // Move everybody down one slot
                for(int i = NUM_ELEMENTS-1; i > 0; i--)
                {
                    t = names_array[i];
                    t2 = names_array[i-1];
                    t.setText(t2.getText());
// Now the timestamp
                    t = scores_array[i];
                    t2 = scores_array[i-1];
                    t.setText(t2.getText());
                }

                t.requestFocus();
// Insert the new leader
                t = (TextView) findViewById(R.id.enter_name);
                MainActivity.this.t = (TextView)findViewById(R.id.hi_score1);
                t.setText(editText.getText());

                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss", Locale.getDefault());
                String formattedText = sdf.format(new Date());

                t2 = (TextView) findViewById(R.id.hi_score_num1);
                t2.setText(formattedText);

                t = (TextView) findViewById(R.id.enter_name);
                int v = t.getVisibility();
                if(v == View.VISIBLE)
                    t.setVisibility(View.INVISIBLE);
                v = done_button.getVisibility();
                if(v == View.VISIBLE)
                    done_button.setVisibility(View.INVISIBLE);


            }
        });
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

