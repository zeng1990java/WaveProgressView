package com.github.zeng1990java.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * $desc
 *
 * @author zxb
 * @date 15/10/28 下午5:46
 */
public class WaveProgressView extends View {

    private static final String STATE_INSTANCE = "state_instance";
    private static final String STATE_MAX = "state_max";
    private static final String STATE_PROGRESS = "state_progress";
    private static final String STATE_WAVE_COLOR = "state_wave_color";

    private static final int DEFAULT_COLOR = Color.parseColor("#1abc9c");

    private Paint mPaint;
    private Paint mBorderPaint;
    private RectF mBorderRectF;
    private Path mPath;

    private int mBorderWidth = dp2px(3);
    private float mBorderRadius = dp2px(2);
    private float mHaftBorderRadius = mBorderRadius / 2;

    private int mWaveWidth;
    private int mWaveHeight;

    private int mAmplitude = dp2px(3);
    private float mAngularVelocity = 2.0f;

    private int mAngle = 0;
    private float mWaveProgressHeight = 50;

    private int mMax = 100;
    private int mProgress = 0;

    private int mWaveColor = DEFAULT_COLOR;

    private ObjectAnimator mAngleAnim;

    public WaveProgressView(Context context) {
        this(context, null);
    }

    public WaveProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        int attrId = getResources().getIdentifier("colorAccent", "attr", getContext().getPackageName());
        if (attrId > 0){
            TypedValue colorAccent = new TypedValue();
            getContext().getTheme().resolveAttribute(attrId, colorAccent, true);
            mWaveColor = colorAccent.data;
        }


        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WaveProgressView);

        mBorderWidth = a.getDimensionPixelSize(R.styleable.WaveProgressView_waveBorderWidth, mBorderWidth);
        mAmplitude = a.getDimensionPixelSize(R.styleable.WaveProgressView_waveAmplitude, mAmplitude);
        mBorderRadius = a.getDimensionPixelSize(R.styleable.WaveProgressView_waveBorderRadius, (int)mBorderRadius);
        mHaftBorderRadius = mBorderRadius / 2;

        mWaveColor = a.getColor(R.styleable.WaveProgressView_waveColor, mWaveColor);
        mMax = a.getInt(R.styleable.WaveProgressView_waveMax, 100);
        mProgress = a.getInteger(R.styleable.WaveProgressView_waveProgress, 0);

        a.recycle();

        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mWaveColor);

        mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBorderPaint.setColor(mWaveColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);
        mBorderPaint.setStyle(Paint.Style.STROKE);

        mBorderRectF = new RectF();
        mPath = new Path();

        setupAngleAnim();
    }

    private void setupAngleAnim() {
        if (!isViewVisiable()){
            return;
        }
        if (mAngleAnim == null) {
            mAngleAnim = ObjectAnimator.ofInt(this, "angle", 0, 360);
            mAngleAnim.setDuration(800);
            mAngleAnim.setRepeatMode(ObjectAnimator.RESTART);
            mAngleAnim.setRepeatCount(ObjectAnimator.INFINITE);
            mAngleAnim.setInterpolator(new LinearInterpolator());
        }
        if (!mAngleAnim.isRunning()) {
            mAngleAnim.start();
        }
    }

    private void cancelAngleAnim(){
        if (mAngleAnim != null){
            mAngleAnim.cancel();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWaveWidth = (int) (getWidth() - mHaftBorderRadius);
        mWaveHeight = (int) (getHeight() - mHaftBorderRadius);

        mBorderRectF.set(mHaftBorderRadius, mHaftBorderRadius, mWaveWidth, mWaveHeight);
    }

    private void updatePath() {
        this.mPath.reset();
        for (int i = 0; i < mWaveWidth; i++) {
            int x = i;
            int y = (int) clamp(
                    mAmplitude * Math.sin((i* mAngularVelocity + mAngle * Math.PI) / 180.0f) + (mWaveHeight - mWaveProgressHeight),
                    mHaftBorderRadius,
                    mWaveHeight
            );
            if (i == 0) {
                this.mPath.moveTo( x, y);
            }
            this.mPath.quadTo( x, y, x + 1, y);
        }
        this.mPath.lineTo(mWaveWidth, mWaveHeight);
        this.mPath.lineTo(0, mWaveHeight);
        this.mPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        updatePath();

        canvas.drawPath(mPath, mPaint);
        canvas.drawRoundRect(mBorderRectF, mBorderRadius, mBorderRadius, mBorderPaint);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setupAngleAnim();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancelAngleAnim();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        startOrCancelAngleAnim();
    }

    @Override
    public void setAlpha(float alpha) {
        super.setAlpha(alpha);
        startOrCancelAngleAnim();
    }

    private void startOrCancelAngleAnim() {
        if (isViewVisiable()) {
            setupAngleAnim();
        }else {
            cancelAngleAnim();
        }
    }

    private boolean isViewVisiable(){
        return getVisibility() == VISIBLE && getAlpha()*255>0;
    }

    public int getMax() {
        return mMax;
    }

    public int getProgress() {
        return mProgress;
    }

    public void setMax(int max) {
        mMax = max;
    }

    public void setProgress(int progress) {
        mProgress = progress;
        if (mProgress > mMax){
            mProgress = mMax;
        }
        if (mProgress < 0){
            mProgress = 0;
        }
        float pecent = mProgress * 1.0f / mMax;
        mWaveProgressHeight = pecent * mWaveHeight;
        invalidate();
    }

    public int getWaveColor() {
        return mWaveColor;
    }

    public void setWaveColor(int waveColor) {
        mWaveColor = waveColor;
        mPaint.setColor(mWaveColor);
        mBorderPaint.setColor(mWaveColor);
    }

    public void setAngle(int angle){
        this.mAngle = angle;
        invalidate();
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
        bundle.putInt(STATE_MAX, mMax);
        bundle.putInt(STATE_PROGRESS, mProgress);
        bundle.putInt(STATE_WAVE_COLOR, mWaveColor);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle){
            Bundle bundle = (Bundle) state;
            mMax = bundle.getInt(STATE_MAX, 100);
            mProgress = bundle.getInt(STATE_PROGRESS, 0);
            mWaveColor = bundle.getInt(STATE_WAVE_COLOR, DEFAULT_COLOR);
            super.onRestoreInstanceState(bundle.getParcelable(STATE_INSTANCE));
            return ;
        }
        super.onRestoreInstanceState(state);
    }

    private static double clamp(double value, double max, double min) {
        return Math.max(Math.min(value, min), max);
    }

    private static int dp2px(int dp){
        return (int) (Resources.getSystem().getDisplayMetrics().density * dp);
    }
}

