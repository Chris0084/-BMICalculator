package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //class Variables or fields
    private TextView resultText;
    private RadioButton maleRadio;
    private RadioButton femaleRadio;

    private EditText ageInput;
    private EditText feetInput;
    private EditText inchesInput;
    private EditText weightInput;

    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setUpButtonClickListener();
    }

    private void findViews() {
        resultText = findViewById(R.id.text_view_result);
        maleRadio = findViewById(R.id.radio_button_male);
        femaleRadio = findViewById(R.id.radio_button_female);
        ageInput = findViewById(R.id.edit_text_age);
        feetInput = findViewById(R.id.edit_text_feet);
        inchesInput = findViewById(R.id.edit_text_inches);
        weightInput = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);

    }

    private void setUpButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiResult = calculateBmi();

                String ageText = ageInput.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18) {
                    displayResult(bmiResult);
                } else {
                    displayGuidance(bmiResult);
                }
            }
        });
    }


    private double calculateBmi() {
        String feetText = feetInput.getText().toString();
        String inchesText = inchesInput.getText().toString();
        String weightText = weightInput.getText().toString();

        //convert the number sting to the int variables
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;

        double heightInMeters = totalInches * 0.0254;

        return weight / (heightInMeters * heightInMeters);

    }

    private void displayResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        resultText.setText(bmiTextResult);

        String fullResultString = "";
        if (bmi < 18.5) {
            //Display under weight
            fullResultString = bmiTextResult + " - Under Weight";

        } else if (bmi > 25) {
            // Display overweight
            fullResultString = bmiTextResult + " - Over Weight";
        } else {
            // Display Healthy
            fullResultString = bmiTextResult + " - Correct Weight";

        }
        resultText.setText(fullResultString);

    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if (maleRadio.isChecked()) {
            fullResultString = bmiTextResult + " please consult with your dr for boys healthy range!";
        } else if (femaleRadio.isChecked()) {
            fullResultString = bmiTextResult + " please consult with your dr for Girls healthy range!";
        } else {
            fullResultString = bmiTextResult + " please consult with your dr for healthy range!";

        }
        resultText.setText(fullResultString);
    }


}