package com.dragon.blockchain_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ItemList extends AppCompatActivity {
    ImageButton back;
    TextView buy1, buy2, text21, text22, text12, text11, buy3, text31, text32;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        back = findViewById(R.id.back);
        buy1 = findViewById(R.id.buy1);
        buy2 = findViewById(R.id.buy2);
        buy3 = findViewById(R.id.buy3);
        text11 = findViewById(R.id.text11);
        text12 = findViewById(R.id.text12);
        text21 = findViewById(R.id.text21);
        text22 = findViewById(R.id.text22);
        text31 = findViewById(R.id.text31);
        text32 = findViewById(R.id.text32);
        if (Qrcode2.inqrcode == true){
            text21.setVisibility(View.INVISIBLE);
            text22.setVisibility(View.INVISIBLE);
            buy2.setVisibility(View.INVISIBLE);
        }
        if (Qrcode.inqrcode == true){
            text12.setVisibility(View.INVISIBLE);
            text11.setVisibility(View.INVISIBLE);
            buy1.setVisibility(View.INVISIBLE);
        }
        if (Qrcode3.inqrcode == true){
            text31.setVisibility(View.INVISIBLE);
            text32.setVisibility(View.INVISIBLE);
            buy3.setVisibility(View.INVISIBLE);
        }

        buy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ItemList.this, Qrcode.class));
            }
        });
        buy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ItemList.this, Qrcode2.class));
            }
        });
        buy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ItemList.this, Qrcode3.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ItemList.this,MainActivity.class));
            }
        });
    }
}
