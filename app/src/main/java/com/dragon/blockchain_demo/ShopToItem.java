package com.dragon.blockchain_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ShopToItem extends AppCompatActivity {
    ImageView shopItem, back, shopItem2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_to_item);
        shopItem = findViewById(R.id.shopitem);
        shopItem2 = findViewById(R.id.imageView2);
        back = findViewById(R.id.button);
        shopItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopToItem.this, ShopList3.class));
            }
        });
        shopItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopToItem.this, ShopList3.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopToItem.this, MainActivity.class));
            }
        });
    }
}
