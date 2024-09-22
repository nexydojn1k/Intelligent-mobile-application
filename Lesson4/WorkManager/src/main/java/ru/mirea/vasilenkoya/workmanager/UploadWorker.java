package ru.mirea.vasilenkoya.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

public class UploadWorker extends Worker {
    static final String TAG = "UploadWorker";

    // Конструктор, принимающий контекст и параметры работы
    public UploadWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params){
        super(context, params);
    }

    // Метод, выполняющий основную работу
    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: start");                    // Логирование начала выполнения работы
        try {
            TimeUnit.SECONDS.sleep(10);               // Имитация длительной работы (задержка на 10 секунд)
        } catch (InterruptedException e) {
            e.printStackTrace();                             // Логирование ошибок, если работа прервана
            return Result.failure();                         // Возврат неудачи при прерывании
        }

        Log.d(TAG, "doWork: end");                      // Логирование окончания работы
        return Result.success();                             // Возврат успешного результата
    }
}
