package ma.my.grant.utilities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import ma.my.grant.R;

public class SnackBar {
    public static final String SUCCESS = "#009688";
    public static final String ERROR = "#E91E63";
    public static final String WARNING = "#FFC107";

    public static void showSnackBar(Context context, String message, String color) {
        int visibilityTime = 2000;
        final TextView alert = ((Activity) context).findViewById(R.id.alert);
        alert.bringToFront();
        alert.setBackground(context.getDrawable(R.drawable.error_alert));
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -200);
        animation.setDuration(600);
        alert.setElevation(18);
        alert.setText(message);
        alert.setTextColor(Color.parseColor(color));
        alert.startAnimation(animation);
        animation.setFillAfter(true);

        new CountDownTimer(visibilityTime, 1000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                TranslateAnimation animation = new TranslateAnimation(0, 0, -140, 0);
                animation.setDuration(600);
                alert.startAnimation(animation);
                animation.setFillAfter(true);
            }
        }.start();
    }
}
