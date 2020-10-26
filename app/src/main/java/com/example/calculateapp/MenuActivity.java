package com.example.calculateapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    TextView textViewLog;

    ArrayAdapter<Integer>arrayAdapter;
    int pointOfLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        spinner=findViewById(R.id.spinner);
        button=findViewById((R.id.button));
        textViewLog=findViewById((R.id.textViewLog));
        button.setOnClickListener(this);
        arrayAdapter=new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item);
        arrayAdapter.add(10);
        arrayAdapter.add(20);
        arrayAdapter.add(30);

        spinner.setAdapter(arrayAdapter);
        Log.d("LifeCycle:Menu","#OnCreate");

    }
    protected void onStart() {
        super.onStart();
        Log.d("LifeCycle:Menu","#OnStart");
    }
    protected void onPause() {
        super.onPause();
        Log.d("LifeCycle:Menu","#OnPause");
    }
    protected void onResume() {
        super.onResume();
        Log.d("LifeCycle:Menu","#OnResume");
    }
    protected void onStop() {
        super.onStop();
        Log.d("LifeCycle:Menu","#OnStop");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycle:Menu","#onDestroy");
    }
    @Override
    public void onClick(View v) {
        int numberOfQestion;
       numberOfQestion= (int) spinner.getSelectedItem();
        Intent intent= new Intent(MenuActivity.this,MainActivity.class);
        intent.putExtra("numberOfQestion",numberOfQestion);
      //  startActivity(intent);
        startActivityForResult(intent, REQUESTCODE_TEST);



    }
    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d("Life:Menu","onActivityResult");
        Log.d("Life:Menu","" + requestCode);
        Log.d("Life:Menu","" + resultCode);
        // 受け取るためのコード
      if(requestCode==1 && resultCode==-1){
          Log.d("Life:Menu","onActivityResultOfIf");
           pointOfLog = intent.getIntExtra("Point", 0);

           textViewLog.setText(String.valueOf(pointOfLog));
           Log.d("Life:Menu:Point","" + pointOfLog);

       }


    }

}