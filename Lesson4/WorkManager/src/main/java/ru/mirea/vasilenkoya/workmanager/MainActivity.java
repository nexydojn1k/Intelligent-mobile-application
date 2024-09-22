package ru.mirea.vasilenkoya.workmanager;

import ru.mirea.vasilenkoya.workmanager.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Установка ограничений для фоновой задачи
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)          // Задача требует неограниченного доступа к сети
                .setRequiresCharging(true)                              // Задача выполняется только при зарядке устройства
                .build();

        // Создание запроса на выполнение фоновой задачи
        WorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class) // Указание класса, который будет выполнять задачу
                .setConstraints(constraints)                                               // Применение ограничений
                .build();

        // Добавление запроса на выполнение задачи в очередь
        WorkManager.getInstance(this).enqueue(uploadWorkRequest);
    }
}
