package com.agaigets.easyemail.email;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.agaigets.easyemail.R;
import com.agaigets.easyemail.ShowEmail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

public class EmailUtils {

    private static final String LOG_TAG = "EmailUtils";
    public static final String SMTP_HOST = "smtp.163.com";
    public static final String POP3_HOST = "pop.163.com";
    public static Store store = null;
    public static final int MESSAGE_COUNT = 10;

    public static void authenticate(final Context context, final String email, final String password) {
        Log.e(LOG_TAG, "authenticate: " + email);
        Log.e(LOG_TAG, "authenticate: " + password);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Properties properties = new Properties();
                properties.put("mail.store.protocol", "pop3");
                properties.put("mail.pop3.host", POP3_HOST);
                properties.put("mail.pop3.port", "995");
                properties.put("mail.pop3.starttls.enable", "true");
                Session session = Session.getDefaultInstance(properties);
                try {
                    store = session.getStore("pop3s");
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
            context.startActivity(new Intent(context, ShowEmail.class));
        } else {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, R.string.login_fail, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static ArrayList<Email> fetchEmail() {
        if (store.isConnected()) {
            Folder folder = null;
            Message[] messages=null;
            try {
                folder = store.getFolder("INBOX");
                folder.open(Folder.READ_WRITE);
                messages = folder.getMessages(folder.getMessageCount() - MESSAGE_COUNT, folder.getMessageCount());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            ArrayList<Email> emails = new ArrayList<>();
            for (Message msg : messages) {
                Email email = null;
                try {
                    Log.e("ASD", "fetchEmail: "+"正常邮件");
                    email=new Email(InternetAddress.toString(msg.getFrom()),InternetAddress.toString(msg.getRecipients(Message.RecipientType.TO)),msg.getReceivedDate(),msg.getSentDate(),msg.getContent(),msg.getSubject());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("ASD", "fetchEmail: "+"错误邮件");
                }
                emails.add(email);
            }
            return emails;
        }
        return null;
    }

}
