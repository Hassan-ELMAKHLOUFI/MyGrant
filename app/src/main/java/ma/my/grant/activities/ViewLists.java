package ma.my.grant.activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ma.my.grant.R;
import ma.my.grant.adapters.StudentsAdapter;
import ma.my.grant.models.Student;
import ma.my.grant.utilities.Database;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewLists extends AppCompatActivity {
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_lists);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        spinner = findViewById(R.id.provinces);
        Button generate = findViewById(R.id.generate);
        final TextView universityName = findViewById(R.id.university_name);
        final TextView universityCity = findViewById(R.id.university_city);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPdfFile("list.pdf");
            }
        });
        fillSpinner();


    }

    private void viewPdfFile(String name) {
        File file = new File(Environment.getExternalStorageDirectory() + "/MyGrant/" + name);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("file://" + file.getAbsolutePath()));
        browserIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(browserIntent);
    }

    public void showSnackBar(String message, String color, int visibilityTime) {
        final TextView alert = findViewById(R.id.alert);
        alert.bringToFront();
        alert.setBackground(getDrawable(R.drawable.error_alert));
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -140);
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


    private void fillSpinner() {
        String[] provinces =
                {
                        "Tanger-Assilah",
                        "M'diq-Fnideq",
                        "Tétouan ",
                        "Fahs-Anjra",
                        "Larache",
                        "Al Hoceima",
                        "Chefchaouen",
                        "Ouazzane",
                        "Oujda-Angad",
                        "Nador",
                        "Driouch",
                        "Jerada",
                        "Berkan",
                        "Taourirt",
                        "Guercif",
                        "Figuig",
                        "Fès",
                        "Meknès",
                        "El Hajeb",
                        "Ifrane",
                        "Moulay Yacoub",
                        "Sefrou",
                        "Boulemane",
                        "Taounate",
                        "Taza",
                        "Rabat",
                        "Salé",
                        "Skhirate-Témara",
                        "Kénitra",
                        "Khémisset",
                        "Sidi Kacem",
                        "Sidi Slimane",
                        "Béni Mellal",
                        "Azilal",
                        "Fquih Ben Salah",
                        "Khénifra",
                        "Khouribga​", "Casablanca",
                        "Mohammadia",
                        "El Jadida",
                        "Nouaceur",
                        "Médiouna",
                        "Benslimane",
                        "Berrechid",
                        "Settat",
                        "Sidi Bennour", "Marrakech",
                        "Chichaoua",
                        "Al Haouz",
                        "Kelâa des Sraghna",
                        "Essaouira",
                        "Rehamna",
                        "Safi",
                        "Youssoufia",
                        "Errachidia",
                        "Ouarzazate",
                        "Midelt",
                        "Tinghir",
                        "Zagora​",
                        "​Agadir Ida-Ou-Tanane",
                        "Inezgane-Aït Melloul",
                        "Chtouka-Aït Baha",
                        "Taroudannt",
                        "Tiznit",
                        "Tata",
                        "Guelmim",
                        "Assa-Zag",
                        "Tan-Tan",
                        "Sidi Ifni",
                        "Laâyoune",
                        "Boujdour",
                        "Tarfaya",
                        "Es-Semara​",
                        "Oued Ed-Dahab",
                        "Aousserd​"
                };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, provinces);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
    }

}
