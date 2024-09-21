package ru.mirea.vasilenkoya.employeedb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmployeeDao {
    // Получить всех сотрудников
    @Query("SELECT * FROM employee")
    List<Employee> getAll();

    // Получить сотрудника по ID
    @Query("SELECT * FROM employee WHERE id = :id")
    Employee getById(long id);

    // Вставить нового сотрудника
    @Insert
    void insert(Employee employee);

    // Обновить информацию о сотруднике
    @Update
    void update(Employee employee);

    // Удалить сотрудника
    @Delete
    void delete(Employee employee);
}
