package ru.mirea.vasilenkoya.camera;

// Импорт необходимых библиотек и классов
import ru.mirea.vasilenkoya.camera.databinding.ActivityMainBinding;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSION = 100; // Код запроса разрешений
    private static final int CAMERA_REQUEST = 0; // Код запроса камеры
    private boolean isWork = false; // Флаг для проверки состояния приложения
    private Uri imageUri; // URI для сохраненного изображения
    private ActivityMainBinding binding; // Привязка для доступа к элементам интерфейса

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); // Инициализация привязки
        setContentView(binding.getRoot()); // Установка основного представления

        // Проверка разрешений на камеру и запись в хранилище
        int cameraPermissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int storagePermissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (cameraPermissionStatus == PackageManager.PERMISSION_GRANTED && storagePermissionStatus == PackageManager.PERMISSION_GRANTED) {
            isWork = true; // Разрешения предоставлены
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION); // Запрос разрешений
        }

        // Обработчик результата запуска камеры
        ActivityResultCallback<ActivityResult> callback = new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // Устанавливаем полученное изображение в ImageView
                    binding.imageView.setImageURI(imageUri);
                }
            }
        };

        // Регистрация обработчика результата
        ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), callback);

        // Установка обработчика клика для ImageView
        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // Создание Intent для захвата изображения
                if (isWork) {
                    try {
                        File photoFile = createImageFile(); // Создание файла для изображения
                        String authorities = getApplicationContext().getPackageName() + ".fileprovider"; // Получение имени провайдера файлов
                        imageUri = FileProvider.getUriForFile(MainActivity.this, authorities, photoFile); // Получение URI для файла
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); // Передача URI в Intent
                        cameraActivityResultLauncher.launch(cameraIntent); // Запуск Intent для камеры
                    } catch (IOException e) {
                        e.printStackTrace(); // Обработка исключений
                    }
                }
            }
        });
    }

    // Метод для создания файла изображения
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date()); // Форматирование метки времени
        String imageFileName = "IMAGE_" + timeStamp + "_"; // Имя файла
        File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES); // Директория для хранения изображений
        return File.createTempFile(imageFileName, ".jpg", storageDirectory); // Создание временного файла
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            isWork = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED; // Проверка статуса разрешений
        }
    }
}
