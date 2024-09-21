package ru.mirea.vasilenkoya.securesharedpreferences;
//2.2 Задание

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.widget.TextView;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {
    // Поле для хранения главного ключа (алиаса) шифрования
    String mainKeyAlias;

    // Поле для хранения защищённого объекта SharedPreferences
    private SharedPreferences secureSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Вызов метода суперкласса для создания активности
        super.onCreate(savedInstanceState);

        // Установка макета активности из файла XML
        setContentView(R.layout.activity_main);

        // Генерация или получение главного ключа шифрования
        try {
            // Используем стандартные параметры для генерации ключа AES с режимом GCM
            KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;

            // Получаем или создаём новый ключ с заданными параметрами
            mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
        } catch (GeneralSecurityException | IOException e) {
            // Обрабатываем возможные ошибки безопасности или ввода/вывода
            throw new RuntimeException(e);  // Выбрасываем исключение в случае ошибки
        }

        // Создание защищённого SharedPreferences
        try {
            // Создаём объект EncryptedSharedPreferences с использованием сгенерированного ключа
            secureSharedPreferences = EncryptedSharedPreferences.create(
                    "secret_shared_prefs",  // Имя файла для хранения данных
                    mainKeyAlias,  // Алиас (имя) главного ключа шифрования
                    getBaseContext(),  // Контекст приложения
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,  // Схема шифрования ключей (AES с SIV)
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM  // Схема шифрования значений (AES с GCM)
            );

            // Сохраняем зашифрованную строку в SharedPreferences
            secureSharedPreferences.edit().putString("secure", "Генри Кавил").apply();
        } catch (GeneralSecurityException | IOException e) {
            // Обрабатываем возможные ошибки безопасности или ввода/вывода
            throw new RuntimeException(e);  // Выбрасываем исключение в случае ошибки
        }

        // Получаем ссылку на TextView из разметки
        TextView tv = findViewById(R.id.textView);

        // Устанавливаем текст в TextView, считав зашифрованное значение из SharedPreferences
        // Если значение не найдено, по умолчанию будет "Not found"
        tv.setText(secureSharedPreferences.getString("secure", "Not found"));
    }
}