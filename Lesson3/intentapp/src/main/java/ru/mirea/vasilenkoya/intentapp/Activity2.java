package ru.mirea.vasilenkoya.intentapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity2 extends AppCompatActivity {
    private TextView dateOut;                               // TextView для отображения даты

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);         // Включение режима edge-to-edge
        setContentView(R.layout.activity_main);             // Установка содержимого активности из XML

        // Установка слушателя для применения отступов окна
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()); // Получение отступов системных панелей
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom); // Установка отступов
            return insets; // Возврат инсет-объекта
        });

        // Получение интента и извлечение даты
        Intent intent = getIntent();
        String date = intent.getStringExtra("date");  // Извлечение строки с ключом "date"
        TextView textView = findViewById(R.id.dateOut);     // Инициализация TextView для отображения даты
        textView.setText("КВАДРАТ ЗНАЧЕНИЯ МОЕГО НОМЕРА ПО СПИСКУ В ГРУППЕ СОСТАВЛЯЕТ 9, а текущее время " + date); // Установка текста в TextView
    }
}
