package ru.mirea.vasilenkoya.notebook;

// Импортируем необходимые библиотеки и классы
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Объявляем элементы интерфейса
    EditText filename;  // Поле для ввода имени файла
    EditText quote;     // Поле для ввода текста
    Boolean isOK;      // Переменная для проверки прав доступа
    private static final int REQUEST_CODE_PERMISSION = 100; // Код запроса разрешения

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Устанавливаем макет активности

        // Инициализируем поля ввода
        filename = findViewById(R.id.editText);
        quote = findViewById(R.id.editText2);

        // Проверяем статус разрешения на запись во внешнее хранилище
        int storagePermissionStatus = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (storagePermissionStatus == PackageManager.PERMISSION_GRANTED) {
            isOK = true; // Если разрешение предоставлено
        } else {
            // Запрашиваем необходимые разрешения
            ActivityCompat.requestPermissions(this, new String[]{
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
        }

        // Проверяем состояние внешнего хранилища
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            Log.w("fewf", "ok"); // Хранилище доступно для записи
        } else {
            Log.w("fewf", "no"); // Хранилище недоступно
        }

        // Дополнительная проверка состояния хранилища
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            Log.w("fewf", "ok");
        }
    }

    // Метод для сохранения текста в файл
    public void Save(View view) {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        // Создаем файл с заданным именем
        File file = new File(path, filename.getText().toString());
        try {
            // Открываем поток для записи в файл
            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile());
            OutputStreamWriter output = new OutputStreamWriter(fileOutputStream);
            output.write(quote.getText().toString()); // Записываем текст
            output.close(); // Закрываем поток
        } catch (IOException e) {
            Log.w("ExternalStorage", "Error writing " + file, e); // Логируем ошибку записи
        }
    }

    // Метод для загрузки текста из файла
    public void Load(View view) {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        // Создаем файл с заданным именем
        File file = new File(path, filename.getText().toString());
        try {
            // Открываем поток для чтения из файла
            FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);

            List<String> lines = new ArrayList<>(); // Список для хранения строк
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine(); // Читаем первую строку
            while (line != null) { // Пока есть строки
                lines.add(line); // Добавляем строку в список
                line = reader.readLine(); // Читаем следующую строку
            }

            quote.setText(""); // Очищаем поле ввода
            for (String string : lines) { // Добавляем строки в поле ввода
                quote.append(string + "\n");
            }
            Log.w("ExternalStorage", String.format("Read from file %s successful", lines.toString())); // Логируем успешное чтение
        } catch (Exception e) {
            Log.w("ExternalStorage", String.format("Read from file %s failed", e.getMessage())); // Логируем ошибку чтения
        }
    }
}
