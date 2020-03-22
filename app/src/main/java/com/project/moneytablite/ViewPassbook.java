package com.project.moneytablite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class ViewPassbook extends AppCompatActivity {


    TextView Nametxt, Atxt;

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_passbook);

        Nametxt = findViewById(R.id.restxt);
        Atxt = findViewById(R.id.Moneytxt);

        if(Result.getCount()>0){
            Result.check();
        }
        else if(Result.getCount()==0)
        {
            Toast.makeText(this, "Currently There is no Data present", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }
        Nametxt.setText(Result.getNames());
        Atxt.setText(Result.getTotal());


    }
}
