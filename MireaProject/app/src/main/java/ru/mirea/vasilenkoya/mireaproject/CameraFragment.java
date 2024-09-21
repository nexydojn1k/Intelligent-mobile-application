package ru.mirea.vasilenkoya.mireaproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import ru.mirea.vasilenkoya.mireaproject.R;

import static android.app.Activity.RESULT_OK;

public class CameraFragment extends Fragment {

    private static final int REQUEST_IMAGE_CAPTURE = 1; // Код запроса для захвата изображения
    private static final int REQUEST_CAMERA_PERMISSION = 100; // Код запроса разрешения на камеру
    private ImageView imageView; // Изображение для отображения захваченной фотографии

    public CameraFragment() {
        // Обязательный пустой конструктор
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Инфляция макета фрагмента
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        imageView = view.findViewById(R.id.imageView); // Получаем ссылку на ImageView для отображения изображения
        Button buttonTakePicture = view.findViewById(R.id.button_take_picture); // Получаем кнопку для захвата изображения

        buttonTakePicture.setOnClickListener(v -> {
            // Проверяем наличие разрешения на использование камеры
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                // Запрос разрешения на использование камеры
                ActivityCompat.requestPermissions(requireActivity(),
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_CAMERA_PERMISSION);
            } else {
                // Разрешение уже предоставлено, открываем камеру
                openCamera();
            }
        });

        return view;
    }

    private void openCamera() {
        // Создаем Intent для захвата изображения с помощью камеры
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Проверяем, что есть приложение, которое может обработать данный Intent
        if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE); // Запускаем камеру
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Обработка результата захвата изображения
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras(); // Получаем дополнительные данные
            Bitmap imageBitmap = (Bitmap) extras.get("data"); // Извлекаем изображение в формате Bitmap
            imageView.setImageBitmap(imageBitmap); // Устанавливаем захваченное изображение в ImageView
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // Обработка результата запроса разрешений
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Разрешение предоставлено, открываем камеру
                openCamera();
            } else {
                // Разрешение не предоставлено, выводим сообщение пользователю
                Toast.makeText(requireContext(), "Разрешение на использование камеры отклонено", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
