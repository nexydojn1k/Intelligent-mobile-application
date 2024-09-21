package ru.mirea.vasilenkoya.looper;

import ru.mirea.vasilenkoya.looper.databinding.ActivityMainBinding;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Создание Handler для обработки сообщений в основном потоке
        Handler mainThreadHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg){
                Log.d(MainActivity.class.getSimpleName(), "Task execute. This is result: " +
                        msg.getData().getString("result"));
            }
        };

        // Создание и запуск пользовательского Looper
        MyLooper myLooper = new MyLooper(mainThreadHandler);
        myLooper.start();

        binding.TextView.setText("Мой номер по списку №3");
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создание нового сообщения для отправки в Looper
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                String age = binding.editTextAge.getText().toString(); // Получение возраста из EditText
                String occupation = binding.editTextOccupation.getText().toString(); // Получение профессии
                bundle.putString("AGE", age);
                bundle.putString("OCCUPATION", occupation);
                msg.setData(bundle); // Установка данных в сообщение
                myLooper.mHandler.sendMessage(msg); // Отправка сообщения в обработчик Looper
            }
        });
    }
}
