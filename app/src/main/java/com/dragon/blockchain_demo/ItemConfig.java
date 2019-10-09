package com.dragon.blockchain_demo;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ItemConfig extends AppCompatActivity {
    Spinner notify;
    Button bt2;
    ImageButton bt;
    ImageButton bt3, question;
    TextView detail, alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_config);
        notify = findViewById(R.id.notify_spinner);
        bt = findViewById(R.id.switching);
        bt2 = findViewById(R.id.button);
        bt3 = findViewById(R.id.button2);
        detail = findViewById(R.id.detail);
        alert = findViewById(R.id.alert);
        question = findViewById(R.id.question);
        ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(this, R.array.notify_array2, R.layout.spinner_item);
        nAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        notify.setAdapter(nAdapter);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ItemConfig.this);
                builder.setMessage("確認");
                builder.setMessage("確定將 B 點 110 pt 交換至 A 點 55 pt嗎?");
                builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Thread.sleep(2530);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        detail.setText("需要A點: 150 pt\n\n您有A點: 150 pt");
                        alert.setVisibility(View.INVISIBLE);
                        Toast.makeText(ItemConfig.this,"撮合成功!", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ItemConfig.this,"交易成功!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ItemConfig.this, Receipt2.class));
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ItemConfig.this, MainActivity.class));
            }
        });
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ItemConfig.this);
                builder.setTitle("現有點數");
                builder.setMessage("A點: 95 pt\nB點: 160 pt\nC點: 170 pt");
                builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });
    }
}
