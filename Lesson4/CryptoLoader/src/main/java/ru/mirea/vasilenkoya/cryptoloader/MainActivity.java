package ru.mirea.vasilenkoya.cryptoloader;

// Импорт необходимых библиотек
import ru.mirea.vasilenkoya.cryptoloader.databinding.ActivityMainBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.security.InvalidParameterException;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String> {

    ActivityMainBinding binding; // Переменная для привязки
    public final String TAG = this.getClass().getSimpleName(); // Для логирования
    private final int LoaderID = 5678; // Идентификатор загрузчика

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); // Инициализация привязки
        setContentView(binding.getRoot()); // Установка корневого представления
    }

    public void OnClickButton(View view) throws Exception {
        Log.d(TAG, "OnClickButton: click"); // Логирование нажатия кнопки

        // Генерация ключа AES
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // Инициализация генератора ключа с длиной 128 бит
        SecretKey secretKey = keyGenerator.generateKey(); // Генерация секретного ключа

        // Инициализация шифра
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey); // Установка режима шифрования

        String secretMessage = binding.editText.getText().toString(); // Получение сообщения из EditText
        byte[] encryptedMessage = cipher.doFinal(secretMessage.getBytes()); // Шифрование сообщения
        String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessage); // Кодирование шифрованного сообщения в Base64

        // Подготовка данных для загрузчика
        Bundle bundle = new Bundle();
        bundle.putString(MyLoader.ARG_WORD, encodedMessage); // Шифрованное сообщение
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded()); // Кодирование ключа в Base64
        bundle.putString(MyLoader.ARG_KEY, encodedKey); // Добавление ключа в bundle

        // Инициализация загрузчика
        LoaderManager.getInstance(this).initLoader(LoaderID, bundle, this);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        if (i == LoaderID) {
            Toast.makeText(this, "onCreateLoader:" + i, Toast.LENGTH_SHORT).show(); // Уведомление о создании загрузчика
            try {
                return new MyLoader(this, bundle); // Создание экземпляра MyLoader
            } catch (Exception e) {
                throw new RuntimeException(e); // Обработка исключения
            }
        }
        throw new InvalidParameterException("Invalid loader id"); // Ошибка, если ID загрузчика некорректен
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        if (loader.getId() == LoaderID) {
            Log.d(TAG, "onLoadFinished: " + s); // Логирование результата загрузки
            Toast.makeText(this, "onLoadFinished. Message: : " + s, Toast.LENGTH_SHORT).show(); // Уведомление о завершении загрузки
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.d(TAG, "onLoaderReset"); // Логирование сброса загрузчика
    }
}
