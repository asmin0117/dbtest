package com.example.dbtest;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {
    ImageView btnclose;
    Button onBack, offBack, onSound, offSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dialog dialog = new Dialog(SettingActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setting);


        //소프트키(네비게이션바) 없애기 시작
        View decorView = getWindow().getDecorView();

        int uiOption = getWindow().getDecorView().getSystemUiVisibility();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        decorView.setSystemUiVisibility(uiOption);
        //소프트키(네비게이션바) 없애기 끝


        btnclose = (ImageView) findViewById(R.id.btnclose);
       onBack = (Button)findViewById(R.id.onBack);
       offBack =(Button) findViewById(R.id.offBack);
       onSound = (Button) findViewById(R.id.onSound);
       offSound = (Button) findViewById(R.id.offSound);

       btnclose.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });

       onBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Resources res1 = getResources();
               Drawable drawable = res1.getDrawable(R.drawable.on_light);
               Drawable drawable1 = res1.getDrawable(R.drawable.off_dark);
               onBack.setBackground(drawable);
               offBack.setBackground(drawable1);
           }
       });

       offBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Resources res1 = getResources();
               Drawable drawable = res1.getDrawable(R.drawable.on_dark);
               Drawable drawable1 = res1.getDrawable(R.drawable.off_light);
               onBack.setBackground(drawable);
               offBack.setBackground(drawable1);
           }
       });

       onSound.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Resources res1 = getResources();
               Drawable drawable = res1.getDrawable(R.drawable.on_light);
               Drawable drawable1 = res1.getDrawable(R.drawable.off_dark);
               onSound.setBackground(drawable);
               offSound.setBackground(drawable1);
           }
       });

       offSound.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Resources res1 = getResources();
               Drawable drawable = res1.getDrawable(R.drawable.on_dark);
               Drawable drawable1 = res1.getDrawable(R.drawable.off_light);
               onSound.setBackground(drawable);
               offSound.setBackground(drawable1);
           }
       });

    }
}
