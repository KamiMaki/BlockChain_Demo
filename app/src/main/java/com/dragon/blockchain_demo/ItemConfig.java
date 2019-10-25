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
    ImageButton bt3;
    TextView detail, alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_config);
        notify = findViewById(R.id.notify_spinner);
        bt2 = findViewById(R.id.button);
        bt3 = findViewById(R.id.button2);
        detail = findViewById(R.id.detail);
        alert = findViewById(R.id.alert);
        ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(this, R.array.notify_array2, R.layout.spinner_item);
        nAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        notify.setAdapter(nAdapter);
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
    }
}
