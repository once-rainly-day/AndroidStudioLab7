// ColorActivity.java
package com.example.letspaint;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ColorActivity extends AppCompatActivity {

    private EditText redEditText;
    private EditText greenEditText;
    private EditText blueEditText;
    private Button colorButton;
    private Button checkButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_activity);

        redEditText = findViewById(R.id.editRed);
        greenEditText = findViewById(R.id.editGreen);
        blueEditText = findViewById(R.id.editBlue);

        textView = findViewById(R.id.textView);
        checkButton = findViewById(R.id.check_btn);
        checkButton.setOnClickListener(v -> {
            // оголошення стандартних значень для того,
            // щоб програма працювала коректно, якщо
            // користувач не ввів дані в поля
            if (redEditText.getText().toString().isEmpty()) {
                redEditText.setText("0");
            }
            if (greenEditText.getText().toString().isEmpty()) {
                greenEditText.setText("0");
            }
            if (blueEditText.getText().toString().isEmpty()) {
                blueEditText.setText("0");
            }
            // читання введених значень
            int red = Integer.parseInt(redEditText.getText().toString());
            int green = Integer.parseInt(greenEditText.getText().toString());
            int blue = Integer.parseInt(blueEditText.getText().toString());
            // зміна кольору текстового поля, щоб наочно
            // показати користувачу обраний ним колір
            textView.setTextColor(Color.rgb(red, green, blue));
        });

        colorButton = findViewById(R.id.color_btn);
        colorButton.setOnClickListener(v -> {

            if (redEditText.getText().toString().isEmpty()) {
                redEditText.setText("0");
            }
            if (greenEditText.getText().toString().isEmpty()) {
                greenEditText.setText("0");
            }
            if (blueEditText.getText().toString().isEmpty()) {
                blueEditText.setText("0");
            }
            // читання введених значень
            int red = Integer.parseInt(redEditText.getText().toString());
            int green = Integer.parseInt(greenEditText.getText().toString());
            int blue = Integer.parseInt(blueEditText.getText().toString());
            // передавання RGB-значень в головну активність
            Intent resultIntent = new Intent();
            resultIntent.putExtra("red", red);
            resultIntent.putExtra("green", green);
            resultIntent.putExtra("blue", blue);
            setResult(RESULT_OK, resultIntent);
            // повідомлення про вдалу операцію
            Toast.makeText(this, "Color changed!", Toast.LENGTH_SHORT).show();
            // кінець активності, повернення до PaintActivity.java
            finish();
        });

        // кнопка повернення, на випадок, якщо користувач
        // не хоче змінювати колір лінії
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> onBackPressed());
    }
}



