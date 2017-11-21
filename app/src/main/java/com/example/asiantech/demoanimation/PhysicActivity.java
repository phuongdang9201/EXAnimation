package com.example.asiantech.demoanimation;

import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Copyright © 2017 AsianTech inc.
 * Created by phuongdn on 26/10/2017.
 */
public class PhysicActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private static final int MIN_STIFFNESS = 1;
    private static final int MAX_PROGRESS = 100;
    private static final float MAX_STIFFNESS = SpringForce.STIFFNESS_HIGH;

    private float mDx = 0f;
    private float mDy = 0f;

    private View mDragView;
    private View mFirstView;
    private View mSecondView;
    private TextView mTvStiffness;
    private TextView mTvDampingRatio;
    private SeekBar mSeekBarStiffness;
    private SeekBar mSeekBarDampingRatio;

    private SpringAnimation mSpringFirstX;
    private SpringAnimation mSpringFirstY;
    private SpringAnimation mSecondXAnim;
    private SpringAnimation mSecondYAnim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physic);
        initViews();
        updateAnimation();
        updateValueForAnim();
    }

    private void initViews() {
        mDragView = findViewById(R.id.drag);
        mFirstView = findViewById(R.id.first);
        mSecondView = findViewById(R.id.second);
        mSeekBarStiffness = findViewById(R.id.stiffness_bar);
        mSeekBarDampingRatio = findViewById(R.id.damping_bar);
        mTvStiffness = findViewById(R.id.stiffness_value);
        mTvDampingRatio = findViewById(R.id.damping_value);
    }

    private void updateValueForAnim() {
        mSeekBarStiffness.setMax((int) (MAX_STIFFNESS - MIN_STIFFNESS));
        mSeekBarStiffness.setOnSeekBarChangeListener(this);

        mSeekBarDampingRatio.setMax(MAX_PROGRESS);
        mSeekBarDampingRatio.setOnSeekBarChangeListener(this);

        mSeekBarStiffness.setProgress((int) SpringForce.STIFFNESS_LOW - MIN_STIFFNESS);
        mSeekBarDampingRatio.setProgress((int) (SpringForce.DAMPING_RATIO_LOW_BOUNCY * 100));
    }


    private void updateAnimation() {
        // Create Spring animation
        mSpringFirstX = createSpringAnimation(mFirstView, SpringAnimation.X);
        mSpringFirstY = createSpringAnimation(mFirstView, SpringAnimation.Y);
        mSecondXAnim = createSpringAnimation(mSecondView, SpringAnimation.X);
        mSecondYAnim = createSpringAnimation(mSecondView, SpringAnimation.Y);

        // Get default view Y distance
        final float viewYDistance = getResources().getDimensionPixelSize(R.dimen.circle_size) + getResources().getDimensionPixelSize(R.dimen.circle_distance);

        // Update position first x animation
        mSpringFirstX.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() {
            @Override
            public void onAnimationUpdate(DynamicAnimation animation, float value, float velocity) {
                mSecondXAnim.animateToFinalPosition(value);
            }
        });

        // Update position first y animation
        mSpringFirstY.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() {
            @Override
            public void onAnimationUpdate(DynamicAnimation animation, float value, float velocity) {
                mSecondYAnim.animateToFinalPosition(value + viewYDistance);
            }
        });

        mDragView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    // capture the difference between view's top left corner and touch point
                    case MotionEvent.ACTION_DOWN:
                        mDx = v.getX() - event.getRawX();
                        mDy = v.getY() - event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //  a different approach would be to change the view's LayoutParams.
                        float newX = event.getRawX() + mDx;
                        float newY = event.getRawY() + mDy;
                        mDragView.animate().x(newX).y(newY).setDuration(0).start();
                        mSpringFirstX.animateToFinalPosition(newX);
                        mSpringFirstY.animateToFinalPosition(newY + viewYDistance);
                        break;
                }
                return true;
            }
        });
    }


    private void setStiffness(float stiffness) {
        mSpringFirstX.getSpring().setStiffness(stiffness);
        mSpringFirstY.getSpring().setStiffness(stiffness);
        mSecondXAnim.getSpring().setStiffness(stiffness);
        mSecondYAnim.getSpring().setStiffness(stiffness);
    }

    private void setDampingRatio(float dampingRatio) {
        mSpringFirstX.getSpring().setDampingRatio(dampingRatio);
        mSpringFirstY.getSpring().setDampingRatio(dampingRatio);
        mSecondXAnim.getSpring().setDampingRatio(dampingRatio);
        mSecondYAnim.getSpring().setDampingRatio(dampingRatio);
    }


    private SpringAnimation createSpringAnimation(View view, DynamicAnimation.ViewProperty property) {
        SpringAnimation springAnimation = new SpringAnimation(view, property);
        SpringForce springForce = new SpringForce();
        springAnimation.setSpring(springForce);
        return springAnimation;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.stiffness_bar:
                float actualStiffness = progress + MIN_STIFFNESS;
                mTvStiffness.setText(String.valueOf(actualStiffness));
                setStiffness(actualStiffness);
                break;
            case R.id.damping_bar:
                float actualDampingRatio = progress / 100f;
                mTvDampingRatio.setText(String.valueOf(actualDampingRatio));
                setDampingRatio(actualDampingRatio);
                break;
            default:
                Log.d("xxx", "error onProgressChanged!");
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // No-op.
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // No-op.
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        finish();
    }
}
