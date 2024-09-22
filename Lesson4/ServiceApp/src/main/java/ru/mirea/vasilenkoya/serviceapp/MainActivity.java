package ru.mirea.vasilenkoya.serviceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ru.mirea.vasilenkoya.serviceapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;        // Binding для доступа к UI элементам
    private int PermissionCode = 200;   // Код запроса разрешений

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Проверка наличия разрешения на отправку уведомлений
        if (ContextCompat.checkSelfPermission(this, "POST_NOTIFICATIONS")
                == PackageManager.PERMISSION_GRANTED) {
            Log.d(MainActivity.class.getSimpleName(), "Разрешения получены");
        } else {
            Log.d(MainActivity.class.getSimpleName(), "Нет разрешений!");
            // Запрос необходимых разрешений
            ActivityCompat.requestPermissions(this, new String[]{"POST_NOTIFICATIONS",
                    "FOREGROUND_SERVICE"}, PermissionCode);
        }

        // Установка обработчика нажатия для кнопки "Play"
        binding.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(MainActivity.this, PlayerService.class);
                // Запуск службы в фоновом режиме
                ContextCompat.startForegroundService(MainActivity.this, serviceIntent);
            }
        });

        // Установка обработчика нажатия для кнопки "Pause"
        binding.pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Остановка службы
                stopService(new Intent(MainActivity.this, PlayerService.class));
            }
        });
    }
}
