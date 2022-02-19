package ma.my.grant.activities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import ma.my.grant.R;
import ma.my.grant.utilities.*;

public class ForgetPassword extends AppCompatActivity {
    EditText mEmail;
    EditText mTel;
    RadioGroup radioGroup;
    RadioButton radioTel, radioEmail;
    String randomCode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        mEmail = findViewById(R.id.Email);
        mTel = findViewById(R.id.Password);
        radioGroup = findViewById(R.id.radiogroup);
        radioTel = findViewById(R.id.radioTel);
        radioEmail = findViewById(R.id.radioEmail);
        Button Send = findViewById(R.id.send);
        Button validate = findViewById(R.id.validate);
        EditText newPassword = findViewById(R.id.new_password);
        EditText code = findViewById(R.id.code);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (code.getText().toString().equals(randomCode)) {
                    String generatedPassword = Password.generateRandomPassword(12, true, true, true, true);
                    try {
                        String query = "UPDATE students SET password_s = ? WHERE email_s = ?";
                        PreparedStatement preparedStatement = Database.connection.prepareStatement(query);
                        preparedStatement.setString(1, Password.hashPassword(newPassword.getText().toString()));
                        preparedStatement.setString(2, mEmail.getText().toString().trim());
                        int result = preparedStatement.executeUpdate();
                        if (result == 1) {
                            SnackBar.showSnackBar(ForgetPassword.this, "The operation completed successfully !", SnackBar.SUCCESS);
                        } else {
                            SnackBar.showSnackBar(ForgetPassword.this, "An error occurred, please try again !", SnackBar.ERROR);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if ((mEmail.getText().length() == 0) && (mTel.getText().length() == 0)) {
                    SnackBar.showSnackBar(ForgetPassword.this, "Please fill in all the blanks correctly !", SnackBar.WARNING);
                }
                if (!(radioTel.isChecked()) && !(radioEmail.isChecked())) {
                    SnackBar.showSnackBar(ForgetPassword.this, "Please fill in all the blanks correctly !", SnackBar.WARNING);
                } else {
                    int checked = radioGroup.getCheckedRadioButtonId();
                    String email = mEmail.getText().toString();
                    String phone = mTel.getText().toString();
                    try {
                        String query = "SELECT email_s, phone_number_s, id_s FROM students WHERE email_s = ?";
                        PreparedStatement preparedStatement = Database.connection.prepareStatement(query);
                        preparedStatement.setString(1, email.trim());
                        final ResultSet resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {
                            String retrievedPhoneNumber = resultSet.getString(2);
                            if (retrievedPhoneNumber.equals(phone)) {
                                Random random = new Random();
                                int code = random.nextInt(999999 + 1 - 111111) + 111111;
                                randomCode = String.valueOf(code);
                                if (radioTel.getId() == checked) {
                                    boolean sent = Sms.sendSms(ForgetPassword.this, phone.substring(1), "My Grant: Your verification code is: " + code);
                                    if (sent) {
                                        SnackBar.showSnackBar(ForgetPassword.this, "The code has been sent, Please check your phone", SnackBar.SUCCESS);
                                    }
                                } else if (radioEmail.getId() == checked) {
                                    Log.d("GLSID", "|" + email + "|");
                                    boolean sent = Mail.sendMailToStudent(ForgetPassword.this, email.trim(), "Verification code", "My Grant: Your verification code is: " + code);
                                    if (sent) {
                                        SnackBar.showSnackBar(ForgetPassword.this, "The code has been sent, Please check your inbox", SnackBar.SUCCESS);
                                    }
                                }
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        });
    }
}
