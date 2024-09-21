package ru.mirea.vasilenkoya.lesson3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge; // Импортирование класса для поддержки edge-to-edge интерфейсов
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets; // Импортирование класса для работы сInsets
import androidx.core.view.ViewCompat; // Импортирование класса для поддержки view
import androidx.core.view.WindowInsetsCompat; // Импортирование класса для работы с WindowInsets

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Включение режима edge-to-edge для приложения
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main); // Установка содержимого активности из XML

        // Установка слушателя для применения отступов окна
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            // Получение отступов для системных панелей (например, статус-бара и навигационной панели)
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // Установка отступов для вьюхи, чтобы избежать перекрытия контента
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets; // Возврат инсет-объекта
        });
    }
}

//  Режим edge-to-edge в Android — это режим, в котором интерфейс приложения использует всю площадь экрана,
//      включая области за системными элементами (например, статус-баром и навигационной панелью).
//      Этот режим позволяет приложениям создавать более визуально привлекательные и
//      современные интерфейсы, особенно на устройствах с тонкими рамками и вырезами.