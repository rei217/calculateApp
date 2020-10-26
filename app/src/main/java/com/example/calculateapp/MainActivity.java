package com.example.calculateapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textViewLeft,textViewOperator,textViewRight;
    EditText editText;
    Button buttonQuestion,buttonAnswerCheck;
    ImageView imageView;
    Random random;

    int id;
    int intQuestionLeft,intQuestionRight;
    int intOperator;
    int intMyAnswer,intRealAnswer;

    TextView textViewRemaining,textViewCollect,textViewPoint,textViewMessage;
    Button buttonBack;

    int numberOfQuestion,numberOfRemaining,numberOfCorrect,point;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewLeft = findViewById(R.id.textViewLeft);
        textViewOperator = findViewById(R.id.textViewOperator);
        textViewRight = findViewById(R.id.textViewRight);
        editText = findViewById(R.id.editText);
        buttonQuestion = findViewById(R.id.buttonQuestion);
        buttonAnswerCheck = findViewById(R.id.buttonAnswerCheck);
        imageView = findViewById(R.id.imageView);

        textViewRemaining=findViewById(R.id.textViewRemaining);
        textViewCollect=findViewById(R.id.textViewCollect);
        textViewPoint=findViewById(R.id.textViewPoint);
        textViewMessage=findViewById(R.id.textViewMessage);
        buttonBack=findViewById(R.id.buttonBack);

        buttonQuestion.setOnClickListener(this);
        buttonAnswerCheck.setOnClickListener(this);

        buttonBack.setOnClickListener(this);

        Intent intent=getIntent();
        //情報の塊
        Bundle bundle=intent.getExtras();
        //かたまりから取り出す
        numberOfQuestion= bundle.getInt("numberOfQestion");
        numberOfRemaining=numberOfQuestion;
        textViewRemaining.setText(String.valueOf(numberOfQuestion));
        Log.d("LifeCycle:Main","#OnCreate");

        question();



    }
    protected void onStart() {
        super.onStart();
        Log.d("LifeCycle:Main","#OnStart");
    }
    protected void onPause() {
        super.onPause();
        Log.d("LifeCycle:Main","#OnPause");
    }
    protected void onResume() {
        super.onResume();
        Log.d("LifeCycle:Main","#OnResume");
    }
    protected void onStop() {
        super.onStop();
        Log.d("LifeCycle:Main","#OnStop");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycle:Main","#onDestroy");
    }

    @Override
    public void onClick(View v) {

          id=v.getId();
//
       switch(id){
           case R.id.buttonQuestion:
//                random=new Random();
//                intQuestionLeft=random.nextInt(100)+1;
//                intQuestionRight=random.nextInt(100)+1;
//                textViewLeft.setText(String.valueOf(intQuestionLeft));
//                textViewRight.setText(String.valueOf(intQuestionRight));
//                intOperator=random.nextInt(2)+1;
//                switch (intOperator){
//                    case 1:
//                        textViewOperator.setText("+");
//                        break;
//                    case 2:
//                        textViewOperator.setText(("-"));
//                }
//                editText.setText("");
//
//                imageView.setVisibility(View.INVISIBLE);
               question();
              break;


            case R.id.buttonAnswerCheck:
//                imageView.setVisibility(View.VISIBLE);
//               intMyAnswer=Integer.parseInt((String.valueOf(editText.getText())));
//
//               switch (intOperator){
//                   case 1:
//                       intRealAnswer=intQuestionLeft+intQuestionRight;
//                       break;
//                   case 2:
//                       intRealAnswer=intQuestionLeft-intQuestionRight;
//                       break;
//               }
//               if (intMyAnswer==intRealAnswer){
//                   imageView.setImageResource(R.drawable.maru);
//               }else{
//                   imageView.setImageResource(R.drawable.batsu);
//               }
                answerCheck();
                break;

           case R.id.buttonBack:
               backToMenue();
               break;
        }
    }

    public void question(){
        buttonQuestion.setEnabled(false);
        buttonBack.setEnabled(false);

        buttonAnswerCheck.setEnabled(true);
        //残り問題数を計算して表示

        random=new Random();
        intQuestionLeft=random.nextInt(100)+1;
        intQuestionRight=random.nextInt(100)+1;
        textViewLeft.setText(String.valueOf(intQuestionLeft));
        textViewRight.setText(String.valueOf(intQuestionRight));
        intOperator=random.nextInt(2)+1;
        switch (intOperator){
            case 1:
                textViewOperator.setText("+");
                break;
                case 2:
                    textViewOperator.setText(("-"));
        }
        editText.setText("");

        imageView.setVisibility(View.INVISIBLE);


    }

    public void answerCheck() {

        buttonAnswerCheck.setEnabled(false);
        buttonQuestion.setEnabled(true);
       // buttonBack.setEnabled(false);
        buttonBack.setEnabled(true);

        numberOfRemaining-=1;
        textViewRemaining.setText(String.valueOf(numberOfRemaining));

        imageView.setVisibility(View.VISIBLE);
        intMyAnswer=Integer.parseInt((String.valueOf(editText.getText())));

            switch (intOperator){
                case 1:
                    intRealAnswer=intQuestionLeft+intQuestionRight;
                    break;
                case 2:
                    intRealAnswer=intQuestionLeft-intQuestionRight;
                    break;
            }
            if (intMyAnswer==intRealAnswer){
                imageView.setImageResource(R.drawable.maru);
                //正解数を計算して表示する
                numberOfCorrect+=1;
                textViewCollect.setText(String.valueOf(numberOfCorrect));


            }else{
                imageView.setImageResource(R.drawable.batsu);
            }
         //正答率を計算
        point=(int)((double)numberOfCorrect/(double)((numberOfQuestion-numberOfRemaining))*100);
        textViewPoint.setText(String.valueOf(point));

        //残り問題数がなくなった場合
        if(numberOfRemaining==0){
            buttonQuestion.setEnabled(false);
            buttonAnswerCheck.setEnabled(false);
            buttonBack.setEnabled(true);
            textViewMessage.setText("Finish");
        }


    }
    public void backToMenue(){
       //finish();
//        Intent intent =new Intent(MainActivity.this,MenuActivity.class);
//        intent.putExtra("keyPoint",point);
//        startActivity(intent);

        Intent intent = new Intent();
        Log.d("Life:Main","BackToMenu");
        intent.putExtra("Point", point);
        setResult(RESULT_OK, intent);
        finish();
    }

}