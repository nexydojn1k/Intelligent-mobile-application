package ru.mirea.vasilenkoya.mireaproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class BackgroundTaskFragment extends Fragment {

    private static final int REQUEST_PERMISSION_CODE = 100; // Код запроса разрешений

    public BackgroundTaskFragment() {
        // Обязательный пустой конструктор
    }

    public static BackgroundTaskFragment newInstance(String param1, String param2) {
        // Создание нового экземпляра фрагмента с передачей аргументов (пока не используется)
        BackgroundTaskFragment fragment = new BackgroundTaskFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Инфляция макета фрагмента
        return inflater.inflate(R.layout.fragment_background_task, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Запрос разрешений перед запуском фоновой задачи
        checkAndRequestPermissions();
    }

    // Проверка и запрос разрешений
    private void checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Разрешения не предоставлены, запрашиваем
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
        } else {
            // Разрешения уже предоставлены, можно запускать задачу
            startBackgroundTask();
        }
    }

    // Обработка результата запроса разрешений
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Разрешение предоставлено
                startBackgroundTask();
            } else {
                // Разрешение не предоставлено, выводим сообщение пользователю
                Toast.makeText(requireContext(), "Permission denied. Cannot perform background task.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Запуск фоновой задачи через WorkManager
    private void startBackgroundTask() {
        // Создание запроса на выполнение фоновой работы
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        // Запуск фоновой работы с помощью WorkManager
        WorkManager.getInstance(requireContext()).enqueue(workRequest);
    }
}
