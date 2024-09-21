package ru.mirea.vasilenkoya.mireaproject;

import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    // Объявление EditText для ввода данных пользователя
    private EditText etName, etSurname, etPatronymic, etGroup, etAge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Создание и возврат корневого представления фрагмента
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Инициализация EditText из макета
        etName = view.findViewById(R.id.et_name);
        etSurname = view.findViewById(R.id.et_surname);
        etPatronymic = view.findViewById(R.id.et_patronymic);
        etGroup = view.findViewById(R.id.et_group);
        etAge = view.findViewById(R.id.et_age);

        // Инициализация кнопки "Сохранить"
        Button btnSave = view.findViewById(R.id.btn_save);

        // Установка слушателя для кнопки сохранения
        btnSave.setOnClickListener(v -> saveProfile());

        return view; // Возврат корневого представления
    }

    // Метод для сохранения профиля в SharedPreferences
    private void saveProfile() {
        // Получение экземпляра SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Сохранение введенных данных
        editor.putString("name", etName.getText().toString());
        editor.putString("surname", etSurname.getText().toString());
        editor.putString("patronymic", etPatronymic.getText().toString());
        editor.putString("group", etGroup.getText().toString());
        editor.putString("age", etAge.getText().toString());
        editor.apply(); // Применение изменений

        // Уведомление пользователя о сохранении
        Toast.makeText(getContext(), "Profile saved", Toast.LENGTH_SHORT).show();
    }
}
