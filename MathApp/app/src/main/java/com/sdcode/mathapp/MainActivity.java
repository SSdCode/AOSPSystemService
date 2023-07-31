package com.sdcode.mathapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.SdMathServiceManager;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MathApp";
    private EditText editTextNumber1, editTextNumber2;
    private Button buttonCalculate;
    private TextView textViewResult;
    private SdMathServiceManager mathServiceManager;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        // Context.SDMATH_SERVICE = sdmath
        mathServiceManager = (SdMathServiceManager) getSystemService("sdmath");

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndDisplayResults();
            }
        });
    }

    private void calculateAndDisplayResults() {
        try {
            int number1 = Integer.parseInt(editTextNumber1.getText().toString());
            int number2 = Integer.parseInt(editTextNumber2.getText().toString());

            // Use the SdMathServiceManager to perform the math operations
            int additionResult = mathServiceManager.add(number1, number2);
            int multiplicationResult = mathServiceManager.multiply(number1, number2);

            // Display the results in the TextView
            String resultText = "Multiplication: " + multiplicationResult + "\n" +
                    "Addition: " + additionResult;
            textViewResult.setText(resultText);
        } catch (Exception e) {
            Log.e(TAG, "Error performing math operations: " + e.getMessage());
            e.printStackTrace();
        }
    }
}