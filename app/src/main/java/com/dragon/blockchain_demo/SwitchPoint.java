package com.dragon.blockchain_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;



public class SwitchPoint extends AppCompatActivity {
    Spinner notify;
    Button bt;
    ImageButton bt2;
    TextView row11,row12,row21,row22,row31,row32,row41,row42,tv1, amount;
    TableLayout table;
    CheckBox check1, check2, check3, check4;
    String current_position;
    String[] array = {"阿柑姨芋圓", "kkday", "中華航空", "遠傳電信", "途中。九份國際青年旅舍","途中。"};
    double[] exchange = {20.0, 4.0, 1.0, 4.0, 5.0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_point);
        notify = findViewById(R.id.notify_spinner);
        bt = findViewById(R.id.button);
        bt2 = findViewById(R.id.button2);
        ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(this, R.array.notify_array, R.layout.spinner_item);
        nAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        notify.setAdapter(nAdapter);
        row11 = findViewById(R.id.row11);
        row12 = findViewById(R.id.row12);
        row21 = findViewById(R.id.row21);
        row22 = findViewById(R.id.row22);
        row31 = findViewById(R.id.row31);
        row32 = findViewById(R.id.row32);
        row41 = findViewById(R.id.row41);
        row42 = findViewById(R.id.row42);
        check1 = findViewById(R.id.checkBox1);
        check2 = findViewById(R.id.checkBox2);
        check3 = findViewById(R.id.checkBox3);
        check4 = findViewById(R.id.checkBox4);
        tv1 = findViewById(R.id.textview1);
        table = findViewById(R.id.table);
        amount = findViewById(R.id.amount);

        final String[] newarray = {"", "", "", ""};
        final double[] newexchange = {0, 0, 0, 0};
        notify.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                int position = pos - 1;
                if (pos != 0) {
                    for (int i = 0; i < 4; i++) {
                        if (position > i) {
                            newarray[i] = array[i];
                            newexchange[i] = exchange[i] / exchange[position];
                        } else {
                            newarray[i] = array[i + 1];
                            newexchange[i] = exchange[i + 1] / exchange[position];
                        }
                    }
                    row11.setText(newarray[0]);
                    row12.setText(String.valueOf(newexchange[0]));
                    row21.setText(newarray[1]);
                    row22.setText(String.valueOf(newexchange[1]));
                    row31.setText(newarray[2]);
                    row32.setText(String.valueOf(newexchange[2]));
                    row41.setText(newarray[3]);
                    row42.setText(String.valueOf(newexchange[3]));
                    table.setVisibility(View.VISIBLE);
                    check1.setText(newarray[0]);
                    check2.setText(newarray[1]);
                    check3.setText(newarray[2]);
                    check4.setText(newarray[3]);
                    check1.setVisibility(View.VISIBLE);
                    check2.setVisibility(View.VISIBLE);
                    check3.setVisibility(View.VISIBLE);
                    check4.setVisibility(View.VISIBLE);
                    tv1.setVisibility(View.VISIBLE);
                    amount.setVisibility(View.VISIBLE);
                    if (position == 4)
                        current_position = array[5];
                    else
                        current_position = array[position];
                }
                else {
                    table.setVisibility(View.INVISIBLE);
                    check1.setVisibility(View.INVISIBLE);
                    check2.setVisibility(View.INVISIBLE);
                    check3.setVisibility(View.INVISIBLE);
                    check4.setVisibility(View.INVISIBLE);
                    tv1.setVisibility(View.INVISIBLE);
                    amount.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                table.setVisibility(View.INVISIBLE);
                check1.setVisibility(View.INVISIBLE);
                check2.setVisibility(View.INVISIBLE);
                check3.setVisibility(View.INVISIBLE);
                check4.setVisibility(View.INVISIBLE);
                tv1.setVisibility(View.INVISIBLE);
                amount.setVisibility(View.INVISIBLE);
            }
        });
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
        check1.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    double sum = 10 * Double.valueOf(row12.getText().toString());
                    double total =  Double.parseDouble(amount.getText().toString().substring(9,amount.getText().toString().indexOf(" pt"))) + sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                }
                else {
                    double sum = 10 * Double.valueOf(row12.getText().toString());
                    double total =  Double.parseDouble(amount.getText().toString().substring(9,amount.getText().toString().indexOf(" pt"))) - sum;
                    String s = "獲得途中。點數: " + total + " pt";
                    amount.setText(s);
                }
                //TODO fix amount text crush when length not the same
            }
        });
        check2.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    double sum = 80 * Double.valueOf(row22.getText().toString());
                    double total =  Double.parseDouble(amount.getText().toString().substring(9,amount.getText().toString().indexOf(" pt"))) + sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                }
                else{
                    double sum = 80 * Double.valueOf(row22.getText().toString());
                    double total =  Double.parseDouble(amount.getText().toString().substring(9,amount.getText().toString().indexOf(" pt"))) - sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                }
            }
        });
        check3.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    double sum = 3000 * Double.valueOf(row32.getText().toString());
                    double total =  Double.parseDouble(amount.getText().toString().substring(9,amount.getText().toString().indexOf(" pt"))) + sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                }
                else{
                    double sum = 3000 * Double.valueOf(row32.getText().toString());
                    double total =  Double.parseDouble(amount.getText().toString().substring(9,amount.getText().toString().indexOf(" pt"))) - sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                }
            }
        });
        check4.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    double sum = 1000 * Double.valueOf(row42.getText().toString());
                    double total =  Double.parseDouble(amount.getText().toString().substring(9,amount.getText().toString().indexOf(" pt"))) + sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                }
                else{
                    double sum = 1000 * Double.valueOf(row42.getText().toString());
                    double total =  Double.parseDouble(amount.getText().toString().substring(9,amount.getText().toString().indexOf(" pt"))) - sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                }
            }
        });
    }
}
