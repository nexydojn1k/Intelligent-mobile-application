package ru.mirea.vasilenkoya.employeedb;

import android.app.Application;
import androidx.room.Room;

public class App extends Application {
    public static App instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries() // Разрешение выполнения запросов в основном потоке
                .build();
    }

    public static App getInstance() {
        return instance; // Получение экземпляра приложения
    }

    public AppDatabase getDatabase() {
        return database; // Метод для доступа к базе данных
    }
}
