package ma.my.grant.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import ma.my.grant.R;

import java.util.ArrayList;

public class StudentDashboard extends AppCompatActivity {
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_dashboard);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        preferences = getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);

        TextView welcome = findViewById(R.id.welcome);
        RelativeLayout Menu1 = findViewById(R.id.menu1);
        RelativeLayout Menu2 = findViewById(R.id.menu2);
        RelativeLayout Menu3 = findViewById(R.id.menu3);
        RelativeLayout Menu4 = findViewById(R.id.menu4);
        Button logOut = findViewById(R.id.LogOut);

        welcome.setText("Welcome "+preferences.getString("first","undefined")+" "+preferences.getString("last","undefined"));
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.edit().putString("type", "undefined").apply();
                preferences.edit().putString("email", "undefined").apply();
                startActivity(new Intent(StudentDashboard.this, LogIn.class));
                finish();
            }
        });

        Menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentDashboard.this, FillingInformation.class));
            }
        });

        Menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentDashboard.this, ViewLists.class));
            }
        });


        Menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentDashboard.this, SubmitComplaint.class));
            }
        });


        Menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentDashboard.this, StudentProfile.class));
            }
        });
    }

    @Override
    public void onBackPressed() {

    }


}
