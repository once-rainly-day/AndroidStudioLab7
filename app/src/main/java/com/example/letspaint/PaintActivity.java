// PaintActivity.java
package com.example.letspaint;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PaintActivity extends AppCompatActivity {

    static final int MENU_COLOR_WHITE = Menu.FIRST + 1;
    static final int MENU_NEW_IMAGE = Menu.FIRST + 2;
    static final int MENU_SAVE = Menu.FIRST + 3;
    static final int MENU_COLOR = Menu.FIRST + 4;
    static final int MENU_WIDTH = Menu.FIRST + 5;

    PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paintView = new PaintView(this);
        setContentView(paintView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, MENU_COLOR_WHITE, Menu.NONE, R.string.menu_erase);
        menu.add(Menu.NONE, MENU_NEW_IMAGE, Menu.NONE, R.string.menu_new);
        menu.add(Menu.NONE, MENU_SAVE, Menu.NONE, R.string.menu_save);
        menu.add(Menu.NONE, MENU_COLOR, Menu.NONE, R.string.menu_color);
        menu.add(Menu.NONE, MENU_WIDTH, Menu.NONE, R.string.menu_width);
        return true;
    }
    // реалізація пунктів контекстного меню
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // вибір білого кольору та широкого пензля
            // для того щоб імітувати роботу ластика
            case MENU_COLOR_WHITE:
                paintView.set_line_color(Color.WHITE);
                paintView.set_line_width(70);
                Toast.makeText(this, "Eraser picked.", Toast.LENGTH_SHORT).show();
                return true;
            // очищення холсту
            case MENU_NEW_IMAGE:
                paintView.clear();
                Toast.makeText(this, "Сanvas cleaned.", Toast.LENGTH_SHORT).show();
                return true;
            // збереження малюнку в пам'ять пристрою
            case MENU_SAVE:
                paintView.save_image();
                return true;
            // виклик активності, яка реалізує інтерфейс
            // зміни кольору пензля
            case MENU_COLOR:
                Intent colorIntent = new Intent(PaintActivity.this, ColorActivity.class);
                startActivityForResult(colorIntent, 1); // Запускаем ToolsActivity с requestCode 1
                return true;
            // виклик активності, яка реалізує інтерфейс
            // зміни ширини пензля
            case MENU_WIDTH:
                Intent widthIntent = new Intent(PaintActivity.this, WidthActivity.class);
                startActivityForResult(widthIntent, 2); // Запускаем ToolsActivity с requestCode 1
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // Зчитування результуючих даних з активностей
    // створених для зміни кольору та ширини пензля
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1){
                // читання даних з ColorActivity
                int red = data.getIntExtra("red", 0);
                int green = data.getIntExtra("green", 0);
                int blue = data.getIntExtra("blue", 0);
                // передавання RGB-параметрів в метод set_line_color()
                paintView.set_line_color(Color.rgb(red, green, blue));
            }
            else if (requestCode == 2){
                // читання даних з WidthActivity
                int width = data.getIntExtra("width", 6);
                // передавання значенння SeekBar в метод set_line_width()
                paintView.set_line_width(width);
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}


