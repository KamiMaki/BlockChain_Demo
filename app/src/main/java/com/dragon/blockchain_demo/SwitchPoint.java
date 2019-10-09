package com.dragon.blockchain_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class SwitchPoint extends AppCompatActivity {
    Spinner notify;
    EditText amount;
    Button bt;
    ImageButton bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_point);
        notify = findViewById(R.id.notify_spinner);
        amount = findViewById(R.id.amount);
        bt = findViewById(R.id.button);
        bt2 = findViewById(R.id.button2);
        ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(this, R.array.notify_array, R.layout.spinner_item);
        nAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        notify.setAdapter(nAdapter);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Thread.sleep(2530);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(SwitchPoint.this,"撮合成功!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(SwitchPoint.this, Receipt.class));
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SwitchPoint.this,MainActivity.class));
            }
        });
    }
}
