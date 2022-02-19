package ma.my.grant.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.StrictMode;
import android.view.Display;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.layout.font.FontProvider;
import ma.my.grant.R;
import ma.my.grant.utilities.Database;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;


public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        new CountDownTimer(4500, 1000) {
            public void onTick(long millisUntilFinished) {
                String databaseServerIP = getResources().getString(R.string.database_server_ip);
                String databaseServerPort = getResources().getString(R.string.database_server_port);
                String databaseName = getResources().getString(R.string.database_name);
                String databaseUser = getResources().getString(R.string.database_user);
                String databasePassword = getResources().getString(R.string.database_password);
                if (Database.connection == null)
                    Database.connect(databaseServerIP, databaseServerPort, databaseName, databaseUser, databasePassword);
            }

            public void onFinish() {
                final SharedPreferences preferences = getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);
                Intent intent = null;
                String type = preferences.getString("type", "undefined");
                switch (type) {
                    case "undefined":
                        intent = new Intent(getApplicationContext(), LogIn.class);
                        break;
                    case "admin":
                        intent = new Intent(getApplicationContext(), AdminDashboard.class);
                        break;
                    case "student":
                        intent = new Intent(getApplicationContext(), StudentDashboard.class);
                        break;
                }
                startActivity(intent);
                finish();
            }
        }.start();
        final ImageView splash = findViewById(R.id.logo);
        Display display = getWindowManager().getDefaultDisplay();
        float height = display.getHeight();
        TranslateAnimation animation = new TranslateAnimation(0, 0, -300, 0);
        animation.setDuration(3000);
        splash.startAnimation(animation);
    }


}
