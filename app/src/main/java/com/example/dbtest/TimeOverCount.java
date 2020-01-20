package com.example.dbtest;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;


public class TimeOverCount extends CountDownTimer {

   /* protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }*/


    public TimeOverCount(long millisInFuture, long countDownInterval){
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished){

    }

    @Override
    public void onFinish() {

            /*
            Intent intent = new Intent(MainActivity.this, WrongEndingActivity.class);
            startActivity(intent);
            finish();

             */

    }
}
