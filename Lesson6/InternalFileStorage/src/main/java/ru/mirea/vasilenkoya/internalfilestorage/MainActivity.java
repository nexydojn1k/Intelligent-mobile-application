package ru.mirea.vasilenkoya.internalfilestorage;
//Задание 3.1
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    // Переменная для хранения EditText
    EditText ed;

    // Имя файла, в который будем сохранять данные
    private String fileName = "mirea.txt";

    // Тэг для логирования
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Привязываем разметку activity_main
        setContentView(R.layout.activity_main);

        // Связываем EditText с элементом на экране
        ed = findViewById(R.id.editText);

        // Загружаем текст из файла и устанавливаем его в EditText
        // Чтение данных происходит синхронно, т.к. файл может быть небольшим
        String textFromFile = getTextFromFile();
        if (textFromFile != null) {
            ed.setText(textFromFile);  // Если данные успешно считаны, устанавливаем их в EditText
        }
    }

    // Метод для чтения текста из файла
    private String getTextFromFile() {
        FileInputStream fin = null;  // Объявляем поток для чтения данных из файла
        try {
            // Открываем файл для чтения
            fin = openFileInput(fileName);

            // Считываем данные в байтовый массив
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);

            // Преобразуем байты в строку
            String text = new String(bytes);

            // Логируем прочитанный текст
            Log.d(LOG_TAG, text);

            // Возвращаем текст
            return text;
        } catch (IOException ex) {
            // Если произошла ошибка, показываем её в виде тоста
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                // Закрываем поток, если он был открыт
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                // Обрабатываем возможные ошибки при закрытии потока
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return null;  // Возвращаем null, если чтение из файла не удалось
    }

    // Метод для сохранения данных в файл
    public void Save(View view) {
        // Получаем текст из EditText
        String string = ed.getText().toString();
        FileOutputStream outputStream;  // Объявляем поток для записи в файл
        try {
            // Открываем файл для записи в приватном режиме (данные будут перезаписаны)
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);

            // Записываем строку в файл в виде байтов
            outputStream.write(string.getBytes());

            // Закрываем поток после записи
            outputStream.close();

            // Уведомляем пользователя, что данные сохранены
            Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // Если произошла ошибка, выводим её в консоль и уведомляем пользователя
            e.printStackTrace();
            Toast.makeText(this, "Error saving data", Toast.LENGTH_SHORT).show();
        }
    }
}
