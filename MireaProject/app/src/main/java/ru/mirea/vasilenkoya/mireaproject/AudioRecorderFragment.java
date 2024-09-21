package ru.mirea.vasilenkoya.mireaproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.widget.Toast;
import java.io.IOException;

public class AudioRecorderFragment extends Fragment {

    // Константы для запроса разрешений
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static final String LOG_TAG = "AudioRecorder"; // Тег для логирования
    private MediaRecorder recorder = null; // Объект для записи аудио
    private MediaPlayer player = null; // Объект для воспроизведения аудио
    private String fileName = null; // Путь к файлу аудиозаписи
    private boolean permissionToRecordAccepted = false; // Флаг разрешения на запись
    private String[] permissions = {Manifest.permission.RECORD_AUDIO}; // Массив разрешений

    public AudioRecorderFragment() {
        // Обязательный пустой конструктор
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Инфляция макета фрагмента
        View view = inflater.inflate(R.layout.fragment_audio_recorder, container, false);

        // Инициализация кнопок
        Button recordButton = view.findViewById(R.id.record_button);
        Button playButton = view.findViewById(R.id.play_button);

        // Установка пути к файлу для сохранения аудиозаписи
        fileName = requireActivity().getExternalCacheDir().getAbsolutePath() + "/audiorecordtest.3gp";

        // Обработчик нажатия на кнопку записи
        recordButton.setOnClickListener(v -> {
            // Проверка разрешений на запись аудио
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                // Запрос разрешений, если они не предоставлены
                ActivityCompat.requestPermissions(requireActivity(),
                        permissions,
                        REQUEST_RECORD_AUDIO_PERMISSION);
            } else {
                // Начать запись, если разрешение предоставлено
                startRecording();
            }
        });

        // Обработчик нажатия на кнопку воспроизведения
        playButton.setOnClickListener(v -> startPlaying());

        return view; // Возвращаем вид фрагмента
    }

    private void startRecording() {
        // Инициализация MediaRecorder
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC); // Установка источника звука
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); // Формат файла
        recorder.setOutputFile(fileName); // Путь к файлу
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); // Кодек аудио

        try {
            recorder.prepare(); // Подготовка к записи
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed"); // Логирование ошибки
        }

        recorder.start(); // Начинаем запись
        Toast.makeText(requireContext(), "Recording started", Toast.LENGTH_SHORT).show(); // Уведомление о начале записи
    }

    private void stopRecording() {
        recorder.stop(); // Останавливаем запись
        recorder.release(); // Освобождаем ресурсы
        recorder = null; // Сбрасываем объект
        Toast.makeText(requireContext(), "Recording stopped", Toast.LENGTH_SHORT).show(); // Уведомление об остановке записи
    }

    private void startPlaying() {
        player = new MediaPlayer(); // Инициализация MediaPlayer
        try {
            player.setDataSource(fileName); // Установка источника аудиофайла
            player.prepare(); // Подготовка к воспроизведению
            player.start(); // Начинаем воспроизведение
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed"); // Логирование ошибки
        }
    }

    private void stopPlaying() {
        player.release(); // Освобождаем ресурсы
        player = null; // Сбрасываем объект
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // Обработка результатов запроса разрешений
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            permissionToRecordAccepted = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
            if (!permissionToRecordAccepted) {
                // Уведомление об отказе в разрешении
                Toast.makeText(requireContext(), "Permission to record audio denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop(); // Вызов метода родителя
        // Остановка записи и воспроизведения при остановке фрагмента
        if (recorder != null) {
            stopRecording();
        }
        if (player != null) {
            stopPlaying();
        }
    }
}
