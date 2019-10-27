package com.dragon.blockchain_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;


public class SwitchPoint extends AppCompatActivity {
    Spinner notify;
    Button bt;
    ImageButton bt2;
    TextView row11,row12,row21,row22,row31,row32,row41,row42,tv1, amount;
    TextView pt1,pt2,pt3,pt4;
    TableLayout table;
    CheckBox check1, check2, check3, check4;
    String current_position;
    String result,result2;
    String[] company_name = new String[5];
    int useToken = 0;
    double [] exchange = new double[5];
    int[] point = {10,80,3000,1000,0};
    int [] ID = new int [5];
    int [] change_Amount = new int[5];

    boolean[] selected = new boolean[5];
    int total = 0;//兌換獲得的點數
    int point_amount[] = new int[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_point);
        Thread thread = new Thread(mutiThread);
        thread.start(); // 開始執行
        Thread thread1 = new Thread(mutiThread2);
        thread1.start(); // 開始執行
        for(int i = 0;i<5;i++) change_Amount[i] = 0;

        notify = findViewById(R.id.notify_spinner);
        bt = findViewById(R.id.button);
        bt2 = findViewById(R.id.button2);
        ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(this, R.array.notify_array, R.layout.spinner_item);
        nAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        notify.setAdapter(nAdapter);
        pt1 = findViewById(R.id.pt1);
        pt2 = findViewById(R.id.pt2);
        pt3 = findViewById(R.id.pt3);
        pt4 = findViewById(R.id.pt4);
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
                            newarray[i] = company_name[i];
                            newexchange[i] = exchange[i] / exchange[position];
                        } else {
                            newarray[i] = company_name[i + 1];
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
                        current_position = "途中。";
                    else
                        current_position = company_name[position];
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
                Thread thread2 = new Thread(mutiThread3);
                thread2.start(); // 開始執行
                //Toast.makeText(SwitchPoint.this,"撮合成功!", Toast.LENGTH_LONG).show();
                //startActivity (new Intent(SwitchPoint.this, Receipt.class));
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
                    int sum = (int)(point[0] * Double.valueOf(row12.getText().toString()));
                    total = total  + sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                    useToken++;
                    selected[0] = true;
                    change_Amount[0] = change_Amount[0]+ sum ;
                    System.out.println("---sum---"+change_Amount[0]);

                }
                else {
                    int sum = (int)(point[0] * Double.valueOf(row12.getText().toString()));
                    total = total  - sum;
                    String s = "獲得 "+current_position+"點數: " + total + " pt";
                    amount.setText(s);
                    useToken--;
                    selected[0] = false;
                    change_Amount[0] = change_Amount[0]- sum ;
                }
                //TODO fix amount text crush when length not the same
            }
        });
        check2.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    int sum = (int)(point[1] * Double.valueOf(row22.getText().toString()));
                    total = total  + sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                    useToken++;
                    selected[1] = true;
                    change_Amount[1] = change_Amount[1]+ sum ;
                    System.out.println("---sum---"+change_Amount[1]);
                }
                else{
                    int sum = (int)(point[1] * Double.valueOf(row22.getText().toString()));
                    total = total  - sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                    useToken--;
                    selected[1] = false;
                    change_Amount[1] = change_Amount[1]- sum ;
                }
            }
        });
        check3.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    int sum = (int)(point[2] * Double.valueOf(row32.getText().toString()));
                    total = total  + sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                    useToken++;
                    selected[2] = true;
                    change_Amount[2] = change_Amount[2]+ sum ;
                    System.out.println("---sum---"+change_Amount[2]);
                }
                else{
                    int sum = (int)(point[2] * Double.valueOf(row32.getText().toString()));
                    total = total  - sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                    useToken--;
                    selected[2] = false;
                    change_Amount[2] = change_Amount[2] - sum ;
                }
            }
        });
        check4.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    int sum = (int)(point[3] * Double.valueOf(row42.getText().toString()));
                    total = total  + sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                    useToken++;
                    selected[3] = true;
                    change_Amount[3] = change_Amount[3]+ sum ;
                    System.out.println("---sum---"+change_Amount[3]);
                }
                else{
                    int sum = (int)(point[3] * Double.valueOf(row42.getText().toString()));
                    total = total  - sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                    useToken--;
                    selected[3] = false;
                    change_Amount[3] = change_Amount[3]- sum ;
                }
            }
        });
    }
    public void refresh_pt()
    {
        pt1.setText(point_amount[0]+"pt");
        pt2.setText(point_amount[1]+"pt");
        pt3.setText(point_amount[2]+"pt");
        pt4.setText(point_amount[3]+"pt");
    }

    private  Runnable mutiThread = new Runnable() {
        public void run() {

            try {
                URL url = new URL("http://140.113.65.235/getMP.php");
                // 開始宣告 HTTP 連線需要的物件，這邊通常都是一綑的
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // 建立 Google 比較挺的 HttpURLConnection 物件
                connection.setRequestMethod("POST");
                // 設定連線方式為 POST
                connection.setDoOutput(true); // 允許輸出
                connection.setDoInput(true); // 允許讀入
                connection.setUseCaches(false); // 不使用快取
                connection.connect(); // 開始連線

                int responseCode =
                        connection.getResponseCode();
                // 建立取得回應的物件
                if (responseCode ==
                        HttpURLConnection.HTTP_OK) {
                    // 如果 HTTP 回傳狀態是 OK ，而不是 Error
                    InputStream inputStream =
                            connection.getInputStream();
                    // 取得輸入串流
                    BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                    // 讀取輸入串流的資料
                    String box = ""; // 宣告存放用字串
                    String line = null; // 宣告讀取用的字串
                    while ((line = bufReader.readLine()) != null) {
                        box += line + "\n";
                        // 每當讀取出一列，就加到存放字串後面
                    }
                    inputStream.close(); // 關閉輸入串流
                    result = box; // 把存放用字串放到全域變數
                }
                // 讀取輸入串流並存到字串的部分
                // 取得資料後想用不同的格式
                // 例如 Json 等等，都是在這一段做處理
                connection.disconnect();

            } catch (Exception e) {
                result = e.toString(); // 如果出事，回傳錯誤訊息
            }

            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        JSONArray array = new JSONArray(result);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObject = array.getJSONObject(i);
                            company_name[i] = jsonObject.getString("name");
                            exchange[i] = jsonObject.getInt("mp");
                            ID[i] = jsonObject.getInt("ID");
                            // Log.d("TAG", "name:" + name + ", mp:" + mp + ", ID:" + ID);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    private  Runnable mutiThread2 = new Runnable() {
        public void run() {

            try {
                URL url = new URL("http://140.113.65.235/getuserpoint.php");
                // 開始宣告 HTTP 連線需要的物件，這邊通常都是一綑的
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // 建立 Google 比較挺的 HttpURLConnection 物件
                connection.setRequestMethod("POST");
                // 設定連線方式為 POST
                connection.setDoOutput(true); // 允許輸出
                connection.setDoInput(true); // 允許讀入
                connection.setUseCaches(false); // 不使用快取
                connection.connect(); // 開始連線

                int responseCode =
                        connection.getResponseCode();
                // 建立取得回應的物件
                if (responseCode ==
                        HttpURLConnection.HTTP_OK) {
                    // 如果 HTTP 回傳狀態是 OK ，而不是 Error
                    InputStream inputStream =
                            connection.getInputStream();
                    // 取得輸入串流
                    BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                    // 讀取輸入串流的資料
                    String box = ""; // 宣告存放用字串
                    String line = null; // 宣告讀取用的字串
                    while ((line = bufReader.readLine()) != null) {
                        box += line + "\n";
                        // 每當讀取出一列，就加到存放字串後面
                    }
                    inputStream.close(); // 關閉輸入串流
                    result = box; // 把存放用字串放到全域變數
                }
                connection.disconnect();
                // 讀取輸入串流並存到字串的部分
                // 取得資料後想用不同的格式
                // 例如 Json 等等，都是在這一段做處理

            } catch (Exception e) {
                result = e.toString(); // 如果出事，回傳錯誤訊息
            }

            // 當這個執行緒完全跑完後執行
            runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        JSONArray array = new JSONArray(result);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObject = array.getJSONObject(i);
                            point_amount[i] = jsonObject.getInt("amount");

                            Log.d("TAG", "amount" +point_amount[i]);
                        }
                        refresh_pt();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
    private final Runnable mutiThread3 = new Runnable() {
        public void run() {

            try {
                URL url = new URL("http://140.113.65.235/uploadchange.php");
                BufferedReader reader = null;
                // 開始宣告 HTTP 連線需要的物件，這邊通常都是一綑的
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // 建立 Google 比較挺的 HttpURLConnection 物件
                connection.setRequestMethod("POST");
                // 設定連線方式為 POST
                connection.setDoOutput(true); // 允許輸出
                connection.setDoInput(true); // 允許讀入
                connection.setUseCaches(false); // 不使用快取
                connection.setRequestMethod("POST");  //設定以Post方式提交資料
                //conn.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("Charset", "UTF-8");
                // 設定檔案型別:
                //conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
                // 設定接收型別否則返回415錯誤
                //conn.setRequestProperty("accept","*/*")此處為暴力方法設定接受所有型別，以此來防範返回415;
                connection.setRequestProperty("accept", "application/json");
                connection.connect(); // 開始連線
                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
                String time = sDateFormat.format(new Date());
                String orderno = time + total;
                JSONArray CID = new JSONArray();
                JSONArray change_amount = new JSONArray();
                List<Integer> company = new ArrayList<>();
                List<Integer> CAmount = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    if (selected[i]) {
                        company.add(ID[i]);
                        CAmount.add(change_Amount[i]);
                    }
                }
                System.out.println("---use token---" + useToken);

                for (int i = 0; i < useToken; i++) {
                    CID.put(company.get(i));
                    change_amount.put(CAmount.get(i));
                    String Json = CID.toString();
                    String Json1 = change_amount.toString();

                    System.out.println("---CID---" + Json);
                    System.out.println("---CHANGEAMOUNT---" + Json1);
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("orderno", orderno);
                jsonObject.put("CID", CID);
                jsonObject.put("change_amount", change_amount);


                int responseCode = connection.getResponseCode();
                // 建立取得回應的物件
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    String Json = jsonObject.toString();

                    System.out.println("-----------    " + Json);
                    // 如果 HTTP 回傳狀態是 OK ，而不是 Error
                    if (Json != null && !TextUtils.isEmpty(Json)) {
                        byte[] writebytes = Json.getBytes();
                        // 設定檔案長度
                        System.out.println("upload~~~~~~~");
                        connection.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                        OutputStream outwritestream = connection.getOutputStream();
                        outwritestream.write(Json.getBytes());
                        outwritestream.flush();
                        outwritestream.close();
                        Log.d("upload result" ,""+connection.getResponseCode());
                        System.out.println("upload result" + connection.getResponseCode());//如輸出200，則對了

                    }

                        reader = new BufferedReader(
                                new InputStreamReader(connection.getInputStream()));
                        result = reader.readLine();
                        System.out.println(result);



                }


            } catch (Exception e) {
                result = e.toString(); // 如果出事，回傳錯誤訊息
            }

            // 當這個執行緒完全跑完後執行
            /*runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        JSONArray array = new JSONArray(result);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObject = array.getJSONObject(i);
                            point_amount[i] = jsonObject.getInt("amount");

                            Log.d("TAG", "amount" +point_amount[i]);
                        }
                        refresh_pt();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });*/
        }
    };

    private  Runnable r1 = new Runnable() {
        @Override
        public void run() {
            refresh_pt();
        }
    };
    private TimerTask task = new TimerTask() {
        public void run() {
            Thread t2 = new Thread(r1);
            t2.start();
        }
    };

}
