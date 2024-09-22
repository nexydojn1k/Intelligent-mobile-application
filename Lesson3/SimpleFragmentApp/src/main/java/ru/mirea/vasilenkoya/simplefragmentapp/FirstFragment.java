package ru.mirea.vasilenkoya.simplefragmentapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment; // Импорт для работы с фрагментами

import android.view.LayoutInflater; // Импорт для инфлейта вью
import android.view.View; // Импорт для работы с вью
import android.view.ViewGroup; // Импорт для группировки вью

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    // Определение аргументов для инициализации фрагмента
    private static final String ARG_PARAM1 = "param1"; // Аргумент 1
    private static final String ARG_PARAM2 = "param2"; // Аргумент 2

    // Параметры для фрагмента
    private String mParam1; // Хранит значение аргумента 1
    private String mParam2; // Хранит значение аргумента 2

    // Конструктор фрагмента (пустой)
    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Используйте этот фабричный метод для создания нового экземпляра
     * этого фрагмента с заданными параметрами.
     *
     * @param param1 Параметр 1.
     * @param param2 Параметр 2.
     * @return Новый экземпляр фрагмента FirstFragment.
     */
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();               // Создание нового экземпляра фрагмента
        Bundle args = new Bundle();                                 // Создание Bundle для передачи параметров
        args.putString(ARG_PARAM1, param1);                         // Добавление параметра 1
        args.putString(ARG_PARAM2, param2);                         // Добавление параметра 2
        fragment.setArguments(args);                                // Установка аргументов фрагмента
        return fragment;                                            // Возврат созданного фрагмента
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                     // Вызов метода родительского класса
        if (getArguments() != null) {                           // Проверка, были ли установлены аргументы
            mParam1 = getArguments().getString(ARG_PARAM1);     // Получение значения аргумента 1
            mParam2 = getArguments().getString(ARG_PARAM2);     // Получение значения аргумента 2
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }
}
