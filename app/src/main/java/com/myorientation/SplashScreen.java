package com.myorientation;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
               Log.d("ensetm","connected successfully to database");
                String databaseServerIP = getResources().getString(R.string.database_server_ip);
                String databaseServerPort = getResources().getString(R.string.database_server_port);
                String databaseName = getResources().getString(R.string.database_name);
                String databaseUser = getResources().getString(R.string.database_user);
                String databasePassword = getResources().getString(R.string.database_password);
                Database.connect(databaseServerIP, databaseServerPort,databaseName, databaseUser, databasePassword);









                String message = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                        "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                        "<head>\n" +
                        "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                        "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" +
                        "  <title>Skyline Confirm Email</title>\n" +
                        "  <style type=\"text/css\">\n" +
                        "    @import url(http://fonts.googleapis.com/css?family=Lato:400);\n" +
                        "\n" +
                        "    /* Take care of image borders and formatting */\n" +
                        "\n" +
                        "    img {\n" +
                        "      max-width: 600px;\n" +
                        "      outline: none;\n" +
                        "      text-decoration: none;\n" +
                        "      -ms-interpolation-mode: bicubic;\n" +
                        "    }\n" +
                        "\n" +
                        "    a {\n" +
                        "      text-decoration: none;\n" +
                        "      border: 0;\n" +
                        "      outline: none;\n" +
                        "    }\n" +
                        "\n" +
                        "    a img {\n" +
                        "      border: none;\n" +
                        "    }\n" +
                        "\n" +
                        "    /* General styling */\n" +
                        "\n" +
                        "    td, h1, h2, h3  {\n" +
                        "      font-family: Helvetica, Arial, sans-serif;\n" +
                        "      font-weight: 400;\n" +
                        "    }\n" +
                        "\n" +
                        "    body {\n" +
                        "      -webkit-font-smoothing:antialiased;\n" +
                        "      -webkit-text-size-adjust:none;\n" +
                        "      width: 100%;\n" +
                        "      height: 100%;\n" +
                        "      color: #37302d;\n" +
                        "      background: #ffffff;\n" +
                        "    }\n" +
                        "\n" +
                        "     table {\n" +
                        "      border-collapse: collapse !important;\n" +
                        "    }\n" +
                        "\n" +
                        "\n" +
                        "    h1, h2, h3 {\n" +
                        "      padding: 0;\n" +
                        "      margin: 0;\n" +
                        "      color: #ffffff;\n" +
                        "      font-weight: 400;\n" +
                        "    }\n" +
                        "\n" +
                        "    h3 {\n" +
                        "      color: #21c5ba;\n" +
                        "      font-size: 24px;\n" +
                        "    }\n" +
                        "\n" +
                        "    .important-font {\n" +
                        "      color: #21BEB4;\n" +
                        "      font-weight: bold;\n" +
                        "    }\n" +
                        "\n" +
                        "    .hide {\n" +
                        "      display: none !important;\n" +
                        "    }\n" +
                        "\n" +
                        "    .force-full-width {\n" +
                        "      width: 100% !important;\n" +
                        "    }\n" +
                        "  </style>\n" +
                        "\n" +
                        "  <style type=\"text/css\" media=\"screen\">\n" +
                        "    @media screen {\n" +
                        "       /* Thanks Outlook 2013! http://goo.gl/XLxpyl*/\n" +
                        "      td, h1, h2, h3 {\n" +
                        "        font-family: 'Lato', 'Helvetica Neue', 'Arial', 'sans-serif' !important;\n" +
                        "      }\n" +
                        "    }\n" +
                        "  </style>\n" +
                        "\n" +
                        "  <style type=\"text/css\" media=\"only screen and (max-width: 480px)\">\n" +
                        "    /* Mobile styles */\n" +
                        "    @media only screen and (max-width: 480px) {\n" +
                        "      table[class=\"w320\"] {\n" +
                        "        width: 320px !important;\n" +
                        "      }\n" +
                        "\n" +
                        "      table[class=\"w300\"] {\n" +
                        "        width: 300px !important;\n" +
                        "      }\n" +
                        "\n" +
                        "      table[class=\"w290\"] {\n" +
                        "        width: 290px !important;\n" +
                        "      }\n" +
                        "\n" +
                        "      td[class=\"w320\"] {\n" +
                        "        width: 320px !important;\n" +
                        "      }\n" +
                        "\n" +
                        "      td[class=\"mobile-center\"] {\n" +
                        "        text-align: center !important;\n" +
                        "      }\n" +
                        "\n" +
                        "      td[class*=\"mobile-padding\"] {\n" +
                        "        padding-left: 20px !important;\n" +
                        "        padding-right: 20px !important;\n" +
                        "        padding-bottom: 20px !important;\n" +
                        "      }\n" +
                        "\n" +
                        "      td[class*=\"mobile-block\"] {\n" +
                        "        display: block !important;\n" +
                        "        width: 100% !important;\n" +
                        "        text-align: left !important;\n" +
                        "        padding-bottom: 20px !important;\n" +
                        "      }\n" +
                        "\n" +
                        "      td[class*=\"mobile-border\"] {\n" +
                        "        border: 0 !important;\n" +
                        "      }\n" +
                        "\n" +
                        "      td[class*=\"reveal\"] {\n" +
                        "        display: block !important;\n" +
                        "      }\n" +
                        "    }\n" +
                        "  </style>\n" +
                        "</head>\n" +
                        "<body class=\"body\" style=\"padding:0; margin:0; display:block; background:#ffffff; -webkit-text-size-adjust:none\" bgcolor=\"#ffffff\">\n" +
                        "<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"100%\">\n" +
                        "  <tr>\n" +
                        "    <td align=\"center\" valign=\"top\" bgcolor=\"#ffffff\"  width=\"100%\">\n" +
                        "\n" +
                        "    <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n" +
                        "      <tr>\n" +
                        "        <td style=\"border-bottom: 3px solid #3bcdc3;\" width=\"100%\">\n" +
                        "          <center>\n" +
                        "            <table cellspacing=\"0\" cellpadding=\"0\" width=\"500\" class=\"w320\">\n" +
                        "              <tr>\n" +
                        "                <td valign=\"top\" style=\"padding:10px 0; text-align:left;\" class=\"mobile-center\">\n" +
                        "                  <img width=\"250\" height=\"62\" src=\"https://www.filepicker.io/api/file/TtSuN5UdTmO0NXWPfYCJ\">\n" +
                        "                </td>\n" +
                        "              </tr>\n" +
                        "            </table>\n" +
                        "          </center>\n" +
                        "        </td>\n" +
                        "      </tr>\n" +
                        "      <tr>\n" +
                        "        <td background=\"https://www.filepicker.io/api/file/kmlo6MonRpWsVuuM47EG\" bgcolor=\"#8b8284\" valign=\"top\" style=\"background: url(https://www.filepicker.io/api/file/kmlo6MonRpWsVuuM47EG) no-repeat center; background-color: #8b8284; background-position: center;\">\n" +
                        "          <!--[if gte mso 9]>\n" +
                        "          <v:rect xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"true\" stroke=\"false\" style=\"mso-width-percent:1000;height:303px;\">\n" +
                        "            <v:fill type=\"tile\" src=\"https://www.filepicker.io/api/file/kmlo6MonRpWsVuuM47EG\" color=\"#8b8284\" />\n" +
                        "            <v:textbox inset=\"0,0,0,0\">\n" +
                        "          <![endif]-->\n" +
                        "          <div>\n" +
                        "            <center>\n" +
                        "              <table cellspacing=\"0\" cellpadding=\"0\" width=\"530\" height=\"303\" class=\"w320\">\n" +
                        "                <tr>\n" +
                        "                  <td valign=\"middle\" style=\"vertical-align:middle; padding-right: 15px; padding-left: 15px; text-align:left;\" height=\"303\">\n" +
                        "\n" +
                        "                    <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n" +
                        "                      <tr>\n" +
                        "                        <td>\n" +
                        "                          <h1></h1><br>\n" +
                        "                          <h2>To track your order or make any changes please click the button below.</h2>\n" +
                        "                          <br>\n" +
                        "                        </td>\n" +
                        "                      </tr>\n" +
                        "                    </table>\n" +
                        "\n" +
                        "                    <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n" +
                        "                    <tr>\n" +
                        "                    <td class=\"hide reveal\">\n" +
                        "                      &nbsp;\n" +
                        "                    </td>\n" +
                        "                      <td style=\"width:150px; height:33px; background-color: #3bcdc3;\" >\n" +
                        "                        <div>\n" +
                        "                          <a href=\"#\" style=\"background-color:#3bcdc3;border-radius:4px;color:#ffffff;display:inline-block;font-family:sans-serif;font-size:13px;font-weight:bold;line-height:40px;text-align:center;text-decoration:none;width:150px;-webkit-text-size-adjust:none;\">My Order</a>\n" +
                        "                          </div>\n" +
                        "                      </td>\n" +
                        "                      <td>\n" +
                        "                        &nbsp;\n" +
                        "                      </td>\n" +
                        "                    </tr>\n" +
                        "                  </table>\n" +
                        "                  </td>\n" +
                        "                </tr>\n" +
                        "              </table>\n" +
                        "            </center>\n" +
                        "          </div>\n" +
                        "          <!--[if gte mso 9]>\n" +
                        "            </v:textbox>\n" +
                        "          </v:rect>\n" +
                        "          <![endif]-->\n" +
                        "        </td>\n" +
                        "      </tr>\n" +
                        "      <tr class=\"force-full-width\">\n" +
                        "        <td valign=\"top\" class=\"force-full-width\">\n" +
                        "          <center>\n" +
                        "            <table cellspacing=\"0\" cellpadding=\"0\" width=\"500\" class=\"w320\">\n" +
                        "              <tr>\n" +
                        "                <td valign=\"top\" style=\"border-bottom:1px solid #a1a1a1;\">\n" +
                        "\n" +
                        "                <table cellspacing=\"0\" cellpadding=\"0\" class=\"force-full-width\">\n" +
                        "                  <tr>\n" +
                        "                    <td style=\"padding: 30px 0;\" class=\"mobile-padding\">\n" +
                        "\n" +
                        "                    <table class=\"force-full-width\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                        "                      <tr>\n" +
                        "                        <td style=\"text-align: left;\">\n" +
                        "                          <span class=\"important-font\">\n" +
                        "                            Bob Erlicious <br>\n" +
                        "                          </span>\n" +
                        "                          123 Flower Drive <br>\n" +
                        "                          Victoria, BC <br>\n" +
                        "                          V9P 2E8 <br>\n" +
                        "                          1(250)222-2232\n" +
                        "                        </td>\n" +
                        "                        <td style=\"text-align: right; vertical-align:top;\">\n" +
                        "                          <span class=\"important-font\">\n" +
                        "                            Invoice: 00234<br>\n" +
                        "                          </span>\n" +
                        "                          April 3, 2014\n" +
                        "\n" +
                        "                        </td>\n" +
                        "                      </tr>\n" +
                        "                    </table>\n" +
                        "\n" +
                        "                    </td>\n" +
                        "                  </tr>\n" +
                        "                  <tr>\n" +
                        "                    <td style=\"padding-bottom: 30px;\" class=\"mobile-padding\">\n" +
                        "\n" +
                        "                      <table class=\"force-full-width\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                        "                        <tr>\n" +
                        "\n" +
                        "                          <td class=\"mobile-block\">\n" +
                        "                            <table cellspacing=\"0\" cellpadding=\"0\" class=\"force-full-width\">\n" +
                        "                              <tr>\n" +
                        "                                <td class=\"mobile-border\" style=\"background-color: #3bcdc3; color: #ffffff; padding: 5px; border-right: 3px solid #ffffff; text-align:left;\">\n" +
                        "                                  Expected Delivery Date\n" +
                        "                                </td>\n" +
                        "                              </tr>\n" +
                        "                              <tr>\n" +
                        "                                <td  style=\"background-color: #ebebeb; padding: 8px; border-top: 3px solid #ffffff; text-align:left;\">\n" +
                        "                                  Monday, 13th November 2014\n" +
                        "                                </td>\n" +
                        "                              </tr>\n" +
                        "                            </table>\n" +
                        "                          </td>\n" +
                        "\n" +
                        "                        </tr>\n" +
                        "                      </table>\n" +
                        "\n" +
                        "                    </td>\n" +
                        "                  </tr>\n" +
                        "                </table>\n" +
                        "\n" +
                        "\n" +
                        "                </td>\n" +
                        "              </tr>\n" +
                        "              <tr>\n" +
                        "                <td>\n" +
                        "\n" +
                        "                  <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n" +
                        "                    <tr>\n" +
                        "                      <td class=\"mobile-padding\" style=\"text-align:left;\">\n" +
                        "                      <br>\n" +
                        "                        Thank you for your business. Please <a style=\"color: #2BB6AC;\" href=\"#\">contact us</a> with any questions regarding this invoice.\n" +
                        "                      <br>\n" +
                        "                      <br>\n" +
                        "                      Awesome Inc\n" +
                        "                      <br>\n" +
                        "                      <br>\n" +
                        "                      <br>\n" +
                        "\n" +
                        "                      </td>\n" +
                        "                    </tr>\n" +
                        "                  </table>\n" +
                        "                </td>\n" +
                        "              </tr>\n" +
                        "            </table>\n" +
                        "          </center>\n" +
                        "        </td>\n" +
                        "      </tr>\n" +
                        "      <tr>\n" +
                        "        <td style=\"background-color:#c2c2c2;\">\n" +
                        "          <center>\n" +
                        "            <table cellspacing=\"0\" cellpadding=\"0\" width=\"500\" class=\"w320\">\n" +
                        "              <tr>\n" +
                        "                <td>\n" +
                        "                  <table cellspacing=\"0\" cellpadding=\"30\" width=\"100%\">\n" +
                        "                    <tr>\n" +
                        "                      <td style=\"text-align:center;\">\n" +
                        "                        <a href=\"#\">\n" +
                        "                          <img width=\"61\" height=\"51\" src=\"https://www.filepicker.io/api/file/vkoOlof0QX6YCDF9cCFV\" alt=\"twitter\" />\n" +
                        "                        </a>\n" +
                        "                        <a href=\"#\">\n" +
                        "                          <img width=\"61\" height=\"51\" src=\"https://www.filepicker.io/api/file/fZaNDx7cSPaE23OX2LbB\" alt=\"google plus\" />\n" +
                        "                        </a>\n" +
                        "                        <a href=\"#\">\n" +
                        "                          <img width=\"61\" height=\"51\" src=\"https://www.filepicker.io/api/file/b3iHzECrTvCPEAcpRKPp\" alt=\"facebook\" />\n" +
                        "                        </a>\n" +
                        "                      </td>\n" +
                        "                    </tr>\n" +
                        "                  </table>\n" +
                        "                </td>\n" +
                        "              </tr>\n" +
                        "              <tr>\n" +
                        "                <td>\n" +
                        "                  <center>\n" +
                        "                    <table style=\"margin:0 auto;\" cellspacing=\"0\" cellpadding=\"5\" width=\"100%\">\n" +
                        "                      <tr>\n" +
                        "                        <td style=\"text-align:center; margin:0 auto;\" width=\"100%\">\n" +
                        "                           <a href=\"#\" style=\"text-align:center;\">\n" +
                        "                             <img style=\"margin:0 auto;\" width=\"123\" height=\"24\" src=\"https://www.filepicker.io/api/file/u7CkMXcOSlG8TMbr3LnG\" alt=\"logo link\" />\n" +
                        "                           </a>\n" +
                        "                        </td>\n" +
                        "                      </tr>\n" +
                        "                    </table>\n" +
                        "                  </center>\n" +
                        "                </td>\n" +
                        "              </tr>\n" +
                        "            </table>\n" +
                        "          </center>\n" +
                        "        </td>\n" +
                        "      </tr>\n" +
                        "    </table>\n" +
                        "    </td>\n" +
                        "  </tr>\n" +
                        "</table>\n" +
                        "</body>\n" +
                        "</html>";


             //  Gmail gmail = new Gmail(getResources().getString(R.string.email),getResources().getString(R.string.password),"h.elmakhloufi_etu@enset-media.ac.ma","DATABASE CONNECTED",message);
          //gmail.sendMail();
            }

            public void onFinish() {
                startActivity(new Intent(SplashScreen.this, FillingInformation.class));
                finish();
            }
        }.start();
        final ImageView splash = findViewById(R.id.logo);
        Display display = getWindowManager().getDefaultDisplay();
        float height = display.getHeight();
        TranslateAnimation animation = new TranslateAnimation(0, 0, -300, 0);
        animation.setDuration(2000);
        splash.startAnimation(animation);
    }

}
