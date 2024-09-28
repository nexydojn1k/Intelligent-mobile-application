package ru.mirea.vasilenkoya.lesson4;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import ru.mirea.vasilenkoya.lesson4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;                // Переменная для привязки

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Инициализация привязки
        binding = ActivityMainBinding.inflate(getLayoutInflater()); //метод inflate заявляет, что активируется binding
        setContentView(binding.getRoot());                          // возвращение корневого представления

        // Установка текста в TextView
        binding.textViewMirea.setText("Мой номер по списку №3");

        // Установка обработчика нажатий для кнопки
        binding.buttonMirea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Логирование при нажатии на кнопку
                Log.d(MainActivity.class.getSimpleName(), "OnClickListener");
            }
        });
    }
}
