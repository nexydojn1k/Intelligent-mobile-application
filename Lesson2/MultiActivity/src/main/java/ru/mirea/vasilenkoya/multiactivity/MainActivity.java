package ru.mirea.vasilenkoya.multiactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName(); // Логическое имя класса для использования в логах
    private EditText Info; // Поле для ввода информации

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Устанавливаем разметку для активности
    }

    // Метод для обработки нажатия кнопки, открывающей новую активность
    public void OnClickNewActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class); // Создаем интент для перехода ко второй активности
        Info = findViewById(R.id.Info); // Находим элемент EditText по идентификатору
        intent.putExtra("key", Info.getText().toString()); // Добавляем текст из EditText в интент
        startActivity(intent); // Запускаем новую активность
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart"); // Логируем вызов метода onStart
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState"); // Логируем восстановление состояния активности
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.i(TAG, "onPostCreate"); // Логируем завершение создания активности
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume"); // Логируем переход в состояние "возобновлено"
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(TAG, "onPostResume"); // Логируем завершение возобновления активности
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause"); // Логируем переход в состояние "приостановлено"
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG, "onAttachedToWindow"); // Логируем присоединение активности к окну
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i(TAG, "onSaveInstanceState"); // Логируем сохранение состояния активности
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop"); // Логируем остановку активности
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart"); // Логируем перезапуск активности
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy"); // Логируем уничтожение активности
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG, "onDetachedFromWindow"); // Логируем отсоединение активности от окна
    }
}
