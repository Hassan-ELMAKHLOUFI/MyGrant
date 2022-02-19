package ma.my.grant.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import ma.my.grant.R;


public class SingleStudentView extends AppCompatActivity {
    private EditText firstName, lastName, email, phoneNumber, newPassword;
    private Button cancel, edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_student_view);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phone_number);
        newPassword = findViewById(R.id.new_password);
        cancel = findViewById(R.id.go_back);
        edit = findViewById(R.id.edit);


        final SharedPreferences preferences = getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);
        String firstNameText = preferences.getString("first", "undefined");
        String lastNameText = preferences.getString("last", "undefined");
        String emailText = preferences.getString("email", "undefined");
        String phoneNumberText = preferences.getString("phone", "undefined");


        firstName.setText(firstNameText);
        lastName.setText(lastNameText);
        email.setText(emailText);
        phoneNumber.setText(phoneNumberText);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstName.getText().toString().trim().isEmpty() || lastName.getText().toString().isEmpty() || email.getText().toString().isEmpty() || phoneNumber.getText().toString().isEmpty() || newPassword.getText().toString().isEmpty()) {
                    Toast.makeText(SingleStudentView.this, "The fields may not be empty !", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });
    }


}

