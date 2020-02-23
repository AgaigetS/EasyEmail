package com.agaigets.easyemail;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agaigets.easyemail.adapter.EmailListAdapter;
import com.agaigets.easyemail.decorator.MyRecyclerViewDecorator;
import com.agaigets.easyemail.email.Email;
import com.agaigets.easyemail.email.EmailUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;

public class ShowEmail extends AppCompatActivity {

    private List<Email> emails;
    private EmailListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_email);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frag_email_list);
        RecyclerView recyclerView = (RecyclerView) fragment.getView();

        emails = new ArrayList<>();
        adapter = new EmailListAdapter(emails);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MyRecyclerViewDecorator());
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Email> emails = EmailUtils.fetchEmail();
                adapter.setList(emails);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
}
