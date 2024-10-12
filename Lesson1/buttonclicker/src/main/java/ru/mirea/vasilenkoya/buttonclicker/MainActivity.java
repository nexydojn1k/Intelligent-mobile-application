package ru.mirea.vasilenkoya.buttonclicker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView tvOut;  // Изменил имя на существующий TextView
    private Button btnWhoAmI;
    private Button btnItIsNotMe;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация TextView и кнопок
        tvOut = findViewById(R.id.tvOut);  // Обновлено на существующий ID
        btnWhoAmI = findViewById(R.id.btnWhoAmI);
        btnItIsNotMe = findViewById(R.id.btnItIsNotMe);
        checkBox = findViewById(R.id.checkBox);

        // Листенер для кнопки btnWhoAmI
        View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOut.setText("Мой номер по списку № 3");
            }
        };

        btnWhoAmI.setOnClickListener(oclBtnWhoAmI);

        // Настройка окна Insets для tvOut
        ViewCompat.setOnApplyWindowInsetsListener(tvOut, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onMyButtonClick(View view) {
        tvOut.setText("Это не я сделал");
        checkBox.isChecked(); // Возможно, вы хотите добавить действие с чекбоксом
    }
}
