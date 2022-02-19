package ma.my.grant.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import ma.my.grant.R;
import ma.my.grant.utilities.Database;
import ma.my.grant.utilities.Password;
import ma.my.grant.utilities.SnackBar;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Register extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        final EditText email = findViewById(R.id.email);
        final EditText phone_number = findViewById(R.id.phone_number);
        final EditText password = findViewById(R.id.password);
        final EditText repeatedPassword = findViewById(R.id.repeat_password);
        Button logIn = findViewById(R.id.log_in);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, LogIn.class));
            }
        });
        Button register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!email.getText().toString().trim().isEmpty() && !phone_number.getText().toString().trim().isEmpty() && !password.getText().toString().trim().isEmpty() && !repeatedPassword.getText().toString().trim().isEmpty()) {
                        if(email.getText().toString().endsWith("@taalim.ma")){
                            if (password.getText().toString().equals(repeatedPassword.getText().toString())) {
                                String query = "INSERT INTO students (id_s, email_s, phone_number_s, password_s) VALUES (DEFAULT, ?, ?, ?);";
                                PreparedStatement preparedStatement = Database.connection.prepareStatement(query);
                                preparedStatement.setString(1, email.getText().toString().trim());
                                preparedStatement.setString(2, phone_number.getText().toString().trim());
                                preparedStatement.setString(3, Password.hashPassword(password.getText().toString().trim()));
                                int result = preparedStatement.executeUpdate();
                                if (result == 1) {
                                    SnackBar.showSnackBar(Register.this, "Your account has been created successfully !", SnackBar.SUCCESS);
                                    new CountDownTimer(2000, 1000) {
                                        public void onTick(long millisUntilFinished) {
                                        }

                                        public void onFinish() {
                                            startActivity(new Intent(Register.this, LogIn.class));
                                            finish();
                                        }
                                    }.start();
                                } else {
                                    SnackBar.showSnackBar(Register.this, "The given email or phone number is already taken !", SnackBar.ERROR);
                                }
                            } else {
                                SnackBar.showSnackBar(Register.this, "The given passwords did not match !", SnackBar.WARNING);
                            }
                        }else{
                            SnackBar.showSnackBar(Register.this, "Please enter a valid taalim.ma email !", SnackBar.WARNING);
                        }

                    } else {
                        SnackBar.showSnackBar(Register.this, "Please fill in all the blanks correctly !", SnackBar.WARNING);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
