package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View ageScreen = (View) findViewById(R.id.ageScreen);
        Snackbar notif = Snackbar.make(ageScreen, "Must be over 20.", Snackbar.LENGTH_SHORT);
        Button yesButton = (Button) findViewById(R.id.yesButton);
        Button noButton = (Button) findViewById(R.id.noButton);

        noButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                notif.show();
            }
        });

        yesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent systemPage = new Intent(MainActivity.this, SystemActivity.class);
                startActivity(systemPage);
            }
        });
    }
}
