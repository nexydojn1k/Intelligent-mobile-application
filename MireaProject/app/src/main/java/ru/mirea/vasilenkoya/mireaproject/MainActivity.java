package ru.mirea.vasilenkoya.mireaproject;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import ru.mirea.vasilenkoya.mireaproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration; // Конфигурация для управления AppBar
    private ActivityMainBinding binding; // Binding для работы с макетом

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Инициализация View Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // Устанавливаем корневой элемент макета

        // Установка Toolbar как ActionBar
        setSupportActionBar(binding.appBarMain.toolbar);

        // Установка действия для FloatingActionButton (FAB)
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Отображение Snackbar с сообщением
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null) // Опциональное действие
                        .setAnchorView(R.id.fab).show(); // Привязка к FAB
            }
        });

        // Настройка DrawerLayout и NavigationView для навигации
        DrawerLayout drawer = binding.drawerLayout; // Получаем DrawerLayout
        NavigationView navigationView = binding.navView; // Получаем NavigationView

        // Конфигурация AppBar для управления Navigation
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_brouser, R.id.nav_sensor, R.id.nav_profile) // Добавляем nav_profile
                .setOpenableLayout(drawer) // Указываем DrawerLayout
                .build();

        // Настройка NavController для управления навигацией между фрагментами
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration); // Связываем ActionBar с NavController
        NavigationUI.setupWithNavController(navigationView, navController); // Связываем NavigationView с NavController
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Раздуваем меню; добавляем элементы в панель действий
        getMenuInflater().inflate(R.menu.main, menu); // Загружаем меню из ресурсов
        return true; // Возвращаем true для отображения меню
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Поддержка навигации вверх (Up Button) с NavController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) // Поднимаемся по навигации
                || super.onSupportNavigateUp(); // Вызов родительского метода
    }
}
