package ru.mirea.vasilenkoya.favoritebook;

import static android.content.Intent.EXTRA_TEXT;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FavoriteBook extends AppCompatActivity {
    private ActivityResultLauncher<Intent> activityResultLauncher;      // Лаунчер для получения результата из другой активности
    static final String KEY = "book_name";                              // Ключ для передачи названия книги
    static final String USER_MESSAGE = "MESSAGE";                      // Ключ для сообщения пользователя
    private TextView textViewUserBook;                                 // TextView для отображения названия книги
    private Button btnSend;                                            // Кнопка для отправки информации о книге

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);                     // Включение режима edge-to-edge
        setContentView(R.layout.activity_favorite_book);                // Установка содержимого активности из XML

        // Установка слушателя для применения отступов окна
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());          // Получение отступов системных панелей
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);  // Установка отступов
            return insets;                                                                       // Возврат инсет-объекта
        });

        textViewUserBook = findViewById(R.id.textViewBook); // Инициализация TextView для отображения названия книги

        // Создание коллбэка для обработки результата активности
        ActivityResultCallback<ActivityResult> callback = new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {     // Проверка, успешно ли завершилась активность
                    Intent data = result.getData();                     // Получение данных из результата
                    String userBook = data.getStringExtra(EXTRA_TEXT);  // Извлечение названия книги из данных
                    textViewUserBook.setText("Название Вашей любимой книги: " + userBook); // Установка текста в TextView
                }
            }
        };
        // Регистрация лаунчера для получения результата из другой активности
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                callback);
    }

    // Метод для получения информации о книге
    public void getInfoAboutBook(View view) {
        Intent intent = new Intent(this, ShareActivity.class); // Создание интента для перехода на ShareActivity
        intent.putExtra(KEY, "1984");       // Передача названия книги
        activityResultLauncher.launch(intent);   // Запуск активности с лаунчером
    }
}
