package ma.my.grant.activities;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ma.my.grant.R;
import ma.my.grant.adapters.EstablishmentsAdapter;
import ma.my.grant.adapters.StudentBrothersAdapter;
import ma.my.grant.models.Brother;
import ma.my.grant.utilities.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ManageStudentBrothers extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Brother> list;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_student_brothers);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        spinner = findViewById(R.id.university);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleViewContainer);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Button add = findViewById(R.id.add_establishment);
        final TextView first = findViewById(R.id.brother_first_name);
        final TextView last = findViewById(R.id.brother_last_name);
        final TextView birthDate = findViewById(R.id.brother_birth_date);
        final Spinner studying = findViewById(R.id.studying);
        final Spinner handicapped = findViewById(R.id.handicapped);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);
        String lastName = preferences.getString("last", "undefined");
        String studentId = preferences.getString("id", "undefined");
        last.setText(lastName);
        String[] array = {"Yes", "No"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studying.setAdapter(adapter);
        studying.setSelection(0);
        handicapped.setAdapter(adapter);
        handicapped.setSelection(0);
        getList();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //TODO
                    String query = "INSERT INTO brothers (id_b, first_name_b, last_name_b, birth_date_b, is_studying, is_handicapped) VALUES (DEFAULT, ?, ?, ?, ?, ?) RETURNING id_b";
                    PreparedStatement preparedStatement = Database.connection.prepareStatement(query);
                    preparedStatement.setString(1, first.getText().toString().trim());
                    preparedStatement.setString(2, last.getText().toString().trim());
                    Calendar cal = Calendar.getInstance();
                    Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate.getText().toString().trim());
                    cal.setTime(date1);
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);
                    java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
                    preparedStatement.setDate(3, sqlDate);
                    preparedStatement.setBoolean(4, studying.getSelectedItem().toString().equals("Yes"));
                    preparedStatement.setBoolean(5, handicapped.getSelectedItem().toString().equals("Yes"));
                    ResultSet result = preparedStatement.executeQuery();
                    if (result.next()) {
                        int brotherId = result.getInt(1);
                        query = "INSERT INTO have (id_s, id_b) VALUES (?, ?)";
                        preparedStatement = Database.connection.prepareStatement(query);
                        preparedStatement.setInt(1, Integer.parseInt(studentId));
                        preparedStatement.setInt(2, brotherId);
                        int status = preparedStatement.executeUpdate();
                        if (status == 1) {
                            showSnackBar("Saved", "#E91E63", 2000);
                            getList();
                        }
                    }
                } catch (SQLException | ParseException e) {
                    e.printStackTrace();
                }
            }
        });

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

    public void getList() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);
        String studentId = preferences.getString("id", "undefined");
        list.clear();
        try {
            String query = "SELECT * FROM brothers WHERE id_b IN(SELECT id_b FROM have WHERE id_s = ?)";
            PreparedStatement preparedStatement = Database.connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(studentId));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String birthDate = resultSet.getDate(4).toString();
                boolean isStudying = resultSet.getBoolean(5);
                boolean isHandicapped = resultSet.getBoolean(6);
                Brother element = new Brother(id, firstName, lastName, birthDate, isStudying, isHandicapped);
                list.add(element);
            }
            adapter = new StudentBrothersAdapter(ManageStudentBrothers.this, list);
            recyclerView.setAdapter(adapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
