package com.example.izual.studentftk;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Антон on 23.12.2014.
 */


public class FragmentPlacePage extends Fragment {

    String[] Places = { "Миша Бетаев Вчера в 18:00", "Владимир Ицыксон Вчера в 13:00", "Марат Ахин 01.12 в 10:00", "Вы здесь были 01.09 в 11:00"};
    ImageView m_Photo;
    ListView PlacesList;
    View viewPlacePage;
    TextView PlaceName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewPlacePage = inflater.inflate(R.layout.fragment_place_page, container, false);

        PlacesList = (ListView) viewPlacePage.findViewById(R.id.list_place);


        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                Places.length);
        Map<String, Object> tmp;
        for (int i = 0; i < Places.length; i++) {
            tmp = new HashMap<String, Object>();
            tmp.put("Text", Places[i]);
            // tmp.put("Image", R.drawable.places);
            data.add(tmp);
        }

        String[] from = {/* "Image",*/"Text" };
        int[] to = { /*R.id.imgPlaces,*/ R.id.TxtPlaces };
        SimpleAdapter adapter = new SimpleAdapter(
                getActivity(),//getActionBar().getThemedContext(),
                data,
                R.layout.places_layout,from,to
        );
        PlacesList.setAdapter(adapter);
        m_Photo = (ImageView)viewPlacePage.findViewById(R.id.photoPlace);
        m_Photo.setImageResource(R.drawable.photo_place);
        return viewPlacePage;
    }
}
