package ma.my.grant.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ma.my.grant.R;
import ma.my.grant.adapters.StudentsAdapter;
import ma.my.grant.models.Student;
import ma.my.grant.utilities.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewStudents extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Student> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_students);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleViewContainer);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getList();

    }

    public void getList() {
        list.clear();
        try {
            String query = "SELECT id_s, first_name_s, last_name_s, cne_s FROM students";
            PreparedStatement preparedStatement = Database.connection.prepareStatement(query);
            //preparedStatement.setString(1, email.getText().toString().trim());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String first = resultSet.getString(2);
                String last = resultSet.getString(3);
                String cne = resultSet.getString(4);
                Student element = new Student();
                element.setId(id);
                element.setFirstName(first);
                element.setLastName(last);
                element.setCne(cne);
                list.add(element);
            }
            adapter = new StudentsAdapter(ViewStudents.this, list);
            recyclerView.setAdapter(adapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
