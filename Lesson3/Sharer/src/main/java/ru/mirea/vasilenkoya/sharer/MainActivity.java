package ru.mirea.vasilenkoya.sharer;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Включение режима edge-to-edge
        setContentView(R.layout.activity_main); // Установка содержимого активности из XML

        // Установка слушателя для применения отступов окна
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()); // Получение отступов системных панелей
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom); // Установка отступов
            return insets; // Возврат инсет-объекта
        });

        // Создание интента для отправки текста
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*"); // Установка типа данных, которые будут отправлены
        intent.putExtra(Intent.EXTRA_TEXT, "MIREA"); // Добавление текста в интент
        startActivity(Intent.createChooser(intent, "Выбор за вами!")); // Запуск выбора приложения для отправки текста
    }
}
