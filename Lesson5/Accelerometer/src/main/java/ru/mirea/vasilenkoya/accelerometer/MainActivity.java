package ru.mirea.vasilenkoya.accelerometer;

// Импортируем необходимые библиотеки и классы
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // Объявляем элементы интерфейса
    private TextView azimuthTextView; // Для отображения значения азимута
    private TextView pitchTextView;    // Для отображения значения угла наклона
    private TextView rollTextView;     // Для отображения значения крена
    private SensorManager sensorManager; // Менеджер для работы с сенсорами
    private Sensor accelerometerSensor; // Акселерометр

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Устанавливаем макет активности

        // Инициализируем SensorManager и получаем акселерометр
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Инициализируем TextView для отображения значений
        azimuthTextView = findViewById(R.id.textViewAzimuth);
        pitchTextView = findViewById(R.id.textViewPitch);
        rollTextView = findViewById(R.id.textViewRoll);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Регистрация слушателя сенсоров при возобновлении активности
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Отмена регистрации слушателя сенсоров при паузе активности
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Проверяем тип сенсора, который вызвал событие
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // Получаем значения акселерометра
            float valueAzimuth = event.values[0]; // Азимут
            float valuePitch = event.values[1];    // Угол наклона
            float valueRoll = event.values[2];     // Крен

            // Обновляем текстовые поля значениями акселерометра
            azimuthTextView.setText("Azimuth: " + valueAzimuth);
            pitchTextView.setText("Pitch: " + valuePitch);
            rollTextView.setText("Roll: " + valueRoll);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Метод не реализован, можно использовать для обработки изменений точности сенсора
    }
}
