package ru.mirea.vasilenkoya.favoritebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ShareActivity extends AppCompatActivity {
    private TextView textView;              // TextView для отображения информации о любимой книге
    private EditText userBookName;          // EditText для ввода названия книги пользователем

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);                // Включение режима edge-to-edge
        setContentView(R.layout.activity_share);                   // Установка содержимого активности из XML

        // Установка слушателя для применения отступов окна
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()); // Получение отступов системных панелей
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom); // Установка отступов для вьюхи
            return insets; // Возврат инсет-объекта
        });

        // Получение интента и извлечение названия книги
        Intent intent = getIntent();
        String bookName = intent.getStringExtra(FavoriteBook.KEY);
        textView = findViewById(R.id.textView);                             // Инициализация TextView
        textView.setText("Любимая книга разработчика - " + bookName);       // Установка текста в TextView
    }

    // Метод для отправки названия книги
    public void sendBook(View view) {
        userBookName = findViewById(R.id.userBookName);                 // Инициализация EditText для ввода названия книги
        String bookName = userBookName.getText().toString();            // Получение текста из EditText

        // Создание интента для отправки текста
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, bookName);                   // Добавление названия книги в интент
        setResult(Activity.RESULT_OK, intent);                          // Установка результата активности
        finish();                                                       // Закрытие активности
    }
}
