package com.example.izual.studentftk;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Антон on 12.12.2014.
 */
public class FragmentPlaces extends Fragment {

    // имена атрибутов для Map
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    private ListView listPlaces;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewPlaces = inflater.inflate(R.layout.fragment_places, container, false);

        // массивы данных
        String[] items = getResources().getStringArray(R.array.items_piaces);
        int [] img = {R.drawable.ic_places_main_body, R.drawable.ic_places_body,
                R.drawable.ic_places_dining_room, R.drawable.ic_places_cafe, R.drawable.ic_places_beauty,
                R.drawable.ic_places_other};
        // упаковываем данные в понятную для адаптера структуру
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                items.length);
        Map<String, Object> m;
        for (int i = 0; i < items.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, items[i]);
            m.put(ATTRIBUTE_NAME_IMAGE, img[i]);
            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT,
                ATTRIBUTE_NAME_IMAGE };
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.tvText, R.id.ivImg };

        // создаем адаптер

        SimpleAdapter adapter = new SimpleAdapter(
                getActivity() ,//getActionBar().getThemedContext(),
                data,
                R.layout.item,from,to
        );
        // определяем список и присваиваем ему адаптер
        listPlaces = (ListView) viewPlaces.findViewById(R.id.list_places);
        listPlaces.setAdapter(adapter);
        return viewPlaces;
    }
}
