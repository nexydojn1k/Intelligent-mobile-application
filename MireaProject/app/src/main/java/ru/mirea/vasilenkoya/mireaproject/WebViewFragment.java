package ru.mirea.vasilenkoya.mireaproject;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WebViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebViewFragment extends Fragment {

    // Аргументы для инициализации фрагмента
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1; // Параметр 1
    private String mParam2; // Параметр 2

    public WebViewFragment() {
        // Required empty public constructor
    }

    /**
     * Используйте этот фабричный метод для создания нового экземпляра
     * этого фрагмента, используя предоставленные параметры.
     *
     * @param param1 Параметр 1.
     * @param param2 Параметр 2.
     * @return Новый экземпляр фрагмента WebViewFragment.
     */
    public static WebViewFragment newInstance(String param1, String param2) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Подключаем макет фрагмента
        View root = inflater.inflate(R.layout.fragment_web_view, container, false);

        WebView webView = root.findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient()); // Чтобы страницы открывались внутри WebView
        webView.loadUrl("https://www.google.com"); // Страница по умолчанию

        return root;
    }
}
