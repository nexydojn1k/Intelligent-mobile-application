package ru.mirea.vasilenkoya.simplefragmentapp;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge; // Импорт для поддержки полноэкранного режима
import androidx.appcompat.app.AppCompatActivity; // Импорт для использования AppCompatActivity
import androidx.core.graphics.Insets; // Импорт для работы с Insets
import androidx.core.view.ViewCompat; // Импорт для работы с ViewCompat
import androidx.core.view.WindowInsetsCompat; // Импорт для работы с WindowInsets
import androidx.fragment.app.Fragment; // Импорт для работы с фрагментами
import androidx.fragment.app.FragmentManager; // Импорт для управления фрагментами

public class SimpleFragmentApp extends AppCompatActivity {
    Fragment fragment1, fragment2; // Объявление переменных для фрагментов
    FragmentManager fragmentManager; // Объявление переменной для управления фрагментами

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_fragment_app); // Установка содержимого активности
        fragment1 = new FirstFragment(); // Инициализация первого фрагмента
        fragment2 = new SecondFragment(); // Инициализация второго фрагмента
    }

    // Метод, который вызывается при нажатии на кнопку
    public void onClick(View view) {
        fragmentManager = getSupportFragmentManager(); // Получение экземпляра FragmentManager
        if (view.getId() == R.id.btnFirstFragment) { // Проверка, была ли нажата кнопка первого фрагмента
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment1).commit(); // Замена текущего фрагмента на первый
        } else if (view.getId() == R.id.btnSecondFragment) { // Проверка, была ли нажата кнопка второго фрагмента
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment2).commit(); // Замена текущего фрагмента на второй
        }
    }
}
