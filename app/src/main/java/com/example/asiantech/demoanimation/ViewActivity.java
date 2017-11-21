package com.example.asiantech.demoanimation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
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
public class ViewActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView mViewCircle;
    private TextView mTvTypeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        initViews();
    }

    private void initViews() {
        mViewCircle = findViewById(R.id.imgView);
        mTvTypeView = findViewById(R.id.tvTypeView);

        Spinner spinner = findViewById(R.id.spinnerSelection);
        // Creating adapter for spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.animation_array, R.layout.item_spinner);
        // Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(adapter);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Button Animation View Programmatically
        Button btnProgram = findViewById(R.id.btnViewProgram);
        btnProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMeBlink(mViewCircle, 200, 100);
                makeTranslation(mViewCircle, 200, 200);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Animation mAnimation;
        switch (position) {
            case 1:
                mAnimation = AnimationUtils.loadAnimation(ViewActivity.this, R.anim.fade_in);
                mTvTypeView.setText(getString(R.string.animation_title_fade_in));
                break;
            case 2:
                mAnimation = AnimationUtils.loadAnimation(ViewActivity.this, R.anim.fade_out);
                mTvTypeView.setText(getString(R.string.animation_title_fade_out));
                break;
            case 3:
                mAnimation = AnimationUtils.loadAnimation(ViewActivity.this, R.anim.move);
                mTvTypeView.setText(getString(R.string.animation_title_move));
                break;
            case 4:
                mAnimation = AnimationUtils.loadAnimation(ViewActivity.this, R.anim.rotate);
                mTvTypeView.setText(getString(R.string.animation_title_rotate));
                break;
            case 5:
                mAnimation = AnimationUtils.loadAnimation(ViewActivity.this, R.anim.slide_up);
                mTvTypeView.setText(getString(R.string.animation_title_slide_up));
                break;
            case 6:
                mAnimation = AnimationUtils.loadAnimation(ViewActivity.this, R.anim.slide_down);
                mTvTypeView.setText(getString(R.string.animation_title_slide_down));
                break;
            case 7:
                mAnimation = AnimationUtils.loadAnimation(ViewActivity.this, R.anim.zoom_in);
                mTvTypeView.setText(getString(R.string.animation_title_zoom_in));
                break;
            case 8:
                mAnimation = AnimationUtils.loadAnimation(ViewActivity.this, R.anim.zoom_out);
                mTvTypeView.setText(getString(R.string.animation_title_zoom_out));
                break;
            case 9:
                mAnimation = AnimationUtils.loadAnimation(ViewActivity.this, R.anim.anim_sequential);
                mTvTypeView.setText(getString(R.string.animation_title_sequential));
                break;
            case 10:
                mAnimation = AnimationUtils.loadAnimation(ViewActivity.this, R.anim.anim_together);
                mTvTypeView.setText(getString(R.string.animation_title_together));
                break;
            default:
                mAnimation = AnimationUtils.loadAnimation(ViewActivity.this, R.anim.blink);
                mTvTypeView.setText(getString(R.string.animation_title_blink));
                break;
        }
        mViewCircle.setAnimation(mAnimation);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // No-op.
    }

    private View makeMeBlink(View view, int duration, int offset) {
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setInterpolator(new AccelerateInterpolator());
        anim.setDuration(duration);
        anim.setStartOffset(offset);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        view.startAnimation(anim);
        return view;
    }

    private View makeTranslation(View view, int duration, int offset) {
        TranslateAnimation animation = new TranslateAnimation(
                Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0.0f,
                Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, -150.0f);
        animation.setDuration(duration);
        animation.setStartOffset(offset);
        view.startAnimation(animation);

        TranslateAnimation animation1 = new TranslateAnimation(
                Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, 0.0f,
                Animation.ABSOLUTE, 150.0f, Animation.ABSOLUTE, 0.0f);
        animation1.setDuration(duration);
        animation.setStartOffset(offset);
        view.startAnimation(animation1);
        return view;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        finish();
    }
}
