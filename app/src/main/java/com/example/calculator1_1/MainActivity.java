package com.example.calculator1_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bArithmetic;
    Button bExponentation;
    Button bTrigonometric;
    Button bLogarithmic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bArithmetic = findViewById(R.id.arifmetic_operations);
        bArithmetic.setOnClickListener(this);
        bExponentation = findViewById(R.id.exponentiation);
        bExponentation.setOnClickListener(this);
        bTrigonometric = findViewById(R.id.trigonometric_function);
        bTrigonometric.setOnClickListener(this);
        bLogarithmic = findViewById(R.id.logarithmic);
        bLogarithmic.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.arifmetic_operations:
                Intent intentArith = new Intent(this, ArithmeticOperations.class);
                startActivity(intentArith);
                break;
            case R.id.exponentiation:
                Intent intentExp = new Intent(this, Exponentiation.class);
                startActivity(intentExp);
                break;
            case R.id.trigonometric_function:
                Intent intentTrig = new Intent(this, Trigonometric.class);
                startActivity(intentTrig);
                break;
            case R.id.logarithmic:
                Intent intentLog = new Intent(this, Logarithmic.class);
                startActivity(intentLog);
                break;
            default:
                break;
        }
    }
}
