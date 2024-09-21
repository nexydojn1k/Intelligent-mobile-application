package ru.mirea.vasilenkoya.mireaproject;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataFragment extends Fragment {

    // Константы для имен параметров
    private static final String ARG_PARAM1 = "param1"; // Параметр 1
    private static final String ARG_PARAM2 = "param2"; // Параметр 2

    // Переменные для хранения параметров
    private String mParam1; // Хранит значение параметра 1
    private String mParam2; // Хранит значение параметра 2

    public DataFragment() {
        // Обязательный пустой конструктор
    }

    /**
     * Используйте этот фабричный метод для создания нового экземпляра
     * данного фрагмента с помощью переданных параметров.
     *
     * @param param1 Параметр 1.
     * @param param2 Параметр 2.
     * @return Новый экземпляр фрагмента DataFragment.
     */
    public static DataFragment newInstance(String param1, String param2) {
        DataFragment fragment = new DataFragment(); // Создаем новый экземпляр фрагмента
        Bundle args = new Bundle(); // Создаем Bundle для хранения аргументов
        args.putString(ARG_PARAM1, param1); // Добавляем параметр 1 в Bundle
        args.putString(ARG_PARAM2, param2); // Добавляем параметр 2 в Bundle
        fragment.setArguments(args); // Устанавливаем аргументы для фрагмента
        return fragment; // Возвращаем созданный фрагмент
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Проверяем, есть ли аргументы, и извлекаем их
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1); // Получаем значение параметра 1
            mParam2 = getArguments().getString(ARG_PARAM2); // Получаем значение параметра 2
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Инфляция макета для данного фрагмента
        return inflater.inflate(R.layout.fragment_data, container, false);
    }
}
