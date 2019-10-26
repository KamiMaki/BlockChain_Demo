package com.dragon.blockchain_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Receipt2 extends AppCompatActivity {
    ImageButton bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt2);
        bt = findViewById(R.id.button);
        Toast.makeText(Receipt2.this,"點數兌換成功!", Toast.LENGTH_LONG).show();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Receipt2.this,"商品已儲存至優惠券!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Receipt2.this, MainActivity.class));
            }
        });
    }
}
