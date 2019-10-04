package com.example.calculator1_1;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ArithmeticOperations extends AppCompatActivity implements View.OnClickListener {
    Button bAdd;
    Button bSub;
    Button bMulti;
    Button bDivision;
    EditText edIntA;
    EditText edIntB;
    TextView textView;
    //File file = new File("MyString");
    Answer answer;
    private final static String FILE_NAME = "content_Arirhmetic.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arithmetic_operations);

        bAdd = findViewById(R.id.addition);
        bAdd.setOnClickListener(this);
        bSub = findViewById(R.id.subtraction);
        bSub.setOnClickListener(this);
        bMulti = findViewById(R.id.multiplication);
        bMulti.setOnClickListener(this);
        bDivision = findViewById(R.id.division);
        bDivision.setOnClickListener(this);

        edIntA = findViewById(R.id.EditArifmeticIntA);
        edIntB = findViewById(R.id.EditArifmeticIntB);
        textView = findViewById(R.id.textArithmetic);
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
        long a = 0;
        long b = 0;
        if (edIntA.getText().toString().equals("") || edIntB.getText().toString().equals("")) {
            Toast.makeText(this, "Ничего вводить нельзя!", Toast.LENGTH_SHORT).show();
            textView.setText("Введи уж что-нибудь");
            return;
        }
        try {
            a = Long.parseLong(edIntA.getText().toString());
            b = Long.parseLong(edIntB.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this, "Ух , большие числа вводишь. Иди за длинной арифметикой на пайтон", Toast.LENGTH_SHORT).show();
            return;
        }
        long c = 0;
        switch (view.getId()) {
            case R.id.addition:
                try {
                    c = a + b;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Ух ,ну куда  ты такие большие числа вводишь", Toast.LENGTH_SHORT).show();
                }
                answer.setAns(String.valueOf(c));
                textView.setText(String.valueOf(c));
                break;
            case R.id.subtraction:
                try {
                    c = a - b;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Ух ,ну куда  ты такие большие числа вводишь", Toast.LENGTH_SHORT).show();
                }
                answer.setAns(String.valueOf(c));
                textView.setText(String.valueOf(c));
                break;
            case R.id.multiplication:
                try {

                    try {
                        c = (a * b) % Long.MAX_VALUE;
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Ух ,огромнейшее число ты получил,мой друг, четсно ,я не знаю чемуоно равно", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                answer.setAns(String.valueOf(c));
                textView.setText(String.valueOf(c));
                if (c < 0) {
                    textView.setText("Очень Большое числа");return;
                }
                break;
            case R.id.division:
                try {
                    c = a / b;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Не дели на ноль ,хулиган!" + e.toString(), Toast.LENGTH_SHORT).show();
                    textView.setText("");
                    return;
                }
                answer.setAns(String.valueOf(c));
                textView.setText(String.valueOf(c) + " ,а остаток равен: " + String.valueOf(a % b));
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
