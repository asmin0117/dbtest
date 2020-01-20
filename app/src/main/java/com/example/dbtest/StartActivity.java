package com.example.dbtest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.app.AlertDialog;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import java.util.Random;

public class StartActivity extends AppCompatActivity {

    private static final String TAG = "AnonymousAuth";
    private FirebaseAuth mAuth;
    private SharedPreferences appData;

    private String username = null;

    ImageView setting;
    Button btnstart, btncall;
    private BackPressCloseHandler backPressCloseHandler;

    //String uid = mAuth.getUid();

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference("user");




    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_start);

        backPressCloseHandler = new BackPressCloseHandler(this);

    mAuth = FirebaseAuth.getInstance();
    appData = getSharedPreferences("appData", MODE_PRIVATE);




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

        setting = (ImageView) findViewById(R.id.setting);
        btnstart = (Button) findViewById(R.id.btnstart);
        btncall = (Button) findViewById(R.id.btncall);



        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, DayActivity.class);
                startActivity(intent);
                signInAnonymously();
                //String name = mAuth.getUid();

                //String uid = user.getUid();

                //username = "user_ " + new Random().nextInt(1000);

                //databaseReference.setValue("name");
                //databaseReference.child("users").child(name).updateChildren(username);
                //databaseReference.child("users").child(name).child("username").setValue(username);

                //databaseReference.setValue("username");

                String uid = mAuth.getUid();
                username = "user_ " + new Random().nextInt(1000);
               databaseReference.child("uid").push().setValue(username);
                finish();
            }
        });


}
    @Override public void onBackPressed() { backPressCloseHandler.onBackPressed(); }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


    private void signInAnonymously() {
        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "signInAnonymously : success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
/*
                    String uid = mAuth.getUid();
                    username = "user_ " + new Random().nextInt(1000);
                    databaseReference.child(uid).setValue(username);
                    //databaseReference.push().setValue(username);
                    //databaseReference.push().setValue(uid);
*/
                } else {
                    Log.w(TAG, "signInAnonymously : failure", task.getException());
                    Toast.makeText(StartActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }

            }
        });
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }
/*
    private void save() {
        SharedPreferences.Editor editor = appData.edit();
        editor.putString("ID", )
    }

    public void checker() {
        SharedPreferences sf = getSharedPreferences("불러오기", MODE_PRIVATE);

    }
*/

    private void updateUI(FirebaseUser user) {
        boolean isSignedIn = (user != null);

        if (isSignedIn) {

            String uid = user.getUid();
            UserInfo userinfo = new UserInfo() {
                @NonNull
                @Override
                public String getUid() {
                    return null;
                }

                @NonNull
                @Override
                public String getProviderId() {
                    return null;
                }

                @Nullable
                @Override
                public String getDisplayName() {
                    return null;
                }

                @Nullable
                @Override
                public Uri getPhotoUrl() {
                    return null;
                }

                @Nullable
                @Override
                public String getEmail() {
                    return null;
                }

                @Nullable
                @Override
                public String getPhoneNumber() {
                    return null;
                }

                @Override
                public boolean isEmailVerified() {
                    return false;
                }
            };
            //userinfo.setUID(uid);
            //databaseReference.child("users").child(uid).setValue(userinfo);
           // username = "user_ " + new Random().nextInt(1000);
            //databaseReference.push().child(uid).child("username").setValue(username);

            //databaseReference.push().child(uid).setValue(username);

            //databaseReference.push().setValue(uid);
        }
        else
        {
            Log.d("AnonymousAuth", "user null");
        }
    }

/*
    private void writeNewPost(String , String n)
    {
        String uid = mAuth.getUid();
        String key = databaseReference.child("post").push().getKey();
        databaseReference.child("posts").child(key).setValue(uid);
    }
*/


}


