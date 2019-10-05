package com.example.calculator1_1;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;

public class Exponentiation extends AppCompatActivity implements View.OnClickListener {

    Button bcalculate;
    EditText edA;
    EditText edB;
    TextView textView;
    Answer answer;
    private final static String FILE_NAME = "content_Exponentation.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exponentiation);

        bcalculate = findViewById(R.id.button_exponentation);
        bcalculate.setOnClickListener(this);
        edA = findViewById(R.id.edit_a_exponentation);
        edB = findViewById(R.id.edit_b_exponentation);
        textView = findViewById(R.id.text_exponentation);
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
        if (edA.getText().toString().equals("") || edB.getText().toString().equals("")) {
            textView.setText("Введи уж что-нибудь");
            Toast.makeText(this, "Ничего вводить нельзя!", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (view.getId()) {
            case R.id.button_exponentation:
                try {
                    a = Long.parseLong(edA.getText().toString());
                    b = Long.parseLong(edB.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "НЕ вводи такие большие числа ,пожалуйста", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                long c = 0;
                c = (long) Math.pow(a, b);
                if (c == Long.MAX_VALUE) {
                    textView.setText("Не вводи такие большие значения");
                }
                answer.setAns(pow(a, b));
                textView.setText(pow(a, b));
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

    private String pow(long a, long b) {
        String answer = String.valueOf(a);
        BigInteger bigInteger = new BigInteger(answer);
        BigInteger two = bigInteger;
        if(b==0){
            return "1";
        }
        for (int i = 0; i < b-1; i++) {
            bigInteger = bigInteger.multiply(two);
        }
        answer = bigInteger.toString();
        if (answer.length() > 40) {
            Toast.makeText(this, "40 цифорок в числе это максимум, неужели тебе этого не хватит?", Toast.LENGTH_LONG).show();
            answer = answer.substring(0, 40);
        }
        return answer;
    }
}

