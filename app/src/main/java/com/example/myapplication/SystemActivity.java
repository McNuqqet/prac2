package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SystemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_activity);

        Button metricButton = (Button) findViewById(R.id.metricButton);
        Button imperialButton = (Button) findViewById(R.id.imperialButton);
        Intent inputPage = new Intent(SystemActivity.this, InputActivity.class);

        imperialButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                inputPage.putExtra("system", "Imperial");
                startActivity(inputPage);
            }
        });

        metricButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                inputPage.putExtra("system", "Metric");
                startActivity(inputPage);
            }
        });
    }
}
