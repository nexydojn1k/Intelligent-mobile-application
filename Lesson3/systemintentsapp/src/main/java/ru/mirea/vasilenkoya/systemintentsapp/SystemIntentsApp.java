package ru.mirea.vasilenkoya.systemintentsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class SystemIntentsApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_intents_app);       // Установка содержимого активности
    }

    // Метод для вызова телефона
    public void onClickCall() {
        Intent intent = new Intent(Intent.ACTION_DIAL);             // Создание интента для вызова
        intent.setData(Uri.parse("tel:89811112233"));       // Установка номера телефона
        startActivity(intent);                                      // Запуск активности
    }

    // Метод для открытия браузера
    public void onClickOpenBrowser() {
        Intent intent = new Intent(Intent.ACTION_VIEW);                     // Создание интента для просмотра
        intent.setData(Uri.parse("http://developer.android.com"));  // Установка URL
        startActivity(intent);                                              // Запуск активности
    }

    // Метод для открытия карт
    public void onClickOpenMaps() {
        Intent intent = new Intent(Intent.ACTION_VIEW);                     // Создание интента для просмотра
        intent.setData(Uri.parse("geo:55.749479,37.613944"));       // Установка географических координат
        startActivity(intent);                                              // Запуск активности
    }
}
