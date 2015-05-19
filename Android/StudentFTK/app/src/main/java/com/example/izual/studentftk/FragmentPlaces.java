package com.example.izual.studentftk;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.izual.studentftk.FragmentManagement.FragmentPlacesCallbacks;

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

    private FragmentPlacesCallbacks mCallbacks;

    public interface OnSelectedListIdListener {
        void onListIdSelected(int ListIndex);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewPlaces = inflater.inflate(R.layout.fragment_places, container, false);
        // определяем список и присваиваем ему адаптер
        listPlaces = (ListView) viewPlaces.findViewById(R.id.list_places);
        listPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
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
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), data, R.layout.item, from, to);
        listPlaces.setAdapter(adapter);
        return viewPlaces;
    }

    private void selectItem(int position) {
        if (mCallbacks != null) {
            mCallbacks.onFragmentPlacesItemSelected(position);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (FragmentPlacesCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }
}
