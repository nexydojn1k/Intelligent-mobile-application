package ru.mirea.vasilenkoya.toastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Edit;                      // Объявляем переменную для EditText

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Устанавливаем разметку для активности
    }

    // Метод, вызываемый при нажатии кнопки
    public void OnClickToast(View view) {
        Edit = findViewById(R.id.editTextText);     // Инициализируем EditText с помощью его идентификатора
        int count = Edit.getText().length();        // Получаем количество символов в EditText
        // Создаем и показываем Toast-сообщение с количеством символов
        Toast toast = Toast.makeText(getApplicationContext(),
                "СТУДЕНТ №3 ГРУППА БСБО-06-22 Количество символов - " + Integer.toString(count),
                Toast.LENGTH_SHORT);
        toast.show();                               // Отображаем Toast-сообщение
    }
}
