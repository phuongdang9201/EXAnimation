package com.example.asiantech.demoanimation;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Copyright © 2017 AsianTech inc.
 * Created by phuongdn on 30/10/2017.
 */
public class LoadingDialog extends DialogFragment {

    static LoadingDialog newInstance() {
        return new LoadingDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        // Init dialog fragment
        if (dialog.getWindow() != null) {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        // Set content for layout
        dialog.setContentView(R.layout.dialog_loading);

        // Set animation for image
        ImageView imgProgress = dialog.findViewById(R.id.imgProgress);
        imgProgress.setBackgroundResource(R.drawable.list_anim_dialog);
        AnimationDrawable animation = (AnimationDrawable) imgProgress.getBackground();
        animation.start();

        // Set property for dialog
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }
}
