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
        animWave(waveProgressView, 10 * 1000);

        waveProgressView = (WaveProgressView) findViewById(R.id.wave_progress_view_2);
        animWave(waveProgressView, 15 * 1000);

        waveProgressView = (WaveProgressView) findViewById(R.id.wave_progress_view_0);
        animWave(waveProgressView, 5 * 1000);
    }

    private void animWave(WaveProgressView waveProgressView, long duration){
        ObjectAnimator progressAnim = ObjectAnimator.ofInt(waveProgressView, "progress", 0, waveProgressView.getMax());
        progressAnim.setDuration(duration);
        progressAnim.setRepeatCount(ObjectAnimator.INFINITE);
        progressAnim.setRepeatMode(ObjectAnimator.RESTART);
        progressAnim.start();
    }

}
