package ma.my.grant.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import ma.my.grant.R;
import ma.my.grant.utilities.Database;

import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FillingInformation<simpleSpinner> extends AppCompatActivity {
    private SharedPreferences preferences;
    EditText editTextFirstname, editTextLastname, editTextBirthdate, editTextCity, editTextAdress, editTextCne, editTextCin, editTextSocilastatus, editTextEmail, editTextProfilephoto, editTextFullnumber, editTextPassword;
    Button btnEnregistrer, btnChoose, btnValider, addBrothers;
    String itemSElected1, itemSElected2;
    private Spinner editTextProvince;
    final int REQUEST_CODE_GALLERY = 999;
    ImageView imageView;
    EditText legalGuardianName, legalGuardianPhoneNumber, legalGuardianAnnualIncome;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    public static String imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return Base64.encodeToString(stream.toByteArray(), Base64.DEFAULT);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filling_information);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        preferences = getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);
        addBrothers = findViewById(R.id.add_brothers);
        editTextFirstname = findViewById(R.id.Firstname);
        editTextLastname = findViewById(R.id.Lastname);
        editTextBirthdate = findViewById(R.id.Birthdate);
        editTextCity = findViewById(R.id.City);
        editTextProvince = findViewById(R.id.Province);
        editTextAdress = findViewById(R.id.Adress);
        editTextCin = findViewById(R.id.Cin);
        editTextCne = findViewById(R.id.cne);
        editTextSocilastatus = findViewById(R.id.Socilastatus);
        editTextEmail = findViewById(R.id.email);
        imageView = findViewById(R.id.imageView);



        fillSpinner();

        addBrothers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FillingInformation.this, ManageStudentBrothers.class);
                startActivity(intent);
            }
        });

        btnChoose = findViewById(R.id.Profilephoto);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        FillingInformation.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });


        btnValider = findViewById(R.id.valider);
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextFirstname.getText().toString().isEmpty() || editTextLastname.getText().toString().isEmpty()
                        || editTextCin.getText().toString().isEmpty() || editTextCne.getText().toString().isEmpty()
                        || editTextBirthdate.getText().toString().isEmpty() || editTextCity.getText().toString().isEmpty()
                        || editTextAdress.getText().toString().isEmpty()
                        || editTextSocilastatus.getText().toString().isEmpty() || editTextProfilephoto.getText().toString().isEmpty() || editTextFullnumber.getText().toString().isEmpty()) {
                    showSnackBar("Rien pour enregistrer !", "#FFC107", 1500);
                } else {
                    //openDialog();}
                }
            }
        });
        btnEnregistrer = findViewById(R.id.enregistrer);
        btnEnregistrer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //  Log.d("ensetm","connected successfully to database");


                if (editTextFirstname.getText().toString().isEmpty() || editTextLastname.getText().toString().isEmpty()
                        || editTextCin.getText().toString().isEmpty() || editTextCne.getText().toString().isEmpty()
                        || editTextBirthdate.getText().toString().isEmpty() || editTextCity.getText().toString().isEmpty()
                        || editTextAdress.getText().toString().isEmpty()
                        || editTextSocilastatus.getText().toString().isEmpty() || editTextFullnumber.getText().toString().isEmpty()) {
                }


              /*

                    Snackbar.with(FillingInformation.this, null)
                            .type(Type.ERROR)
                            .message("Quelque informations manquantes ! faites attention!! ")
                            .duration(Duration.LONG)
                            .fillParent(true)
                            .textAlign(Align.LEFT)
                            .show();
*/
                // showSnackBar("Please fill in all the blanks correctly !", "#FFC107", 1500);


                else {


                    String Firstnamevalue = editTextFirstname.getText().toString();
                    String Lastnamevalue = editTextLastname.getText().toString();
                    String Cnevalue = editTextCne.getText().toString();
                    String Cinvalue = editTextCin.getText().toString();
                    String Birthdatevalue = editTextBirthdate.getText().toString();
                    String Cityvalue = editTextCity.getText().toString();
                    String Provincevalue = editTextProvince.getSelectedItem().toString();
                    String Adressvalue = editTextAdress.getText().toString();
                    String Socilastatusvalue = editTextSocilastatus.getText().toString();
                    //String Profilephotovalue = editTextProfilephoto.getText().toString();
                    int Fullnumbervalue = Integer.parseInt(editTextFullnumber.getText().toString());
                    String Emailvalue = editTextEmail.getText().toString();

                    Intent intent = new Intent(FillingInformation.this, GenerateReceipt.class);
                    intent.putExtra("Firstname", Firstnamevalue);
                    intent.putExtra("Lastname", Lastnamevalue);
                    intent.putExtra("Cne", Cnevalue);
                    intent.putExtra("Cin", Cinvalue);
                    intent.putExtra("Birthdate", Birthdatevalue);
                    intent.putExtra("City", Cityvalue);
                    intent.putExtra("Province", Provincevalue);
                    intent.putExtra("Adress", Adressvalue);
                    intent.putExtra("Socilastatus", Socilastatusvalue);
                    // intent.putExtra("Profilephoto", Profilephotovalue);
                    intent.putExtra("Fullnumber", Fullnumbervalue);
                    intent.putExtra("Email", Emailvalue);

                    startActivity(intent);
                    String databaseServerIP = getResources().getString(R.string.database_server_ip);
                    String databaseServerPort = getResources().getString(R.string.database_server_port);
                    String databaseName = getResources().getString(R.string.database_name);
                    String databaseUser = getResources().getString(R.string.database_user);
                    String databasePassword = getResources().getString(R.string.database_password);

                    try {
                        PreparedStatement statement = Database.connection.prepareStatement("UPDATE students SET first_name_s = ?, last_name_s = ?, cne_s = ?, cin_s = ?,birth_date_s = ?,city_s = ?,province_s = ?, address_s = ?, social_status_s = ?, PROFILE_PHOTO_S = ? WHERE email_s = ?");
                        statement.setString(1, Firstnamevalue);
                        statement.setString(2, Lastnamevalue);
                        statement.setString(3, Cnevalue);
                        statement.setString(4, Cinvalue);
                        Calendar cal = Calendar.getInstance();
                        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(Birthdatevalue);
                        cal.setTime(date1);
                        cal.set(Calendar.HOUR_OF_DAY, 0);
                        cal.set(Calendar.MINUTE, 0);
                        cal.set(Calendar.SECOND, 0);
                        cal.set(Calendar.MILLISECOND, 0);
                        java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());

                        statement.setDate(5, sqlDate);
                        statement.setString(6, Cityvalue);
                        statement.setString(7, Provincevalue);
                        statement.setString(8, Adressvalue);
                        statement.setString(9, Socilastatusvalue);
                        String image = imageViewToByte(imageView);
                        statement.setString(10, image);
                        statement.setString(11, preferences.getString("email", "undefined"));
                        Log.d("ensetm", "test test");
                        int i = statement.executeUpdate();

                    } catch (Exception e) {
                        Log.d("ensetm", e.getMessage());

                    }



