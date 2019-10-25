package com.dragon.blockchain_demo;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Receipt3 extends AppCompatActivity {
    ImageButton bt;
    TextView confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt3);
        bt = findViewById(R.id.back);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Receipt3.this, MainActivity.class));
            }
        });
        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Receipt3.this,"交易成功! 請靜待商品送達!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Receipt3.this, MainActivity.class));
            }
        });
    }
}
