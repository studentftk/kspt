package com.example.izual.studentftk.FragmentManagement;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.izual.studentftk.R;

/**
 * Created by oglandx on 18.05.2015.
 */

public class PlaceholderFragment extends Fragment {

    public PlaceholderFragment() {}

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.navigation, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((TopLevelFragmentManager) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}