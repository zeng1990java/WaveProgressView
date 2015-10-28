package com.github.zeng1990java.waveprogressview;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.zeng1990java.widget.WaveProgressView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WaveProgressView waveProgressView = (WaveProgressView) findViewById(R.id.wave_progress_view);
        waveProgressView.setMax(100);
        ObjectAnimator progressAnim = ObjectAnimator.ofInt(waveProgressView, "progress", 0, waveProgressView.getMax());
        progressAnim.setDuration(10 * 1000);
        progressAnim.setRepeatCount(ObjectAnimator.INFINITE);
        progressAnim.setRepeatMode(ObjectAnimator.RESTART);
        progressAnim.start();

        waveProgressView = (WaveProgressView) findViewById(R.id.wave_progress_view_2);
        progressAnim = ObjectAnimator.ofInt(waveProgressView, "progress", 0, waveProgressView.getMax());
        progressAnim.setDuration(15 * 1000);
        progressAnim.setRepeatCount(ObjectAnimator.INFINITE);
        progressAnim.setRepeatMode(ObjectAnimator.RESTART);
        progressAnim.start();
    }
}
