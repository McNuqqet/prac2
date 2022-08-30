package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        Intent receiveInputs = getIntent();
        float weight = receiveInputs.getFloatExtra("weight", 0);
        float height = receiveInputs.getFloatExtra("height", 0);
        float bmi = receiveInputs.getFloatExtra("bmi", 0);
        String[] units = receiveInputs.getStringArrayExtra("units");

        TextView classResult = (TextView) findViewById(R.id.classResult);
        TextView weightResult = (TextView) findViewById(R.id.weightResult);
        TextView heightResult = (TextView) findViewById(R.id.heightResult);
        TextView bmiResult = (TextView) findViewById(R.id.bmiResult);

        Button finishButton = (Button) findViewById(R.id.finishButton);
        Button zoomIn = (Button) findViewById(R.id.zoomIn);
        Button zoomOut = (Button) findViewById(R.id.zoomOut);

        // 0xFF###### idk why FF but converts hexcode to integer
        if (bmi < 18.5) {
            classResult.setText("Underweight");
            classResult.setBackgroundColor(0xFFFFD204); // Yellow
        } else if (bmi < 25) {
            classResult.setText("Healthy weight");
            classResult.setBackgroundColor(0xFF3CD226); // Green
        } else if (bmi < 30) {
            classResult.setText("Overweight but not obese");
            classResult.setBackgroundColor(0xFFEEE616); // Yellow
        } else if (bmi < 35) {
            classResult.setText("Obese class I");
            classResult.setBackgroundColor(0xFFF2B21B); // Orange
        } else if (bmi < 40) {
            classResult.setText("Obese class II");
            classResult.setBackgroundColor(0xFFF64C09); // Red-Orange
        } else {
            classResult.setText("Obese class III");
            classResult.setBackgroundColor(0xFFF60909); // Red
        }

        DecimalFormat df = new DecimalFormat("#.#");
        weightResult.setText("Weight: " + weight + " " + units[0]);
        heightResult.setText("height: " + height + " " + units[1]);
        bmiResult.setText("Your BMI is " + df.format(bmi));

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent finish = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(finish);
            }
        });

        zoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseText(classResult);
                increaseText(bmiResult);
                increaseText(weightResult);
                increaseText(heightResult);

                increaseButton(zoomIn);
                increaseButton(zoomOut);
                increaseButton(finishButton);
            }
        });

        zoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseText(classResult);
                decreaseText(bmiResult);
                decreaseText(weightResult);
                decreaseText(heightResult);

                decreaseButton(zoomIn);
                decreaseButton(zoomOut);
                decreaseButton(finishButton);
            }
        });
    }

    private void increaseText(TextView text) {
        float size = text.getTextSize();
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, size * 1.1F);
    }

    private void decreaseText(TextView text) {
        float size = text.getTextSize();
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, size * 0.9F);
    }

    private void increaseButton(Button b) {
        float size = b.getTextSize();
        b.setTextSize(TypedValue.COMPLEX_UNIT_PX, size * 1.1F);

        size = b.getWidth();
        b.setWidth((int) (size * 1.1F));

        size = b.getHeight();
        b.setHeight((int) (size * 1.1F));
    }

    private void decreaseButton(Button b) {
        float size = b.getTextSize();
        b.setTextSize(TypedValue.COMPLEX_UNIT_PX, size * 0.9F);

        size = b.getWidth();
        b.setWidth((int) (size * 0.9F));

        size = b.getHeight();
        b.setHeight((int) (size * 0.9F));
    }
}

