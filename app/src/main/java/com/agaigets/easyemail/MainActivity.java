package com.agaigets.easyemail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.agaigets.easyemail.email.EmailUtils;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {


    private TextInputEditText et_email;
    private TextInputEditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_email = (TextInputEditText) findViewById(R.id.et_email);
        et_password = (TextInputEditText) findViewById(R.id.et_password);
    }

    public void login(View view) {
        EmailUtils.authenticate(this, et_email.getText().toString().trim(), et_password.getText().toString().trim());
    }
}
