package com.dragon.blockchain_demo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Coupon extends AppCompatActivity {
    Spinner notify;
    Button bt;
    ImageButton bt2, switching;
    TextView total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        notify = findViewById(R.id.notify_spinner);
        bt = findViewById(R.id.button);
        bt2 = findViewById(R.id.back);
        switching = findViewById(R.id.switching);
        total = findViewById(R.id.total);
        ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(this, R.array.notify_array3, R.layout.spinner_item);
        nAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        notify.setAdapter(nAdapter);
        notify.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 4){
                    total.setText("B: 50 pt");
                    total.setTextColor(Color.parseColor("#ffffff"));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Thread.sleep(1160);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(Coupon.this,"交易成功!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Coupon.this, Receipt3.class));
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Coupon.this,MainActivity.class));
            }
        });
        switching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Coupon.this,SwitchPoint.class));
            }
        });
    }
}
