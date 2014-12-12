package com.example.izual.studentftk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * Created by Антон on 12.12.2014.
 */

public class FragmentMessages extends Fragment {

    private ListView listMessages;

    // имена атрибутов для Map
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_TIME = "time";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        // return super.onCreateView(inflater, container, savedInstanceState);

        View viewMessages = inflater.inflate(R.layout.fragment_messages, container, false);

        // массивы данных
        String[] texts = { "Политехнический университет - многофункциональное государственное высшее учебное заведение.",
                " В 2010 году он получил статус национального исследовательского университета, что явилось признанием его роли и возможностей как в области подготовки кадров, так и в мультидисциплинарных научных исследованиях и разработках.",
                "В рейтинге технических университетов России Политехнический неизменно занимает ведущие позиции.",
                "Университет готовит бакалавров и магистров по 49 направлениям науки и техники,",
                "специалистов (инженеров, экономистов, менеджеров) по 9 специальностям" };
        String[] times = { "20:00", "20:11",  "20:12",  "20:13",  "20:14" };
        int img = R.drawable.ic_friends;

        // упаковываем данные в понятную для адаптера структуру
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                texts.length);
        Map<String, Object> m;
        for (int i = 0; i < texts.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, texts[i]);
            m.put(ATTRIBUTE_NAME_TIME, times[i]);
            m.put(ATTRIBUTE_NAME_IMAGE, img);
            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_TIME,
                ATTRIBUTE_NAME_IMAGE };
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.tvText, R.id.tvText1, R.id.ivImg };

        // создаем адаптер
        SimpleAdapter sAdapter = new SimpleAdapter( getActivity(),//getActionBar().getThemedContext(),
         data, R.layout.item_message,from, to);

        // определяем список и присваиваем ему адаптер
        listMessages = (ListView) viewMessages.findViewById(R.id.listMessages);
        listMessages.setAdapter(sAdapter);
        return viewMessages;
    }
}
