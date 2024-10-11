package ru.mirea.vasilenkoya.intentfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);             // Устанавливаем разметку для активности
    }

    // Метод для открытия URI в браузере
    public void OnClickOpenURI(View view) {
        Uri address = Uri.parse("https://www.mirea.ru/");         // Создаем URI с адресом сайта
        Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address); // Создаем интент для открытия URL
        startActivity(openLinkIntent);                                   // Запускаем активность, связанную с интентом
    }

    // Метод для отправки текста через приложения
    public void OnClickSend(View view) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);                        // Создаем интент для отправки данных
        shareIntent.setType("text/plain");                                          // Устанавливаем тип содержимого
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "MIREA");                  // Добавляем заголовок
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Василенко Юлия Алексеевна"); // Добавляем текст
        startActivity(Intent.createChooser(shareIntent, "МОИ ФИО"));            // Запускаем выбор приложения для отправки
    }
}
