package ru.mirea.vasilenkoya.mireaproject;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {

    // Конструктор класса Worker
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams); // Инициализация базового класса
    }

    @NonNull
    @Override
    public Result doWork() {
        // Код фоновой задачи
        // Здесь можно выполнять длительные операции, такие как загрузка данных или обработка файлов

        // Возвращаем результат выполнения задачи
        return Result.success(); // Успешное завершение задачи
    }
}
