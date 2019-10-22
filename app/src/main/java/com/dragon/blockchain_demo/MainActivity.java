package com.dragon.blockchain_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton pointswitch, shop, Item, check,gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pointswitch = findViewById(R.id.point);
        shop = findViewById(R.id.shop);
        Item = findViewById(R.id.coupon);
        check = findViewById(R.id.check);
        gps = findViewById(R.id.gps);
        pointswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SwitchPoint.class));
            }
        });
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ItemConfig.class));
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.kkday.blockchain");
                startActivity(intent);
            }
        });
        Item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShopToItem.class));
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ItemList.class));
            }
        });
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,GoogleMap.class));
            }
        });
    }
}
