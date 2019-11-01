package com.dragon.blockchain_demo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
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
import java.util.Timer;
import java.util.TimerTask;


public class SwitchPoint extends AppCompatActivity {
    Button btn3;
    Spinner notify;
    Button bt;
    ImageButton bt2;
    TextView row11,row12,row21,row22,row31,row32,row41,row42,tv1, amount;
    TextView pt1,pt2,pt3,pt4;
    TableLayout table;
    CheckBox check1, check2, check3, check4;
    String current_position;
    String result,result2;
    ProgressBar progressBar;
    String[] company_name = new String[5];
    int useToken = 0;
    double [] exchange = new double[5];
    //int[] point = {10,80,3000,1000,0};
    String [] CID = new String [5];
    int [] change_Amount = new int[5];
    String[] newarray = {"", "", "", ""};
    double[] newexchange = {0, 0, 0, 0};
    int Comb;
    WebView webview;
    String upload_com = "";
    String upload_amount = "";

    boolean[] selected = new boolean[5];
    int total = 0;//兌換獲得的點數
    int point_amount[] = {0,0,0,0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_point);
        //Thread thread = new Thread(mutiThread);
        //thread.start(); // 開始執行
        Thread thread1 = new Thread(mutiThread2);
        thread1.start(); // 開始執行
        for(int i = 0;i<5;i++) change_Amount[i] = 0;

        notify = findViewById(R.id.notify_spinner);
        bt = findViewById(R.id.button);
        bt2 = findViewById(R.id.button2);
        webview = findViewById(R.id.web_view);
        progressBar = findViewById(R.id.progressBar);
        ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(this, R.array.notify_array, R.layout.spinner_item);
        nAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        btn3 = findViewById(R.id.button3);
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
        webview =(WebView) findViewById(R.id.web_view);
        webview.getSettings().setJavaScriptEnabled(true);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webview.addJavascriptInterface(new JavaScriptInterface(SwitchPoint.this), "JSInterface");

        //webview.getSettings().setPluginsEnabled(true);

