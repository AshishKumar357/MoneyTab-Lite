package com.project.moneytablite;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class QueryActivity extends AppCompatActivity {

    Button logBtn;
    Toolbar toolbar;
    EditText line1,line2;
    Result ab;

    ArrayList<ExampleItem> mExampleList;
    ArrayList<ExampleItem> showList;
    private ExampleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        logBtn = findViewById(R.id.lgbtn);
        toolbar= findViewById(R.id.toolbar3);
        line1 = findViewById(R.id.edittext_line_1);
        line2 = findViewById(R.id.edittext_line_2);

        SharedPreferences sp=getSharedPreferences("MYPREF", MODE_PRIVATE);
        String display= sp.getString("display","");
        toolbar.setTitle(sp.getString("uname",""));


        loadData();
        buildRecyclerView();
        setInsertButton();

        Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog2(QueryActivity.this,"Are you sure?","Once you click on " +
                        "Save all Changes will be reflected in your account permanently","Save","Cancel");

            }
        });
        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QueryActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }




    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<ExampleItem>>() {}.getType();
        mExampleList = gson.fromJson(json, type);

        if (mExampleList == null) {
            mExampleList = new ArrayList<>();
        }
    }

     private void buildRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

         mAdapter = new ExampleAdapter(showList = new ArrayList<ExampleItem>());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setInsertButton() {
        Button buttonInsert = findViewById(R.id.button_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(QueryActivity.this,"Are you sure?","Once you click on " +
                        "Insert this record will be stored temporarily","Insert","Cancel");

            }
        });
    }

    private void insertItem(String line1, String line2) {
        mExampleList.add(new ExampleItem(line1, line2));
        showList.add(new ExampleItem(line1, line2));
        mAdapter.notifyItemInserted(mExampleList.size());
        Result.addMember(line1,Integer.valueOf(line2));
    }

    public void showAlertDialog(Context context, String title, String message, String posBtnMsg, String negBtnMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder( context );
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(posBtnMsg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                insertItem(line1.getText().toString(), line2.getText().toString());
                line1.setText("");
                line2.setText("");

                dialog.cancel();
            }
        });
        builder.setNegativeButton(negBtnMsg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                line1.setText("");
                line2.setText("");
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void showAlertDialog2(Context context, String title, String message, String posBtnMsg, String negBtnMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder( context );
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(posBtnMsg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                saveData();
                Intent intent = new Intent(QueryActivity.this,QueryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                dialog.cancel();
            }
        });
        builder.setNegativeButton(negBtnMsg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                line1.setText("");
                line2.setText("");
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}

