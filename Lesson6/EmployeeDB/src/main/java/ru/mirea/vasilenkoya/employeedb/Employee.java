package ru.mirea.vasilenkoya.employeedb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Employee {
    @PrimaryKey(autoGenerate = true) // Автоинкремент для ID
    public long id; // Уникальный идентификатор сотрудника
    public String name; // Имя сотрудника
    public int salary; // Зарплата сотрудника
}
