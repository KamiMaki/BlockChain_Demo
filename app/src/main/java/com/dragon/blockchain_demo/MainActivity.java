package com.dragon.blockchain_demo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    Vibrator vibrate;
    ImageButton pointswitch, shop, Item, check,gps, iot, guide;
    private ShakeListener mShaker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pointswitch = findViewById(R.id.point);
        shop = findViewById(R.id.shop);
        Item = findViewById(R.id.coupon);
        check = findViewById(R.id.check);
        gps = findViewById(R.id.gps);
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
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.kkday.iTravelChain");
//                if( intent != null ){
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity( intent );
//                }
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
        iot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                setCouponDialog2();
            }
        });
        guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setCouponDialog3();
            }
        });
        mShaker = new ShakeListener(this);
        mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
            public void onShake(){
                setCouponDialog4();
            }
        });
    }
    private void setCouponDialog2() {
        vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] mVibratePattern = new long[]{0, 500, 200, 500, 200, 500};
        vibrate.vibrate(mVibratePattern,-1);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialog);
        dialogBuilder.setTitle("提示:")
                .setMessage("偵測到附近二氧化碳濃度過高\n請盡量避開人潮")
                .setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        dialogBuilder.show();
    }
    private void setCouponDialog3() {
        vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] mVibratePattern = new long[]{0, 1000, 200, 400};
        vibrate.vibrate(mVibratePattern,-1);
        ImageView image = new ImageView(this);
        image.setImageResource(R.mipmap.shake);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialog);
        dialogBuilder.setTitle("今日活動通知:")
                .setMessage("定期導覽:  10:00   15:00\n\n淘金體驗:  10:30   13:00\n\n坑道探險:  09:30 ~ 16:30\n\n點數搖搖:  熱烈進行中!!!")
                .setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setView(image)
                .setNeutralButton("搖一搖", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        dialogBuilder.show();
    }
    private void setCouponDialog4() {
        ImageView image = new ImageView(this);
        image.setImageResource(R.mipmap.dialogcoin);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialog);
        dialogBuilder.setTitle("提示:")
                .setMessage("恭喜獲得點數 10 點!!")
                .setPositiveButton("讚啦", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setView(image);
        dialogBuilder.show();
    }
}
