package ma.my.grant.utilities;

import android.content.Context;
import co.nedim.maildroidx.MaildroidX;
import co.nedim.maildroidx.MaildroidXType;
import ma.my.grant.R;

public class Mail {
    public static boolean sendMailToAdmin(Context context, String from, String subject, String body) {
        try {
            MaildroidX.Builder builder = new MaildroidX.Builder();
            builder.smtp("smtp.gmail.com")
                    .smtpUsername(context.getResources().getString(R.string.email))
                    .smtpPassword(context.getResources().getString(R.string.password))
                    .port("465")
                    .type(MaildroidXType.HTML)
                    .to("y.lafryhi@gmail.com")
                    .from(from)
                    .subject(subject)
                    .body(body)
                    .isJavascriptDisabled();
            builder.mail();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean sendMailToStudent(Context context, String to, String subject, String body) {
        MaildroidX.Builder builder = new MaildroidX.Builder();
        builder.smtp("smtp.gmail.com")
                .smtpUsername(context.getResources().getString(R.string.email))
                .smtpPassword(context.getResources().getString(R.string.password))
                .port("465")
                .type(MaildroidXType.HTML)
                .to(to)
                .from("my.grant.ensetm@gmail.com")
                .subject(subject)
                .body(body)
                .isJavascriptDisabled();
        builder.mail();
        return true;
    }
}
