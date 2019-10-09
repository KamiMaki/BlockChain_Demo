package com.dragon.blockchain_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class Qrcode2 extends AppCompatActivity {
    ImageButton bt;
    static boolean inqrcode = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode2);
        bt = findViewById(R.id.back);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inqrcode = true;
                startActivity(new Intent(Qrcode2.this,ItemList.class));
            }
        });
    }
}
