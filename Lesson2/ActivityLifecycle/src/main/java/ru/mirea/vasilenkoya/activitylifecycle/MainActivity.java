package ru.mirea.vasilenkoya.activitylifecycle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName(); // Тег для логирования
    private EditText MethodName;                            // Поле для текстового ввода

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);         // Вызов метода родительского класса
        setContentView(R.layout.activity_main);     // Установка разметки для активности
    }

    @Override
    protected void onStart() {
        super.onStart();            // Вызов метода родительского класса
        Log.i(TAG, "onStart"); // Логирование вызова onStart
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState); // Вызов метода родительского класса
        Log.i(TAG, "onRestoreInstanceState");       // Логирование вызова onRestoreInstanceState
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);      // Вызов метода родительского класса
        Log.i(TAG, "onPostCreate");             // Логирование вызова onPostCreate
    }

    @Override
    protected void onResume() {
        super.onResume();               // Вызов метода родительского класса
        Log.i(TAG, "onResume");    // Логирование вызова onResume
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();               // Вызов метода родительского класса
        Log.i(TAG, "onPostResume");    // Логирование вызова onPostResume
    }

    @Override
    protected void onPause() {
        super.onPause();                // Вызов метода родительского класса
        Log.i(TAG, "onPause");     // Логирование вызова onPause
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();             // Вызов метода родительского класса
        Log.i(TAG, "onAttachedToWindow");  // Логирование вызова onAttachedToWindow
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState); // Вызов метода родительского класса
        Log.i(TAG, "onSaveInstanceState");                  // Логирование вызова onSaveInstanceState
    }

    @Override
    protected void onStop() {
        super.onStop();                 // Вызов метода родительского класса
        Log.i(TAG, "onStop");      // Логирование вызова onStop
    }

    @Override
    protected void onRestart() {
        super.onRestart();                  // Вызов метода родительского класса
        Log.i(TAG, "onRestart");       // Логирование вызова onRestart
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();                      // Вызов метода родительского класса
        Log.i(TAG, "onDestroy");           // Логирование вызова onDestroy
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();                   // Вызов метода родительского класса
        Log.i(TAG, "onDetachedFromWindow");        // Логирование вызова onDetachedFromWindow
    }

    public void GetInfo(View view) {
        MethodName = findViewById(R.id.editTextText); // Получение ссылки на EditText из разметки
        Log.i(TAG, MethodName.getText().toString());  // Логирование текста, введенного в EditText
    }
}