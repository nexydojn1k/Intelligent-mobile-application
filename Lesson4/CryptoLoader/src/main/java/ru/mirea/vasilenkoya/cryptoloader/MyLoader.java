package ru.mirea.vasilenkoya.cryptoloader;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyLoader extends AsyncTaskLoader<String> {
    private String message; // Расшифрованное сообщение
    public static final String ARG_WORD = "data"; // Ключ для передачи зашифрованного сообщения
    public static final String ARG_KEY = "key"; // Ключ для передачи шифровального ключа

    // Конструктор загрузчика
    public MyLoader(@NonNull Context context, Bundle args) throws Exception {
        super(context);
        if(args != null) {
            String encodedMessage = args.getString(ARG_WORD); // Получение зашифрованного сообщения
            String encodedKey = args.getString(ARG_KEY); // Получение закодированного ключа

            // Инициализация шифра
            Cipher cipher = Cipher.getInstance("AES");
            byte[] decodedKey = Base64.getDecoder().decode(encodedKey); // Декодирование ключа
            SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); // Создание секретного ключа
            cipher.init(Cipher.DECRYPT_MODE, originalKey); // Установка режима расшифровки

            // Расшифровка сообщения
            byte[] decryptedMessage = cipher.doFinal(Base64.getDecoder().decode(encodedMessage));
            message = new String(decryptedMessage); // Преобразование байтового массива в строку
        }
    }

    @Override
    protected void onStartLoading(){
        super.onStartLoading();
        forceLoad(); // Принудительная загрузка данных
    }

    @Override
    public String loadInBackground() {
        SystemClock.sleep(5000); // Имитируем длительную операцию (например, расшифровку)
        return message; // Возвращаем расшифрованное сообщение
    }
}
