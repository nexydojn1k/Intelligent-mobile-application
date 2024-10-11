package ru.mirea.vasilenkoya.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;

import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Создание объекта AlertDialog.Builder для построения диалогового окна
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Установка заголовка, сообщения и иконки для диалогового окна
        builder.setTitle("Здравствуй МИРЭА!")                               // Заголовок диалога
                .setMessage("Успех близок?")                                // Сообщение, отображаемое в диалоговом окне
                .setIcon(R.mipmap.ic_launcher_round)                        // Установка иконки для диалога
                .setPositiveButton("Иду дальше", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Закрываем окно (действие при нажатии кнопки "Иду дальше")
                    }
                })
                .setNeutralButton("На паузе", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Действие при нажатии кнопки "На паузе"
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Действие при нажатии кнопки "Нет"
                    }
                });

        // Создание и возврат объекта диалога
        return builder.create();
    }
}
