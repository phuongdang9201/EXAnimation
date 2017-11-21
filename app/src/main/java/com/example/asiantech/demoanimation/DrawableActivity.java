package com.example.asiantech.demoanimation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Copyright © 2017 AsianTech inc.
 * Created by phuongdn on 26/10/2017.
 */
public class DrawableActivity extends AppCompatActivity {

    private ImageView mImgDrawable;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        initViews();
    }

    private void initViews() {
        mImgDrawable = findViewById(R.id.imgDrawable);
        Button btnLoadCharacter = findViewById(R.id.btnCharacterAnimation);
        btnLoadCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAnimationCharacter();
            }
        });

        Button btnLoadDialog = findViewById(R.id.btnDialogCustom);
        btnLoadDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAnimationDialog();
            }
        });
    }

    private void loadAnimationCharacter() {
        mImgDrawable.setBackgroundResource(R.drawable.list_anim_character);
        AnimationDrawable animation = (AnimationDrawable) mImgDrawable.getBackground();
        animation.start();
    }

    private void loadAnimationDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog.newInstance();
            mLoadingDialog.show(getSupportFragmentManager(), "DialogFragment");
            return;
        }
        mLoadingDialog.show(getSupportFragmentManager(), "DialogFragment");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        finish();
    }
}
