package com.example.izual.studentftk;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Антон on 12.12.2014.
 */
public class FragmentAbout extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewAbout = inflater.inflate(R.layout.fragment_about, container, false);
        return viewAbout;
    }
}
