package com.agaigets.easyemail.email;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.agaigets.easyemail.R;

import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

public class EmailUtils {

    private static final String LOG_TAG = "EmailUtils";
    public static final String SMTP_HOST = "smtp.163.com";
    public static final String POP3_HOST = "pop.163.com";

    public static void authenticate(final Context context, final String email, final String password) {
        Log.e(LOG_TAG, "authenticate: " + email);
        Log.e(LOG_TAG, "authenticate: " + password);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Properties properties = new Properties();
                properties.put("mail.pop3.host", POP3_HOST);
                properties.put("mail.pop3.port", "995");
                properties.put("mail.pop3.starttls.enable", "true");
                Session session = Session.getDefaultInstance(properties);
                try {
                    Store store = session.getStore("pop3s");
                    store.connect(POP3_HOST, email, password);
                    getLoginStatus(context, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    getLoginStatus(context, false);
                }
            }
        }).start();
    }

    public static void getLoginStatus(final Context context, boolean flag) {
        if (flag) {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, R.string.login_success, Toast.LENGTH_SHORT).show();
                }
            });
            //TODO 开启Intent
        } else {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, R.string.login_fail, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
