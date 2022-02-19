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
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.layout.font.FontProvider;
import ma.my.grant.R;
import ma.my.grant.adapters.GrantsAdapter;
import ma.my.grant.adapters.StudentsAdapter;
import ma.my.grant.adapters.UniversitiesAdapter;
import ma.my.grant.models.Student;
import ma.my.grant.utilities.Database;
import ma.my.grant.utilities.Password;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GenerateLists extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Student> list;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generate_lists);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        spinner = findViewById(R.id.provinces);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleViewContainer);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Button generate = findViewById(R.id.generate);

        fillSpinner();
        //TODO
        getList();
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getList();
                String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
                String type = "Liste des étuduants boursiers";
                String province = spinner.getSelectedItem().toString();
                try {
                    String query = "SELECT id_s, first_name_s, last_name_s, cne_s, cin_s, birth_date_s FROM students";
                    PreparedStatement preparedStatement = Database.connection.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    ArrayList<Student> students = new ArrayList<>();
                    while (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        String first = resultSet.getString(2);
                        String last = resultSet.getString(3);
                        String cne = resultSet.getString(4);
                        String cin = resultSet.getString(5);
                        String birthDate = resultSet.getDate(6).toString();
                        Student element = new Student();
                        element.setId(id);
                        element.setFirstName(first);
                        element.setLastName(last);
                        element.setCne(cne);
                        element.setCin(cin);
                        element.setBirthDate(birthDate);
                        students.add(element);
                    }
                    String content = prepareTemplate(type, province, date, students);
                    createHtmlFile(content);
                    createPdfFile(province + ".pdf");


                        viewPdfFile(province + "list.pdf");


                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void getList() {
        list.clear();
        try {
            String query = "SELECT id_s, first_name_s, last_name_s, cne_s FROM students";
            PreparedStatement preparedStatement = Database.connection.prepareStatement(query);
           // preparedStatement.setString(1, spinner.getSelectedItem().toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String first = resultSet.getString(2);
                String last = resultSet.getString(2);
                String cne = resultSet.getString(4);
                Student element = new Student();
                element.setId(id);
                element.setFirstName(first);
                element.setLastName(last);
                element.setCne(cne);
                list.add(element);
            }
            adapter = new StudentsAdapter(GenerateLists.this, list);
            recyclerView.setAdapter(adapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }
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


    public void createHtmlFile(String content) {
        try {
            File root = new File(Environment.getExternalStorageDirectory(), "MyGrant");
            if (!root.exists()) {
                root.mkdirs();
            }
            File file = new File(root, "temporary.html");
            FileWriter writer = new FileWriter(file);
            writer.append(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String prepareTemplate(String type, String province, String date, ArrayList<Student> students) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("province", province);
        velocityContext.put("type", type);
        velocityContext.put("date", date);
        velocityContext.put("students", students);
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("resource.loader", "android");
        velocityEngine.setProperty("android.resource.loader.class", "ma.my.grant.utilities.Loader");
        velocityEngine.setProperty("context", getApplicationContext());
        velocityEngine.setProperty("velocimacro.library", "");
        velocityEngine.init();
        Template template = velocityEngine.getTemplate("list.vm");
        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext, stringWriter);
        return stringWriter.toString();
    }

    public void createPdfFile(String name) throws IOException {
        ConverterProperties properties = new ConverterProperties();
        FontProvider fontProvider = new DefaultFontProvider();
        FontProgram fontProgram = FontProgramFactory.createFont(Environment.getExternalStorageDirectory() + "/MyGrant/fonts/font.ttf");
        fontProvider.addFont(fontProgram);
        properties.setFontProvider(fontProvider);
        HtmlConverter.convertToPdf(new File(Environment.getExternalStorageDirectory() + "/MyGrant/temporary.html"), new File(Environment.getExternalStorageDirectory() + "/MyGrant/" + name), properties);
    }

    private void viewPdfFile(String name) {
        File file = new File(Environment.getExternalStorageDirectory() + "/MyGrant/" + name);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("file://" + file.getAbsolutePath()));
        browserIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(browserIntent);
    }


}
