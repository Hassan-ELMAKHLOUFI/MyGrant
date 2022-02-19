package ma.my.grant.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import ma.my.grant.R;
import ma.my.grant.utilities.Mail;
import ma.my.grant.utilities.SnackBar;

public class SubmitComplaint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_complaint);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);
        Button submit = findViewById(R.id.submit);
        TextView complaint = findViewById(R.id.complaint);
        String studentEmailAddress = preferences.getString("email", "undefined");
        String studentFirstName = preferences.getString("first", "undefined");
        String studentLastName = preferences.getString("last", "undefined");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = complaint.getText().toString().trim();
                if (content.isEmpty()) {
                    SnackBar.showSnackBar(SubmitComplaint.this, "Please fill in all the blanks correctly !", SnackBar.WARNING);
                } else {
                    boolean status = Mail.sendMailToAdmin(SubmitComplaint.this, studentEmailAddress, "New complaint from : " + studentFirstName + " " + studentLastName, content);
                    if (status) {
                        SnackBar.showSnackBar(SubmitComplaint.this, "Your complaint has been sent successfully !", SnackBar.SUCCESS);
                        complaint.setText("");
                    }
                }
            }
        });

    }

}
