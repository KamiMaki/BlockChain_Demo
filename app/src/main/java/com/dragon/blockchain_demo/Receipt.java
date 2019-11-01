package com.dragon.blockchain_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Receipt extends AppCompatActivity {
    ImageButton bt;
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        bt = findViewById(R.id.button);
        tv1 = findViewById(R.id.textView55);
        tv2 = findViewById(R.id.textView54);
        Toast.makeText(Receipt.this,"撮合成功!", Toast.LENGTH_SHORT).show();
        Bundle bundle =this.getIntent().getExtras();

        String[] A = bundle.getString("A").split(",");
        String B = bundle.getString("B");
        String[] amount = bundle.getString("amount").split(",");
        tv1.setText(amount[0]);
        tv2.setText(amount[1]);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(Receipt.this, MainActivity.class));
            }
        });
    }
}
