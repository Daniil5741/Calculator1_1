package com.example.calculator1_1;

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

public class Logarithmic extends AppCompatActivity implements View.OnClickListener {

    Button bLogarith;
    EditText edA;
    EditText edB;
    TextView textView;
    Answer answer;
    private final static String FILE_NAME = "content_Logarithmic.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logarithmic);

        bLogarith = findViewById(R.id.button_logarithmic);
        bLogarith.setOnClickListener(this);
        edA = findViewById(R.id.edit_a_logarithmic);
        edB = findViewById(R.id.edit_b_logarithmic);
        textView = findViewById(R.id.text_logarithmic);
        answer = new Answer();

        try {
            FileInputStream fileInputStream = openFileInput(FILE_NAME);
            ObjectInputStream os = new ObjectInputStream(fileInputStream);
            Object one = os.readObject();
            Answer answer = (Answer) one;
            os.close();
            fileInputStream.close();
            textView.setText(answer.getAns());
        } catch (IOException | RuntimeException e) {
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
        if (edA.getText().toString().equals("") || edB.getText().toString().equals("")) {
            textView.setText("Введи уж что-нибудь");
            Toast.makeText(this, "Ничего вводить нельзя!", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            a = Long.parseLong(edA.getText().toString());
            b = Long.parseLong(edB.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this, "НЕ вводи огромные числа", Toast.LENGTH_SHORT).show();
            return;
        }

        double d = 0;
        try {
            d = (Math.log(a)) / (Math.log(b));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "НЕ дели на ноль", Toast.LENGTH_SHORT).show();
        }
        textView.setText(" log(a) with base = " +String.format("%.12f",d));
        answer.setAns(" log(a) with base = " + String.format("%.12f",d));
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
