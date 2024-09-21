package ru.mirea.vasilenkoya.intentapp;

import android.content.Intent;
import android.icu.text.SimpleDateFormat; // Импорт для форматирования даты
import android.os.Bundle;

import androidx.activity.EdgeToEdge; // Импорт для включения режима edge-to-edge
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets; // Импорт для работы с Insets
import androidx.core.view.ViewCompat; // Импорт для поддержки view
import androidx.core.view.WindowInsetsCompat; // Импорт для работы с WindowInsets

import java.util.Date; // Импорт для работы с объектами даты

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Включение режима edge-to-edge
        setContentView(R.layout.activity_main); // Установка содержимого активности из XML

        // Установка слушателя для применения отступов окна
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()); // Получение отступов системных панелей
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom); // Установка отступов для вьюхи
            return insets; // Возврат инсет-объекта
        });

        long dateInMillis = System.currentTimeMillis(); // Получение текущего времени в миллисекундах
        String format = "yyyy-MM-dd HH:mm:ss"; // Форматирование даты
        SimpleDateFormat sdf = new SimpleDateFormat(format); // Создание экземпляра SimpleDateFormat
        String dateString = sdf.format(new Date(dateInMillis)); // Форматирование текущей даты

        // Создание интента для перехода к Activity2 и передача отформатированной даты
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("date", dateString); // Добавление даты в интент
        startActivity(intent); // Запуск второй активности
    }
}
