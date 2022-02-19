package ma.my.grant.utilities;

import android.content.Context;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.layout.font.FontProvider;
import ma.my.grant.R;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;

public class Sms {
    public static boolean sendSms(Context context, String to, String body) {
        Document document = null;
        try {
            document = Jsoup.connect("https://rest.nexmo.com/sms/json").ignoreContentType(true)
                    .data("from", "MY GRANT")
                    .data("text", body)
                    .data("to", "212" + to)
                    .data("api_key", context.getResources().getString(R.string.nexmo_api_key))
                    .data("api_secret", context.getResources().getString(R.string.nexmo_api_secret))
                    .userAgent("Mozilla")
                    .post();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println(document);
        return true;
    }
}
