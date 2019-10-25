package com.dragon.blockchain_demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageButton pointswitch, shop, Item, check,gps, beacon, iot, guide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pointswitch = findViewById(R.id.point);
        shop = findViewById(R.id.shop);
        Item = findViewById(R.id.coupon);
        check = findViewById(R.id.check);
        gps = findViewById(R.id.gps);
        beacon = findViewById(R.id.beacon);
        iot = findViewById(R.id.beacon2);
        guide = findViewById(R.id.beacon3);
        pointswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SwitchPoint.class));
            }
        });
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShopList.class));
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
        beacon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCouponDialog();
            }
        });
        iot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCouponDialog2();
            }
        });
        guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCouponDialog3();
            }
        });
    }
    private void setCouponDialog() {
        ImageView image = new ImageView(this);
        image.setImageResource(R.mipmap.newcoupon);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialog);
        dialogBuilder.setTitle("優惠活動:")
                .setView(image)
                .setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        dialogBuilder.show();
    }
    private void setCouponDialog2() {
        ImageView image = new ImageView(this);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialog);
        dialogBuilder.setTitle("警告:")
                .setMessage("此處不可使用閃光燈!!")
                .setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        dialogBuilder.show();
    }
    private void setCouponDialog3() {
        ImageView image = new ImageView(this);
        image.setImageResource(R.mipmap.coupon_beacon);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialog);
        dialogBuilder.setTitle("今日活動通知:")
                .setMessage("定期導覽:  10:00   15:00\n\n淘金體驗:  10:30   13:00\n\n坑道探險:  09:30 ~ 16:30")
                .setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        dialogBuilder.show();
    }
}
