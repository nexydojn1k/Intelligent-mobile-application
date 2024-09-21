package ru.mirea.vasilenkoya.employeedb;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = App.getInstance().getDatabase(); // Получаем экземпляр базы данных
        EmployeeDao employeeDao = db.employeeDao(); // Получаем доступ к DAO

        // Создаем нового сотрудника
        Employee employee = new Employee();
        employee.id = 1;
        employee.name = "Vladimir Putin";
        employee.salary = 10000;

        // Записываем сотрудника в базу
        employeeDao.insert(employee);

        // Загружаем всех сотрудников
        List<Employee> employees = employeeDao.getAll();
        Log.d(TAG, "All Employees: " + employees.toString()); // Логируем всех сотрудников

        // Получаем определенного работника с id = 1
        employee = employeeDao.getById(1);

        // Обновляем поля объекта
        employee.salary = 20000;
        employeeDao.update(employee); // Сохраняем изменения в базе

        // Логируем информацию о сотруднике
        Log.d(TAG, employee.name + " " + employee.salary);
    }
}
