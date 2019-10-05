package com.example.calculator1_1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Trigonometric extends AppCompatActivity implements View.OnClickListener {
    Button bSin;
    Button bCos;
    Button bTg;
    EditText edMain;
    CheckBox checkBoxGrad;
    CheckBox checkBoxRad;
    TextView textView;
    Answer answer;
    private final static String FILE_NAME = "content_Trigonometric.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trigonometric);

        bSin = findViewById(R.id.button_sin_trigonometric);
        bCos = findViewById(R.id.button_cos_trigonometric);
        bTg = findViewById(R.id.button_tg_trigonometric);
        bSin.setOnClickListener(this);
        bCos.setOnClickListener(this);
        bTg.setOnClickListener(this);
        edMain = findViewById(R.id.edit_a_trigonometric);
        checkBoxRad = findViewById(R.id.checkbox_rad);
        checkBoxGrad = findViewById(R.id.checkbox_grad);
        textView = findViewById(R.id.text_trigonometric);

        answer = new Answer();
        try {
            FileInputStream fileInputStream = openFileInput(FILE_NAME);
            ObjectInputStream os = new ObjectInputStream(fileInputStream);
            Object one = os.readObject();
            Answer answer = (Answer) one;
            os.close();
            fileInputStream.close();
            textView.setText(answer.getAns());
        } catch (IOException e) {
            e.printStackTrace();
            textView.setText("0");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if (edMain.getText().toString().equals("")) {
            Toast.makeText(this, "Ничего вводить нельзя!", Toast.LENGTH_SHORT).show();
            textView.setText("Введи уж что-нибудь");
            return;
        }
        double a = Double.parseDouble(edMain.getText().toString());

        if (checkBoxRad.isChecked() && checkBoxGrad.isChecked()) {
            Toast.makeText(this, "Невозможно вычислить и то ,и то", Toast.LENGTH_SHORT).show();
            return;
        }
        String measure = "";
        if (checkBoxGrad.isChecked()) {
            a = Math.toRadians(a);
            measure = "(GRad)";
        } else {
            a = Math.toDegrees(a);
            measure = "(Rad)";
        }
        switch (view.getId()) {
            case R.id.button_sin_trigonometric:
                answer.setAns(String.valueOf("sin = " + String.format("%.12f", Math.sin(a)) + " " + measure));
                textView.setText("sin = " + String.format("%.12f", Math.sin(a)) + " " + measure);
                break;
            case R.id.button_cos_trigonometric:
                answer.setAns("cos = " + String.format("%.12f", Math.cos(a)) + " " + measure);
                textView.setText("cos = " + String.format("%.12f", Math.cos(a)) + " " + measure);
                break;
            case R.id.button_tg_trigonometric:
                answer.setAns("tg= " + String.format("%.12f", Math.tan(a)) + " " + measure);
                textView.setText("tg= " + String.format("%.12f", Math.tan(a)) + " " + measure);
                break;
            default:
                break;
        }
        try {
            FileOutputStream fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fileOutputStream);
            os.writeObject(answer);
            os.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Файл не создался", Toast.LENGTH_SHORT).show();
        }
    }
}
