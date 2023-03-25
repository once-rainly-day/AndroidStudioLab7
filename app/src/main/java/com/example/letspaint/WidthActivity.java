// WidthActivity.java
package com.example.letspaint;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WidthActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private Button widthButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.width_activity);

        seekBar = findViewById(R.id.seekBar);

        widthButton = findViewById(R.id.width_btn);
        widthButton.setOnClickListener(v -> {
            int width = (seekBar.getProgress());

            Intent resultIntent = new Intent();
            resultIntent.putExtra("width", width);

            setResult(RESULT_OK, resultIntent);
            // повідомлення про вдалу операцію
            Toast.makeText(this, "Width changed!", Toast.LENGTH_SHORT).show();
            // кінець активності, повернення до PaintActivity.java
            finish();
        });

        // кнопка повернення, на випадок, якщо користувач
        // не хоче змінювати ширину лінії
        Button backButton = findViewById(R.id.back_button2);
        backButton.setOnClickListener(v -> onBackPressed());
    }
}
