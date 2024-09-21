package ru.mirea.vasilenkoya.serviceapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class PlayerService extends Service {
    private MediaPlayer mediaPlayer; // Объект для воспроизведения музыки
    public static final String CHANNEL_ID = "ForegroundServiceChannel"; // Идентификатор канала уведомлений

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented"); // Не поддерживается в этом сервисе
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start(); // Начинаем воспроизведение музыки
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopForeground(true); // Останавливаем уведомление, когда музыка завершена
            }
        });
        return super.onStartCommand(intent, flags, startId); // Возвращаем статус службы
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Создание канала уведомлений для отображения во время воспроизведения
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentText("Playing...") // Текст уведомления
                .setSmallIcon(R.mipmap.ic_launcher) // Иконка уведомления
                .setPriority(NotificationCompat.PRIORITY_HIGH) // Высокий приоритет уведомления
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Clouds - Pastel Ghost")) // Расширенный текст уведомления
                .setContentTitle("MusicPlayer"); // Заголовок уведомления

        // Создание канала уведомлений
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Student Василенко Ю.А.", importance);
        channel.setDescription("MIREA CHANNEL"); // Описание канала
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.createNotificationChannel(channel); // Регистрация канала
        startForeground(1, builder.build()); // Запуск службы в фоновом режиме с уведомлением

        // Инициализация MediaPlayer с музыкой
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.setLooping(false); // Устанавливаем зацикливание
    }

    @Override
    public void onDestroy() {
        stopForeground(true); // Остановка фонового уведомления
        mediaPlayer.stop(); // Остановка воспроизведения музыки
    }
}
