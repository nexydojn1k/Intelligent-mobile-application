package ru.mirea.vasilenkoya.systemintentsapp;

import android.content.Intent; // Импорт для работы с интентами
import android.net.Uri; // Импорт для работы с URI
import android.os.Bundle; // Импорт для работы с Bundle

import androidx.activity.EdgeToEdge; // Импорт для поддержки полноэкранного режима
import androidx.appcompat.app.AppCompatActivity; // Импорт для использования AppCompatActivity
import androidx.core.graphics.Insets; // Импорт для работы с Insets
import androidx.core.view.ViewCompat; // Импорт для работы с ViewCompat
import androidx.core.view.WindowInsetsCompat; // Импорт для работы с WindowInsets

public class SystemIntentsApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_intents_app); // Установка содержимого активности
    }

    // Метод для вызова телефона
    public void onClickCall() {
        Intent intent = new Intent(Intent.ACTION_DIAL); // Создание интента для вызова
        intent.setData(Uri.parse("tel:89811112233")); // Установка номера телефона
        startActivity(intent); // Запуск активности
    }

    // Метод для открытия браузера
    public void onClickOpenBrowser() {
        Intent intent = new Intent(Intent.ACTION_VIEW); // Создание интента для просмотра
        intent.setData(Uri.parse("http://developer.android.com")); // Установка URL
        startActivity(intent); // Запуск активности
    }

    // Метод для открытия карт
    public void onClickOpenMaps() {
        Intent intent = new Intent(Intent.ACTION_VIEW); // Создание интента для просмотра
        intent.setData(Uri.parse("geo:55.749479,37.613944")); // Установка географических координат
        startActivity(intent); // Запуск активности
    }
}
