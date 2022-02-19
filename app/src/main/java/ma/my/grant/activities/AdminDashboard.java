package ma.my.grant.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import ma.my.grant.R;

public class AdminDashboard extends AppCompatActivity {
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_dashboard);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        preferences = getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);

        RelativeLayout menu1 = findViewById(R.id.menu1);
        RelativeLayout menu2 = findViewById(R.id.menu2);
        RelativeLayout menu3 = findViewById(R.id.menu3);
        RelativeLayout menu4 = findViewById(R.id.menu4);
        Button logOut = findViewById(R.id.LogOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.edit().putString("type", "undefined").apply();
                preferences.edit().putString("email", "undefined").apply();
                startActivity(new Intent(AdminDashboard.this, LogIn.class));
                finish();
            }
        });

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, ManageUniversities.class));
            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, ManageEstablishments.class));
            }
        });

        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, ViewStudents.class));
            }
        });


        menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, GenerateLists.class));
            }
        });
    }

    @Override
    public void onBackPressed() {

    }


}