        // webview.setWebViewClient();(new WebChromeClient() ) ; //不調用系統瀏覽器
        webview.loadUrl("http://140.113.65.235/uploadchange.html");




        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                Thread thread = new Thread(mutiThread);
                thread.start();
            }
        };
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                Thread thread = new Thread(mutiThread2);
                thread.start();
            }
        };
        Timer timer01 = new Timer();
        timer01.schedule(task1, 0, 1000);
        Timer timer02 = new Timer();
        timer02.schedule(task2, 0, 1000);


        notify.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                System.out.println(CID);
                System.out.println(company_name);
                int position = pos - 1;
                Comb = position;
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
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SwitchPoint.this,"撮合成功!", Toast.LENGTH_LONG).show();
                startActivity (new Intent(SwitchPoint.this, Receipt.class));

            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload_com = "";
                upload_amount = "";
               /* Timer timer03 = new Timer();

                TimerTask task3 = new TimerTask() {
                    @Override
                    public void run() {
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                if(point_amount[3] == 0)
                                {
                                    Looper.prepare();
                                    Toast.makeText(SwitchPoint.this,"撮合成功!", Toast.LENGTH_LONG).show();
                                    startActivity (new Intent(SwitchPoint.this, Receipt.class));


                                }
                            }
                        });
                        thread.start();
                    }
                } ;
                */
                Timer timer03 = new Timer();
                timer03.schedule(timerTask3 , 8000);
                if(progressBar.getVisibility() == ProgressBar.VISIBLE)
                {
                    progressbar_off();
                }
                else
                {
                    progressbar_on();
                }
                for(int i = 0;i<5;i++)
                {
                    if(selected[i])
                    {
                        upload_com+=CID[i];
                        upload_com+=',';
                        upload_amount+=change_Amount[i];
                        upload_amount+=',';
                    }
                }
                String companyB = CID[Comb];
                webview.post(new Runnable() {
                    @Override
                    public void run() {
                        webview.loadUrl("javascript:test('"+upload_com+"','"+companyB+"','"+upload_amount+"')");


                    }

                });

                //Thread thread2 = new Thread(mutiThread3);
                //thread2.start(); // 開始執行
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
                    int sum = (int)(point_amount[0] * Double.valueOf(row12.getText().toString()));
                    total = total  + sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                    useToken++;
                    selected[0] = true;
                    change_Amount[0] = change_Amount[0]+ point_amount[0] ;
                    System.out.println("---sum---"+change_Amount[0]);

                }
                else {
                    int sum = (int)(point_amount[0] * Double.valueOf(row12.getText().toString()));
                    total = total  - sum;
                    String s = "獲得 "+current_position+"點數: " + total + " pt";
                    amount.setText(s);
                    useToken--;
                    selected[0] = false;
                    change_Amount[0] = change_Amount[0]- point_amount[0] ;
                }
                //TODO fix amount text crush when length not the same
            }
        });
        check2.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    int sum = (int)(point_amount[1] * Double.valueOf(row22.getText().toString()));
                    total = total  + sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                    useToken++;
                    selected[1] = true;
                    change_Amount[1] = change_Amount[1]+ point_amount[1] ;
                    System.out.println("---sum---"+change_Amount[1]);
                }
                else{
                    int sum = (int)(point_amount[1] * Double.valueOf(row22.getText().toString()));
                    total = total  - sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                    useToken--;
                    selected[1] = false;
                    change_Amount[1] = change_Amount[1]- point_amount[1] ;
                }
            }
        });
        check3.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    int sum = (int)(point_amount[2] * Double.valueOf(row32.getText().toString()));
                    total = total  + sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                    useToken++;
                    selected[2] = true;
                    change_Amount[2] = change_Amount[2]+ point_amount[2] ;
                    System.out.println("---sum---"+change_Amount[2]);
                }
                else{
                    int sum = (int)(point_amount[2] * Double.valueOf(row32.getText().toString()));
                    total = total  - sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                    useToken--;
                    selected[2] = false;
                    change_Amount[2] = change_Amount[2]- point_amount[2] ;
                }
            }
        });
        check4.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    int sum = (int)(point_amount[3] * Double.valueOf(row42.getText().toString()));
                    total = total  + sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                    useToken++;
                    selected[3] = true;
                    change_Amount[3] = change_Amount[3]+ point_amount[3] ;
                    System.out.println("---sum---"+change_Amount[3]);
                }
                else{
                    int sum = (int)(point_amount[3] * Double.valueOf(row42.getText().toString()));
                    total = total  - sum;
                    String s = "獲得 "+current_position+" 點數: " + total + " pt";
                    amount.setText(s);
                    useToken--;
                    selected[3] = false;
                    change_Amount[3] = change_Amount[3]- point_amount[3] ;
                }
            }
        });
    }
    public class JavaScriptInterface {
        private Activity activity;
        public JavaScriptInterface(Activity activiy) {
            this.activity = activiy;
        }
        @JavascriptInterface
        public void get_result()
        {

            Toast.makeText(activity, "我被從WebView呼叫了", Toast.LENGTH_SHORT).show();
        }
    }
    public void progressbar_on()
    {
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }
    public void progressbar_off()
    {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }
    TimerTask timerTask3 = new TimerTask() {
        @Override
        public void run() {
            //Toast.makeText(SwitchPoint.this,"撮合成功!", Toast.LENGTH_LONG).show();
            Bundle bundle = new Bundle();
            bundle.putString("B",company_name[Comb]);
            bundle.putString("A",upload_com);
            bundle.putString("amount",upload_amount);
            Intent intent = new Intent(SwitchPoint.this,Receipt.class);
            intent.putExtras(bundle);
            startActivity (intent);
        }
    };

    public  Runnable mutiThread = new Runnable() {
        public void run() {

            try {
                //webview.loadUrl("javascript:updateMP()");
                //Log.d("update pt","");
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
                    BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                    // 讀取輸入串流的資料
                    String box = ""; // 宣告存放用字串
                    String line = null; // 宣告讀取用的字串
                    while ((line = bufReader.readLine()) != null) {
                        box += line + "\n";
                        // 每當讀取出一列，就加到存放字串後面
                    }
                    inputStream.close(); // 關閉輸入串流

                    result = box; // 把存放用字串放到全域變數
                    //System.out.println("RESU:LTTTTTTTTT"+result);
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
                            CID[i] = jsonObject.getString("CID");


                        }
                        for (int i = 0; i < 4; i++) {
                            if (4 > i) {
                                newarray[i] = company_name[i];
                                newexchange[i] = exchange[i] / exchange[4];
                            } else {
                                newarray[i] = company_name[i + 1];
                                newexchange[i] = exchange[i + 1] / exchange[4];
                            }
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
               // System.out.println("更改PT");
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

                            //Log.d("TAG", "amount" +point_amount[i]);
                        }
                        //System.out.println("更改PT的文字");
                        pt1.setText(point_amount[0]+"pt");
                        pt2.setText(point_amount[1]+"pt");
                        pt3.setText(point_amount[2]+"pt");
                        pt4.setText(point_amount[3]+"pt");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
}
