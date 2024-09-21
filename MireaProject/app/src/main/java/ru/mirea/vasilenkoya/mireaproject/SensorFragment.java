package ru.mirea.vasilenkoya.mireaproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ru.mirea.vasilenkoya.mireaproject.R;

public class SensorFragment extends Fragment implements SensorEventListener {

    private SensorManager sensorManager; // Менеджер для работы с датчиками
    private Sensor accelerometer; // Датчик ускорения
    private Sensor magnetometer; // Датчик магнитного поля
    private TextView directionTextView; // TextView для отображения направления

    private float[] gravity; // Значения ускорения
    private float[] geomagnetic; // Значения магнитного поля

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Подключаем макет фрагмента
        View view = inflater.inflate(R.layout.fragment_sensor, container, false);
        directionTextView = view.findViewById(R.id.directionTextView); // Связываем TextView с кодом
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Инициализация менеджера датчиков
        sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);

        // Подключаем акселерометр и магнитометр
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Регистрируем слушателей для датчиков при возобновлении активности
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Отключаем слушателей при приостановке активности для сохранения энергии
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Обработка изменений датчиков
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            gravity = event.values; // Получаем значения ускорения
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            geomagnetic = event.values; // Получаем значения магнитного поля
        }

        // Если есть значения от обоих датчиков
        if (gravity != null && geomagnetic != null) {
            float[] R = new float[9]; // Матрица вращения
            float[] I = new float[9]; // Матрица инерции
            boolean success = SensorManager.getRotationMatrix(R, I, gravity, geomagnetic); // Получаем матрицу вращения
            if (success) {
                float[] orientation = new float[3]; // Массив для хранения ориентации
                SensorManager.getOrientation(R, orientation); // Получаем ориентацию
                float azimuth = (float) Math.toDegrees(orientation[0]); // Азимут, угол на север
                directionTextView.setText("Направление на север: " + Math.round(azimuth) + "°"); // Обновляем текст
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Можно реализовать обработку изменения точности датчика, если необходимо
    }
}
