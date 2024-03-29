package ma.my.grant.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ma.my.grant.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class GenerateReceipt extends AppCompatActivity {
    Bitmap b;
    String path;
    File folder;
    File file;
    File filePath;
    TextView tvNom, tvPrenom, tvCin, tvCne, tvEtablissement, tvDateNaissance, tvNoteBac, tvDateBac, tvFiliere, tvTel, tvEmail;
    private String signature_pdf_;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generate_receipt);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Button clickButton = findViewById(R.id.imprimer);

        clickButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                takeScreenShot();
                createPdf();
                viewPdf(fileName);
            }
        });
        tvNom = findViewById(R.id.nomGenereted);
        tvNom.setText(getIntent().getStringExtra("nom"));
        tvPrenom = findViewById(R.id.prenomGenereted);
        tvPrenom.setText(getIntent().getStringExtra("prenom"));
        tvCin = findViewById(R.id.cinGenereted);
        tvCin.setText(getIntent().getStringExtra("cin"));
        tvCne = findViewById(R.id.cinGenereted);
        tvCin.setText(getIntent().getStringExtra("cin"));
        tvCne = findViewById(R.id.cneGenereted);
        tvCne.setText(getIntent().getStringExtra("cne"));
        tvEtablissement = findViewById(R.id.etablissementGenereted);

        String eta = getIntent().getStringExtra("etablissement");
        tvEtablissement.setText(eta);
        tvDateNaissance = findViewById(R.id.datenaissanceGenereted);
        tvDateNaissance.setText(getIntent().getStringExtra("datenaissance"));
        tvCne = findViewById(R.id.cneGenereted);
        tvCne.setText(getIntent().getStringExtra("cne"));
        tvNoteBac = findViewById(R.id.notebacGenereted);
        tvNoteBac.setText(getIntent().getStringExtra("notebac"));
        tvDateBac = findViewById(R.id.datebacGenereted);
        tvDateBac.setText(getIntent().getStringExtra("datebac"));
        tvFiliere = findViewById(R.id.filiereGenereted);
        String fil = getIntent().getStringExtra("filiere");
        tvFiliere.setText(fil);
        tvTel = findViewById(R.id.telGenereted);
        tvTel.setText(getIntent().getStringExtra("tel"));
        tvEmail = findViewById(R.id.emailGenereted);
        tvEmail.setText(getIntent().getStringExtra("email"));

    }


    private void takeScreenShot() {


        folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Signature/");

        if (!folder.exists()) {
            boolean success = folder.mkdir();
        }

        path = folder.getAbsolutePath();

        fileName = String.valueOf(System.currentTimeMillis());


        path = path + "/" + signature_pdf_ + fileName + ".pdf";// path where pdf will be stored

        View u = findViewById(R.id.scroll);
        ScrollView z = findViewById(R.id.scroll); // parent view
        int totalHeight = z.getChildAt(0).getHeight();// parent view height
        int totalWidth = z.getChildAt(0).getWidth();// parent view width

        String extr = Environment.getExternalStorageDirectory() + "/Signature/";
        file = new File(extr);
        if (!file.exists())
            file.mkdir();

        String signatcure_img_ = null;
        String fileName = signatcure_img_ + ".jpg";
        File myPath = new File(extr, fileName);
        String imagesUri = myPath.getPath();
        FileOutputStream fos = null;
        b = getBitmapFromView(u, totalHeight, totalWidth);

        try {
            fos = new FileOutputStream(myPath);
            b.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        createPdf();
    }


    public Bitmap getBitmapFromView(View view, int totalHeight, int totalWidth) {

        Bitmap returnedBitmap = Bitmap.createBitmap(totalWidth, totalHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }

    private void createPdf() {
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(b.getWidth(), b.getHeight(), 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        canvas.drawPaint(paint);
        Bitmap bitmap = Bitmap.createScaledBitmap(b, b.getWidth(), b.getHeight(), true);
        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);
        filePath = new File(path);
        try {
            document.writeTo(new FileOutputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        document.close();
    }

    private void viewPdf(String file) {
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/Signature/null" + file + ".pdf");
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("file://" + pdfFile.getAbsolutePath()));
        startActivity(browserIntent);
    }

}
