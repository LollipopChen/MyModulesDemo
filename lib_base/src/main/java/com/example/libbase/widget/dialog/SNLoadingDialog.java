package com.example.libbase.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.libbase.R;
import com.example.libbase.utils.SNStringUtils;

public class SNLoadingDialog {
    private Context mContext;
    private ImageView image;
    private LinearLayout layout;
    private TextView text;
    private Dialog loadingDialog;

    public SNLoadingDialog(Context context) {
        this(context, "");
    }

    public SNLoadingDialog(Context context, int resId) {
        this(context, context.getString(resId));
    }

    public SNLoadingDialog(Context context, String message) {
        this.mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.sn_layout_loading_dialog, null);

        layout = view.findViewById(R.id.sn_loading_dialog_view);
        image = view.findViewById(R.id.sn_loading_dialog_image);
        text = view.findViewById(R.id.sn_loading_dialog_text);
        if (!SNStringUtils.isEmpty(message)) {
            text.setText(message);
        }

        loadingDialog = new Dialog(context, R.style.SNLoadingDialog);
        loadingDialog.setCancelable(false);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
    }

    public void setCancelable(boolean isCancelable) {
        loadingDialog.setCancelable(isCancelable);
    }

    public void showProgress() {
        if (loadingDialog != null && !loadingDialog.isShowing()) {
            Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.sn_loading_animation);
            image.startAnimation(anim);
            loadingDialog.show();
        }
    }

    public void dismissProgress() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    public boolean isShow() {
        return loadingDialog.isShowing();
    }
}
