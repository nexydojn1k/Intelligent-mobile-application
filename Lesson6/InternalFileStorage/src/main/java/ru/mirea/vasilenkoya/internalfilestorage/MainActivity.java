package ru.mirea.vasilenkoya.internalfilestorage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.FileOutputStream;
import ru.mirea.vasilenkoya.internalfilestorage.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private String fileName = "mirea.txt"; // Имя файла для сохранения
    private Button saveBtn;
    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Включаем поддержку edge-to-edge
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        saveBtn = binding.saveBtn; // Кнопка для сохранения
        inputText = binding.inputText; // Поле ввода текста

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = inputText.getText().toString(); // Получаем текст из EditText
                FileOutputStream outputStream;
                try {
                    // Открываем файл для записи
                    outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                    outputStream.write(string.getBytes()); // Записываем строку в файл
                    outputStream.close(); // Закрываем поток
                } catch (Exception e) {
                    e.printStackTrace(); // Выводим ошибку в консоль
                }
            }
        });
    }
}
