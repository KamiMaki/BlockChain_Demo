package com.dragon.blockchain_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

public class PointWeb extends AppCompatActivity {
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_web);
        back = findViewById(R.id.back);
        WebView web_view;
        web_view = findViewById(R.id.web_view);
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.loadUrl("http://47046c48.ngrok.io/");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PointWeb.this,ShopList.class));
            }
        });
    }
}
