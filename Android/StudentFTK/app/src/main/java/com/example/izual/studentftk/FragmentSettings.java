package com.example.izual.studentftk;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Антон on 12.12.2014.
 */


public class FragmentSettings extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewSettings = inflater.inflate(R.layout.fragment_settings, container, false);
        //int mo = getArguments().getInt("2");
       // Toast.makeText(getActivity(), "choytka " + mo, Toast.LENGTH_SHORT).show();
        return viewSettings;
    }


}
