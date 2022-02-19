package ma.my.grant.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ma.my.grant.R;
import ma.my.grant.adapters.UniversitiesAdapter;
import ma.my.grant.models.University;
import ma.my.grant.utilities.Database;
import ma.my.grant.utilities.SnackBar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageUniversities extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<University> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_universities);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleViewContainer);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Button addUniversity = findViewById(R.id.add_university);
        Button reset = findViewById(R.id.reset);
        final TextView universityName = findViewById(R.id.university_name);
        final TextView universityCity = findViewById(R.id.university_city);

        getList();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                universityName.setText("");
                universityCity.setText("");
            }
        });

        addUniversity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (universityName.getText().toString().trim().isEmpty() || universityCity.getText().toString().trim().isEmpty()) {
                    SnackBar.showSnackBar(ManageUniversities.this, "The fields may not be blank !", SnackBar.WARNING);
                    //showSnackBar("The fields may not be blank !", "#E91E63", 2000);
                } else {
                    try {
                        String query = "INSERT INTO universities (id_u, name_u, city_u) VALUES (DEFAULT, ?, ?)";
                        PreparedStatement preparedStatement = Database.connection.prepareStatement(query);
                        preparedStatement.setString(1, universityName.getText().toString().trim());
                        preparedStatement.setString(2, universityCity.getText().toString().trim());
                        int result = preparedStatement.executeUpdate();
                        if (result == 1) {
                            universityName.setText("");
                            universityCity.setText("");
                            getList();
                            showSnackBar("Saved", "#E91E63", 2000);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public void showSnackBar(String message, String color, int visibilityTime) {
        final TextView alert = findViewById(R.id.alert);
        alert.bringToFront();
        alert.setBackground(getDrawable(R.drawable.error_alert));
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

    public void getList() {
        list.clear();
        //TODO:
        if (true) {
            //ImageView empty = findViewById(R.id.empty);
            //TextView nothing = findViewById(R.id.nothing);
            //empty.setVisibility(View.GONE);
            // nothing.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        try {
            String query = "SELECT * FROM universities";
            PreparedStatement preparedStatement = Database.connection.prepareStatement(query);
            //preparedStatement.setString(1, email.getText().toString().trim());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String city = resultSet.getString(3);
                University element = new University(id, name, city);
                list.add(element);
            }
            adapter = new UniversitiesAdapter(ManageUniversities.this, list);
            recyclerView.setAdapter(adapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
