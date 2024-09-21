package ru.mirea.vasilenkoya.sharer;

import android.os.Bundle;

import androidx.activity.EdgeToEdge; // Импорт для включения режима edge-to-edge
import androidx.appcompat.app.AppCompatActivity; // Импорт для базовой активности
import androidx.core.graphics.Insets; // Импорт для работы с Insets
import androidx.core.view.ViewCompat; // Импорт для поддержки view
import androidx.core.view.WindowInsetsCompat; // Импорт для работы с WindowInsets

public class SharerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Включение режима edge-to-edge для активити
        setContentView(R.layout.activity_sharer); // Установка содержимого активности из XML

        // Установка слушателя для применения отступов окна
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()); // Получение отступов для системных панелей
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom); // Установка отступов для вьюхи
            return insets; // Возврат инсет-объекта
        });

        // Эта строка повторно вызывает onCreate, что неправильно
        super.onCreate(savedInstanceState); // Неправильный вызов
        setContentView(R.layout.activity_sharer); // Дублирование установки содержимого
    }
}
