package com.example.izual.studentftk;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Антон on 11.12.2014.
 */
public class FragmentProfile extends Fragment {

    String[] Places = { "9-ый корпус вчера в 18:00", "Главное здание вчера в 13:00", "9-ый корпус 01.10 в 10:00", "9-ый корпус 01.09 в 12:00"};
    ImageView m_Photo;
    ListView PlacesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewProfile = inflater.inflate(R.layout.fragment_profile, container, false);

        ListView PlacesList = (ListView) viewProfile.findViewById(R.id.list_place);
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
        m_Photo = (ImageView)viewProfile.findViewById(R.id.photoJen);
        m_Photo.setImageResource(R.drawable.photo);
        return viewProfile;
    }
}
