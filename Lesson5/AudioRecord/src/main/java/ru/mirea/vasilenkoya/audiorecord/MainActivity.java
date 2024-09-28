package ru.mirea.vasilenkoya.audiorecord;

// Импорт необходимых библиотек и классов
import ru.mirea.vasilenkoya.audiorecord.databinding.ActivityMainBinding;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;                            // Привязка для доступа к элементам интерфейса
    private static final int REQUEST_CODE_PERMISSION = 200;         // Код запроса разрешений
    private final String TAG = MainActivity.class.getSimpleName();  // Тег для логирования
    private boolean isWork;                                         // Флаг для проверки состояния приложения
    private String recordFilePath = null;                           // Путь к файлу записи
    private Button recordButton = null;                             // Кнопка записи
    private Button playButton = null;                               // Кнопка воспроизведения
    private MediaRecorder recorder = null;                          // Объект для записи аудио
    private MediaPlayer player = null;                              // Объект для воспроизведения аудио
    boolean isStartRecording = true;                                // Флаг начала записи
    boolean isStartPlaying = true;                                  // Флаг начала воспроизведения

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Инициализация привязки для доступа к элементам интерфейса
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Инициализация кнопок записи и воспроизведения
        recordButton = binding.recordButton;
        playButton = binding.playButton;
        playButton.setEnabled(false);                                                   // Деактивируем кнопку воспроизведения по умолчанию
        recordFilePath = (new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC),
                "/audiorecordtest.3gp")).getAbsolutePath();                         // Устанавливаем путь для сохранения файла

        // Проверка разрешений на запись аудио и запись на внешнюю память
        int audioRecordPermissionStatus = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO);
        int storagePermissionStatus = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (audioRecordPermissionStatus == PackageManager.PERMISSION_GRANTED &&
                storagePermissionStatus == PackageManager.PERMISSION_GRANTED) {
            isWork = true;                                                                      // Разрешения предоставлены
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION); // Запрос разрешений
        }

        // Устанавливаем обработчик кликов для кнопки записи
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStartRecording) {
                    recordButton.setText("Stop recording");                 // Изменяем текст кнопки
                    playButton.setEnabled(false);                           // Деактивируем кнопку воспроизведения
                    startRecording();                                       // Начинаем запись
                } else {
                    recordButton.setText("Start recording");                // Изменяем текст кнопки
                    playButton.setEnabled(true);                            // Активируем кнопку воспроизведения
                    stopRecording();                                        // Останавливаем запись
                }
                isStartRecording = !isStartRecording;                       // Переключаем состояние
            }
        });

        // Устанавливаем обработчик кликов для кнопки воспроизведения
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStartPlaying) {
                    playButton.setText("Stop playing");                 // Изменяем текст кнопки
                    recordButton.setEnabled(false);                     // Деактивируем кнопку записи
                    startPlaying();                                     // Начинаем воспроизведение
                } else {
                    playButton.setText("Start playing");                // Изменяем текст кнопки
                    recordButton.setEnabled(true);                      // Активируем кнопку записи
                    stopPlaying();                                      // Останавливаем воспроизведение
                }
                isStartPlaying = !isStartPlaying;                       // Переключаем состояние
            }
        });
    }

    private void startRecording() {
        recorder = new MediaRecorder();                                 // Создаем новый объект MediaRecorder
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);         // Устанавливаем источник аудио
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); // Устанавливаем формат выходного файла
        recorder.setOutputFile(recordFilePath);                         // Устанавливаем путь к файлу
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);    // Устанавливаем кодировщик аудио
        try {
            recorder.prepare();                 // Подготавливаем MediaRecorder
        } catch (IOException e) {
            Log.e(TAG, "prepare() failed"); // Логируем ошибку
        }
        recorder.start();                           // Начинаем запись
    }

    private void stopRecording() {
        recorder.stop();                            // Останавливаем запись
        recorder.release();                         // Освобождаем ресурсы
        recorder = null;                            // Устанавливаем объект в null
    }

    private void startPlaying() {
        player = new MediaPlayer();                 // Создаем новый объект MediaPlayer
        try {
            player.setDataSource(recordFilePath);   // Устанавливаем источник данных
            player.prepare();                       // Подготавливаем MediaPlayer
            player.start();                         // Начинаем воспроизведение
        } catch (IOException e) {
            Log.d(TAG, "prepare() failed");     // Логируем ошибку
        }
    }

    private void stopPlaying() {
        player.release();                               // Освобождаем ресурсы
        player = null;                                  // Устанавливаем объект в null
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION:
                isWork = grantResults[0] == PackageManager.PERMISSION_GRANTED; // Проверка статуса разрешений
                break;
        }
        if (!isWork) finish(); // Закрываем приложение, если разрешения не предоставлены
    }
}
