package ru.mirea.vasilenkoya.lesson6;
//2.1 Задание
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Метод, который вызывается при создании активности
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Создание объекта SharedPreferences для хранения настроек
        // "mirea_settings" — это имя файла, в котором будут храниться данные
        // Context.MODE_PRIVATE — данные будут доступны только этому приложению
        SharedPreferences sharedPref = getSharedPreferences("mirea_settings", Context.MODE_PRIVATE);

        // Создание редактора SharedPreferences для записи данных
        SharedPreferences.Editor editor = sharedPref.edit();

        // Запись строки в SharedPreferences с ключом "GROUP"
        editor.putString("GROUP", "БСБО-06-22");

        // Запись целочисленного значения с ключом "NUMBER"
        editor.putInt("NUMBER", 3);

        // Запись булевого значения (true/false) с ключом "IS_EXCELLENT"
        editor.putBoolean("IS_EXCELLENT", true);

        // Применение изменений (сохранение данных)
        editor.apply();
    }
}