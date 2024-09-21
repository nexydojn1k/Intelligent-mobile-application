package ru.mirea.vasilenkoya.data_thread;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.TimeUnit;

import ru.mirea.vasilenkoya.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Runnable для выполнения задачи с задержкой 2 секунды
        final Runnable runn1 = new Runnable() {
            public void run() {
                binding.TextView.setText("После 2 сек задержки запускается процесс runn1");
                binding.TextView.append("\nrun_1");
            }
        };

        // Runnable для выполнения задачи с задержкой 1 секунду
        final Runnable runn2 = new Runnable() {
            public void run() {
                binding.TextView.append("\nПосле 1 сек задержки запускается процесс runn2");
                binding.TextView.append("\nrun_2");
            }
        };

        // Runnable для выполнения задачи с задержкой 2 секунды после запуска runn2
        final Runnable runn3 = new Runnable() {
            public void run() {
                binding.TextView.append("\nТак как у процесса runn3 используется метод postDelayed" +
                        " с задержкой в 2000 мс, то он запускается через 2 секунды после запуска" +
                        " процесса run_2. хотя в коде и вызван раньше");
                binding.TextView.append("\nrun_3");
            }
        };

        // Создание нового потока
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2); // Задержка в 2 секунды
                    runOnUiThread(runn1); // Выполнение runn1 на основном потоке
                    TimeUnit.SECONDS.sleep(1); // Задержка в 1 секунду
                    binding.TextView.postDelayed(runn3, 2000); // Запуск runn3 с задержкой в 2 секунды
                    binding.TextView.post(runn2); // Выполнение runn2 на основном потоке
                } catch (InterruptedException e) {
                    e.printStackTrace(); // Обработка прерывания потока
                }
            }
        });
        t.start(); // Запуск потока
    }
}
