package ma.my.grant.activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import ma.my.grant.R;
import ma.my.grant.utilities.Database;
import ma.my.grant.utilities.Password;
import ma.my.grant.utilities.SnackBar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogIn extends AppCompatActivity {
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        preferences = getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        Button forget = findViewById(R.id.forget);
        Button logIn = findViewById(R.id.log_in);
        Button register = findViewById(R.id.register);

        ActivityCompat.requestPermissions(LogIn.this,
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET
                },
                1);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this, ForgetPassword.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this, Register.class));
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!email.getText().toString().trim().isEmpty() && !password.getText().toString().trim().isEmpty()) {
                        if (email.getText().toString().endsWith("@my-grant.ma") && email.getText().toString().contains(".")) {
                            String query = "SELECT password_a FROM administrators WHERE email_a = ?";
                            PreparedStatement preparedStatement = Database.connection.prepareStatement(query);
                            preparedStatement.setString(1, email.getText().toString().trim());
                            ResultSet resultSet = preparedStatement.executeQuery();
                            if (resultSet.next()) {
                                String retrievedPassword = resultSet.getString(1);
                                if (retrievedPassword.equals(Password.hashPassword(password.getText().toString()))) {
                                    SnackBar.showSnackBar(LogIn.this, "Connected successfully !", SnackBar.SUCCESS);
                                    new CountDownTimer(2000, 1000) {
                                        public void onTick(long millisUntilFinished) {
                                        }

                                        public void onFinish() {
                                            preferences.edit().putString("type", "admin").apply();
                                            preferences.edit().putString("email", email.getText().toString()).apply();
                                            startActivity(new Intent(LogIn.this, AdminDashboard.class));
                                            finish();
                                        }
                                    }.start();
                                } else {
                                    SnackBar.showSnackBar(LogIn.this, "The given credentials are incorrect !", SnackBar.ERROR);
                                }
                            } else {
                                SnackBar.showSnackBar(LogIn.this, "There is no admin with that email !", SnackBar.ERROR);
                            }
                        } else if (email.getText().toString().endsWith("@taalim.ma")) {
                            String query = "SELECT password_s, first_name_s, last_name_s, phone_number_s, id_s FROM students WHERE email_s = ?";
                            PreparedStatement preparedStatement = Database.connection.prepareStatement(query);
                            preparedStatement.setString(1, email.getText().toString().trim());
                            final ResultSet resultSet = preparedStatement.executeQuery();
                            if (resultSet.next()) {
                                String retrievedPassword = resultSet.getString(1);
                                if (retrievedPassword.equals(Password.hashPassword(password.getText().toString()))) {
                                    SnackBar.showSnackBar(LogIn.this, "Connected successfully !", SnackBar.SUCCESS);
                                    new CountDownTimer(2200, 1000) {
                                        public void onTick(long millisUntilFinished) {
                                        }

                                        public void onFinish() {
                                            preferences.edit().putString("type", "student").apply();
                                            preferences.edit().putString("email", email.getText().toString()).apply();
                                            try {
                                                preferences.edit().putString("first", resultSet.getString(2)).apply();
                                                preferences.edit().putString("last", resultSet.getString(3)).apply();
                                                preferences.edit().putString("phone", resultSet.getString(4)).apply();
                                                preferences.edit().putString("id", String.valueOf(resultSet.getInt(5))).apply();
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                            startActivity(new Intent(LogIn.this, StudentDashboard.class));
                                            finish();
                                        }
                                    }.start();
                                } else {
                                    SnackBar.showSnackBar(LogIn.this, "The given credentials are incorrect !", SnackBar.ERROR);
                                }
                            } else {
                                SnackBar.showSnackBar(LogIn.this, "There is no user with that email !", SnackBar.ERROR);
                            }
                        } else {
                            SnackBar.showSnackBar(LogIn.this, "The given email is invalid !", SnackBar.ERROR);
                        }
                    } else {
                        SnackBar.showSnackBar(LogIn.this, "Please fill in all the blanks correctly !", SnackBar.WARNING);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
