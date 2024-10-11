package ru.mirea.vasilenkoya.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyDateDialogFragment extends DialogFragment {
    private DatePickerDialog.OnDateSetListener listener; // Интерфейс для получения выбранной даты

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance(); // Получение текущей даты и времени
        int year = c.get(Calendar.YEAR); // Извлечение текущего года
        int month = c.get(Calendar.MONTH); // Извлечение текущего месяца (0-11)
        int day = c.get(Calendar.DAY_OF_MONTH); // Извлечение текущего дня месяца

        // Создание и возврат нового диалогового окна выбора даты
        return new DatePickerDialog(getActivity(), listener, year, month, day);
    }
}