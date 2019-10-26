package com.dragon.blockchain_demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ShopList3 extends AppCompatActivity {
    ImageView item1, item2, coupon1, coupon2, back;
    TextView amount1, amount2, amount_c1, amount_c2, current_pt, use_pt, confirm, refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list3);
        back = findViewById(R.id.button);
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        coupon1 = findViewById(R.id.coupon1);
        coupon2 = findViewById(R.id.coupon2);
        amount1 = findViewById(R.id.amount1);
        amount2 = findViewById(R.id.amount2);
        amount_c1 = findViewById(R.id.amount_c1);
        amount_c2 = findViewById(R.id.amount_c2);
        current_pt = findViewById(R.id.current_pt);
        use_pt = findViewById(R.id.use_pt);
        confirm = findViewById(R.id.confirm);
        refresh = findViewById(R.id.refresh);
        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item1.setAlpha(0.5f);
                int amount = Integer.parseInt(amount1.getText().toString().substring(4)) + 1;
                amount1.setText("數量: " + amount);
                amount1.setTextColor(Color.parseColor("#FFFF00"));
                int points = Integer.parseInt(use_pt.getText().toString().substring(7,use_pt.getText().toString().indexOf(" pt"))) + 20;
                use_pt.setText("使用點數:  " + points + " pt");
                if (Integer.parseInt(use_pt.getText().toString().substring(7,use_pt.getText().toString().indexOf(" pt"))) > Integer.parseInt(current_pt.getText().toString().substring(7,current_pt.getText().toString().indexOf(" pt"))))
                    use_pt.setTextColor(Color.parseColor("#FF0000"));

            }
        });
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item2.setAlpha(0.5f);
                int amount = Integer.parseInt(amount2.getText().toString().substring(4)) + 1;
                amount2.setText("數量: " + amount);
                amount2.setTextColor(Color.parseColor("#FFFF00"));
                int points = Integer.parseInt(use_pt.getText().toString().substring(7,use_pt.getText().toString().indexOf(" pt"))) + 20;
                use_pt.setText("使用點數:  " + points + " pt");
                if (Integer.parseInt(use_pt.getText().toString().substring(7,use_pt.getText().toString().indexOf(" pt"))) > Integer.parseInt(current_pt.getText().toString().substring(7,current_pt.getText().toString().indexOf(" pt"))))
                    use_pt.setTextColor(Color.parseColor("#FF0000"));

            }
        });
        coupon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coupon1.setAlpha(0.5f);
                int amount = Integer.parseInt(amount_c1.getText().toString().substring(4)) + 1;
                amount_c1.setText("數量: " + amount);
                amount_c1.setTextColor(Color.parseColor("#FFFF00"));
                int points = Integer.parseInt(use_pt.getText().toString().substring(7,use_pt.getText().toString().indexOf(" pt"))) + 20;
                use_pt.setText("使用點數:  " + points + " pt");
                if (Integer.parseInt(use_pt.getText().toString().substring(7,use_pt.getText().toString().indexOf(" pt"))) > Integer.parseInt(current_pt.getText().toString().substring(7,current_pt.getText().toString().indexOf(" pt"))))
                    use_pt.setTextColor(Color.parseColor("#FF0000"));

            }
        });
        coupon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coupon2.setAlpha(0.5f);
                int amount = Integer.parseInt(amount_c2.getText().toString().substring(4)) + 1;
                amount_c2.setText("數量: " + amount);
                int points = Integer.parseInt(use_pt.getText().toString().substring(7,use_pt.getText().toString().indexOf(" pt"))) + 40;
                use_pt.setText("使用點數:  " + points + " pt");
                amount_c2.setTextColor(Color.parseColor("#FFFF00"));
                if (Integer.parseInt(use_pt.getText().toString().substring(7,use_pt.getText().toString().indexOf(" pt"))) > Integer.parseInt(current_pt.getText().toString().substring(7,current_pt.getText().toString().indexOf(" pt"))))
                    use_pt.setTextColor(Color.parseColor("#FF0000"));

            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount1.setText("數量: 0");
                amount2.setText("數量: 0");
                amount_c1.setText("數量: 0");
                amount_c2.setText("數量: 0");
                use_pt.setText("使用點數:  0 pt");
                use_pt.setTextColor(Color.parseColor("#000033"));
                amount1.setTextColor(Color.parseColor("#000033"));
                amount2.setTextColor(Color.parseColor("#000033"));
                amount_c1.setTextColor(Color.parseColor("#000033"));
                amount_c2.setTextColor(Color.parseColor("#000033"));
                item1.setAlpha(1.0f);
                item2.setAlpha(1.0f);
                coupon1.setAlpha(1.0f);
                coupon2.setAlpha(1.0f);
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int points = Integer.parseInt(use_pt.getText().toString().substring(7,use_pt.getText().toString().indexOf(" pt")));
                int current_points = Integer.parseInt(current_pt.getText().toString().substring(7,current_pt.getText().toString().indexOf(" pt")));
                if (points > current_points){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ShopList3.this);
                    builder.setMessage("您的 家樂福 點數不足!!");
                    builder.setPositiveButton("兌換點數", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(ShopList3.this, MissSwitchPoint.class));
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();

                }
                else {
                    try {
                        Thread.sleep(1320);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    startActivity(new Intent(ShopList3.this, Qrcode2.class));
                }


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopList3.this, ShopToItem.class));
            }
        });
    }
}
