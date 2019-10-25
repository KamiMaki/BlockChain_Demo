package com.dragon.blockchain_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText account, password;
    TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        startActivity(new Intent(Login.this, GoogleMap.class));
        account = findViewById(R.id.account);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ac = account.getText().toString();
                String pw = password.getText().toString();

                if (ac.equals("dragon")&& pw.equals(""))
                    startActivity(new Intent(Login.this, Loading.class));
                else {
                    Toast.makeText(Login.this, "帳號或密碼錯誤，請重新輸入!!", Toast.LENGTH_LONG).show();
                    account.setText("");
                    password.setText("");
                }
            }
        });

    }
}
