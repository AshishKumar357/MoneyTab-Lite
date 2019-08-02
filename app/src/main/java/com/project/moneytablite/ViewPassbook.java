package com.project.moneytablite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewPassbook extends AppCompatActivity {


    TextView restxt;

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_passbook);

        restxt=findViewById(R.id.restxt);

        if(Result.getCount()>0){
            Result.check();
        }
        else if(Result.getCount()==0)
        {
            Toast.makeText(this, "Currently There is no Data present", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }
        restxt.setText(Result.returnString());



    }
}
