package ru.mirea.vasilenkoya.looper;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.os.Handler;
import java.util.concurrent.TimeUnit;

public class MyLooper extends Thread {
    public Handler mHandler; // Handler для обработки сообщений в этом потоке
    private Handler mainHandler; // Handler для основного потока

    // Конструктор принимает Handler основного потока
    public MyLooper(Handler mainThreadHandler) {
        mainHandler = mainThreadHandler;
    }

    // Метод run, который выполняется при старте потока
    public void run() {
        Log.d("MyLooper", "run");
        Looper.prepare();                                                   // Подготовка Looper для этого потока
        mHandler = new Handler(Looper.myLooper()) {                         // Создание Handler с привязкой к Looper
            public void handleMessage(Message msg) {
                // Получение данных из сообщения
                String age = msg.getData().getString("AGE");
                String occupation = msg.getData().getString("OCCUPATION");
                Log.d("MyLooper get message: ", age);
                Log.d("MyLooper get message: ", occupation);

                // Создание нового сообщения для отправки обратно в основной поток
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("result", String.format("Age is: %s, occupation is: %s", age, occupation));
                message.setData(bundle);

                // Отправка сообщения обратно в очередь сообщений основного потока
                try {
                    // Задержка в зависимости от возраста (в секундах)
                    TimeUnit.SECONDS.sleep(Integer.parseInt(age));
                    mainHandler.sendMessage(message);                          // Отправка сообщения в основной поток
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Looper.loop();                                                         // Запуск цикла обработки сообщений
    }
}
