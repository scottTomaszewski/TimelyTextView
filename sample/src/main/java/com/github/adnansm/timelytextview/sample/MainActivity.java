package com.github.adnansm.timelytextview.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.github.adnansm.timelytextview.TimelyView;
import com.github.adnansm.timelytextview.model.Characters;
import com.nineoldandroids.animation.ObjectAnimator;

public class MainActivity extends Activity {
    public static final int DURATION = 1000;
    public static final char NO_VALUE = '0';
    private TimelyView timelyView = null;
    private SeekBar seekBar = null;
    private Spinner fromSpinner = null;
    private Spinner toSpinner = null;
    private volatile ObjectAnimator objectAnimator = null;

    private volatile char from = NO_VALUE;
    private volatile char to = NO_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timelyView = (TimelyView) findViewById(R.id.textView1);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        fromSpinner = (Spinner) findViewById(R.id.fromSpinner);
        toSpinner = (Spinner) findViewById(R.id.toSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.from_numbers_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                from = '|';
                if (to != NO_VALUE && to != NO_VALUE) {
                    objectAnimator = timelyView.animate(from, to);
                    objectAnimator.setDuration(DURATION);
                } else {
                    objectAnimator = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                to = 'a';
                if (from != NO_VALUE && to != NO_VALUE) {
                    objectAnimator = timelyView.animate(from, to);
                    objectAnimator.setDuration(DURATION);
                } else {
                    objectAnimator = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        seekBar.setMax(DURATION);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (objectAnimator != null) objectAnimator.setCurrentPlayTime(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
