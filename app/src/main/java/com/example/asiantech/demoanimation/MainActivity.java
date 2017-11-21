package com.example.asiantech.demoanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        TextView tvViewAnimation = findViewById(R.id.tvViewAnimation);
        tvViewAnimation.setOnClickListener(this);

        TextView tvPropertyAnimation = findViewById(R.id.tvPropertyAnimation);
        tvPropertyAnimation.setOnClickListener(this);

        TextView tvPropertyDrawable = findViewById(R.id.tvDrawableAnimation);
        tvPropertyDrawable.setOnClickListener(this);

        TextView tvPhysicAnimation = findViewById(R.id.tvPhysicAnimation);
        tvPhysicAnimation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvViewAnimation:
                startActivityWithPushAnimation(new Intent(this, ViewActivity.class));
                break;
            case R.id.tvPropertyAnimation:
                startActivityWithPushAnimation(new Intent(this, PropertyActivity.class));
                break;
            case R.id.tvDrawableAnimation:
                startActivityWithPushAnimation(new Intent(this, DrawableActivity.class));
                break;
            case R.id.tvPhysicAnimation:
                startActivityWithPushAnimation(new Intent(this, PhysicActivity.class));
                break;
            default:
                Log.d("xxx", "Error not found id of view!");
                break;
        }
    }

    public void startActivityWithPushAnimation(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
