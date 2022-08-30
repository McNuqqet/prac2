package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;

public class InputActivity extends AppCompatActivity {
    private boolean updateSlider = true;
    private boolean updateText = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_activity);

        Intent receiveSystem = getIntent();
        String system = receiveSystem.getStringExtra("system");

        int maxHeight = 300;
        int maxWeight = 300;
        String[] units = {"kg", "cm"};

        if(system.equals("Imperial")){
            units[0] = "lb";
            units[1] = "in";
            maxWeight = 665;
            maxHeight = 120;
        }

        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        Snackbar notif = Snackbar.make(findViewById(R.id.inputScreen), "", Snackbar.LENGTH_SHORT);
        Slider heightSlider = (Slider) findViewById(R.id.heightSlider);
        Slider weightSlider = (Slider) findViewById(R.id.weightSlider);

        TextView heightTitle = (TextView) findViewById(R.id.heightTitle);
        TextView weightTitle = (TextView) findViewById(R.id.weightTitle);
        TextView heightUnit = (TextView) findViewById(R.id.heightUnit);
        TextView weightUnit = (TextView) findViewById(R.id.weightUnit);
        TextView inputTitle = (TextView) findViewById(R.id.inputTitle);

        EditText heightValue = (EditText) findViewById(R.id.heightValue);
        EditText weightValue = (EditText) findViewById(R.id.weightValue);

        inputTitle.setText(system + " system selected.\nInput your weight (" + units[0]
                + ") & height (" + units[1] + ") information");
        weightTitle.setText("Weight (" + units[0] + ")");
        heightTitle.setText("Height (" + units[1] + ")");

        weightUnit.setText(units[0]);
        heightUnit.setText(units[1]);

        heightSlider.setValueTo(maxHeight);
        weightSlider.setValueTo(maxWeight);

        weightSlider.addOnChangeListener(new Slider.OnChangeListener(){
            @Override
            public void onValueChange(Slider slider, float value, boolean fromUser){
                if(updateText) {
                    updateSlider = false;
                    weightValue.setText(Float.toString(value));
                    updateSlider = true;
                }
            }
        });

        heightSlider.addOnChangeListener(new Slider.OnChangeListener(){
            @Override
            public void onValueChange(Slider slider, float value, boolean fromUser){
                if(updateText) {
                    updateSlider = false;
                    heightValue.setText(Float.toString(value));
                    updateSlider = true;
                }
            }
        });

        heightValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(updateSlider) {
                    updateText = false;
                    try {
                        float val = Float.parseFloat(String.valueOf(heightValue.getText()));
                        if(val <= heightSlider.getValueTo()) {
                            heightSlider.setValue(val);
                        }
                        else{
                            heightSlider.setValue(heightSlider.getValueTo());
                            heightValue.setText(Float.toString(heightSlider.getValueTo()));
                        }
                    }
                    catch(NumberFormatException e){
                        heightSlider.setValue(0);
                    }
                    catch(IllegalStateException e){
                    }
                    updateText = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        weightValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(updateSlider) {
                    updateText = false;
                    try {
                        float val = Float.parseFloat(String.valueOf(weightValue.getText()));
                        if (val <= weightSlider.getValueTo()) {
                            weightSlider.setValue(val);
                        } else {
                            weightSlider.setValue(weightSlider.getValueTo());
                            weightValue.setText(Float.toString(weightSlider.getValueTo()));
                        }
                    }
                    catch(NumberFormatException e){
                        weightSlider.setValue(0);
                    }
                    updateText = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println(weightValue.getText());
                if(weightSlider.getValue() < 0.00001 && heightSlider.getValue() < 0.00001){
                    notif.setText("Weight and height are missing values");
                    notif.show();
                }
                else if(weightSlider.getValue() < 0.00001){
                    notif.setText("Weight missing a value");
                    notif.show();
                }
                else if(heightSlider.getValue() < 0.00001){
                    notif.setText("Height missing a value");
                    notif.show();
                }
                else {
                    float bmi = 0;
                    if (system.equals("Metric"))
                        bmi = weightSlider.getValue() / heightSlider.getValue() / heightSlider.getValue() * 10000;

                    else if (system.equals("Imperial"))
                        bmi = weightSlider.getValue() / heightSlider.getValue() / heightSlider.getValue() * 703;

                    Intent resultPage = new Intent(InputActivity.this, ResultActivity.class);
                    resultPage.putExtra("weight", weightSlider.getValue());
                    resultPage.putExtra("height", heightSlider.getValue());
                    resultPage.putExtra("bmi", bmi);
                    resultPage.putExtra("units", units);
                    startActivity(resultPage);
                }
            }
        });
    }
}
