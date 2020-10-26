package com.example.calculateapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Spliterator;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUESTCODE_TEST = 1;
    Spinner spinner;
    Button button;
    TextView textView;
    ArrayAdapter<Integer>arrayAdapter;
    int pointOfLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        spinner=findViewById(R.id.spinner);
        button=findViewById((R.id.button));

        button.setOnClickListener(this);
        arrayAdapter=new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item);
        arrayAdapter.add(10);
        arrayAdapter.add(20);
        arrayAdapter.add(30);

        spinner.setAdapter(arrayAdapter);




    }

    @Override
    public void onClick(View v) {
        int numberOfQestion;
       numberOfQestion= (int) spinner.getSelectedItem();
        Intent intent= new Intent(MenuActivity.this,MainActivity.class);
        intent.putExtra("key",numberOfQestion);
      //  startActivity(intent);
        startActivityForResult(intent, REQUESTCODE_TEST);



    }
    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        // 受け取るためのコード

         pointOfLog = intent.getIntExtra("key", 0);
         textView.setText(String.valueOf(pointOfLog));


    }

}