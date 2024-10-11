package ru.mirea.vasilenkoya.dialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class MyTimeDialogFragment extends DialogFragment {

    // Интерфейс для обработки выбранного времени
    private TimePickerDialog.OnTimeSetListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Получаем текущее время
        Calendar c = Calendar.getInstance();
        int cHour = c.get(Calendar.HOUR_OF_DAY); // Час текущего времени
        int cMinute = c.get(Calendar.MINUTE); // Минуты текущего времени

        // Создание и возврат объекта TimePickerDialog с текущими часами и минутами
        return new TimePickerDialog(getActivity(), listener, cHour, cMinute,
                android.text.format.DateFormat.is24HourFormat(getActivity())); // Формат 24 часа
    }
}