/*
                    try {
                        String databaseServerIP = getResources().getString(R.string.database_server_ip);
                        String databaseServerPort = getResources().getString(R.string.database_server_port);
                        String databaseName = getResources().getString(R.string.database_name);
                        String databaseUser = getResources().getString(R.string.database_user);
                        String databasePassword = getResources().getString(R.string.database_password);
                        Database.connect(databaseServerIP, databaseServerPort,databaseName, databaseUser, databasePassword);


                      //  ResultSet resultSet = Database.executeQuery("INSERT INTO  \"student\"    (\"id_e\",\"Firstname\", \"Lastname\", \"Cne\", \"Cin\", \"Birthdate\",\"City\", \"Province\", \"Adress\", \"Socilastatus\", \"Profilephoto\", \"Fullnumber\",\"Email\")       VALUES(\'1\',\'" + Firstnamevalue + "\',\'" + Lastnamevalue+ "\',\'" + Cnevalue + "\',\'" + Cinvalue + "\'," + "to_date(\'" + Birthdatevalue+ "\', 'yyyy-mm-dd')" + ",\'" + Cityvalue+ "\',\'" + Provincevalue+ "\',\'" + Adressvalue  + "\',\'" + Socilastatusvalue + "\',\'" + Profilephotovalue+ "\',\'" + Fullnumbervalue+ "\',\'" + Emailvalue+ "\')");
                         ResultSet resultSet = Database.executeQuery("INSERT INTO  \"student\"    (\"id_e\",\"Firstname\", \"Lastname\")       VALUES(\'1\',\'" + Firstnamevalue + "\',\'" + Lastnamevalue+ "\'");

                        resultSet = Database.executeQuery("commit");


                    } catch (SQLException e) {
                        e.printStackTrace();
                        showSnackBar(e.getErrorCode() + "", "#FFC107", 1500);

                    }
*/

                }
            }

        });


    }


    public void showSnackBar(String message, String color, int visibilityTime) {
        final TextView alert = findViewById(R.id.Alert);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
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
        editTextProvince.setAdapter(adapter);
        editTextProvince.setSelection(0);
    }
}
