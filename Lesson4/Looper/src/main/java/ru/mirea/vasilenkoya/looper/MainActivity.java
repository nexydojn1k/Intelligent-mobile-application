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

        Handler mainThreadHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg){
                Log.d(MainActivity.class.getSimpleName(), "Task execute. This is result: " +
                        msg.getData().getString("result"));
            }
        };
        MyLooper myLooper = new MyLooper(mainThreadHandler);
        myLooper.start();

        binding.TextView.setText("Мой номер по списку №3");
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                String age = binding.editTextAge.getText().toString();
                String occupation = binding.editTextOccupation.getText().toString();
                bundle.putString("AGE", age);
                bundle.putString("OCCUPATION", occupation);
                msg.setData(bundle);
                myLooper.mHandler.sendMessage(msg);
            }
        });
    }
}