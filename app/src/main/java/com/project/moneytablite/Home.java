package com.project.moneytablite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    TextView textView;
    Toolbar toolbar;
    Button logBtn,tbtn, showbtn,viewbtn,aboutbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        toolbar= findViewById(R.id.toolbar3);
        logBtn=findViewById(R.id.lgbtn);
        tbtn= findViewById(R.id.tbtn);
        showbtn= findViewById(R.id.showbtn);
        viewbtn= findViewById(R.id.viewbtn);
        aboutbtn= findViewById(R.id.Aboutbtn);

        SharedPreferences sp=getSharedPreferences("MYPREF", MODE_PRIVATE);
        String display= sp.getString("display","");
        toolbar.setTitle(sp.getString("uname",""));

        tbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Home.this,QueryActivity.class);
                startActivity(intent);
            }
        });

        showbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Home.this,ViewActivity.class);
                startActivity(intent);
            }
        });
        aboutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Home.this,About.class);
                startActivity(intent);
            }
        });
        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Home.this,ViewPassbook.class);
                startActivity(intent);
            }
        });


        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
