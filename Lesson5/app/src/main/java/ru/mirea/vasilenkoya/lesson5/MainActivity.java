package ru.mirea.vasilenkoya.lesson5;

// Импортируем необходимые библиотеки и классы
import ru.mirea.vasilenkoya.lesson5.databinding.ActivityMainBinding;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; // Связывание для доступа к элементам интерфейса

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); // Инициализация привязки
        setContentView(binding.getRoot()); // Устанавливаем корневой макет

        // Получаем доступ к SensorManager для работы с сенсорами
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Получаем список всех доступных сенсоров
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        ListView listSensor = binding.listView; // Получаем ListView из привязки

        // Создаем список для отображения найденных сенсоров
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();

        // Заполняем список данными о каждом сенсоре
        for (int i = 0; i < sensors.size(); i++) {
            HashMap<String, Object> sensorTypeList = new HashMap<>();
            sensorTypeList.put("Name", sensors.get(i).getName()); // Имя сенсора
            sensorTypeList.put("Value", sensors.get(i).getMaximumRange()); // Максимальный диапазон
            arrayList.add(sensorTypeList); // Добавляем в список
        }

        // Создаем адаптер для отображения списка сенсоров в ListView
        SimpleAdapter mHistory = new SimpleAdapter(
                this,
                arrayList,
                android.R.layout.simple_list_item_2, // Макет для каждого элемента списка
                new String[]{"Name", "Value"}, // Ключи для отображаемых данных
                new int[]{android.R.id.text1, android.R.id.text2} // ID текстовых полей в макете
        );

        // Устанавливаем адаптер для ListView
        listSensor.setAdapter(mHistory);
    }
}
