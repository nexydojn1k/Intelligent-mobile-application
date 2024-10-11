package ru.mirea.vasilenkoya.dialog;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     // Вызов метода родительского класса для инициализации активности
        setContentView(R.layout.activity_main); // Установка разметки для активности
    }

    // Метод для отображения диалогового окна
    public void OnClickShowDialog(View view) {
        MyDialogFragment dialogFragment = new MyDialogFragment();       // Создание экземпляра пользовательского диалогового фрагмента
        dialogFragment.show(getSupportFragmentManager(), "mirea");  // Отображение диалогового фрагмента
    }

    // Метод для отображения диалогового окна выбора даты
    public void OnClickDate(View view) {
        MyDateDialogFragment dateDialogFragment = new MyDateDialogFragment();   // Создание экземпляра диалогового фрагмента для выбора даты
        dateDialogFragment.show(getSupportFragmentManager(), "mirea");      // Отображение диалогового фрагмента
    }

    // Метод для отображения диалогового окна с индикатором прогресса
    public void OnClickProgress(View view) {
        MyProgressDialogFragment progressDialogFragment = new MyProgressDialogFragment(); // Создание экземпляра диалогового фрагмента с прогрессом
        progressDialogFragment.show(getSupportFragmentManager(), "mirea");            // Отображение диалогового фрагмента
    }

    // Метод для отображения диалогового окна выбора времени
    public void OnClickTime(View view) {
        MyTimeDialogFragment timeDialogFragment = new MyTimeDialogFragment();   // Создание экземпляра диалогового фрагмента для выбора времени
        timeDialogFragment.show(getSupportFragmentManager(), "mirea");      // Отображение диалогового фрагмента
    }
}
