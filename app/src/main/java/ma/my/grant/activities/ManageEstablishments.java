package ma.my.grant.activities;

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
import ma.my.grant.adapters.UniversitiesAdapter;
import ma.my.grant.models.Establishment;
import ma.my.grant.models.University;
import ma.my.grant.utilities.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageEstablishments extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Establishment> list;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_establishments);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        spinner = findViewById(R.id.university);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleViewContainer);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Button add = findViewById(R.id.add_establishment);
        final TextView name = findViewById(R.id.establishment_name);
        final TextView city = findViewById(R.id.establishment_city);
        final TextView address = findViewById(R.id.establishment_address);

        fillSpinner();
        getList();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String query = "INSERT INTO establishments (id_e, id_u, name_e, city_e, address_e) VALUES (DEFAULT, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = Database.connection.prepareStatement(query);
                    preparedStatement.setInt(1, spinner.getSelectedItemPosition()+1);//TODO
                    preparedStatement.setString(2, name.getText().toString().trim());
                    preparedStatement.setString(3, city.getText().toString().trim());
                    preparedStatement.setString(4, address.getText().toString().trim());
                    int result = preparedStatement.executeUpdate();
                    if (result == 1) {
                        showSnackBar("Saved", "#E91E63", 2000);
                        getList();
                    }
                } catch (SQLException e) {
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
        list.clear();
        try {
            String query = "SELECT * FROM establishments";
            PreparedStatement preparedStatement = Database.connection.prepareStatement(query);
            //preparedStatement.setString(1, email.getText().toString().trim());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int university = resultSet.getInt(2);
                String name = resultSet.getString(3);
                String city = resultSet.getString(4);
                String address = resultSet.getString(5);

                Establishment element = new Establishment(id, university, name, city, address);
                list.add(element);
            }
            adapter = new EstablishmentsAdapter(ManageEstablishments.this, list);
            recyclerView.setAdapter(adapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillSpinner() {
        ArrayList<String> strings = new ArrayList<>();
        try {
            String query = "SELECT * FROM universities";
            PreparedStatement preparedStatement = Database.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String city = resultSet.getString(3);
                strings.add(name);
            }
            adapter = new EstablishmentsAdapter(ManageEstablishments.this, list);
            recyclerView.setAdapter(adapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strings.toArray(new String[]{}));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
    }

}
