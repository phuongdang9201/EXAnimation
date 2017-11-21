package com.example.asiantech.demoanimation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Copyright © 2017 AsianTech inc.
 * Created by phuongdn on 26/10/2017.
 */
public class PropertyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView mImgProperty;
    private TextView mTvTypeProperty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        initViews();
    }

    private void initViews() {
        mImgProperty = findViewById(R.id.imgProperty);
        mTvTypeProperty = findViewById(R.id.tvTypeProperty);
        Spinner spinner = findViewById(R.id.spinnerProperty);
        // Creating adapter for spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.animator_array, R.layout.item_spinner);
        // Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(adapter);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Button Animation programmatically
        Button btnProgram = findViewById(R.id.btnPropertyProgram);
        btnProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initViewProperty();
            }
        });
    }

    private void initViewProperty() {
        ValueAnimator skyAnim = ObjectAnimator.ofInt
                (findViewById(R.id.imgProperty), "backgroundColor",
                        Color.rgb(0x00, 0x00, 0x00), Color.rgb(0x00, 0x66, 0x99));
        skyAnim.setDuration(3000);
        skyAnim.setRepeatCount(ValueAnimator.INFINITE);
        skyAnim.setRepeatMode(ValueAnimator.REVERSE);
        skyAnim.setEvaluator(new ArgbEvaluator());
        skyAnim.start();

        ObjectAnimator cloudAnim = ObjectAnimator.ofFloat(findViewById(R.id.imgProperty), "x", -350);
        cloudAnim.setDuration(3000);
        cloudAnim.setRepeatCount(ValueAnimator.INFINITE);
        cloudAnim.setRepeatMode(ValueAnimator.REVERSE);
        cloudAnim.start();

        ObjectAnimator cloudAnim2 = ObjectAnimator.ofFloat(findViewById(R.id.imgProperty), "x", -300);
        cloudAnim2.setDuration(3000);
        cloudAnim2.setRepeatCount(ValueAnimator.INFINITE);
        cloudAnim2.setRepeatMode(ValueAnimator.REVERSE);
        cloudAnim2.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Animator animator;
        switch (position) {
            case 1:
                animator = AnimatorInflater.loadAnimator(this, R.animator.move);
                mTvTypeProperty.setText(getString(R.string.animation_title_move));
                break;
            case 2:
                animator = AnimatorInflater.loadAnimator(this, R.animator.rotation);
                mTvTypeProperty.setText(getString(R.string.animation_title_rotate));
                break;
            case 3:
                animator = AnimatorInflater.loadAnimator(this, R.animator.scale);
                mTvTypeProperty.setText(getString(R.string.animation_title_scale));
                break;
            case 4:
                animator = AnimatorInflater.loadAnimator(this, R.animator.animator_sequential);
                mTvTypeProperty.setText(getString(R.string.animation_title_sequential));
                break;
            case 5:
                animator = AnimatorInflater.loadAnimator(this, R.animator.animator_together);
                mTvTypeProperty.setText(getString(R.string.animation_title_together));
                break;
            default:
                animator = AnimatorInflater.loadAnimator(this, R.animator.fade);
                mTvTypeProperty.setText(getString(R.string.animation_title_fade));
                break;
        }
        animator.setTarget(mImgProperty);
        animator.start();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // No-op.
    }
}
