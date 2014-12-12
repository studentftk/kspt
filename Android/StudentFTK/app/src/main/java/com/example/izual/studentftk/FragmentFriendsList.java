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
public class FragmentFriendsList extends Fragment {
    // имена атрибутов для Map
    final String ATTRIBUTE_NAME = "name";
    final String ATTRIBUTE_IMAGE = "image";

    private ListView listFriends;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewFriendsList = inflater.inflate(R.layout.fragment_friends_list, container, false);

        // массивы данных
        String[] items = getResources().getStringArray(R.array.items_fragment);
        int img = R.drawable.ic_friends;
        // упаковываем данные в понятную для адаптера структуру
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                items.length);
        Map<String, Object> m;
        for (int i = 0; i < items.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME, items[i]);
            m.put(ATTRIBUTE_IMAGE, img);
            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME,
                ATTRIBUTE_IMAGE };
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.tvText, R.id.ivImg };

        // создаем адаптер

        SimpleAdapter adapter = new SimpleAdapter(
                getActivity() ,//getActionBar().getThemedContext(),
                data,
                R.layout.item,from,to
        );
        // определяем список и присваиваем ему адаптер
        listFriends = (ListView) viewFriendsList.findViewById(R.id.list_friends);
        listFriends.setAdapter(adapter);
        return viewFriendsList;
    }

}