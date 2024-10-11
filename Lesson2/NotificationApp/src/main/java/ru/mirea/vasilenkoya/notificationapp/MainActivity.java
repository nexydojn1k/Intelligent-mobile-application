package ru.mirea.vasilenkoya.notificationapp;

import android.Manifest;
import static android.Manifest.permission.POST_NOTIFICATIONS;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "com.mirea.asd.notification.ANDROID"; // Идентификатор канала уведомлений
    private int PermissionCode = 200;                                               // Код для запроса разрешений

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)        // Указываем, что метод требует API уровня TIRAMISU или выше
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);             // Устанавливаем разметку для активности

        // Проверяем наличие разрешения на отправку уведомлений
        if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            Log.d(MainActivity.class.getSimpleName(), "Разрешения получены");       // Разрешение предоставлено
        } else {
            Log.d(MainActivity.class.getSimpleName(), "Нет разрешений!");       // Разрешение не предоставлено
            // Запрашиваем разрешение у пользователя
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, PermissionCode);
        }
    }

    // Метод, вызываемый при нажатии кнопки для отправки уведомления
    public void OnClickSendNotification(View view) {
        // Проверяем разрешение перед отправкой уведомления
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return; // Если разрешение не предоставлено, выходим из метода
        }

        // Создаем уведомление с использованием NotificationCompat
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentText("Congratulation!")                              // Текст уведомления
                .setPriority(NotificationCompat.PRIORITY_HIGH)                  // Устанавливаем высокий приоритет
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line...")) // Большой текст для уведомления
                .setContentTitle("Mirea");                                            // Заголовок уведомления

        // Настройка канала уведомлений
        int importance = NotificationManager.IMPORTANCE_DEFAULT;                    // Уровень важности канала
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Student FIO Notification", importance);
        channel.setDescription("MIREA Channel");                                    // Описание канала

        // Создаем и отображаем уведомление
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.createNotificationChannel(channel);                     // Создаем канал уведомлений
        notificationManager.notify(1, builder.build());                         // Отправляем уведомление
    }
}
